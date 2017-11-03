package blockchain1.mulvey.eoin;

import java.util.ArrayList;

public class CalculateBlockHash {
	
	private String blockHash;
	
	public CalculateBlockHash(Block oldBlock, ArrayList<String> commands) {
		int index = oldBlock.getIndex() + 1;
		String previousHash = oldBlock.getBlockHash();
		CalculateCommandsHash commandsCalc = new CalculateCommandsHash(commands);
		ArrayList<String> hashCommands = commandsCalc.getHashCommands();
		String commandsHashes = commandsCalc.getHashCommandsString();

        String leftHash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(commandsHashes);
        String rightHash = previousHash;
        
        if(rightHash.length() > hashCommands.toString().length()) {
        	rightHash = previousHash.substring(hashCommands.toString().length(), previousHash.length() -1);
        }
        
        this.blockHash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(leftHash.concat(rightHash).concat(Integer.toString(index)));		
	}
	
	public String getBlockHash() {
		return blockHash;
	}
}
