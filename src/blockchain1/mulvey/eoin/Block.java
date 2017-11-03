package blockchain1.mulvey.eoin;

import java.util.ArrayList;

public class Block {

	private int index;
    private ArrayList<String> hashCommands;
    private String blockHash;

    public Block(Block oldBlock, ArrayList<String> commands) {
    	CalculateBlockHash calcBlockHash = new CalculateBlockHash(oldBlock, commands);
    	
    	this.blockHash = calcBlockHash.getBlockHash();
    	this.hashCommands = new CalculateCommandsHash(commands).getHashCommands();
        
    }
    
    public Block(int index, String genesisString, ArrayList<String> commands) {
    	String leftHash = new CalculateCommandsHash(commands).getHashCommandsString();
    	this.blockHash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(leftHash.concat(genesisString).concat(Integer.toString(index)));	
    	this.hashCommands = new CalculateCommandsHash(commands).getHashCommands();
    	this.index = index;
    }

    public int getIndex() {
    	return index;
    }

    public ArrayList<String> getHashCommands() {
        return hashCommands;
    }

    public String getBlockHash() {
    	return blockHash;
    }
}