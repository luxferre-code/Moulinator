package fr.ulille.moulinator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fr.ulille.moulinator.Bot;
import fr.ulille.moulinator.Color;
import fr.ulille.moulinator.Game;
import fr.ulille.moulinator.Joueur;

public class TestSave {

    public static void load() {
        Game g = Game.loadGame();
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
