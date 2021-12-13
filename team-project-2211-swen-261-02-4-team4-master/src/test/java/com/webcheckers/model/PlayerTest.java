package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test class for the model tier class Player
 * Updated: 10/27/2021 3:09pm
 */
@Tag("Model-Tier")
public class PlayerTest {
    private Player CuT;

    /**
     * Test the player constructor with name param as well as accessor for
     * the name field.
     */
    @Test
    public void nameConstructTest() {
        CuT = new Player("Charlie");
        assertEquals(CuT.getName(), "Charlie");
    }

    /**
     * Test the basic player constructor with a name param and a color param
     * as well as accessors for both fields.
     */
    @Test
    public void nameAndColorConstructTest() {
        CuT = new Player("Bobby", Color.RED);
        assertEquals("Bobby",CuT.getName());
        assertEquals(Color.RED, CuT.getColor());
    }

    /**
     * Test the setName function.
     */
    @Test
    public void setNameTest() {
        CuT = new Player("Null :^)");
        CuT.setName("Tyler");

        assertEquals("Tyler",CuT.getName());
    }

    /**
     * Test multiple calls of the setName function.
     */
    @Test
    public void multSetNameTest() {
        CuT = new Player("Null :^)");
        CuT.setName("Keith");
        CuT.setName("Lily");

        assertEquals("Lily",CuT.getName());
    }

    /**
     * Test the setColor function.
     */
    @Test
    public void setColorTest() {
        CuT = new Player("Null :^)", Color.RED);
        CuT.setColor(Color.WHITE);

        assertEquals(Color.WHITE, CuT.getColor());
    }

    /**
     * Test multiple calls of the setColor function.
     */
    @Test
    public void multSetColorTest() {
        CuT = new Player("Null :^)", Color.RED);
        CuT.setColor(Color.WHITE);
        assertEquals(Color.WHITE, CuT.getColor());

        CuT.setColor(Color.RED);
        assertEquals(Color.RED, CuT.getColor());
    }
}