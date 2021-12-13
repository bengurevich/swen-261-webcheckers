package com.webcheckers.model;

import com.webcheckers.model.Player;

import java.util.*;

/**
 * class that checks for names and takes care of the lobby of players and dictates who plays.
 */
public class PlayerLobby {
    private final List<String> NamesInUse; // List of each name used, will be seen in lobby of game
    private final List<Player> Players; // List of the actual player, not just the name
    private final Map<String, String> passcodes; // passcodes stored for each individual user.

    /**
     * Constructor for PlayerLobby
     *
     */

    public PlayerLobby() {
        NamesInUse = new ArrayList<>();
        Players = new ArrayList<>();
        passcodes = new HashMap<>();
    }

    /**
     * @return String error message if check fails and name is not added, return "Success" if done
     */
    public String checkAndAddName(String Name, String password) {
        //Checks
        if (Name.equals("") && password.equals("")) {
            return "NothingAtAll";
        }
        if (!this.isNameValid(Name)) //if the name fails validity check, return false
            return "Invalid";
        if (!this.isNameUnique(Name)) //if the name fails uniqueness check, return false
            return "Taken";
        if(password.equals("")) {
            return "Nothing";
        }
        if(!passcodes.containsKey(Name))
            passcodes.put(Name,password);
        else if(passcodes.containsKey(Name) && !password.equals(passcodes.get(Name)))
            return "BadCode";



        //if we get here, name valid
        //addition
        NamesInUse.add(Name);
        Players.add(new Player(Name));
        return "Success";
    }


    /**
     * removeName which removes whatever name is given from the list.
      * @param Name
     */
    public void removeName(String Name)
    {
        NamesInUse.remove(Name);
        for(int i =0; i<Players.size(); i++)
        {
            if (Name.equals(Players.get(i).getName()))
            {
                Players.remove(i);
                return;
            }
        }
    }

    /**
     * isNameValid method to check whether name can be used.
     * @param Name the name in question that is being checked.
     * @return boolean
     */

    private boolean isNameValid(String Name) {
        //if name does NOT contain alphanumeric chars, return false,
        return this.containsAlphanumeric(Name);
    }

    /**
     * containsAlphanumeric helper function for isNameValid
     * @param str the name being checked for validity
     * @return boolean
     */

    private boolean containsAlphanumeric(String str) {
        return (!str.equals("")) && (str.matches("^[a-zA-Z0-9]*$")); // "^" - beginning of line | "*" - matches zero or more occurrences | "$" - end of the line
    }

    /**
     * isNameUnique method to check for uniqueness.
     * @param Name the name being checked
     * @return boolean
     */
    private boolean isNameUnique(String Name) {
        return !NamesInUse.contains(Name);
    }

    /**
     * numberOfPlayers method to see how many players are in the list
     * @return int
     */
    public int numberOfPlayers() {
        return NamesInUse.size();
    }

    /**
     * getNamesInUse gives the String list
     * @return String List
     */
    public List<String> getNamesInUse() {return this.NamesInUse;}


    /**
     * getPlayer method which gets the player based off the name given.
     * @param name
     * @return Player
     */
    public Player getPlayer(String name)
    {
        for(int i =0; i<Players.size(); i++)
        {
            if(Players.get(i).getName().equals(name))
            {
                return Players.get(i);
            }
        }
        return null;
    }


    /**
     * iterator which iterates through the list of players
     * @return Iterator
     */

    public Iterator<Player> iterator() {
        return Players.iterator();
    }
}