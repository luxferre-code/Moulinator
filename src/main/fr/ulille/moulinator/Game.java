package fr.ulille.moulinator;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    /**
     * boolean isPlayer1Turn : si c'est le tour du joueur 1
     */
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

    /**
     * Methode qui applique la sauvegarde de la partie
     * @return bollean : si la partie est sauvegardée
     */
    public boolean saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream("resources/" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            this.p1_save = p1;
            this.p2_save = p2;
            this.Board_save = Board;
            this.isPlayer1Turn_save = isPlayer1Turn;
            out.writeObject(this);
            out.close();
            fileOut.close();
            return true;
        } catch(IOException i) {
            i.printStackTrace();
            return false;
        }
    }

    /**
     * @param nameFile : le nom du fichier de la sauvegarde
     * @return Game : la partie chargée
     * @throws IOException : si le fichier n'existe pas
     * @throws ClassNotFoundException : si la classe n'existe pas
     */
    public static Game loadGame(String nameFile) {
        try {
            FileInputStream fileIn = new FileInputStream("resources/" + nameFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Game g = (Game) in.readObject();
            Game.p1 = g.p1_save;
            Game.p2 = g.p2_save;
            Game.Board = g.Board_save;
            Game.isPlayer1Turn = g.isPlayer1Turn_save;
            in.close();
            fileIn.close();
            return g;
        } catch(IOException | ClassNotFoundException ignored) { }
        return null;
    }

    public static void startGame() {
        //TODO
    }

    @Override
    public String toString() {
        return "Disposition de la partie !\n" +
                "Joueur 1 : " + p1 + "\n" +
                "Joueur 2 : " + p2 + "\n" +
                "Plateau : " + Board.toString();
    }

}
