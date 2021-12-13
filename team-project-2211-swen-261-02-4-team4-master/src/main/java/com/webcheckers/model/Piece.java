package com.webcheckers.model;


/**
 * class that represents a piece  on the checkers board
 */
public class Piece {

    /**
     * An enum to determine whether the piece is a regular piece or a king type.
     */
    public enum type
    {
        SINGLE, KING
    }

    /**
     * An enum to determine whether the piece is alive or dead.
     */
    public enum State{
        Alive, Dead
    }



    private final type Type; // type of piece (king or normal)
    private final Color Color; // color of that piece (red or white)
    private State state; // state of the piece (dead or alive)

    /**
     * Constructor for the starting Piece class
     * @param Type type of piece (King or Single)
     * @param Color color of that piece (Red or White)
     * state will always initially be alive upon start of game.
     */
    public Piece(type Type, Color Color) {
        this.Type = Type;
        this.Color = Color;
        this.state = State.Alive;

    }

    /**
     * Constructor for Piece
     * @param Type type of piece (King or Single)
     * @param Color color of that piece (Red or White)
     * @param state state of that piece whether dead or alive
     */
    public Piece(type Type, Color Color, State state) {
        this.Type = Type;
        this.Color = Color;
        this.state = state;

    }

    /**
     * killPiece takes away that specific piece and gets rid of it from the board.
     */
    public void killPiece(){this.state = State.Dead;}

    /**
     * getType returns the type of that specific piece.
     * @return type
     */
    public type getType(){return this.Type;}

    /**
     * getColor returns the color for that piece
     * @return Color
     */
    public Color getColor(){return this.Color;}

    /**
     * getState returns the state of the piece
     * @return State
     */
    public State getState(){return this.state;}

    /**
     * takes the piece and copies all of its inherit traits into another piece.
     * @return Piece
     */
    public Piece copyPiece(){ return new Piece(getType(), getColor(), getState()); }


}


