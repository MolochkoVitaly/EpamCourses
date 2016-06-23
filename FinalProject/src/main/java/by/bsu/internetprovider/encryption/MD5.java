package by.bsu.internetprovider.encryption;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class MD5 ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class MD5 {
    /** Field LOG  */
    private static final Logger LOG = LogManager.getLogger(MD5.class);

    /**
     * Method encipherPassword ...
     *
     * @param password of type String
     * @return String
     */
    public static String encipherPassword(String password) {
        MessageDigest messageDigest = null;
        byte bytesEncoded[] = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1"); // 1 раз !!!!!
            messageDigest.update(password.getBytes("utf8"));
            bytesEncoded = messageDigest.digest();
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOG.error(e);
        }
        BigInteger bigInt = new BigInteger(1, bytesEncoded);
        return bigInt.toString(16);
    }
}
