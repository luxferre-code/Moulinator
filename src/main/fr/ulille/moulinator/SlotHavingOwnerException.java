package fr.ulille.moulinator;

/**
 * <p>La classe qui gere l'exception lié au slot déja occupé</p>
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
