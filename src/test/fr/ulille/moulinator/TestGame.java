package test.fr.ulille.moulinator;

import fr.ulille.moulinator.Game;
import fr.ulille.moulinator.Joueur;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGame {
    Game g1;
    Game g2;
    Game g3;
    Joueur h;
    Joueur v;
    Joueur a;

    @BeforeEach
    public void createGame() {
        g1 = new Game();
        g2 = new Game();
        g3 = new Game();
        h = new Joueur("Hocine");
        v = new Joueur("Valentin");
        a = new Joueur("Adham");
    }
}
