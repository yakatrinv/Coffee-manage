package it.academy.services.auth;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import static it.academy.utils.Data.ALGORITHM;
import static it.academy.utils.Data.ITERATIONS;
import static it.academy.utils.Data.KEY_LENGTH;
import static it.academy.utils.Data.RADIX;
import static it.academy.utils.Data.SALT_LENGTH;
import static it.academy.utils.Data.SECURE_ALG;

public class EncryptService {

    public static final int SIGNUM = 1;
    public static final String BEGIN_HEX = "%0";
    public static final String END_HEX = "d";

    public boolean verifyPassword(String pass,
                                  byte[] encryptedPass,
                                  byte[] salt) {
        byte[] encryptedPassword;
        encryptedPassword = getEncryptedPassword(pass, salt);
        return Arrays.equals(encryptedPassword, encryptedPass);
    }

    public byte[] getEncryptedPassword(String pass, byte[] salt) {
        KeySpec spec = new PBEKeySpec(
                pass.toCharArray(),
                salt,
                ITERATIONS,
                KEY_LENGTH);

        SecretKeyFactory factory;
        byte[] bytes = new byte[0];
        try {
            factory = SecretKeyFactory.getInstance(ALGORITHM);
            bytes = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public byte[] generateSalt() {
        SecureRandom random;
        byte[] salt = new byte[SALT_LENGTH];

        try {
            random = SecureRandom.getInstance(SECURE_ALG);
            random.nextBytes(salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return salt;
    }

    public String toHex(byte[] array) {
        BigInteger bi = new BigInteger(SIGNUM, array);
        String hex = bi.toString(RADIX);

        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format(BEGIN_HEX + paddingLength + END_HEX, 0) + hex;
        } else {
            return hex;
        }
    }

    public byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer
                    .parseInt(hex.substring(2 * i, 2 * i + 2),
                            RADIX);
        }
        return bytes;
    }
}
