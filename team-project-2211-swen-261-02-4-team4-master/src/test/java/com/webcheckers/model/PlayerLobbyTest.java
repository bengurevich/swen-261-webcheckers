package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for the model tier class playerLobby
 * Updated: 10/27/2021 4:47pm
 */
@Tag("Model-Tier")
public class PlayerLobbyTest {
    private PlayerLobby CuT;

    /**
     * Test the constructor for an empty PlayerLobby
     */
    @Test
    public void constructionTest() {
        CuT = new PlayerLobby();

        assertEquals(0,CuT.numberOfPlayers());
    }

    /**
     * Test the username and password parsing
     */
    @Test
    public void checkAndAddNameTest(){
        CuT = new PlayerLobby();
        String invalid = CuT.checkAndAddName("I will fail","password");
        String valid = CuT.checkAndAddName("Billy","password");
        String taken = CuT.checkAndAddName("Billy","password");
        String nothingAtAll = CuT.checkAndAddName("","");
        String nothing = CuT.checkAndAddName("Margret","");
        CuT.removeName("Billy");
        String badCode = CuT.checkAndAddName("Billy","drowssap");

        assertEquals("Invalid", invalid);
        assertEquals("Success", valid);
        assertEquals("Taken",taken);
        assertEquals("NothingAtAll",nothingAtAll);
        assertEquals("Nothing",nothing);
        assertEquals("BadCode",badCode);

        assertEquals(0,CuT.numberOfPlayers());
    }

    /**
     * Test the player constructor with name param as well as accessor for
     * the name field.
     */
//    @Test
//    public void iteratorTest(){
//        CuT = new PlayerLobby();
//
//        List<Player> testPlayers = new ArrayList<>();
//        testPlayers.add(new Player("Noah"));
//        testPlayers.add(new Player("Katie"));
//
//        CuT.checkAndAddName("Noah","password");
//        CuT.checkAndAddName("Katie","password");
//
//        assertSame(testPlayers.iterator(), CuT.iterator());
//    }
}