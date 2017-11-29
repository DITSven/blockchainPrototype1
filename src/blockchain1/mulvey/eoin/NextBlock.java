package blockchain1.mulvey.eoin;

import java.util.ArrayList;

import blockchain.mulvey.eoin.Block;

public class NextBlock {
	private Block nextBlock;
	public NextBlock(ArrayList<Block> blockchain) {
		int index = blockchain.size();
		if(index % 2 != 0) {
			this.nextBlock = new Block(blockchain.get(index - 1), new CreateCommands(blockchain, new BaseCommands().getRequestCommands()).getCommands());
		}
		if(index % 2 == 0) {
			this.nextBlock = new Block(blockchain.get(index - 1), new CreateCommands(blockchain, new BaseCommands().getResponseCommands()).getCommands());
		}        
	}	
	
	public Block getNextBlock() {
		return nextBlock;
	}
}
