package fr.ulille.moulinator;

public enum Color {
    ANSI_RESET("\u001B[0m"),
    ANSI_BLACK("\u001B[30m"),
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_CYAN("\u001B[36m"),
    ANSI_WHITE("\u001B[37m"),
    RESET("\u001B[0m");


    private final String colorCode;

    Color(String colorCode){
        this.colorCode = colorCode;
    }


    public String getColor(){
        return this.colorCode;
    }

    public String toString(){
        return this.colorCode +"";
    }
    public void showColor(){
        System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);
    }

    public static void main(String[] args) {
        System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);
    }
    
}
