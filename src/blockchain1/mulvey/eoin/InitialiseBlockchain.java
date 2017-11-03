package blockchain1.mulvey.eoin;

import java.util.ArrayList;

public class InitialiseBlockchain {
	private ArrayList<Block> blockchain = new ArrayList<Block>();
	public InitialiseBlockchain(Block genesisBlock){
		this.blockchain.add(genesisBlock);
	}
	
	public ArrayList<Block> getBlockchain(){
		return blockchain;
	}
}
