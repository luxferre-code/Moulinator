package fr.ulille.moulinator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestSave {

    public static void load() {
        Game g = Game.loadGame(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".ser");
        System.out.println(g);
    }

    public static void save() {
        Game g = new Game();
        Game.p1 = new Joueur("test1", Color.ANSI_RED);
        Game.p2 = new Bot();
        Game.Board.setJoueurOnSlot(0, Game.p1);
        System.out.println(g);
        g.saveGame();
    }

    public static void main(String[] args) {
        load();
    }

}
