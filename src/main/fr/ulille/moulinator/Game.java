package fr.ulille.moulinator;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public final class Game implements Serializable {
    
    public static Joueur p1, p2;
    public static Board Board = new Board();
    public static GameType gameType;
    public static boolean isPlayer1Turn = true;
    private Joueur p1_save, p2_save;
    private Board Board_save;
    private boolean isPlayer1Turn_save;
    private static final long serialVersionUID = 1L;
    public static final Scanner SCANNER = new Scanner(System.in);
    public static void logger(String s) {
        System.out.println(Color.ANSI_RED + s + Color.ANSI_RESET);
    }

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
