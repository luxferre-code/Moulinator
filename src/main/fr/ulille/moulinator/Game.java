package fr.ulille.moulinator;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public final class Game implements Serializable {
    
    /**
     * Joueur p1, p2 : les joueurs de la partie
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
     * boolean isPlayer1Turn : si c'est au tour du joueur 1 (par defaut true)
     */
    public static boolean isPlayer1Turn = true;

    /**
     * Joueur p1_save, p2_save : les joueurs de la partie sauvegardée
     */
    private Joueur p1_save, p2_save;

    /**
     * Board Board_save : le plateau de la partie sauvegardée
     */
    private Board Board_save;

    /**
     * boolean isPlayer1Turn_save : si c'est au tour du joueur 1 de la partie sauvegardée
     */
    private boolean isPlayer1Turn_save;


    /**
     * Long serialVersionUID : ID pour la serialisation
     * @see Serializable
     */
    private static final long serialVersionUID = 1L;

    /**
     * Scanner SCANNER : le scanner pour lire les entrées clavier
     */
    public static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Affiche les erreurs en rouge
     * @param s
     * @see Color
     * @see String
     */
    public static void logger(String s) {
        System.out.println(Color.ANSI_RED + s + Color.ANSI_RESET);
    }

    /**
     * @return boolean : true si la partie est sauvegardée / false sinon + exception
     * @exception IOException
     * @see FileOutputStream
     * @see ObjectOutputStream
     * @see LocalDate
     * @see DateTimeFormatter
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
     * @param nameFile : le nom du fichier de la partie à charger
     * @return Game : la partie chargée
     * @exception IOException
     * @exception ClassNotFoundException : si la classe n'est pas trouvée
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
