package htc.crypto.rsa;

import java.security.*;

public class KeyPair {
  
  /* Initialize string variables for private and public keys */
  public String privateKey;
  public String publicKey;
  
  try {
    /* Create new instance of KeyPair class */
    KeyPair keyPair;
    String algorithm = "RSA"; // Using RSA algorithm to encrypt keys
    
    /* Generate new key pair using the algorithm */
    keyPair = KeyPairGenerator.getInstance(algorithm).generateKeyPair();
    
    /* generate private key to sign and decrypt data */
    privateKey = keyPair.getPrivate().toString();
    
    /* generate public key to verify signatures and encrypt data */
    publicKey = keyPair.getPublic().toString();
  }
  
  catch (Exception e) {
    throw new RuntimeException(e)
  }
  
}
