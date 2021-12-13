package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Game;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
import spark.Session;

import com.webcheckers.util.Message;

public class GetSignInRoute implements Route{

    static final String VIEW_NAME = "signin.ftl";
    static final String HOME_VIEW = "home.ftl";
    private static final Message WELCOME_MSG = Message.info("Please Login");
    private final TemplateEngine templateEngine;
    private GameCenter gameCenter;


    /**
     * The constructor for the {@code GET /signin} route handler.
     *
     * @param templateEngine
     *    The {@link TemplateEngine} used for rendering page HTML.
     */
    GetSignInRoute(final TemplateEngine templateEngine, GameCenter gameCenter){

        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
    }

    /**
     * Renders the sign-in page.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML signin.ftl
     */
    @Override
    public String handle(Request request, Response response) {
        final Session httpSession = request.session();

        final Map<String, Object> vm = new HashMap<>();
        if (httpSession.attribute("currentUser") != null) {
            String playerName = httpSession.attribute("playerName");
            Message message = Message.info("Welcome " + playerName + " to the world of online checkers");
            vm.put("currentUser",httpSession.attribute("currentUser"));
            vm.put("message", message);
            vm.put("title", "Welcome!");
            response.redirect("/");
            return null;
        }

        vm.put("message", WELCOME_MSG);
        return templateEngine.render(new ModelAndView(vm,VIEW_NAME));

    }
}
