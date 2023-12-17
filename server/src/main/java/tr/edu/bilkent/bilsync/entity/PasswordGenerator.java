package tr.edu.bilkent.bilsync.entity;

import java.security.SecureRandom;

/**
 * The PasswordGenerator class provides a utility for generating random passwords of specified length.
 * It uses a secure random number generator to select characters from a predefined set.
 */
public class PasswordGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+";

    /**
     * Generates a random password of the specified length using the predefined character set.
     *
     * @param length The length of the generated password.
     * @return A randomly generated password.
     */
    public static String generateRandomPassword(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }

        return password.toString();
    }

}
