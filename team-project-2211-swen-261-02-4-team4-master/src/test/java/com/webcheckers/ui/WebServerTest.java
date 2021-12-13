package com.webcheckers.ui;


import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import com.webcheckers.model.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import spark.TemplateEngine;


public class WebServerTest {

    private PlayerLobby playerLobby;
    private TemplateEngine templateEngine;
    private Gson gs;

    @BeforeEach
    public void setup() {
        templateEngine = mock(TemplateEngine.class);
        gs = new Gson();
    }

//    @Test
//    public void testInitialize(){
//        WebServer testServer = new WebServer(templateEngine, gs, playerLobby);
//        try {
//            testServer.initialize();
//        }catch(Exception e){fail();}
//    }
}