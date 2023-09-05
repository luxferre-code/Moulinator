package fr.ulille.moulinator;

public final class Game {
    
    public static Joueur p1, p2;
    public static final Board BOARD = new Board();



    public static void logger(String s) {
        System.out.println(Color.ANSI_RED + s + Color.ANSI_RESET);
    }

}
