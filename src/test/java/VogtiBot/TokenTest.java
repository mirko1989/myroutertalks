package VogtiBot;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.vogt.telegram.bot.router.Token;

public class TokenTest {

    @Test
    public void testTokenGeneration() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String challenge = "abc12x";
        String pass = "password";
        String token = Token.getFor(challenge, pass);
        String expected = "530475d4e11b570129e23ed66a329fc5";

        assertEquals(expected, token);
    }

}