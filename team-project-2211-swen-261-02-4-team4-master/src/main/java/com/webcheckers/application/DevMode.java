package com.webcheckers.application;

/**
 * An enum used for the different dev modes that we want
 * to showcase during our Sprint presentations
 */
public enum DevMode {
    CHAINING, // chaining demonstrates multi jumps ingame
    KING, // demonstrates the process of a single turning into a king piece
    KING_CHAINING, // demonstrates a king doing multiple jumps
    ENDGAME, // endgame as where one player wins the game
    TIE, // a tie where after 40 moves a tie is called
    EMPTY, // unused
    NONE; // regular game and used for release
}
//starting sprint 4