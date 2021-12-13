package com.webcheckers.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-Tier")
public class SpaceTest { //TODO remake this entire test
    private static final int Space_int = 0;
    private static Piece pieces;
    private Space spaces;

    @BeforeAll
    public static void setupMocks() {
        // internal model mocks
        pieces = mock(Piece.class);
    }

//    @Test
//    public void isValidTest() {
//        Space testSpace = new Space(Space_int, Space.Status.VACANT);
//
//        assertTrue(testSpace.isAllowed());
//    }

//    @Test
//    public void isNotValidTest() {
//        Space testSpace = new Space(pieces, Space_int);
//
//        assertFalse(testSpace.isAllowed());
//    }

//    @Test
//    public void isNotOccupiedTest() {
//        Space testSpace = new Space(Space_int, Space.Status.VACANT);
//
//        assertFalse(testSpace.isTaken());
//    }


//    @Test
//    public void isOccupiedTest() {
//        Space testSpace = new Space(pieces, Space_int);
//
//        assertTrue(testSpace.isTaken());
//    }

//    @Test
//    public void validPieceTest() {
//        Space testSpace = new Space(Space_int, Space.Status.VACANT);
//
//        assertTrue(testSpace.isAllowed());
//    }


//    @Test
//    public void removeAPieceTest() {
//        Space testSpace = new Space(pieces, Space_int);
//
//        testSpace.removeCurrentPiece();
//
//        assertEquals(Space.Status.VACANT, testSpace.getState());
//        assertNull(testSpace.getCurrentPiece());
//    }

//    @Test
//    public void movePieceTest() {
//        Space originSpace = new Space(pieces, Space_int);
//        Space testSpace = new Space(Space_int, Space.Status.VACANT);
//
//        assertTrue(testSpace.moveTo(originSpace));
//    }
//
}
