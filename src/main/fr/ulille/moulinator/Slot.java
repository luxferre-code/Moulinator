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
     * int : coordonnées du slot
     */
    private int coordx,coordy;

    /**
     * Constructeur de la classe Slot vide
     */
    public Slot() { }

    /**
     * Constructeur de la classe Slot avec le joueur
     * @param owner : le joueur qui possede le slot
     */
    public Slot(Joueur owner) {
        this.owner = owner;
    }

    /**
     * @return Jouer : le joueur qui possede le slot
     */
    public Joueur getOwner() {
        return owner;
    }

    /**
     * Methode qui retourne la coordonnée x de slot (getter)
     * @return int : la coordonnée x du slot
     */
    public int getx(){
        return coordx;
    }

    /**
     * Methode qui retourne la coordonnée y de slot (getter)
     * @return int : la coordonnée y du slot
     */
    public int gety(){
        return coordy;
    }

    /**
     * @return Jouer : le joueur qui possedait le slot au tour precedent
     */
    public Joueur getExOwner() {
        return exOwner;
    }

    /**
     * @param newOwner : le nouveau proprietaire du slot
     * @return boolean : true si le changement de proprietaire a bien eu lieu sinon false
     * @see Joueur
     */
    public boolean changeOwner(Joueur newOwner) {
        if(this.owner == null) {
            this.owner = newOwner;
            return true;
        }
        if(!this.owner.equals(newOwner)) {
            this.exOwner = this.owner;
            this.owner = newOwner;
            return true;
        }
        return false;
    }

    /**
     * Methode qui gere la libération/occupation d'un slot lors d'un deplacement
     * @param s : le slot à deplacer
     * @return boolean : true si la methode à bien gerer la libération/occupation du slot sinon false
     */
    public boolean moveSlot(Slot s) {
        if(this.equals(s) || this.owner.equals(s.getOwner())) { 
            return false; 
        }
        this.exOwner = this.owner;
        s.exOwner = s.owner;
        this.owner = s.exOwner;
        s.owner = this.exOwner;
        return true;
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
