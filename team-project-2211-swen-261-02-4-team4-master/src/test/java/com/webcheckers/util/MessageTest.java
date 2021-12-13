package com.webcheckers.util;
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
@Tag("util-tier")
public class MessageTest {
    private Message CuT;
    /**
    @BeforeEach
    void setup()
    {

=======
        CuT = new Message("Alligator", Message.Type.INFO);
    }


    @Test
    void errorTest()
    {
        CuT = Message.error("Make sure to import assertions into a test class");
        assertEquals(Message.Type.ERROR, CuT.getType());
    }

    @Test
    void infoTest()
    {
        CuT = Message.info("I hear importing assertions really helps in a test class");
        Assertions.assertSame(Message.Type.INFO, CuT.getType());
    }


//    @Test
//    void toStringTest()
//    {
//        String s_rep = CuT.toString();
//        Assertions.assertSame("{Msg " + CuT.getType() + " '" + CuT.getText() + "'}" , s_rep );
//    }

}
