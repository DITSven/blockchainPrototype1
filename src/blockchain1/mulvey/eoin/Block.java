package blockchain1.mulvey.eoin;

import java.util.ArrayList;

public class Block {

	private int index;
    private ArrayList<String> hashCommands;
    private String blockHash;
    private String previousHash;

    public Block(Block oldBlock, ArrayList<String> commands) {
    	this.blockHash = new CalculateBlockHash(oldBlock, commands).getBlockHash();  	
    	this.hashCommands = new CalculateCommandsHash(commands).getHashCommands();
    	this.index = oldBlock.getIndex() + 1;
    	this.previousHash = oldBlock.getBlockHash();
    }
    
    //Constructor for Genesis Block
    public Block(int index, String genesisString, ArrayList<String> commands) {
    	String leftHash = new CalculateCommandsHash(commands).getHashCommandsString();
    	this.blockHash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(leftHash + genesisString + Integer.toString(index));	
    	this.hashCommands = new CalculateCommandsHash(commands).getHashCommands();
    	this.index = index;
    	this.previousHash = genesisString;
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
    
    public String getPreviousHash() {
    	return previousHash;
    }
}