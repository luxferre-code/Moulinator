package fr.ulille.moulinator;


import java.io.Serializable;

/**
 * <p>La classe qui initialise des joueur</p>
 * @author HOCINE CHEBOUT
 * @author VALENTIN THUILLER
 */
    
public sealed class Joueur implements Serializable permits Bot {
    public final String NAME;
    private Color color;
    private static final Color BASE_COLOR = Color.ANSI_RED;
    public  int onBoard;
    public boolean allPlaced;

    public Joueur(String name, Color color, int onBoard, boolean allPlaced){
        this.NAME = name;
        this.onBoard = onBoard;
        if(onBoard == 6){
            this.allPlaced = true;
        }
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

    public boolean chooseIsYours(Color myColor){
        if(this.color.equals(myColor)){
            return true;
        }
        return false;
    }

    public boolean choose(){
        if(chooseIsYours(this.color)){
            return true;
        }
        return false;
    }

    public boolean chooseIsValid(char c){
        String tmp = c+"";
        char newC = tmp.toLowerCase().charAt(0);
        if(newC>='a' && newC<='x'){
            return true;
        }
        return false;
        }

    public int chooseSlotOwned(char c) throws NoHavingSlotException {
        if(this.allPlaced && chooseIsYours(this.color) && chooseIsValid(c)){
            int numberCase = (int) c-'a';
            return numberCase;
        }
        throw new NoHavingSlotException("No slot owned");
    }

    public int chooseSlotToMove(char c) throws SlotHavingOwnerException {
        if(this.allPlaced && chooseIsYours(this.color) && chooseIsValid(c) ){
            int numberCase = (int) c-'a';
            return numberCase;
        }
        throw new SlotHavingOwnerException("No free slot");

    }

    public void move(char c, char d) throws NoHavingSlotException, SlotHavingOwnerException{
        int first = chooseSlotOwned(c);
        int to = chooseSlotToMove(d);
        Game.Board.moveSlot(first, to);
    }
    

    public String toString(){
        return  this.color + "X" + Color.ANSI_RESET;
    }

    public static void main(String[] args) {
        Joueur j = new Joueur("Hocine");
        System.out.println(j.getName());
        System.out.println();
        System.out.println(j.toString());
        System.out.println(j.color.getColor());
        System.out.println(j.chooseIsYours(BASE_COLOR));
        System.out.println(j.chooseIsValid('z'));
        System.out.println("-----------");
        System.out.println(j.chooseIsValid('A'));
        System.out.println('e');
    }

}