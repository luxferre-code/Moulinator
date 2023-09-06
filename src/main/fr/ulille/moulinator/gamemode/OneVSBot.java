package fr.ulille.moulinator.gamemode;

import fr.ulille.moulinator.Bot;
import fr.ulille.moulinator.CustomPlayer;
import fr.ulille.moulinator.Game;

public class OneVSBot implements GameMode {

    @Override
    public void run() {
        Game.p1 = CustomPlayer.makePlayer();
        Game.p2 = new Bot();
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
