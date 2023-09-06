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
    private Color color;
    private static final Color BASE_COLOR = Color.ANSI_RED;
    public int onBoard;
    public boolean allPlaced;
    protected int nbPiecePlaced = 0;
    public static final int NB_MAX_PIECE = 8;

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

    public boolean choose() {
        if(this.allPlaced) {
            try {
                int from = chooseSlotOwned();
                int to = chooseSlotToMove(from);
                if(to == -1) {
                    return choose();
                }
                Game.Board.moveSlot(from, to);
                System.out.println(this.NAME + " move " + from + " to " + to);
                return true;
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
        }
        return false;
    }

    /**
     * Methode qui verifie la couleur du joueur
     * @param myColor : la couleur du joueur
     * @return boolean : si la couleur est celle du joueur
     */
    public boolean chooseIsYours(Color myColor){
        if(this.color.equals(myColor)){
            return true;
        }
        return false;
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
        return c >= 'a' && c <= 'x';
    }

    /**
     * Methode qui permet au joueur de choisir un slot qui lui appartient
     * @return int : la position du slot choisi par le joueur
     * @throws NoHavingSlotException : Le slot choisie n'est pas possédé par le joueur
     */
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

    /**
     * @param slot
     * @return int : la position du slot sur le plateau où le joueur veux déplacer son pion
     * @throws SlotHavingOwnerException : Le slot choisie est déjà possédé par un joueur ou est déjà occupé
     */
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

    /**
     * @return int : la position du slot sur le plateau où le joueur peut placer son pion parmis les slots libres
     */
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
        } while(!chooseIsValid(c) && !possibility.contains(c));
        return c - 'a';
    }
    

    public String toString(){
        return  this.color + "X" + Color.ANSI_RESET;
    }

    /**
     *  Methode qui permet de savoir si le joueur a placé tous ses pions, si ils ne sont pas tous placé, on incrémente le nombre de pions placé
     */
    protected void addPiecePlaced(){
        if(!this.allPlaced) this.nbPiecePlaced++;
        if(this.nbPiecePlaced == NB_MAX_PIECE) this.allPlaced = true;
    }

    /**
     * @return int : le nombre de pions placé
     */
    public int getNbPiecePlaced() {
        return nbPiecePlaced;
    }

    public static void main(String[] args) {
        Joueur j = CustomPlayer.makePlayer();
        System.out.println(j);
        System.out.println(Game.Board);
        j.choose();
        System.out.println(Game.Board);
    }

}