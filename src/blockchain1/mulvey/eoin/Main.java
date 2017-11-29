package blockchain1.mulvey.eoin;

public class Main {


    public static void main(String[] args) {
        
    	/*
        ArrayList<Block> blockchain = new InitialiseBlockchain(new GenesisBlock().getGenesisBlock()).getBlockchain();
        
        for (int i = 0; i < 20; i++) {
        	blockchain.add(new NextBlock(blockchain).getNextBlock());
        }
        
        for (int j = 0; j < 20; j ++) {
        	System.out.println("Previous hash " + blockchain.get(j).getPreviousHash());
        	System.out.println("Commands: " + blockchain.get(j).getHashCommands());
        	System.out.println("Hash of block " + j + ":");
        	System.out.println(blockchain.get(j).getBlockHash());
        }
        
        */
    	new DevGUI();
    	

    }
    
}