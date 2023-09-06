package fr.ulille.moulinator.panels;

import fr.ulille.moulinator.Game;
import fr.ulille.moulinator.enums.GameType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * <p>La classe qui initialise le menu</p>
 * @author HOCINE CHEBOUT
 * @author VALENTIN THUILLER
 * @author LEIBOVICI EZECHIEL
 * @author BARBEAU SIMON
 * @author TOUMJI ABDALLAH
 * @author BERRAKANE ADHAM
 */

public class Menu {

    private static int choose() throws InterruptedException {
        Thread.sleep(500);
        Scanner sc = Game.SCANNER;
        int choice = 0;
        while(choice < 1 || choice > 7) {
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch(NumberFormatException ignored) {}
        }
        return choice;
    }

    /**
     * Menu du jeu
     */
    public static void execute() throws InterruptedException {
        Game.clearScreen();
        try(BufferedReader br = new BufferedReader(new FileReader(new File("resources/Logo.txt")))) {
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch(IOException ignored) {
            Game.logger("logo not found !");
        }

        System.out.println("""
                ~ Welcome to the Nine Men's Morris ~
                1. Player1 VS Player2
                2. Player1 VS Bot1
                3. Bot1 VS Bot2
                4. Game rules
                5. Load save
                6. Settings
                7. Quit""");

        int choice = choose();
        Game.clearScreen();
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
}

