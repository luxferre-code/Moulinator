package fr.ulille.moulinator;

import fr.ulille.moulinator.enums.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals("Hocine",h.getName());
        Assertions.assertEquals("Valentin",v.getName());
        Assertions.assertEquals("Adham",a.getName());
        Assertions.assertNotEquals("Hocine",v.getName());
    }

    @Test
    public void testChooseIsValid(){
        Assertions.assertTrue(h.chooseIsValid('a'));
        Assertions.assertTrue(v.chooseIsValid('b'));
        Assertions.assertTrue(a.chooseIsValid('c'));
        Assertions.assertTrue(h.chooseIsValid('d'));
        Assertions.assertTrue(v.chooseIsValid('e'));
        Assertions.assertTrue(a.chooseIsValid('f'));
        Assertions.assertTrue(h.chooseIsValid('g'));
        Assertions.assertTrue(v.chooseIsValid('h'));
        Assertions.assertTrue(a.chooseIsValid('i'));
        Assertions.assertTrue(h.chooseIsValid('j'));
        Assertions.assertTrue(v.chooseIsValid('k'));
        Assertions.assertTrue(a.chooseIsValid('l'));
        Assertions.assertTrue(h.chooseIsValid('m'));
        Assertions.assertTrue(v.chooseIsValid('n'));
        Assertions.assertTrue(a.chooseIsValid('o'));
        Assertions.assertTrue(h.chooseIsValid('p'));
        Assertions.assertTrue(v.chooseIsValid('q'));
        Assertions.assertTrue(a.chooseIsValid('r'));
        Assertions.assertTrue(h.chooseIsValid('s'));
        Assertions.assertTrue(v.chooseIsValid('t'));
        Assertions.assertTrue(a.chooseIsValid('u'));
        Assertions.assertTrue(h.chooseIsValid('v'));
        Assertions.assertTrue(v.chooseIsValid('w'));
        Assertions.assertTrue(a.chooseIsValid('x'));
        Assertions.assertFalse(h.chooseIsValid('y'));
        Assertions.assertFalse(v.chooseIsValid('z'));
        Assertions.assertTrue(v.chooseIsValid('A'));
        Assertions.assertTrue(a.chooseIsValid('B'));
        Assertions.assertTrue(a.chooseIsValid('E'));
        Assertions.assertFalse(v.chooseIsValid('Z'));
    }
    
}
