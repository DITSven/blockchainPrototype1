package blockchain1.mulvey.eoin;

import java.util.ArrayList;

public class CalculateBlockHash {
	
	private String blockHash;
	
	public CalculateBlockHash(Block oldBlock, ArrayList<String> commands) {
		int index = oldBlock.getIndex() + 1;
		String previousHash = oldBlock.getBlockHash();
		CalculateCommandsHash commandsCalc = new CalculateCommandsHash(commands);
		String commandsHashes = commandsCalc.getHashCommandsString();

        String leftHash = Integer.toString(index);
        leftHash = leftHash + org.apache.commons.codec.digest.DigestUtils.sha256Hex(commandsHashes);
        String rightHash = previousHash;
        
        this.blockHash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(leftHash + rightHash);		
	}
	
	public String getBlockHash() {
		return blockHash;
	}
}
