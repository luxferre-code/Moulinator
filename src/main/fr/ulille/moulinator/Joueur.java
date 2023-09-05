package fr.ulille.moulinator;


/**
 * <p>La classe qui initialise des joueur</p>
 * @author HOCINE CHEBOUT
 * @author VALENTIN THUILLER
 */
public sealed class Joueur permits Bot{
    public final String NAME;
    private Color color;
    private static final Color BASE_COLOR = Color.ANSI_RED;
    public  int onBoard;
    public boolean allPlaced;

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


    public boolean chooseIsYours(){
        if
    }

    public String toString(){
        return  this.color + "X" + Color.ANSI_RESET;
    }

    public static void main(String[] args) {
        Joueur j = new Joueur("test");
        System.out.println(j);
    }

}