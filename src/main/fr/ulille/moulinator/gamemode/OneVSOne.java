package fr.ulille.moulinator.gamemode;

import fr.ulille.moulinator.Game;
import fr.ulille.moulinator.Joueur;
import fr.ulille.moulinator.panels.CustomPlayer;

import java.util.Scanner;

public class OneVSOne implements GameMode{

    @Override
    public void run(boolean isSave) {
        if(!isSave) {
            System.out.println("Player 1 :");
            Game.p1 = CustomPlayer.makePlayer();
            System.out.println("Player 2 :");
            Game.p2 = CustomPlayer.makePlayer();
        }
        Scanner sc = Game.SCANNER;
        boolean valide = false;
        while (!valide){
            System.out.println("Have you created your desired players yes/no : "  + Game.p1.toStringName() + "  " + Game.p2.toStringName());
            String choix = sc.next();
            if (choix.toLowerCase().contains("no")){
                Game.p1.getColor().setUsed(false);
                Game.p2.getColor().setUsed(false);
                run();
            } else if (choix.toLowerCase().contains("yes")){
                valide = true;
            } else {
                System.out.println("Invalid choice");
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
        gameOver();
    }

    public static void main(String[] args) {
        GameMode gameMode = new OneVSOne();
        gameMode.run();
    }
}
