package com.webcheckers.model;

/**
 * Enum for different game viewModes. PLAY and SPECTATOR are implemented.
 */
public enum ViewMode {
    PLAY, // play mode, so this is the view of a player playing a game
    SPECTATOR, // spectator mode, so this user cannot change the game,
    // but can watch any games and can switch from lobby to lobby.
    REPLAY
}
