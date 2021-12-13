package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;

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
class PostSignInRouteTest {

    private PostSignInRoute CuT;
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private PlayerLobby plobby;
    private GameCenter gameCenter;

    @BeforeEach
    public void setup() {


        request = mock(Request.class);
        session = mock(Session.class);


        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        gameCenter = mock(GameCenter.class);
        CuT = new PostSignInRoute(engine, gameCenter);

    }


        // test a valid player sign in
        @Test
        public void ValidSignIn(){


            when(request.session()).thenReturn(session); //ask about this

            // look for every method that is called and mock it

            String s = "Joe"; //not testing, just running method
            when(request.queryParams("name")).thenReturn(s);//overriding queryParams with joe
            when(request.queryParams("password")).thenReturn("testPassword");
            when(plobby.checkAndAddName(s,"testPassword")).thenReturn("Success"); //checks the string s from above and sets it to return sucess

            //HTTP session . attribute is just storing, so we do not need to test

            final TemplateEngineTester testHelper = new TemplateEngineTester();

            when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

            CuT.handle(request, response);

            testHelper.assertViewModelExists();



            testHelper.assertViewModelAttribute("message",  Message.info("Welcome " + s + " to the world of online checkers"));
            testHelper.assertViewModelAttribute("title", "Welcome!");
            testHelper.assertViewModelAttribute("players", plobby.getNamesInUse());

            //return statement does not need to bed tested, but we could check that it is not null

        //pass in player lobby
        //when check and add names is called, return success

        }

        // test a failed sign in
        @Test
        public void invalidSignInTaken1() {
            when(request.session()).thenReturn(session);

            final TemplateEngineTester testHelper = new TemplateEngineTester();


            String s = "Joe"; //not testing, just running method
            when(request.queryParams("name")).thenReturn(s);//overriding queryParams with joe

            when(request.queryParams("password")).thenReturn("testPassword");
            when(plobby.checkAndAddName(any(String.class),any(String.class))).thenReturn("Taken"); //checks the string s from above and sets it to return sucess

            when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

            CuT.handle(request, response);

            testHelper.assertViewModelExists();
            testHelper.assertViewModelAttribute("message",PostSignInRoute.NAMESTATUS);
        }
        @Test
        public void invalidSignInInvalid(){
            when(request.session()).thenReturn(session);
            final TemplateEngineTester testHelper = new TemplateEngineTester();
            String s = "Joe"; //not testing, just running method
            when(request.queryParams("name")).thenReturn(s);//overriding queryParams with joe

            when(request.queryParams("password")).thenReturn("testPassword");
            when(plobby.checkAndAddName(any(String.class),any(String.class))).thenReturn("Invalid"); //checks the string s from above and sets it to return invalid

            when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
            CuT.handle(request, response);
            testHelper.assertViewModelExists();
            testHelper.assertViewModelAttribute("message",PostSignInRoute.NAMESTATUS);
        }
        @Test
        public void invalidSignInTaken2() {
            when(request.session()).thenReturn(session);
            final TemplateEngineTester testHelper = new TemplateEngineTester();
            String s = "Joe"; //not testing, just running method
            when(request.queryParams("name")).thenReturn(s);//overriding queryParams with joe

            when(request.queryParams(("password"))).thenReturn("testPassword");
            when(plobby.checkAndAddName(any(String.class),any(String.class))).thenReturn("Success"); //checks the string s from above and sets it to return invalid

            when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
            CuT.handle(request, response);
            testHelper.assertViewModelAttribute("message",PostSignInRoute.NAMESTATUS);
            testHelper.assertViewModelAttribute("title","Welcome!");
            testHelper.assertViewModelAttribute(PostSignInRoute.PLAYERS, plobby.getNamesInUse());

        }
}