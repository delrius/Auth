package ua.kiev.naukma.auth.client.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {
    public static String hash(String str) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(str.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);
            return hashtext;
        } catch (Exception e) {
            return null;
        }
    }
}
