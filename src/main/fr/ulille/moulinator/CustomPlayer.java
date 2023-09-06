package fr.ulille.moulinator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomPlayer {

    public static Joueur makePlayer() {
        Joueur j;
        Scanner sc = Game.SCANNER;
        System.out.println("What is player 1's name : ");
        String nom = sc.next();
        String color = "";
        System.out.println("Choose a color :");
        List<Integer> colors = new ArrayList<>();
        for(Color c : Color.values()) {
            if(!c.isUsed()) {
                System.out.println(c.ordinal() + " -> " + c.toString() + c.getColor() + Color.ANSI_WHITE);
                colors.add(c.ordinal());
            }
        }
        boolean choisie = false;
        while(!choisie) {
            System.out.println("Choose a color between the following or press z to go back to the name choice:");
            color = sc.next();
            if(color.contains("z")){
                return CustomPlayer.makePlayer();
            } 
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