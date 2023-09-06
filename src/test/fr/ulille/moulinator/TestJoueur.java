package test.fr.ulille.moulinator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.ulille.moulinator.Board;
import fr.ulille.moulinator.Color;
import fr.ulille.moulinator.Joueur;

public class TestJoueur {
    Joueur h;
    Joueur v;
    Joueur a;

    @BeforeEach
    public void createJoueur() {
        h = new Joueur("Hocine",Color.ANSI_BLACK);
        v = new Joueur("Valentin",Color.ANSI_YELLOW);
        a = new Joueur("Adham");
    }

    @BeforeEach
    public void placeSlot(){
        Board b = new Board();
        b.setJoueurOnSlot(0, h);
        b.setJoueurOnSlot(1, h);
        b.setJoueurOnSlot(2, h);
        b.setJoueurOnSlot(3, h);
        b.setJoueurOnSlot(4, h);
        b.setJoueurOnSlot(5, h);
        b.setJoueurOnSlot(6, v);
        b.setJoueurOnSlot(7, v);
        b.setJoueurOnSlot(8, v);
        b.setJoueurOnSlot(9, v);
        b.setJoueurOnSlot(10, v);
        b.setJoueurOnSlot(11, v);

    }

    @Test
    public void testGetName(){
        assertEquals("Hocine",h.getName());
        assertEquals("Valentin",v.getName());
        assertEquals("Adham",a.getName());
        assertNotEquals("Hocine",v.getName());
    }

    @Test
    public void testChooseIsValid(){
        assertTrue(h.chooseIsValid('a'));
        assertTrue(v.chooseIsValid('b'));
        assertTrue(a.chooseIsValid('c'));
        assertTrue(h.chooseIsValid('d'));
        assertTrue(v.chooseIsValid('e'));
        assertTrue(a.chooseIsValid('f'));
        assertTrue(h.chooseIsValid('g'));
        assertTrue(v.chooseIsValid('h'));
        assertTrue(a.chooseIsValid('i'));
        assertTrue(h.chooseIsValid('j'));
        assertTrue(v.chooseIsValid('k'));
        assertTrue(a.chooseIsValid('l'));
        assertTrue(h.chooseIsValid('m'));
        assertTrue(v.chooseIsValid('n'));
        assertTrue(a.chooseIsValid('o'));
        assertTrue(h.chooseIsValid('p'));
        assertTrue(v.chooseIsValid('q'));
        assertTrue(a.chooseIsValid('r'));
        assertTrue(h.chooseIsValid('s'));
        assertTrue(v.chooseIsValid('t'));
        assertTrue(a.chooseIsValid('u'));
        assertTrue(h.chooseIsValid('v'));
        assertTrue(v.chooseIsValid('w'));
        assertTrue(a.chooseIsValid('x'));
        assertFalse(h.chooseIsValid('y'));
        assertFalse(v.chooseIsValid('z'));
        assertTrue(v.chooseIsValid('A'));
        assertTrue(a.chooseIsValid('B'));
        assertTrue(a.chooseIsValid('E'));
        assertFalse(v.chooseIsValid('Z'));
    }
    
}
