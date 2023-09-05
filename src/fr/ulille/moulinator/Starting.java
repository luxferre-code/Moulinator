// package fr.ulille.moulinator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

    import java.io.BufferedReader;

public class Starting {


    public static void main(String[] args) throws InterruptedException, IOException {
        String fichier = "Logo.txt"; 
        
        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            
            while ((ligne = br.readLine()) != null) {
                System.out.println(ligne);
                Thread.sleep(50);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Menu m = new Menu();
        m.choose();
    }


}
