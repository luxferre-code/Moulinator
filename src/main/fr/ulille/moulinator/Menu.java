package fr.ulille.moulinator;

import java.util.Scanner;
import java.io.*;

public class Menu {
    /*public void choose() throws IOException{
        File f=new File("Logo.txt");
        FileReader fr= new FileReader(f);
        BufferedReader br = new BufferedReader(fr);  
        StringBuffer sb = new StringBuffer();    
        String line;
        while((line = br.readLine()) != null){
            sb.append(line);      
            sb.append("\n");     
        }
        fr.close();
        System.out.println(sb);
        Scanner sc = new Scanner(Game.INPUT_STREAM);
        String str="0";
        while(!str.equals("1") && !str.equals("2") && !str.equals("3") && !str.equals("4"  )  && !str.equals("5")){
            System.out.println("Choose a Gamemode: \n1. Play with a player \n2. Play with a bot \n3. Bot vs Bot \n4. Regles du jeu \n5. Exit");
            str = sc.next();
        }
        if(str.equals("1")){
            System.out.println("Lance un 1V1");
        }
            if(str.equals("2")){
                str="0";
                while(!str.equals("1") && !str.equals("2")){
                    System.out.println("Choose the bot: \n1. Easy bot \n2. Hard bot");
                    str = sc.next(); 
                }
                if(str.equals("1")){
                    System.out.println("Bot simple");
                }
                else{
                    System.out.println("Bot hard");
                }
            }
            if (str.equals("3")) {
                while(!str.equals("1") && !str.equals("2")){
                    System.out.println("Choose the bots: \n1. Easy bots \n2. Hard bots");
                    str = sc.next(); 
                }
                if(str.equals("1")){
                    System.out.println("Bots simple");
                }
                else{
                    System.out.println("Bots3 hard");
                    }
            } 
            if(str.equals("4")){
                System.out.println("Régles du jeu");
                File f2= new File("regles.txt");
                FileReader fr2= new FileReader(f2);
                BufferedReader br2 = new BufferedReader(fr2);     
                String line2;
                while((line2 = br2.readLine()) != null){
                    System.out.println(line2);
                }
                fr2.close();
                try(Scanner sc2 = new Scanner(Game.INPUT_STREAM)) {
                    System.out.println("Appuyez sur entrée pour continuer");
                    sc2.nextLine();
                }
            }
            
            if(str.equals("5")){
                    System.out.println("Au revoir");
                    System.exit(0);
            }
        choose();
    }*/

    private static int choose(int min, int max) throws InterruptedException {
        Thread.sleep(500);
        Scanner sc = Game.SCANNER;
        int choice = 0;
        while(choice < min || choice > max) {
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch(NumberFormatException ignored) {}
        }
        return choice;
    }

    public static void execute() throws InterruptedException {
        try(BufferedReader br = new BufferedReader(new FileReader(new File("resources/Logo.txt")))) {
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch(IOException ignored) {
            Game.logger("logo not found !");
        }

        System.out.println("~ Bienvenue dans le jeu du moulin ~" +
                           "\n1. Jouer avec un joueur" +
                           "\n2. Jouer contre un bot" +
                           "\n3. Bot vs Bot" +
                           "\n4. Règles du jeu" +
                           "\n5. Quitter");

        int choice = choose(1, 5);
        switch(choice) {
            case 1 -> {
                Game.gameType = GameType.PLAYER_VS_PLAYER;
                Game.startGame();
            }
            case 2 -> {
                Game.gameType = GameType.PLAYER_VS_BOT;
                Game.startGame();
            }
            case 3 -> {
                Game.gameType = GameType.BOT_VS_BOT;
                Game.startGame();
            }
            case 4 -> {
                try(BufferedReader br = new BufferedReader(new FileReader(new File("resources/regles.txt")))) {
                    String line;
                    while((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch(IOException ignored) {
                    Game.logger("regles.txt not found !");
                }
                Scanner sc = Game.SCANNER;
                System.out.println("Appuyez sur entrée pour continuer");
                sc.nextLine();
                execute();
            }
            case 5 -> {
                System.out.println("Au revoir");
                System.exit(0);
            }
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

}

