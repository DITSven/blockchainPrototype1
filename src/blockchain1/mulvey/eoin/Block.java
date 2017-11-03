package blockchain1.mulvey.eoin;

import java.util.ArrayList;

public class Block {

	private int index;
    private String previousHash;
    private ArrayList<String> commands;
    private String blockHash;

    public Block(int previousIndex, String previousHash, ArrayList<String> commands) {
    	this.index = previousIndex + 1;
        this.previousHash = previousHash;
        this.commands = commands;

        ArrayList<String> hashCommands = new ArrayList<String>();
        String commandsHashes = "";
        
        for (int i = 0; i < commands.size(); i++ ) {
        	String sha256Temp = org.apache.commons.codec.digest.DigestUtils.sha256Hex(commands.get(i));
        	hashCommands.add(sha256Temp);
        	commandsHashes.concat(sha256Temp);
        }

        String leftHash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(commandsHashes);
        String rightHash = previousHash;
        
        if(rightHash.length() > hashCommands.toString().length()) {
        	rightHash = previousHash.substring(hashCommands.toString().length(), previousHash.length() -1);
        }
        
        String hashID = org.apache.commons.codec.digest.DigestUtils.sha256Hex(leftHash.concat(rightHash));
        hashCommands.add(hashID);
        String contents = hashCommands.toString();
        this.blockHash = contents;
    }

    public int getIndex() {
    	return index;
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    public String getBlockHash() {
    	return blockHash;
    }
    
    public String getPreviousHash() {
    	return previousHash;
    }
}