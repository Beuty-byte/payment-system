package util;

public interface PasswordEncoder {
    String SALT = "5f8f041b75042e56";
    String getEncoderPassword(String unencryptedPassword);
}
