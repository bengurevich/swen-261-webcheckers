package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


import com.google.gson.Gson;

import com.webcheckers.application.GameCenter;

import com.webcheckers.model.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
import spark.Session;

import com.webcheckers.util.Message;

/**
 * class that allows for the two players and any spectators to join the game session.
 */
public class GetGameRoute implements Route{

    static final String VIEW_NAME = "game.ftl"; // route for the game

    private final TemplateEngine templateEngine; // template engine needed for game


    private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers."); // welcome message given to every player and spectator

    private final String ACTIVE = "activeColor"; // active color is the current player

    static final String NAME_PARAM = "name"; // name of the current user

    private final String VIEW_MODE = "viewMode"; // the view mode that the user is in

    private final String USER = "currentUser"; // current user is the user

    private final String RED_PLAYER = "redPlayer"; // redPlayer is the redPlayer for this game

    private final String WHITE_PLAYER = "whitePlayer"; //whitePlayer is the white player for this game

    private final String BOARD = "board"; // board is the specific board for this game

    private final String CHOSEN_PLAYER = "challenge"; // challenge is seeing which player was challenged by current user
    private final GameCenter gameCenter; // gamecenter class initialized
    private final PlayerLobby pLobby; //unused

    private Gson gson; // gson for the game
    private Game game; // creates the game class

    /**
     * The constructor for the {@code GET /signin} route handler.
     *
     * @param templateEngine
     *    The {@link TemplateEngine} used for rendering page HTML.
     * @param gameCenter
     *    The program-wide gameCenter which keeps track of games and players.
     * @param gson
     *    The Gson used.
     */

    GetGameRoute(final TemplateEngine templateEngine, GameCenter gameCenter, Gson gson){


        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.templateEngine = templateEngine;

        this.gson = gson;

        this.gameCenter = gameCenter;
        this.pLobby = gameCenter.getPlayerLobby();

    }

    /**
     * The handle function for GetGameRoute.
     * Depending on conditions, may create a new game session, put challenged players into
     * already created sessions, update game sessions, etc.
     * @param request request body for GET request.
     * @param response response body
     * @return Model View render
     */
    @Override
    public String handle(Request request, Response response) {
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();
        httpSession.attribute(NAME_PARAM, NAME_PARAM);
        Player youPlayer = httpSession.attribute(USER); // originally "YOU"

        if(!youPlayer.isInGame()) { // handles creating a new game between two players



            final String opponentPlayerName = request.queryParams(CHOSEN_PLAYER);
            Player opponentPlayer = gameCenter.getPlayerLobby().getPlayer(opponentPlayerName);

            if(opponentPlayer.isInGame())
            {
                vm.put("message", new Message("Player already in Game", Message.Type.ERROR));
                response.redirect("/");
                return null;
            }

            game = gameCenter.createGame(youPlayer, opponentPlayer);
            game.setActiveColor(Color.RED);
            youPlayer.setInGame();
            youPlayer.setGame(game);
            youPlayer.setColor(Color.RED);
            opponentPlayer.setGame(game);
            opponentPlayer.setInGame();
            opponentPlayer.setColor(Color.WHITE);

        }
        else{
            if (youPlayer.getGame() == null) { // if player resigned
                String playerName = httpSession.attribute("playerName");
                Message message = Message.info("Welcome " + playerName + " to the world of online checkers");
                vm.put("name", NAME_PARAM);
                vm.put("currentUser",httpSession.attribute("currentUser"));
                vm.put("message", message);
                vm.put("title", "Welcome!");
                vm.put("players", gameCenter.getPlayerLobby().getNamesInUse());
                vm.put("playerName", playerName);
                youPlayer.leaveGame();
                response.redirect("/");
                return templateEngine.render(new ModelAndView(vm , "home.ftl"));
            }
            game = youPlayer.getGame();
        }

        vm.put("title", "Welcome!");
        vm.put(USER, youPlayer);
        vm.put(VIEW_MODE, ViewMode.PLAY);
        vm.put(BOARD, game.getGameBoard());
        vm.put(RED_PLAYER, game.getRedPlayer());
        vm.put(WHITE_PLAYER, game.getWhitePlayer());
        vm.put(ACTIVE, game.getActiveColor());
        game.isOver();
        if (game.getWinner() != null || game.isTied()){
            final Map<String, Object> modeOptions = new HashMap<>(2);
            modeOptions.put("isGameOver", true);
            modeOptions.put("gameOverMessage", game.getGameOverMessage());
            vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
            youPlayer.setGame(null);
            youPlayer.leaveGame();
        }
        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));



    }

}
