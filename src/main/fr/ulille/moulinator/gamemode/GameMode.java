package fr.ulille.moulinator.gamemode;

import fr.ulille.moulinator.Game;
import fr.ulille.moulinator.enums.Color;

public interface GameMode {

    default void run() {
        this.run(false);
    }
    void run(boolean isSave);

    default void gameOver() {
        String message = "No more moves ! GGWP";
        for(int cpt = 0;cpt < message.length();cpt++){
            try{
                System.out.print(Game.rdmColor().toString() + message.charAt(cpt) + Color.ANSI_RESET);
                Thread.sleep(100);
            }catch(Exception ignored){}
        }
        System.out.println();
    }

}
