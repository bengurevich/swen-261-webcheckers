package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Player;
import spark.*;

/**
 * class used for spectator and stopping the session for that spectator.
 */
public class GetSpecStopRoute implements Route {

    private final GameCenter gameCenter; // gameCenter initialized
    private TemplateEngine templateEngine; // template engine used for spectator
    private final String USER = "currentUser"; // currentUser is the current person who wants to spectate.

    /**
     * Constructor for GetSpecStopRoute
     * @param templateEngine templateEngine for spectator
     * @param gameCenter gameCenter used for that specific game.
     */
    public GetSpecStopRoute(TemplateEngine templateEngine, GameCenter gameCenter)
    {
        this.gameCenter = gameCenter;
        this.templateEngine = templateEngine;
    }

    /**
     * handle method that takes the spectator out of the game.
     * @param request request needed for the specific session
     * @param response the response will take the spectator to the home page
     * @return null
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session httpSession = request.session();
        Player user = httpSession.attribute(USER);
        user.setOutOfGame();
        response.redirect("/");
        return null;
    }
}
