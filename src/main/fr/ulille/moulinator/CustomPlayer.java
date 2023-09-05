package fr.ulille.moulinator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomPlayer {

    public static Joueur makePlayer() {
        Joueur j;
        Scanner sc = new Scanner(Game.INPUT_STREAM);
        System.out.println("Quel est le nom du joueur ? ");
        String nom = sc.next();
        String color = "";
        System.out.println("Maintenant choisis une couleur:");
        List<Integer> colors = new ArrayList<>();
        for(Color c : Color.values()) {
            if(!c.isUsed()) {
                System.out.println(c.ordinal() + " -> " + c.toString() + c.getColor() + Color.ANSI_RESET);
                colors.add(c.ordinal());
            }
        }
        boolean choisie = false;
        while(!choisie) {
            System.out.println("Choisissez une couleur parmis celles-ci:");
            color = sc.next();
            if(colors.contains(Integer.parseInt(color))) {
                choisie = true;
            } else {
                System.out.println("Invalid color");
            }
        }
        Color.values()[Integer.parseInt(color)].setUsed(true);
        j = new Joueur(nom, Color.values()[Integer.parseInt(color)]);
        return j;
    }
    
    public static void main(String[] args){
        System.out.println(makePlayer());
        System.out.println(makePlayer());
    }
}