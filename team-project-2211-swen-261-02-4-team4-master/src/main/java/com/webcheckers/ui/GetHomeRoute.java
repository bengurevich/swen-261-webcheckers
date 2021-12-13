package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.model.PlayerLobby;
import spark.*;

import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
  static final String PLAYER_LOBBY = "pLobby";
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());
  private final int SESSION_TIMEOUT_PERIOD = 600;

  static final String NAME_PARAM = "name";
  private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");

  private final String NUM_PLAYERS = "playernum";
  private GameCenter gameCenter;
  private final String PLAYERS = "players";
  private final TemplateEngine templateEngine;


  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, GameCenter gameCenter) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    //
    this.gameCenter = gameCenter;
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    final Session httpSession = request.session();
    Map<String, Object> vm = new HashMap<>();
    if (httpSession.attribute("currentUser") != null) {
      String playerName = httpSession.attribute("playerName");
      Message message = Message.info("Welcome " + playerName + " to the world of online checkers");
      vm.put("name", NAME_PARAM);
      vm.put("currentUser",httpSession.attribute("currentUser"));
      vm.put("message", message);
      vm.put("title", "Welcome!");
      vm.put(PLAYERS, gameCenter.getPlayerLobby().getNamesInUse());
      vm.put("playerName", playerName);
      //System.out.println("Ploby:" + pLobby.NamesInUse);
      Player into_game = gameCenter.getPlayerLobby().getPlayer(playerName);
      if(into_game.isInGame())
      {
        response.redirect("/game");
        return null;
      }
      else
      return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }
    //if not logged in
    LOG.finer("GetHomeRoute is invoked.");

    vm.put("title", "Welcome!");
    // display a user message in the Home page
    vm.put("message", WELCOME_MSG);
    vm.put(NUM_PLAYERS, gameCenter.getPlayerLobby().numberOfPlayers());
    httpSession.attribute(PLAYER_LOBBY, gameCenter.getPlayerLobby());
    httpSession.maxInactiveInterval(SESSION_TIMEOUT_PERIOD);

    // render the View
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }
}
