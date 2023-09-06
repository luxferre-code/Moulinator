package fr.ulille.moulinator;

import java.util.Scanner;
import java.io.*;

public class Menu {

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

        System.out.println("~ Welcome to the mill game ~" +
                           "\n1. Player VS Player" +
                           "\n2. Player VS Bot" +
                           "\n3. Bot VS Bot" +
                           "\n4. Game rules" +
                           "\n5. Load save" +
                           "\n6. Settings" +
                           "\n7. Quit");

        int choice = choose(1, 7);
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
                System.out.println("Press enter to continue");
                sc.nextLine();
                execute();
            }
            case 5 -> {
                Game.info("Load save, please wait...");
                Game g = Game.loadGame();
                System.out.println(g);
                if(g == null) {
                    Game.logger("No save found !");
                    execute();
                } else {
                    Game.info("Save loaded !");
                    Game.startGame();
                }
            }
            case 6 -> {
                OptionsMenu.execute();
                execute();
            }
            case 7 -> {
                System.out.println("Bye bye !");
                System.exit(0);
            }
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        execute();
    }

}

