import java.util.Scanner;
import java.io.*;

public class Menu{
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
        System.out.println(sb.toString());
        Scanner sc = new Scanner(System.in);
        String str="0";
        while(!str.equals("1") && !str.equals("2") && !str.equals("3")){
            System.out.println("Choose a Gamemode: \n1. Play with a player \n2. Play with a bot \n3. Bot vs Bot");
            str = sc.next(); 
        }
        if(str.equals("1")){
            System.out.println("Lance un 1V1");
        }
        else{
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
            else{
                str="0";
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
        }
    }
    public static void main(String[] args) throws IOException{
    Menu  m= new Menu();
    m.choose();
    }
}

