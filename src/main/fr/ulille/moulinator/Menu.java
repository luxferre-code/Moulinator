package fr.ulille.moulinator;

import java.util.Scanner;
import java.io.*;

public class Menu {
    public void choose() throws IOException{
        File f=new File("Logo.txt");
        FileReader fr= new FileReader(f);
        BufferedReader br = new BufferedReader(fr);  
        StringBuffer sb = new StringBuffer();    
        String line;
        while((line = br.readLine()) != null){
            sb.append(line);      
            sb.append("\n");     
        }
        fr.close();
        System.out.println(sb);
        Scanner sc = new Scanner(System.in);
        String str="0";
        while(!str.equals("1") && !str.equals("2") && !str.equals("3") && !str.equals("4"  )  && !str.equals("5")){
            System.out.println("Choose a Gamemode: \n1. Play with a player \n2. Play with a bot \n3. Bot vs Bot \n4. Regles du jeu \n5. Quitter");
            str = sc.next(); 
        }
        if(str.equals("1")){
            System.out.println("Lance un 1V1");
        }
            if(str.equals("2")){
                str="0";
                while(!str.equals("1") && !str.equals("2")){
                    System.out.println("Choose the bot: \n1. Easy bot \n2. Hard bot");
                    str = sc.next(); 
                }
                if(str.equals("1")){
                    System.out.println("Bot simple");
                }
                else{
                    System.out.println("Bot hard");
                }
            }
            if (str.equals("3")) {
                while(!str.equals("1") && !str.equals("2")){
                    System.out.println("Choose the bots: \n1. Easy bots \n2. Hard bots");
                    str = sc.next(); 
                }
                if(str.equals("1")){
                    System.out.println("Bots simple");
                }
                else{
                    System.out.println("Bots3 hard");
                    }
            } 
            if(str.equals("4")){
                System.out.println("Regles du jeu");
                File f2= new File("regles.txt");
                FileReader fr2= new FileReader(f2);
                BufferedReader br2 = new BufferedReader(fr2);     
                String line2;
                while((line2 = br2.readLine()) != null){
                    System.out.println(line2);
                }
                fr2.close();
            }
            
            if(str.equals("5")){
                    System.out.println("Au revoir");
                    System.exit(0);
            }
        
    }

    public static void main(String[] args) throws IOException{
    Menu  m= new Menu();
    m.choose();
    }
}

