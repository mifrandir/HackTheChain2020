package htc.crypto.rsa;

public class KeyPair {
  /** The key that's used to sign and decrypt messages. */
  private Key prv;

  /** The key that's used to verify signatures and encrypt messages. */
  private Key pub;

  /**
   * Returns the private key associated with this pair.
   * 
   * @return private key
   */
  public Key getPrivate() {
    return this.prv;
  }

  /**
   * Returns the public key associated with this pair.
   * 
   * @return public key
   */
  public Key getPublic() {
    return this.pub;
  }

  /**
   * Constructor to create a new key pair.
   * 
   * @return A new, unique key pair.
   */
  public static KeyPair generate() {
    // TODO: Implement key pair generation.
    throw new UnsupportedOperationException();
  }
}