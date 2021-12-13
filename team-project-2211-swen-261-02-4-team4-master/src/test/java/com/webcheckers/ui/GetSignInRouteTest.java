package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import com.webcheckers.ui.GetSignInRoute;
import com.webcheckers.ui.PostSignInRoute;
import spark.HaltException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.webcheckers.model.PlayerLobby;
import spark.*;
import com.webcheckers.ui.WebServer;

@Tag("UI-Tier")
public class GetSignInRouteTest {
    private GetSignInRoute CuT;

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private GameCenter gameCenter;



    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        gameCenter = new GameCenter();
        CuT = new GetSignInRoute(engine, gameCenter);


    }

//    @Test
//    public void alreadySignedIn() throws Exception {
//        String playerName = "player";
//        when(request.session().attribute(PostSignInRoute.NAME_PARAM))
//                .thenReturn(playerName);
//
//        CuT.handle(request, response);
//        verify(response).redirect(WebServer.HOME_URL);
//
//    }

    @Test
    public void notSignedInYet() throws Exception {
        CuT.handle( request, response );
        verify( response, never() ).redirect( any() );
    }

}
