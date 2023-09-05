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
    public boolean chooseIsYours(){
        //if
        return false;
    }

    public int chooseSlotOwned() throws NoHavingSlotException {
        return 0;
    }

    public String toString(){
        return  this.color + "X" + Color.ANSI_RESET;
    }

    public static void main(String[] args) {
        Joueur j = new Joueur("Hocine");
        System.out.println(j.getName());
        System.out.println(j.toString());
        System.out.println(j.color.getColor());
        System.out.println(j.chooseIsYours(BASE_COLOR));
    }

}