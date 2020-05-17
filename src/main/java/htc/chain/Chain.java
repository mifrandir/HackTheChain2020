package htc.chain;

import java.util.HashSet;
import java.util.Set;

public class Chain {
	// Create hash set to append blocks
	Set<Block> chain = new HashSet <Block>();
	
	public void addAndValidateChain(Block block) {
		chain.add(block);
	}
}
