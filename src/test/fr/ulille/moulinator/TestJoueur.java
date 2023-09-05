package test.fr.ulille.moulinator;
import fr.ulille.moulinator.Joueur;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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
    public void testChooseSlotOwned() throws NoHavingSlotException{
        assertEquals(0,h.chooseSlotOwned('a'));
        assertEquals(1,v.chooseSlotOwned('b'));
        assertEquals(4,a.chooseSlotOwned('e'));
        assertNotEquals(0,h.chooseSlotOwned('z'));
        assertEquals(0,v.chooseSlotOwned('A'));
    }


    
}
