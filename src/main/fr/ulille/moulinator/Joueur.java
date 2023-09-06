package fr.ulille.moulinator;


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
     * Constructeur de la classe Joueur completement défini
     * @param name : nom du joueur
     * @param color : couleur du joueur
     * @param onBoard : nombre de pion sur le plateau
     * @param allPlaced : si le joueur a placé tous ses pions
     */
    public Joueur(String name, Color color, int onBoard, boolean allPlaced){
        this.NAME = name;
        this.onBoard = onBoard;
        this.allPlaced = allPlaced;
        this.color = color;
    }

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
     * Constructeur de la classe Joueur avec la couleur et le nom par defaut
     */
    public Joueur(){
        this.NAME = "undefined";
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
                    Menu.execute();
                } catch(Exception ignored) { }
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
            return true;
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

    public int chooseSlotToMove(int slot) throws SlotHavingOwnerException {
        List<Character> possibility = new ArrayList<>();
        for(Integer i : Game.Board.allFreePosition(slot)) {
            possibility.add((char) (i + 'a'));
        }
        System.out.print("Choose a slot to move " + possibility.toString().replace("[", "(").replace("]", ")") + ": ");
        char c;
        do {
            String s = Game.SCANNER.next();
            c = s.charAt(0);
            c = removeMaj(c);
            this.executeCommand(s);
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
            String s = Game.SCANNER.next();
            c = s.charAt(0);
            c = removeMaj(c);
            this.executeCommand(s);
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

    public String toStringName(){
        return  this.color + this.NAME + Color.ANSI_RESET;
    }

    protected void addPiecePlaced(){
        if(!this.allPlaced) this.nbPiecePlaced++;
        if(this.nbPiecePlaced == Game.maxBilles) this.allPlaced = true;
    }

    public int getNbPiecePlaced() {
        return nbPiecePlaced;
    }

    public boolean isDead(){
        return Game.Board.allPositionPlayer(this).size() <= Game.minBilles && this.allPlaced;
    }

    public Color getColor() {
        return color;
    }

    public static void main(String[] args) {
        Joueur j = CustomPlayer.makePlayer();
        System.out.println(j);
        System.out.println(Game.Board);
        j.choose();
        System.out.println(Game.Board);
    }

}