package htc.chain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Block {

	private static Logger logger = Logger.getLogger(Block.class.getName());
	private String hash;
	private String previousHash;
	private String data;
	private long timeStamp;
	private int nonce;



    public Block(String data, String previousHash, long timeStamp) //Constructor initializes all the data in the block 
    {

        this.data = data;

        this.previousHash = previousHash;

        this.timeStamp = timeStamp;

        this.hash = calculateBlockHash();

    }



    public String mineBlock(int prefix) { // a block is represented  by a hash value. Generating the hash value of a block is called “mining” the block.

        String prefixString = new String(new char[prefix]).replace('\0', '0'); //defining the prefix we desire to find

        while (!hash.substring(0, prefix)//If we a have found what we are looking for for  we increment the nonce and calculate the hash in a loop until we find desired prefix

            .equals(prefixString)) {

            nonce++;

            hash = calculateBlockHash();

        }

        return hash;

    }



    public String calculateBlockHash() { //calculate the hash of a block *note*:A hash is an output of something known as a hash function. A hash function maps input data of arbitrary size to output data of fixed size

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



    public String getHash() { //displays hash 

        return this.hash;

    }



    public String getPreviousHash() { //displays previous hash 

        return this.previousHash;

    }



    public void setData(String data) { //displays new data 

        this.data = data;

    }

}
