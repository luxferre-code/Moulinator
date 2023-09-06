package fr.ulille.moulinator.gamemode;

import fr.ulille.moulinator.Board;
import fr.ulille.moulinator.Game;

public class Demo implements GameMode {

    @Override
    public void run(boolean isSave) {
        Game.info("Starting demo mode");
        try { Thread.sleep(3000); } catch(Exception ignored) {}
        while(true) {
            Game.Board = new Board();
            new BotVSBot().run(false);
        }
    }
}
