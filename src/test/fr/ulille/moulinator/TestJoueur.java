package test.fr.ulille.moulinator;

import fr.ulille.moulinator.Joueur;

public class TestJoueur {

    public static void main(String[] args) {
        Joueur h = new Joueur("Hocine");
        Joueur v = new Joueur("Valentin");
        Joueur a = new Joueur("Adham");
        assert h.NAME.equals("Hocine");
        System.out.println("success 1/3");
        assert v.NAME.equals("Valentin");
        System.out.println("success 2/3");
        assert a.NAME.equals("Adham");
        System.out.println("success 3/3");
        System.out.println("All tests passed successfully.");
    }


    
}
