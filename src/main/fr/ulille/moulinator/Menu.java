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

        System.out.println("~ Bienvenue dans le jeu du moulin ~" +
                           "\n1. Jouer avec un joueur" +
                           "\n2. Jouer contre un bot" +
                           "\n3. Bot vs Bot" +
                           "\n4. Règles du jeu" +
                           "\n5. Charger une partie" +
                           "\n6. Quitter");

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
                Game.info("Chargement de la partie...");
                Game g = Game.loadGame();
                System.out.println(g);
                if(g == null) {
                    Game.logger("No save found !");
                    execute();
                } else {
                    Game.info("Partie chargée avec succès !");
                    Game.startGame();
                }
            }
            case 6 -> {
                System.out.println("Au revoir");
                System.exit(0);
            }
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

}

