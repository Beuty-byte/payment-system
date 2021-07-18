package util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class PasswordEncoderImpl implements PasswordEncoder{

    private PasswordEncoderImpl() {
    }

    private final static PasswordEncoderImpl INSTANCE = new PasswordEncoderImpl();

    public static PasswordEncoderImpl getInstance(){
        return INSTANCE;
    }

    @Override
    public String getEncoderPassword(String unencryptedPassword) {
        byte[] bytes = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update((unencryptedPassword + SALT).getBytes(StandardCharsets.UTF_8));
            bytes = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInteger = new BigInteger(1, Objects.requireNonNull(bytes));
        return bigInteger.toString(16);
    }
}
