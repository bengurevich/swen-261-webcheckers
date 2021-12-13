package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.PlayerLobby;
import org.junit.jupiter.api.*;
import spark.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
Author: Ardit Koti
 */

@Tag("UI-tier")
public class PostHomeRouteTest {

    private PostHomeRoute CuT;
    private Request request;
    private Response response;
    private Session session;
    private TemplateEngine engine;
    private PlayerLobby pLobby;
    private GameCenter gameCenter;

    @BeforeEach
    public void setup()
    {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        pLobby = mock(PlayerLobby.class);
        gameCenter = mock(GameCenter.class);
        CuT = new PostHomeRoute(engine, gameCenter);
    }


    @Test
    void handle() {
        CuT.handle(request, response);
        Assertions.assertTrue(true);
    }
}