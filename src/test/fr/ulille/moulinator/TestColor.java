package fr.ulille.moulinator;

import fr.ulille.moulinator.enums.Color;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
        assertEquals("RED",colorCodeRed.getColor());
        assertEquals("BLACK",colorCodeBlack.getColor());
        assertEquals("GREEN",colorCodeGreen.getColor());
        assertEquals("YELLOW",colorCodeYellow.getColor());
        assertEquals("BLUE",colorCodeBlue.getColor());
        assertNotEquals("RED",colorCodeBlack.getColor());
    }

    @Test
    public void testShowColor(){
        assertEquals("\u001B[31mThis text is RED\u001B[0m",colorCodeRed.showColor());
        assertEquals("\u001B[30mThis text is BLACK\u001B[0m",colorCodeBlack.showColor());
        assertEquals("\u001B[32mThis text is GREEN\u001B[0m",colorCodeGreen.showColor());
        assertEquals("\u001B[33mThis text is YELLOW\u001B[0m",colorCodeYellow.showColor());
        assertEquals("\u001B[34mThis text is BLUE\u001B[0m",colorCodeBlue.showColor());
        assertNotEquals("\u001B[31mThis text is RED\u001B[0m",colorCodeBlack.showColor());

    }


    
}
