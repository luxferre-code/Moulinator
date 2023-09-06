package fr.ulille.moulinator.gamemode;

import fr.ulille.moulinator.CustomPlayer;
import fr.ulille.moulinator.Game;
import fr.ulille.moulinator.Joueur;

public class OneVSOne implements GameMode{

    @Override
    public void run() {
        System.out.println("Joueur 1 :");
        Game.p1 = CustomPlayer.makePlayer();
        System.out.println("Joueur 2 :");
        Game.p2 = CustomPlayer.makePlayer();
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
