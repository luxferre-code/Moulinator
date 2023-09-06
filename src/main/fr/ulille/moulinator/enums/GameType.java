package fr.ulille.moulinator.enums;


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

    PLAYER_VS_PLAYER("Joueur contre Joueur"),
    PLAYER_VS_BOT("Joueur contre Bot"),
    BOT_VS_BOT("Bot contre Bot");

    private final String name;

    GameType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
