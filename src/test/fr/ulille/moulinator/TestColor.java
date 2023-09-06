package fr.ulille.moulinator;

import fr.ulille.moulinator.enums.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestColor {
        Color colorCodeRed;
        Color colorCodeBlack;
        Color colorCodeGreen;
        Color colorCodeYellow;
        Color colorCodeBlue;

    @BeforeEach
    public void createColor() {
        colorCodeRed = Color.ANSI_RED;
        colorCodeBlack = Color.ANSI_BLACK;
        colorCodeGreen = Color.ANSI_GREEN;
        colorCodeYellow = Color.ANSI_YELLOW;
        colorCodeBlue = Color.ANSI_BLUE;
    }
    @Test
    public void testGetColor(){
        Assertions.assertEquals("RED",colorCodeRed.getColor());
        Assertions.assertEquals("BLACK",colorCodeBlack.getColor());
        Assertions.assertEquals("GREEN",colorCodeGreen.getColor());
        Assertions.assertEquals("YELLOW",colorCodeYellow.getColor());
        Assertions.assertEquals("BLUE",colorCodeBlue.getColor());
        Assertions.assertNotEquals("RED",colorCodeBlack.getColor());
    }

    @Test
    public void testShowColor(){
        Assertions.assertEquals("\u001B[31mThis text is RED\u001B[0m",colorCodeRed.showColor());
        Assertions.assertEquals("\u001B[30mThis text is BLACK\u001B[0m",colorCodeBlack.showColor());
        Assertions.assertEquals("\u001B[32mThis text is GREEN\u001B[0m",colorCodeGreen.showColor());
        Assertions.assertEquals("\u001B[33mThis text is YELLOW\u001B[0m",colorCodeYellow.showColor());
        Assertions.assertEquals("\u001B[34mThis text is BLUE\u001B[0m",colorCodeBlue.showColor());
        Assertions.assertNotEquals("\u001B[31mThis text is RED\u001B[0m",colorCodeBlack.showColor());

    }
}
