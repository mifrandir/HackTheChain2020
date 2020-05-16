package htc.crypto.rsa;

import java.security.*;

public class RSAPair {
  private static final String ALGORITHM = "RSA";

  /**
   * Generate a new KeyPair suitable for RSA encryption.
   * 
   * @return A new KeyPair.
   */
  public static KeyPair generate() {
    try {

      /* Generate new key pair using the algorithm */
      return KeyPairGenerator.getInstance(RSAPair.ALGORITHM).generateKeyPair();

    }

    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
