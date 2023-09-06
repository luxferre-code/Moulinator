package fr.ulille.moulinator;

public final class OptionsMenu {

    /*
     * Nombre maximum de billes par Joueur
     * Nombre minimum avant defaite
     * Debug mode (affichage de toutes les actions)
     */

    public static void execute() {
        String base = Color.ANSI_GREEN + "~ Config Menu ~\n" +
                            Color.ANSI_BLUE + "1 - Max slots for user (value: " + Color.ANSI_YELLOW + Game.maxBilles + Color.ANSI_BLUE + ")\n" +
                            Color.ANSI_BLUE + "2 - Min slots for user before death (value: " + Color.ANSI_YELLOW + Game.minBilles + Color.ANSI_BLUE +")\n" +
                            Color.ANSI_BLUE + "3 - Debug mod (value: " + Color.ANSI_YELLOW + (Game.debugMod ? "yes" : "no") + Color.ANSI_BLUE +")\n" +
                            "4 - Main menu" + Color.ANSI_RESET;
        System.out.println(base);
        int s = -1;
        do {
            String rep = Game.SCANNER.next();
            try {
                s = rep.charAt(0) - '0';
            }catch(Exception ignored) {}
        }while(s <= 0 || s > 4);

        switch(s) {
            case 1 -> Game.maxBilles = newValue(Game.minBilles, 11);
            case 2 -> Game.minBilles = newValue(1, Game.maxBilles - 1);
            case 3 -> Game.debugMod = newBoolean();
        }
        if(s == 4) return;
        execute();
    }

    public static int newValue(int min, int max) {
        int newV = -1;
        do {
            System.out.print(Color.ANSI_PURPLE + "New value (between " + min + " and " + max + "): ");
            newV = Game.SCANNER.nextInt();
        } while(newV < min || newV > max);
        return newV;
    }

    public static boolean newBoolean() {
        while(true) {
            System.out.print(Color.ANSI_PURPLE + "New value (yes or no): ");
            String s = Game.SCANNER.next();
            if(s.equals("yes")) return true;
            else if(s.equals("no")) return false;
        }
    }

    /*
     * ~ Config menu ~
     * 1 - Max slots for user (value: X)
     * 2 - Min slots for user before death (value: X)
     * 3 - Debug mod (value: X)
     */

    public static void main(String[] args) {
        OptionsMenu.execute();
    }

}