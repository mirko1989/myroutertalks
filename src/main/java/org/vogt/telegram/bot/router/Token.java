package org.vogt.telegram.bot.router;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Token {

    public static String getFor(String challenge, String pass) {
        String in = challenge + "-" + pass;
        String token = "";

        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5.update(in.getBytes("UTF-16LE"));
            byte[] digest = md5.digest();
            token = DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

        }

        return token;
    }

}
