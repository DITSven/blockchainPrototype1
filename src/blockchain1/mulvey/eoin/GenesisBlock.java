package blockchain1.mulvey.eoin;

import java.util.ArrayList;

public class GenesisBlock {
	private Block genesisBlock;
	public GenesisBlock(){
		ArrayList<String> genesisCommand = new ArrayList<String>();
		genesisCommand.add("initialiseCommand1");
		genesisCommand.add("initialiseCommand2");
		genesisCommand.add("initialiseCommand3");
		
		this.genesisBlock = new Block(-1, "randomInput", genesisCommand);
	}
	
	public Block getGenesisBlock(){
		return genesisBlock;
	}
}
