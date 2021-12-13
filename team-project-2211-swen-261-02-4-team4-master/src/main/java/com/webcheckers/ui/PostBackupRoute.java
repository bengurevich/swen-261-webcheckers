package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import com.webcheckers.util.Message;
import spark.*;


public class PostBackupRoute implements Route {
    private static final String NO_BACKUP = "No moves to backup to!";
    private static final String BACKUP = "Go back to your last valid move!";
    private static final String USER = "currentUser";
    private static final String VIEW_MODE = "viewMode";
    private GameCenter gameCenter;
    private Player redPlayer;
    private Player whitePlayer;
    private Game game;
    private Gson gson;
    private TemplateEngine templateEngine;

    /**
     * The constructor for the {@code Post /backupMove} route handler.
     * @param templateEngine the templateEngine used for rendering HTML.
     * @param gameCenter The program-wide gameCenter which tracks games.
     * @param gson the Gson
     */
    public PostBackupRoute(TemplateEngine templateEngine, GameCenter gameCenter, Gson gson){
        this.gson = gson;
        this.gameCenter = gameCenter;
        this.templateEngine = templateEngine;
    }

    /**
     * Backups up a series of attempted moves on the board to beginning of turn. Returns error
     * if no moves have been attempted when request is sent.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return gson.toJson whch tells success or ERROR
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session httpSession = request.session();
        Player user = httpSession.attribute(USER);
        game = user.getGame();
        System.out.println("You backed up your move");

        String message = game.backup();

        if(message.startsWith("ERROR")){
            return gson.toJson(Message.error(message.substring(7)));
        }
        return gson.toJson(Message.info(message));

    }
}
