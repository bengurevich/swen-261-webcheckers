package com.webcheckers.application;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.PlayerLobby;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class that helps control the games being played and manages who goes into each.
 */
public class GameCenter {
    private HashMap<Integer, Game > GameList = new HashMap<>(); // HashMap for the list of games and what game it is
    private PlayerLobby pLobby; // playerLobby needed for managing who is eligible to play.
    private ArrayList<Player> Playing_players; // not used
    public static Integer id; // id needed for specific game key


    /**
     * Constructor for the GameCenter
     */
    public GameCenter() {
        pLobby = new PlayerLobby();
        Playing_players = new ArrayList<>();
        id = 0;
    }


    public HashMap<Integer, Game> getGamesList() {
        return GameList;
    }

    public void listRemove(Game game)
    {
        GameList.remove(game.getGameId());
    }

    /**
     * getPlayerLobby returns the current lobby
     * @return playerLobby
     */
    public PlayerLobby getPlayerLobby(){return this.pLobby;}

    /**
     * createGame creates a game for the players and can be managed.
     * @param you current user that is playing.
     * @param opponent opponent player that will be playing
     * @return game
     */
    public Game createGame(Player you, Player opponent){
        Game game = new Game(you, opponent, id);
        GameList.put(id,game);
        id++;
        return game;
    }

    /**
     * getGame based off the game id given
     * @param id random integer assigned to a game
     * @return specific game
     */
    public Game getGame(Integer id){
        return GameList.get(id);
    }






}
