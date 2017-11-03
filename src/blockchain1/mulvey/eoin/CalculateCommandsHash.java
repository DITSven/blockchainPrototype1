package blockchain1.mulvey.eoin;

import java.util.ArrayList;

public class CalculateCommandsHash {

	private String hashCommandsString;
	private ArrayList<String> hashCommands;
	
	public CalculateCommandsHash(ArrayList<String> commands) {
		this.hashCommandsString = "";
		this.hashCommands = new ArrayList<String>();
        
        for (int i = 0; i < commands.size(); i++ ) {
        	String sha256Temp = org.apache.commons.codec.digest.DigestUtils.sha256Hex(commands.get(i));
        	hashCommands.add(sha256Temp);
        	hashCommandsString.concat(sha256Temp);
        }
	}
	
	public String getHashCommandsString() {
		return hashCommandsString;
	}
	
	public ArrayList<String> getHashCommands() {
		return hashCommands;
	}
}
