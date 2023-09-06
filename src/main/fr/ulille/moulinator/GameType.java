package fr.ulille.moulinator;


/**
 * <p>La classe qui initialise les modes de jeu</p>
 * @author HOCINE CHEBOUT
 * @author VALENTIN THUILLER
 * @author LEIBOVICI EZECHIEL
 * @author BARBEAU SIMON
 * @author TOUMJI ABDALLAH
 * @author BERRAKANE ADHAM
 */
public enum GameType {

    PLAYER_VS_PLAYER("Joueur contre Joueur", true, true),
    PLAYER_VS_BOT("Joueur contre Bot", true, false),
    BOT_VS_BOT("Bot contre Bot", false, false);

    private final String name;
    private final boolean isPlayer1;
    private final boolean isPlayer2;

    GameType(String name, boolean isPlayer1, boolean isPlayer2) {
        this.name = name;
        this.isPlayer1 = isPlayer1;
        this.isPlayer2 = isPlayer2;
    }

    public String getName() {
        return name;
    }

    public boolean isPlayer1() {
        return isPlayer1;
    }

    public boolean isPlayer2() {
        return isPlayer2;
    }

    @Override
    public String toString() {
        return name;
    }

}
