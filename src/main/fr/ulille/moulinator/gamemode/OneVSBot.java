package fr.ulille.moulinator.gamemode;

import java.util.Scanner;

import fr.ulille.moulinator.Bot;
import fr.ulille.moulinator.CustomPlayer;
import fr.ulille.moulinator.Game;

public class OneVSBot implements GameMode {

    @Override
    public void run() {
        Game.p1 = CustomPlayer.makePlayer();
        Game.p2 = new Bot();
        Scanner sc = Game.SCANNER;
        boolean valide = false;
        while (!valide){
            System.out.println("Avez vous bien créer votre joueur oui/non : "  + Game.p1.toStringName());
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
        while(!Game.p1.isDead() && !Game.p2.isDead()) {
            Game.clearScreen();
            System.out.println(Game.Board);
            System.out.println("It's " + (Game.isPlayer1Turn ? Game.p1 : Game.p2) + " turn !");
            if((Game.isPlayer1Turn ? Game.p1 : Game.p2).choose()) {
                Game.isPlayer1Turn = !Game.isPlayer1Turn;
            } else {
                System.out.println("No more moves !");
            }
        }
    }

    public static void main(String[] args) {
        GameMode gameMode = new OneVSBot();
        gameMode.run();
    }

}
