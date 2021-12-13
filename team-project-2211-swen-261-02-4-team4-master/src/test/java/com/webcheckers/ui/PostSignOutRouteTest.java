package com.webcheckers.ui;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.model.PlayerLobby;
import spark.*;
import org.junit.jupiter.api.*;
import javax.sound.sampled.EnumControl;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Ardit Koti
 */
@Tag("UI-tier")
public class PostSignOutRouteTest {
    private PostSignOutRoute CuT;
    private Request request;
    private Response response;
    private Session session;
    private TemplateEngine engine;
    private PlayerLobby pLobby;
    private GameCenter gameCenter;

    @BeforeEach
    void setup()
    {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        pLobby = mock(PlayerLobby.class);
        gameCenter = mock(GameCenter.class);
        CuT = new PostSignOutRoute(engine, gameCenter);
    }

//    @Test
//    void handle() throws Exception {
//        String name = "Ben";
//        String password = "123";
//        pLobby.checkAndAddName(name, password);
//
//        assertNull(pLobby.getNamesInUse());
//        assertTrue(session.attributes().isEmpty());
//    }
}