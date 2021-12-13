package com.webcheckers.model;

import java.util.Iterator;

/**
 * class that represents a player playing WebCheckers
 */
public class Player {

    private boolean inGame; // true or false on whether in game
    private Game game; // creates the game

    /**
     * Constructor for Player
     * @param player_name name of the player who is playing
     * @param color color pieces (Red or White)
     */

    public Player(String player_name, Color color)
    {
        this.name = player_name;
        this.color = color;
    }

    /**
     * Constructor for Player
     * @param name name of player
     */
    public Player(String name){this.name = name;}


    private String name; // name of player
    private Color color; // color associated with player (Red or White)

    /**
     * setColor sets color for player
     * @param color
     */
    public void setColor(Color color){this.color = color;}

    /**
     * getName returns name of player
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * getColor returns name of the color of the player
     * @return Color
     */
    public Color getColor(){return this.color;}

    /**
     * isInGame tells whether the player is in a checkers match
     * @return boolean
     */
    public boolean isInGame(){return inGame;}

    /**
     * setInGame sets the player into a game, making it true
     */
    public void setInGame(){inGame = true;}

    /**
     * setOutOfGame sets the player out of a game, making it false.
     */
    public void setOutOfGame(){inGame = false;}

    /**
     * leaveGame makes it so the player leaves the game, making it false.
     */
    public void leaveGame(){inGame = false;}

    /**
     * getGame returns the game that the player is in.
     * @return Game
     */
    public Game getGame(){return this.game;}

    /**
     * setGame sets the game that the player should be in based off what he controls.
     * @param game is the specific game given
     */
    public void setGame(Game game){this.game = game;}

    /**
     * setName is taking the original name and changing it to the given name.
     * @param name the new name of the player.
     */
    public void setName(String name) {
        this.name = name;
    }


}
