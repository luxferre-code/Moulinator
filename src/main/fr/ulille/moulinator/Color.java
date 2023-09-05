package fr.ulille.moulinator;

import java.io.Serializable;

public enum Color implements Serializable {
    /**
     * La couleur qui sera considérer comme par defaut
     */
    ANSI_RESET("\u001B[0m"),

    /**
     * La couleur noir en ANSI
     */
    ANSI_BLACK("\u001B[30m"),

    /**
     * La couleur rouge en ANSI
     */
    ANSI_RED("\u001B[31m"),

    /**
     * La couleur vert en ANSI
     */
    ANSI_GREEN("\u001B[32m"),

    /** 
     * La couleur jaune en ANSI
     */
    ANSI_YELLOW("\u001B[33m"),

    /**
     * La couleur bleu en ANSI
     */
    ANSI_BLUE("\u001B[34m"),

    /**
     * La couleur violet en ANSI
     */
    ANSI_PURPLE("\u001B[35m"),

    /**
     * La couleur cyan en ANSI
     */
    ANSI_CYAN("\u001B[36m"),

    /**
     * La couleur blanc en ANSI
     */
    ANSI_WHITE("\u001B[37m");



    /**
     * String : la couleur en ANSI
     */
    private final String colorCode;

    Color(String colorCode){
        this.colorCode = colorCode;
    }


    /**
     * @return String : le nom de la couleur
     */
    public String getColor(){
        return this.name().substring(5);
        
    }

    public String toString(){
        return this.colorCode;
    }

    /**
     * @return String : Une phrase en couleur pour tester si la couleur fonctionne
     */
    public String showColor(){
        return this.colorCode + "This text is "+ getColor() + ANSI_RESET;
    }

    public static void main(String[] args) {
        Color colorCode = Color.ANSI_PURPLE;
        System.out.println(colorCode.showColor());
        System.out.println(colorCode.getColor());
    }
    
}
