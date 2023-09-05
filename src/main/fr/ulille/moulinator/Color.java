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


    private final String colorCode;

    Color(String colorCode){
        this.colorCode = colorCode;
    }


    public String getColor(){
        return this.name().substring(5);
        
    }

    public String toString(){
        return this.colorCode +"";
    }
    public String showColor(){
        return this.colorCode + "This text is "+ getColor() + ANSI_RESET;
    }

    public static void main(String[] args) {
        Color colorCode = Color.ANSI_PURPLE;
        System.out.println(colorCode.showColor());
        System.out.println(colorCode.getColor());
    }
    
}
