package fr.ulille.moulinator;


import fr.ulille.moulinator.enums.Color;
import fr.ulille.moulinator.exceptions.NoHavingSlotException;
import fr.ulille.moulinator.exceptions.SlotHavingOwnerException;
import fr.ulille.moulinator.panels.Menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    Color color;
    private static final Color BASE_COLOR = Color.ANSI_RED;
    public int onBoard;
    public boolean allPlaced;
    protected int nbPiecePlaced = 0;

    /**
     * Constructeur de la classe Joueur avec le nom et la couleur
     * @param name : nom du joueur
     * @param color : couleur du joueur
     */
    public Joueur(String name, Color color){
        this.NAME = name;
        this.color = color;
    }
    
    /**
     * Constructeur de la classe Joueur avec le nom et la couleur par defaut
     * @param name : nom du joueur
     */
    public Joueur(String name){
        this.NAME = name;
        this.color = BASE_COLOR;
    }

    /**
     * @return String : le nom du joueur
     */
    public String getName(){
        return this.NAME;
    }

    private boolean executeCommand(String cmd) {
        switch(cmd) {
            case "save" -> {
                if(new Game().saveGame()) {
                    Game.info("Game saved successfully !");
                } else {
                    Game.logger("Error while saving game !");
                }
                return true;
            }
            case "quit" -> {
                Game.info("Goodbye ! :P");
                System.exit(0);
                return true;
            }
            case "menu" -> {
                try {
                    Game.p1 = null;
                    Game.p2 = null;
                    Game.Board = new Board();
                    Color.reset();
                    Menu.execute();
                } catch(Exception e) {
                    if(Game.debugMod) e.printStackTrace();
                 }
                System.exit(0);
            }
        }
        return false;
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
                if(Game.debugMod){
                    Game.info(this.NAME + " move " + from + " to " + to);
                }
                slotPlace = to;
            } catch(NoHavingSlotException e) {
                if(Game.debugMod) e.printStackTrace();
                Game.logger("No slot owned !");
            } catch(SlotHavingOwnerException e) {
                if(Game.debugMod) e.printStackTrace();
                Game.logger("No free slot !");
                return choose();
            }
        } else {
            int slot = chooseFreeSlot();
            Game.Board.setJoueurOnSlot(slot, this);
            if(Game.debugMod){
                Game.info(this.NAME + " place on " + slot);
            }
            this.addPiecePlaced();
            this.onBoard++;
            slotPlace = slot;
        }

        try {
            if(Game.Board.sontAligne(slotPlace)) {
                int ennemiSlot = chooseEnnemiSlot();
                Game.Board.setJoueurOnSlot(ennemiSlot, null);
                if(Game.debugMod){
                    Game.info(this.NAME + " remove " + ennemiSlot);
                }
            }
        } catch(NoHavingSlotException e) {
            if(Game.debugMod) e.printStackTrace();
            Game.logger("No ennemi slot !");
        }

        return slotPlace != -1;
    }

    /**
     * Methode qui verifie si le caractère est en majuscule et le met en minuscule
     * @param c : le caractère donner par l'utilisateur
     * @return char : le caractère en minuscule
     */
    private char removeMaj(char c){
        if(c >= 'A' && c <= 'Z') {
            return (char) (c - 'A' + 'a');
        }
        return c;
    }

    /**
     * @param c : le caractère donner par l'utilisateur
     * @return boolean : si le caractère est valide
     */
    public boolean chooseIsValid(char c){
        if(c>='A' && c<='Z'){
            String newC = c+"";
            newC = newC.toLowerCase();
            c = newC.charAt(0);
        }
        return c >= 'a' && c <= 'x';
    }

    public int chooseEnnemiSlot() throws NoHavingSlotException {
        List<Integer> ennemiSlot = Game.Board.allPositionPlayer(Game.isPlayer1Turn ? Game.p2 : Game.p1);
        List<Character> possibility = new ArrayList<>();
        for(Integer i : ennemiSlot) {
            possibility.add((char) (i + 'a'));
        }
        System.out.print("Choose a ennemi to remove " + possibility.toString().replace("[", "(").replace("]", ")") + ": ");
        char c = '.';
        do {
            String s = Game.SCANNER.next();
            if(!this.executeCommand(s)) {
                c = s.charAt(0);
                c = removeMaj(c);
                if(!chooseIsValid(c)) {
                    Game.logger("Invalid slot !");
                }
                else if(!possibility.contains(c)) {
                    Game.logger("Slot not ennemi !");
                }
            }
        } while(!chooseIsValid(c) && !possibility.contains(c));
        return c - 'a';
    }

    /**
     * Methode qui permet au joueur de choisir un slot qui lui appartient
     * @return int : la position du slot choisi par le joueur
     * @throws NoHavingSlotException : Le slot choisie n'est pas possédé par le joueur
     */
    public int chooseSlotOwned() throws NoHavingSlotException {
        System.out.print("Choose a slot to move: ");
        char c = '.';
        do {
            String s = Game.SCANNER.next();
            if(!this.executeCommand(s)) {
                c = s.charAt(0);
                c = removeMaj(c);
                if(!chooseIsValid(c)) {
                    Game.logger("Invalid slot !");
                }
            }

        } while(!chooseIsValid(c));
        return c - 'a';
    }

    /**
     * @param slot : la position du slot choisi par le joueur
     * @return int : la position du slot sur le plateau où le joueur veux déplacer son pion
     * @throws SlotHavingOwnerException : Le slot choisie est déjà possédé par un joueur ou est déjà occupé
     */
    public int chooseSlotToMove(int slot) throws SlotHavingOwnerException {
        List<Character> possibility = new ArrayList<>();
        for(Integer i : Game.Board.allFreePosition(slot)) {
            possibility.add((char) (i + 'a'));
        }
        System.out.print("Choose a slot to move " + possibility.toString().replace("[", "(").replace("]", ")") + ": ");
        char c = '.';
        do {
            String s = Game.SCANNER.next();
            if(!this.executeCommand(s)) {
                c = s.charAt(0);
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
            }
        } while(!chooseIsValid(c) && !possibility.contains(c));
        return c - 'a';
    }

    /**
     * @return int : la position du slot sur le plateau où le joueur peut placer son pion parmis les slots libres
     */
    public int chooseFreeSlot() {
        List<Character> possibility = new ArrayList<>();
        for(Integer i : Game.Board.allFreePosition()) {
            possibility.add((char) (i + 'a'));
        }
        System.out.print("Choose a slot to place " + possibility.toString().replace("[", "(").replace("]", ")") + ": ");
        char c = '.';
        do {
            String s = Game.SCANNER.next();
            if(!this.executeCommand(s)) {
                c = s.charAt(0);
                c = removeMaj(c);
                if(!chooseIsValid(c)) {
                    Game.logger("Invalid slot !");
                }
                else if(!possibility.contains(c)) {
                    Game.logger("Slot not free !");
                }
            }

        } while(!chooseIsValid(c) || !possibility.contains(c));
        return c - 'a';
    }
    

    public String toString(){
        return  this.color + "X" + Color.ANSI_RESET;
    }

    public String toStringName(){
        return  this.color + this.NAME + Color.ANSI_RESET;
    }

    /**
     *  Methode qui permet de savoir si le joueur a placé tous ses pions, si ils ne sont pas tous placé, on incrémente le nombre de pions placé
     */
    protected void addPiecePlaced(){
        if(!this.allPlaced) this.nbPiecePlaced++;
        if(this.nbPiecePlaced == Game.maxBilles) this.allPlaced = true;
    }

    public boolean isDead(){
        return Game.Board.allPositionPlayer(this).size() <= Game.minBilles && this.allPlaced;
    }

    public Color getColor() {
        return color;
    }
}