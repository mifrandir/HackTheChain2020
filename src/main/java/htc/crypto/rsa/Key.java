package htc.crypto.rsa;

public class Key {
  /**
   * Basically every Key will use this default exponent. The actual key is stored
   * in the exponent.
   */
  public static final long DEFAULT_EXPONENT = 65537;

  private long modulus;

  public long getModulus() {
    return this.modulus;
  }

  public long getExponent() {
    return DEFAULT_EXPONENT;
  }

  /**
   * Creates an immutable key object.
   * 
   * @param mod The modulus to be used to en-/decrypt something with this key.
   */
  public Key(long mod) {
    this.modulus = mod;
  }

  /**
   * Encrypts a given block of data with this key.
   *
   * @param data the data to be encrypted
   * @return encrypted data
   */
  public byte[] encrypt(byte[] data) {
    // TODO: Implement me
    throw new UnsupportedOperationException();
  }

  /**
   * Decrypts a given block of data with this key.
   * 
   * @param data the data to be decrypted
   * @return encrypted data
   */
  public byte[] decrypt(byte[] data) {
    // TODO: Implement me
    throw new UnsupportedOperationException();
  }
}