package test.fr.ulille.moulinator;

import fr.ulille.moulinator.Joueur;
import static org.junit.Assert.assertEquals;
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


    
}
