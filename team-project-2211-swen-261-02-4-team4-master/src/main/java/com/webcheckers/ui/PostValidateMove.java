package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.MoveValidator;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
import spark.Session;

import java.util.HashMap;
import java.util.Map;

public class PostValidateMove implements Route{


    static final String NAME_PARAM = "name";
    private final String VIEW_MODE = "viewMode";
    private final String USER = "currentUser";
    private final String CHOSEN_PLAYER = "challenge";

    private final TemplateEngine templateEngine;
    private final Gson gson;
    private final GameCenter gameCenter;

    /**
     * The constructor for the {@code post /validateMove} route handler.
     * @param templateEngine the templateEngine for rendering HTML
     * @param gameCenter The program-wide gameCenter which tracks games
     * @param gson the Gson for messages.
     */
    public PostValidateMove(TemplateEngine templateEngine, GameCenter gameCenter, Gson gson) {
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        this.gson = gson;
    }

    /**
     * Validates moves that are attempted on the board. Returns Gson for success or ERROR.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return Gson.toJson
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session httpSession = request.session();
        final Player player = httpSession.attribute("currentUser");

        Game game = player.getGame();


        // getting session information and the proper game

        final Map<String, Object> vm = new HashMap<>();
        httpSession.attribute(GetHomeRoute.PLAYER_LOBBY, gameCenter.getPlayerLobby());
        httpSession.attribute(NAME_PARAM, NAME_PARAM);
        vm.put(VIEW_MODE, ViewMode.PLAY); // Need to pass in 3 players

        Player youPlayer = httpSession.attribute(USER); // originally "YOU"
        final String opponentPlayerName = request.queryParams(CHOSEN_PLAYER);



        Move move = gson.fromJson(request.queryParams("actionData"), Move.class);


        //System.out.println("\n Move input:: \n    " +
        //        "start: (" + move.getStartPos().getCell()+ ","+ move.getStartPos().getRow() + ")"+"\n    " +
        //       "end:   ("+move.getEndPos().getCell()+ ","+ move.getEndPos().getRow() + ")");

        //todo implement checking a move
        String message = game.takeTurn(move);
        if(message.startsWith("ERROR")){
            return gson.toJson(Message.error(message.substring(7)));
        }
        return gson.toJson(Message.info(message));

        /**
        if (MoveValidator.isOneDiagonal(move)) // is a one diagonal move
            return gson.toJson(Message.info(" ~test~ valid"));
        else
            return gson.toJson(Message.error(" ~test~ not valid"));
         */
    }

}
