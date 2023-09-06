package fr.ulille.moulinator.gamemode;

import java.util.Scanner;

import fr.ulille.moulinator.CustomPlayer;
import fr.ulille.moulinator.Game;
import fr.ulille.moulinator.Joueur;
/**
 * <p>La classe qui initialise le mode de jeu J1 VS J2</p>
 * @author HOCINE CHEBOUT
 * @author VALENTIN THUILLER
 * @author LEIBOVICI EZECHIEL
 * @author BARBEAU SIMON
 * @author TOUMJI ABDALLAH
 * @author BERRAKANE ADHAM
 */
public class OneVSOne implements GameMode{

    @Override
    public void run(boolean isSave) {
        if(!isSave) {
            System.out.println("Joueur 1 :");
            Game.p1 = CustomPlayer.makePlayer();
            System.out.println("Joueur 2 :");
            Game.p2 = CustomPlayer.makePlayer();
        }
        Scanner sc = Game.SCANNER;
        boolean valide = false;
        while (!valide){
            System.out.println("Avez vous bien créer vos joueurs oui/non : "  + Game.p1.toStringName() + "  " + Game.p2.toStringName());
            String choix = sc.next();
            if (choix.toLowerCase().contains("non")){
                Game.p1.getColor().setUsed(false);
                Game.p2.getColor().setUsed(false);
                run();
            } else if (choix.toLowerCase().contains("oui")){
                valide = true;
            } else {
                System.out.println("Choix invalide");
            }

        }
        Game.clearScreen();
        while(!Game.p1.isDead() || !Game.p2.isDead()) {
            Joueur p = Game.isPlayer1Turn ? Game.p1 : Game.p2;
            System.out.println(Game.Board);
            System.out.println("It's " + p + " turn !");
            if(p.choose()) {
                Game.clearScreen();
                Game.isPlayer1Turn = !Game.isPlayer1Turn;
            } else {
                System.out.println("No more moves !");
            }
        }
    }

    public static void main(String[] args) {
        GameMode gameMode = new OneVSOne();
        gameMode.run();
    }
}
