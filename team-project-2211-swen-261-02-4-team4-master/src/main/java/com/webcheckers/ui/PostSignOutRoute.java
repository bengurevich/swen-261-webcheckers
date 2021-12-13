package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.PlayerLobby;
import com.webcheckers.util.Message;
import spark.*;
import java.util.HashMap;
import java.util.Map;

public class PostSignOutRoute implements Route {
    private final int SESSION_TIMEOUT_PERIOD = 600;
    static final String PLAYER_LOBBY = "pLobby";
    private final String NUM_PLAYERS = "playernum";
    private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
    private final String VIEW_NAME = "home.ftl";
    private final TemplateEngine templateEngine;
    private final String player = "currentUser";
    private final GameCenter gameCenter;

    /**
     * The constructor for the {@code post /signout} route handler
     * @param templateEngine the templateEngine for rendering HTML
     * @param gameCenter the program-wide gameCenter for tracking games
     */
    public PostSignOutRoute(TemplateEngine templateEngine, GameCenter gameCenter) {
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
    }

    /**
     * Signs player out of site if player is signed in list of players.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return null ; redirect to home page
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();
        final Player p = httpSession.attribute(player);
        final String playerName = p.getName();
        gameCenter.getPlayerLobby().removeName(playerName);
        httpSession.maxInactiveInterval(SESSION_TIMEOUT_PERIOD);
        httpSession.attribute("currentUser", null);
        httpSession.attribute(PLAYER_LOBBY, gameCenter.getPlayerLobby());
        vm.put("title", "Welcome!");
        vm.put("message", WELCOME_MSG);
        vm.put(NUM_PLAYERS, gameCenter.getPlayerLobby().numberOfPlayers());
        response.redirect("/");
        return null;
    }
}
