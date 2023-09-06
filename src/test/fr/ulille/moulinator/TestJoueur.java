package fr.ulille.moulinator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestJoueur {
    Joueur h;
    Joueur v;
    Joueur a;

    @BeforeEach
    public void createJoueur() {
        h = new Joueur("Hocine");
        v = new Joueur("Valentin");
        a = new Joueur("Adham");
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
        assertTrue(a.chooseIsValid('E'));
        assertFalse(h.chooseIsValid('z'));
        assertTrue(v.chooseIsValid('A'));

    }

    @Test
    public void testChooseSlotOwned() throws Exception{
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
    public void testChooseSlotToMove() throws Exception{
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


    
}
