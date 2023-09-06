package fr.ulille.moulinator.gamemode;

public interface GameMode {

    default void run() {
        this.run(false);
    }
    void run(boolean isSave);

}
