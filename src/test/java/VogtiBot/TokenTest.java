package VogtiBot;

import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class TokenTest {
    
    @Test
    public void whenDigest_HashShouldGenerated() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String s = "a";
        String expected = "4144e195f46de78a3623da7364d04f11";
        
        MessageDigest md5 = MessageDigest.getInstance("md5");
        md5.update(s.getBytes("UTF-16LE"));
        byte[] digest = md5.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();
        
        assertEquals(expected, hash);
    }

}