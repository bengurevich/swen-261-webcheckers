package com.webcheckers.ui;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.Color;
import com.webcheckers.util.Message;
import spark.*;

import static spark.route.HttpMethod.get;


public class PostResignRoute implements Route{
    static final String RESIGN = "You have resigned, better luck next time.";

    private Message message;
    private Gson gson;
    private TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    /**
     * The constructor for the {@code post /resignGame} route handler.
     * @param templateEngine the templateEngine for rendering HTML
     * @param gameCenter the program-wide gameCenter for tracking games.
     * @param gson the Gson
     */

    public PostResignRoute(TemplateEngine templateEngine, GameCenter gameCenter, Gson gson) {
        this.templateEngine = templateEngine;
        this.gson = gson;
        this.gameCenter = gameCenter;
    }

    /**
     * Resigns player from game and displays message that player has resigned. Ends game.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return gson.toJson message
     * @throws Exception
     */

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session httpSession = request.session();
        final Player player = httpSession.attribute("currentUser");
        Game game = player.getGame();
        Player opponent = game.getOtherPlayer(player);

        if (game.declareWinner(opponent)){
            player.setGame(null);
            final Message message = Message.info("You resigned from the game.");
            return gson.toJson(message);
        }
        final Message message = Message.error("You cannot resign.");
        return gson.toJson(message);

    }
}
