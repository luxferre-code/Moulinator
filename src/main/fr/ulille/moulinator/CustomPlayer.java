package fr.ulille.moulinator;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomPlayer{
    public static void execute(){
        Joueur p1,p2;
        Scanner sc=new Scanner(System.in);
        System.out.println("Quel est le nom du premier joueur?");
        String nom=sc.next();
        String color="";
        System.out.println("Maintenant choisis une couleur:");
        Color[] couleurs=Color.values();
        ArrayList<Color> colors=new ArrayList<>();
        for(int i=1;i<couleurs.length;i++){
            colors.add(couleurs[i]);
        }
        boolean choisie=false;
        while(!choisie){
            System.out.println("Choisissez une couleur parmis celles-ci:");
            for(int i=0;i<colors.size();i++){
                System.out.println(colors.get(i).toString()+(i +1)+ ". " +colors.get(i).getColor());
            }   
            color=sc.next();
            if(Integer.parseInt(color)>=1 && Integer.parseInt(color)<=colors.size()){
                choisie=true;
            }
        }
        p1=new Joueur(nom,colors.get(Integer.parseInt(color)-1));
        colors.remove(Integer.parseInt(color)-1);
        choisie=false;
        System.out.println("Quel est le nom du deuxième joueur?");
        nom=sc.next();
        color="";
        System.out.println("Maintenant choisis une couleur:");
        while(!choisie){
            System.out.println("Choisissez une couleur parmis celles-ci:");
            for(int i=0;i<colors.size();i++){
                System.out.println(colors.get(i).toString()+(i +1)+ ". " +colors.get(i).getColor());
            }   
            color=sc.next();
            if(Integer.parseInt(color)>=1 && Integer.parseInt(color)<=colors.size()){
                choisie=true;
            }
        }
        p2=new Joueur(nom,colors.get(Integer.parseInt(color)-1));
        System.out.println(p1.toString() + p2.toString());
    }
    public static void main(String[] args){
        execute();
    }
}