package fr.ulille.moulinator.exceptions;
/**
 * <p>La classe qui gere l'exception lié au slot</p>
 * @author HOCINE CHEBOUT
 * @author VALENTIN THUILLER
 * @author LEIBOVICI EZECHIEL
 * @author BARBEAU SIMON
 * @author TOUMJI ABDALLAH
 * @author BERRAKANE ADHAM
 * @see Exception
 */
public class NoHavingSlotException extends Exception {

    /**
     * @param message : le message d'erreur
     */
    public NoHavingSlotException(String message) {
        super(message);
    }

}
