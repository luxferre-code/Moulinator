package fr.ulille.moulinator.gamemode;

import fr.ulille.moulinator.Game;

public class Demo implements GameMode {

    @Override
    public void run(boolean isSave) {
        Game.info("Starting demo mode");
        try { Thread.sleep(3000); } catch(Exception ignored) {}
        while(true) {
            new BotVSBot().run(false);
        }
    }
}
