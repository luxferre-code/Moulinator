package fr.ulille.moulinator.exceptions;

/**
 * <p>La classe qui gere l'exception lié au slot déja occupé</p>
 * @author HOCINE CHEBOUT
 * @author VALENTIN THUILLER
 * @author LEIBOVICI EZECHIEL
 * @author BARBEAU SIMON
 * @author TOUMJI ABDALLAH
 * @author BERRAKANE ADHAM
 * @see Exception
 */
public class SlotHavingOwnerException extends Exception {


    /**
     * @param message : le message d'erreur
     */
    public SlotHavingOwnerException(String message) {
        super(message);
    }
}
