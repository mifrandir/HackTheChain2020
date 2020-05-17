package htc.chain;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Block {

    private static Logger logger = Logger.getLogger(Block.class.getName());
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    /**
     * Creates a new Block with a given predecessor, a given time and a block of
     * data.
     * 
     * @param data
     * @param previousHash
     * @param timeStamp
     */
    public Block(String data, String previousHash, long timeStamp) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.hash = calculateBlockHash();
    }

    /**
     * With a given prefix length, the nonce is adjusted until the hash contains a
     * prefix of that many zeroes.
     * 
     * @param prefix the minimum number of zeroes in the prefix.
     * @return the hash value that satisfies the criterion.
     */
    public String mine(int prefix) { // a block is represented by a hash value. Generating the hash value of a
                                     // block is called “mining” the block.
        String prefixString = new String(new char[prefix]).replace('\0', '0'); // defining the prefix we desire to find
        while (!hash.substring(0, prefix)// If we haven't found what we are looking for for we increment the nonce and
                                         // calculate the hash in a loop until we find desired prefix
                .equals(prefixString)) {
            nonce++;
            hash = calculateBlockHash();
        }
        return hash;
    }

    /**
     * Creates a hash value that incorporates all the necessary fields of this
     * block.
     * 
     * @return A hash value.
     */
    public String calculateBlockHash() {
        String dataToHash = previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data;
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes("UTF-8"));

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    public String getHash() { // displays hash
        return this.hash;
    }

    public String getPreviousHash() { // displays previous hash
        return this.previousHash;
    }

    public void setData(String data) { // displays new data
        this.data = data;
    }

    public boolean equals(Object o)
    {
   	 if (this == o)
   	 {
   		 return true;
   	 }
   	 if (o == null)
   	 {
   		 return false;
   	 }
   	 if (getClass() != o.getClass())
   	 {
   		 return false;	 
   	 }
   	 Block block = (Block) o; 
   	 return Objects.equals(hash, block.hash) 
   			 && Objects.equals(previousHash, block.previousHash) 
   			 && Objects.equals(data, block.data) 
   			 && Objects.equals(timeStamp, block.timeStamp)
   			 && Objects.equals(nonce, block.nonce);
   	     	 
   		 
    }
    @Override
    public int hashCode() {
        return Objects.hash(hash, previousHash, data, timeStamp, nonce);
    }
}

