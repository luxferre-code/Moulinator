package fr.ulille.moulinator;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;

public class Starting {


    public static void main(String[] args) throws InterruptedException, IOException {
        String fichier = "resources/Logo.txt";
        
        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            ArrayList<String> texte = new ArrayList<>();
            while ((ligne = br.readLine()) != null) {
                texte.add(ligne);
            }
            int taille = 0 ;
            for (int i = 0; i< texte.size(); i = i+1){
                if(taille< i){
                    taille = texte.get(i).length();
                }
            }
            for (int i = 0; i<taille; i =i + 1){
                for (int j = 0; j< texte.size(); j = j+1){
                    if (texte.get(j).length() >= i){
                        System.out.println(texte.get(j).substring(0, i));
                    } else {
                        System.out.println(texte.get(j));
                    }
                }
                Thread.sleep(50);
                System.out.print("\033[H\033[2J");  
                System.out.flush();

            }
        } catch (IOException ignored) {}
        Menu.execute();
    }


}
