package htc.chain;

import java.util.ArrayList;

public class Chain {
	
	// Create array to append blocks
	public ArrayList <Block> blockChain = new ArrayList <Block>();

	public void addAndValidateChain(Block block) {
		
		Block current = block;
		
		// Check that the new block's previous hash is the same as the last block's
		for (int i = blockChain.size() - 1; i >= 0; i--) {
			Block b = blockChain.get(i);
			
			if (b.getHash().equals(current.getPreviousHash())) {
				// if yes, current block will become the new block
				current = b;
			} 
			
			else {
				throw new RuntimeException("Invalid");	
			}
		}
		
		// add the new block to the chain
		this.blockChain.add(block);
	}

}
