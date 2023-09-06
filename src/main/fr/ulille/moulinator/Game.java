package fr.ulille.moulinator;

import fr.ulille.moulinator.gamemode.BotVSBot;
import fr.ulille.moulinator.gamemode.GameMode;
import fr.ulille.moulinator.gamemode.OneVSBot;
import fr.ulille.moulinator.gamemode.OneVSOne;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;


/**
 * <p>La classe qui initialise des joueur</p>
 * @author HOCINE CHEBOUT
 * @author VALENTIN THUILLER
 * @author LEIBOVICI EZECHIEL
 * @author BARBEAU SIMON
 * @author TOUMJI ABDALLAH
 * @author BERRAKANE ADHAM
 */

public final class Game implements Serializable {
    
    /**
     * Joueur p1,p2 : les deux joueurs de la partie
     */
    public static Joueur p1, p2;

    /**
     * Board Board : le plateau de la partie
     */
    public static Board Board = new Board();

    /**
     * GameType gameType : le type de la partie
     */
    public static GameType gameType;
    private GameType gameType_save;
    public static boolean isPlayer1Turn = true;

    /**
     * Joueur p1_save,p2_save : les deux sauvegarde de la partie
     */
    private Joueur p1_save, p2_save;

    /**
     * Board Board_save : la sauvegarde du plateau de la partie
     */
    private Board Board_save;

    /**
     * boolean isPlayer1Turn_save : la sauvegarde du tour du joueur 1
     */
    private boolean isPlayer1Turn_save;


    private static final long serialVersionUID = 1L;

    public static final Scanner SCANNER = new Scanner(System.in);
    public static void logger(String s) {
        System.out.println(Color.ANSI_RED + s + Color.ANSI_RESET);
    }

    public static void info(String s) {
        System.out.println(Color.ANSI_BLUE + "[MOULINFO]: " + s + Color.ANSI_RESET);
    }

    /**
     * Methode qui applique la sauvegarde de la partie
     * @return bollean : si la partie est sauvegardée
     */
    public boolean saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream("resources/save.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            this.p1_save = p1;
            this.p2_save = p2;
            this.Board_save = Board;
            this.isPlayer1Turn_save = isPlayer1Turn;
            this.gameType_save = gameType;
            this.maxBilles_save = maxBilles;
            this.minBilles_save = minBilles;
            this.debugMod_save = debugMod;
            out.writeObject(this);
            out.close();
            fileOut.close();
            return true;
        } catch(IOException i) {
            i.printStackTrace();
            return false;
        }
    }

    public static Game loadGame(String nameFile) {
        try {
            FileInputStream fileIn = new FileInputStream("resources/save.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Game g = (Game) in.readObject();
            Game.p1 = g.p1_save;
            Game.p2 = g.p2_save;
            Game.Board = g.Board_save;
            Game.isPlayer1Turn = g.isPlayer1Turn_save;
            Game.gameType = g.gameType_save;
            Game.maxBilles = g.maxBilles_save;
            Game.minBilles = g.minBilles_save;
            Game.debugMod = g.debugMod_save;
            in.close();
            fileIn.close();
            return g;
        } catch(IOException | ClassNotFoundException ignored) { }
        return null;
    }

    public static void startGame() {
        GameMode gameMode;
        switch(Game.gameType) {
            case PLAYER_VS_PLAYER -> { gameMode = new OneVSOne(); }
            case PLAYER_VS_BOT -> { gameMode = new OneVSBot(); }
            case BOT_VS_BOT -> { gameMode = new BotVSBot(); }
            default -> {
                Game.logger("Error while starting game !");
                return;
            }
        }
        gameMode.run(Game.p1 != null && Game.p2 != null);
    }

    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            }
            else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception ignored) { }
    }

    @Override
    public String toString() {
        return "Disposition de la partie !\n" +
                "Joueur 1 : " + p1 + "\n" +
                "Joueur 2 : " + p2 + "\n" +
                "Plateau : " + Board.toString();
    }

}
