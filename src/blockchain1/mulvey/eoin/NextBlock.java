package blockchain1.mulvey.eoin;

import java.util.ArrayList;

public class NextBlock {
	private Block nextBlock;
	public NextBlock(ArrayList<Block> blockchain) {
		ArrayList<String> blockCommand = new ArrayList<String>();
        blockCommand.add("command1");
        blockCommand.add("command2");
        blockCommand.add("command3");
        
        int index = blockchain.size();
        this.nextBlock = new Block(blockchain.get(index - 1).getIndex(), blockchain.get(index - 1).getBlockHash(), blockCommand);
	}
	
	public Block getNextBlock() {
		return nextBlock;
	}
}
