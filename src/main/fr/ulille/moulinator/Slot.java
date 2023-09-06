package fr.ulille.moulinator;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>La classe qui initialise des emplacement/pions</p>
 * @author HOCINE CHEBOUT
 * @author VALENTIN THUILLER
 * @author LEIBOVICI EZECHIEL
 * @author BARBEAU SIMON
 * @author TOUMJI ABDALLAH
 * @author BERRAKANE ADHAM
 */
public class Slot implements Serializable {

    /**
     * Joueur : le joueur 1 et 2
     */
    private Joueur owner, exOwner;

    /**
     * Constructeur de la classe Slot vide
     */
    public Slot() { }

    /**
     * @return Jouer : le joueur qui possede le slot
     */
    public Joueur getOwner() {
        return owner;
    }

    /**
     * @return Jouer : le joueur qui possedait le slot au tour precedent
     */
    public Joueur getExOwner() {
        return exOwner;
    }

    /**
     * @param newOwner : le nouveau proprietaire du slot
     * @see Joueur
     */
    public void changeOwner(Joueur newOwner) {
        if(this.owner == null) {
            this.owner = newOwner;
            return;
        }
        if(!this.owner.equals(newOwner)) {
            this.exOwner = this.owner;
            this.owner = newOwner;
        }
    }

    /**
     * Methode qui gere la libération/occupation d'un slot lors d'un deplacement
     *
     * @param s : le slot à deplacer
     */
    public void moveSlot(Slot s) {
        if(this.equals(s) || this.owner.equals(s.getOwner())) { 
            return;
        }
        this.exOwner = this.owner;
        s.exOwner = s.owner;
        this.owner = s.exOwner;
        s.owner = this.exOwner;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Slot slot)) return false;
        return Objects.equals(getOwner(), slot.getOwner()) && Objects.equals(getExOwner(), slot.getExOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwner(), getExOwner());
    }

    @Override
    public String toString() {
        if(owner == null) { return "0"; }
        return owner.toString();
    }
}
