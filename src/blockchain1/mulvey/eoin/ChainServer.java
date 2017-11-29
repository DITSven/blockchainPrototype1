package blockchain1.mulvey.eoin;

import java.net.*;
import java.util.ArrayList;

import blockchain.mulvey.eoin.Block;

import java.io.*;

public class ChainServer {
	ServerSocket serverSocket;
	Socket serviceSocket;
	BufferedReader input;
	DataOutputStream output;
	private File chainFile;
	private ArrayList<Block> blockchain;
	String line;
	
	public ChainServer(String filePath) {
		setChainFile(new File(filePath));
		ChainFileOpen(getChainFile());
		//OpenServerSocket();
		//RespondToClient();
		//CloseServerSocket();
		for (int j = 0; j < 20; j ++) {
        	System.out.println("Previous hash " + blockchain.get(j).getPreviousHash());
        	System.out.println("Commands: " + blockchain.get(j).getHashCommands());
        	System.out.println("Hash of block " + j + ":");
        	System.out.println(blockchain.get(j).getBlockHash());
        }
		
	}
	
	@SuppressWarnings("unchecked")
	public void ChainFileOpen(File chainFile) {
		try(FileInputStream fileInput = new FileInputStream(getChainFile())){
			ObjectInputStream objectFileInput = new ObjectInputStream(fileInput);
			setBlockchain(new ArrayList<Block>());
			try {
			getBlockchain().addAll((ArrayList<Block>) objectFileInput.readObject());
			}
			catch(Error e) {
				System.out.println("Blockchain invalid");
			}
			objectFileInput.close();
			fileInput.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
	public void OpenServerSocket() {
		try {
			serverSocket = new ServerSocket(6262);
		}
		catch(IOException e) {
			System.out.println(e);
		}
		try {
			serviceSocket = serverSocket.accept();
		}
		catch(IOException e) {
			System.out.println(e);
		}
		try {
			input = new BufferedReader(new InputStreamReader(serviceSocket.getInputStream()));
		}
		catch(IOException e) {
			System.out.println(e);
		}
		try {
			output = new DataOutputStream(serviceSocket.getOutputStream());
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
	
	public void CloseServerSocket() {
		try {
			output.close();
			input.close();
			serviceSocket.close();
			serverSocket.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
	
	public void RespondToClient() {
		try {
			line = input.readLine();
			int blockNumber = Integer.parseInt(line);
			line = input.readLine();
			String blockHash = line;
			if(getBlockchain().get(blockNumber).getBlockHash() == blockHash) {
				output.writeBytes(getBlockchain().get(blockNumber).getPreviousHash() + "\n");
			}
			output.writeBytes("OK");
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}

	ArrayList<Block> getBlockchain() {
		return blockchain;
	}

	void setBlockchain(ArrayList<Block> blockchain) {
		this.blockchain = blockchain;
	}

	File getChainFile() {
		return chainFile;
	}

	void setChainFile(File file) {
		this.chainFile = file;
	}
}
