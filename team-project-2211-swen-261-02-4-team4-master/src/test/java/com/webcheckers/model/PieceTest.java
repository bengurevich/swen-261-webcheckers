package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Author: Ryan Wolford
 * This is a test class for the model tier class Piece
 */
@Tag("Model-Tier")
class PieceTest {
    private Piece CuT;

    /**
     * Tests the ability to correctly get the Type of the piece for each of its potential values
     */
    @Test
    public void testGetType(){
        CuT = new Piece(Piece.type.KING, Color.RED);
        assertEquals(Piece.type.KING, CuT.getType());
        CuT = new Piece(Piece.type.SINGLE, Color.RED);
        assertEquals(Piece.type.SINGLE, CuT.getType());
    }

    /**
     * Tests the ability to correctly get the Color of the piece for each of its potential values
     */
    @Test
    public void testGetColor(){
        CuT = new Piece(Piece.type.KING, Color.RED);
        assertEquals(Color.RED, CuT.getColor());
        CuT = new Piece(Piece.type.KING, Color.WHITE);
        assertEquals(Color.WHITE, CuT.getColor());
    }

    /**
     * Tests the ability to correctly get the State of the piece as well as the functionality of the
     * killPiece() function in the Piece class.
     */
    @Test
    public void testState(){
        CuT = new Piece(Piece.type.KING, Color.RED);
        assertEquals(Piece.State.Alive, CuT.getState());
        CuT.killPiece();
        assertEquals(Piece.State.Dead, CuT.getState());
    }
}

