package fr.ulille.moulinator.gamemode;

import fr.ulille.moulinator.Bot;
import fr.ulille.moulinator.Game;

public class BotVSBot implements GameMode {

    public void run(boolean isSave) {
        if(!isSave) {
            Game.p1 = new Bot();
            Game.p2 = new Bot();
        }
        Game.clearScreen();
        while(!Game.p1.isDead() && !Game.p2.isDead()) {
            Game.clearScreen();
            System.out.println(Game.Board);
            System.out.println("It's " + (Game.isPlayer1Turn ? Game.p1 : Game.p2).toStringName() + " turn !");
            if((Game.isPlayer1Turn ? Game.p1 : Game.p2).choose()) {
                if(Game.debugMod) System.out.println("Bot 1 => nbPiecePlaced: " + Game.Board.allPositionPlayer((Game.isPlayer1Turn ? Game.p1 : Game.p2)).size() + " allPlaced: " + (Game.isPlayer1Turn ? Game.p1 : Game.p2).allPlaced);
                Game.isPlayer1Turn = !Game.isPlayer1Turn;
                try { Thread.sleep(Game.DELAY_BOT); } catch (Exception ignored) { }
            }
        }
        gameOver();
    }
}
