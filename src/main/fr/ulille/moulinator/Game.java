package fr.ulille.moulinator;

import fr.ulille.moulinator.enums.Color;
import fr.ulille.moulinator.enums.GameType;
import fr.ulille.moulinator.gamemode.BotVSBot;
import fr.ulille.moulinator.gamemode.GameMode;
import fr.ulille.moulinator.gamemode.OneVSBot;
import fr.ulille.moulinator.gamemode.OneVSOne;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

/**
 * <p>La classe qui initialise le jeu</p>
 * @author HOCINE CHEBOUT
 * @author VALENTIN THUILLER
 * @author LEIBOVICI EZECHIEL
 * @author BARBEAU SIMON
 * @author TOUMJI ABDALLAH
 * @author BERRAKANE ADHAM
 */
public final class Game implements Serializable {
    
    /**
     * Joueur p1, p2 : les joueurs de la partie
     */
    public static Joueur p1, p2;
    public static Board Board = new Board();
    public static GameType gameType;
    private GameType gameType_save;
    public static boolean isPlayer1Turn = true;
    private Joueur p1_save, p2_save;
    private Board Board_save;
    private boolean isPlayer1Turn_save;
    @Serial
    private static final long serialVersionUID = 1L;
    public static final Scanner SCANNER = new Scanner(System.in);
    public static int maxBilles = 9;
    public static int minBilles = 2;
    public static boolean debugMod = false;
    private boolean debugMod_save;
    private int maxBilles_save, minBilles_save;
    public static int DELAY_BOT = 1000;
    private int delayBot_save;


    /**
     * Affiche les erreurs en rouge
     * @param s (String)    - Message to show
     * @see Color
     * @see String
     */
    public static void logger(String s) {
        System.out.println(Color.ANSI_RED + s + Color.ANSI_RESET);
    }

    public static void info(String s) {
        System.out.println(Color.ANSI_BLUE + "[MOULINFO]: " + s + Color.ANSI_RESET);
    }

    /**
     * @return boolean : true si la partie est sauvegardée / false sinon + exception
     * @see IOException
     * @see FileOutputStream
     * @see ObjectOutputStream
     * @see LocalDate
     * @see DateTimeFormatter
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
            this.delayBot_save = DELAY_BOT;
            out.writeObject(this);
            out.close();
            fileOut.close();
            return true;
        } catch(IOException i) {
            i.printStackTrace();
            return false;
        }
    }

    public static Game loadGame() {
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
            Game.DELAY_BOT = g.delayBot_save;
            in.close();
            fileIn.close();
            return g;
        } catch(IOException | ClassNotFoundException ignored) { }
        return null;
    }

    /**
     * Methode qui lance la partie
     */
    public static void startGame() {
        GameMode gameMode;
        switch(Game.gameType) {
            case PLAYER_VS_PLAYER -> gameMode = new OneVSOne();
            case PLAYER_VS_BOT -> gameMode = new OneVSBot();
            case BOT_VS_BOT -> gameMode = new BotVSBot();
            default -> {
                Game.logger("Error while starting game !");
                return;
            }
        }
        gameMode.run(Game.p1 != null && Game.p2 != null);
    }

    public static void clearScreen() {
        try {
            Process process;
            if(!System.getProperty("os.name").equals("Windows")) process = new ProcessBuilder("clear").inheritIO().start();
            else process = new ProcessBuilder("cmd", "/c", "cls").inheritIO().start();
            process.waitFor();
        } catch (IOException | InterruptedException ignored) {}
    }

    @Override
    public String toString() {
        return "Disposition de la partie !\n" +
                "Joueur 1 : " + p1 + "\n" +
                "Joueur 2 : " + p2 + "\n" +
                "Plateau : " + Board.toString();
    }

    public static Color rdmColor() {
        return Color.values()[new Random().nextInt(1, Color.values().length)];
    }

}
