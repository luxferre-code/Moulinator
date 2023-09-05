package fr.ulille.moulinator;

import java.util.Scanner;

public class GameTypeSelector {

    public static void execute() {
        System.out.println(Color.ANSI_YELLOW + " ~ Choix du mode de jeu ~ " + Color.ANSI_RESET);
        for(GameType gameType : GameType.values()) {
            System.out.println(gameType.ordinal() + " - " + gameType.getName());
        }
        try(Scanner sc = new Scanner(System.in)) {
            System.out.print("Votre choix : ");
            int choice = sc.nextInt();
            if(choice < 0 || choice >= GameType.values().length) {
                System.out.println(Color.ANSI_RED + "Choix invalide" + Color.ANSI_RESET);
                return;
            }
            GameType gameType = GameType.values()[choice];
        }
    }

    public static void main(String[] args) {
        execute();
    }

}
