package fr.ulille.moulinator;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>La classe qui initialise des joueur</p>
 * @author HOCINE CHEBOUT
 * @author VALENTIN THUILLER
 * @author LEIBOVICI EZECHIEL
 * @author BARBEAU SIMON
 * @author TOUMJI ABDALLAH
 * @author BERRAKANE ADHAM
 */
    
public sealed class Joueur implements Serializable permits Bot {
    public final String NAME;
    private Color color;
    private static final Color BASE_COLOR = Color.ANSI_RED;
    public int onBoard;
    public boolean allPlaced;
    protected int nbPiecePlaced = 0;
    public static final int NB_MAX_PIECE = 6;

    public Joueur(String name, Color color, int onBoard, boolean allPlaced){
        this.NAME = name;
        this.onBoard = onBoard;
        this.allPlaced = allPlaced;
        this.color = color;
    }

    public Joueur(String name, Color color){
        this.NAME = name;
        this.color = color;
    }
    
    public Joueur(String name){
        this.NAME = name;
        this.color = BASE_COLOR;
    }

    public Joueur(){
        this.NAME = "undefined";
        this.color = BASE_COLOR;
    }
    public String getName(){
        return this.NAME;
    }

    public boolean choose() {
        int slotPlace = -1;
        if(this.allPlaced) {
            try {
                int from = chooseSlotOwned();
                int to = chooseSlotToMove(from);
                if(to == -1) {
                    return choose();
                }
                Game.Board.moveSlot(from, to);
                System.out.println(this.NAME + " move " + from + " to " + to);
                slotPlace = to;
            } catch(NoHavingSlotException e) {
                Game.logger("No slot owned !");
            } catch(SlotHavingOwnerException e) {
                Game.logger("No free slot !");
                return choose();
            }
        } else {
            int slot = chooseFreeSlot();
            Game.Board.setJoueurOnSlot(slot, this);
            System.out.println(this.NAME + " place on " + slot);
            this.addPiecePlaced();
            this.onBoard++;
            slotPlace = slot;
        }

        try {
            if(Game.Board.sontAligne(slotPlace)) {
                int ennemiSlot = chooseEnnemiSlot();
                Game.Board.setJoueurOnSlot(ennemiSlot, null);
                System.out.println(this.NAME + " remove " + ennemiSlot);
                if(Game.p1.equals(this)) {
                    Game.p2.onBoard--;
                } else {
                    Game.p1.onBoard--;
                }
            }
        } catch(NoHavingSlotException e) {
            Game.logger("No ennemi slot !");
        }

        return slotPlace != -1;
    }

    private char removeMaj(char c){
        if(c >= 'A' && c <= 'Z') {
            return (char) (c - 'A' + 'a');
        }
        return c;
    }

    public boolean chooseIsValid(char c){
        return c >= 'a' && c <= 'x';
    }

    public int chooseEnnemiSlot() throws NoHavingSlotException {
        List<Integer> ennemiSlot = Game.Board.allPositionPlayer(Game.isPlayer1Turn ? Game.p2 : Game.p1);
        List<Character> possibility = new ArrayList<>();
        for(Integer i : ennemiSlot) {
            possibility.add((char) (i + 'a'));
        }
        System.out.print("Choose a ennemi to remove " + possibility.toString().replace("[", "(").replace("]", ")") + ": ");
        char c;
        do {
            c = Game.SCANNER.next().charAt(0);
            c = removeMaj(c);
            if(!chooseIsValid(c)) {
                Game.logger("Invalid slot !");
            }
            else if(!possibility.contains(c)) {
                Game.logger("Slot not ennemi !");
            }
        } while(!chooseIsValid(c) && !possibility.contains(c));
        return c - 'a';
    }

    public int chooseSlotOwned() throws NoHavingSlotException {
        System.out.print("Choose a slot to move: ");
        char c;
        do {
            c = Game.SCANNER.next().charAt(0);
            c = removeMaj(c);
            if(!chooseIsValid(c)) {
                Game.logger("Invalid slot !");
            }
        } while(!chooseIsValid(c));
        return c - 'a';
    }

    public int chooseSlotToMove(int slot) throws SlotHavingOwnerException {
        List<Character> possibility = new ArrayList<>();
        for(Integer i : Game.Board.allFreePosition(slot)) {
            possibility.add((char) (i + 'a'));
        }
        System.out.print("Choose a slot to move " + possibility.toString().replace("[", "(").replace("]", ")") + ": ");
        char c;
        do {
            c = Game.SCANNER.next().charAt(0);
            c = removeMaj(c);
            if(c == 'z') {
                return -1;
            }
            if(!chooseIsValid(c)) {
                Game.logger("Invalid slot !");
            }
            else if(!possibility.contains(c)) {
                Game.logger("Slot not free !");
            }
        } while(!chooseIsValid(c) && !possibility.contains(c));
        return c - 'a';
    }

    public int chooseFreeSlot() {
        List<Character> possibility = new ArrayList<>();
        for(Integer i : Game.Board.allFreePosition()) {
            possibility.add((char) (i + 'a'));
        }
        System.out.print("Choose a slot to place " + possibility.toString().replace("[", "(").replace("]", ")") + ": ");
        char c;
        do {
            c = Game.SCANNER.next().charAt(0);
            c = removeMaj(c);
            if(!chooseIsValid(c)) {
                Game.logger("Invalid slot !");
            }
            else if(!possibility.contains(c)) {
                Game.logger("Slot not free !");
            }
        } while(!chooseIsValid(c) || !possibility.contains(c));
        return c - 'a';
    }
    

    public String toString(){
        return  this.color + "X" + Color.ANSI_RESET;
    }

    protected void addPiecePlaced(){
        if(!this.allPlaced) this.nbPiecePlaced++;
        if(this.nbPiecePlaced == NB_MAX_PIECE) this.allPlaced = true;
    }

    public int getNbPiecePlaced() {
        return nbPiecePlaced;
    }

    public boolean isDead(){
        return this.onBoard <= 2 && this.allPlaced;
    }

    public static void main(String[] args) {
        Joueur j = CustomPlayer.makePlayer();
        System.out.println(j);
        System.out.println(Game.Board);
        j.choose();
        System.out.println(Game.Board);
    }

}