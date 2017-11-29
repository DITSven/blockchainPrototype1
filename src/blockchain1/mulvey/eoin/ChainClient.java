package blockchain1.mulvey.eoin;
import java.io.*;
import java.net.*;

public class ChainClient {
	Socket clientSocket;
	String host;
	int port;
	int blockNumber;
	String blockHash;
	BufferedReader input;
	DataOutputStream output;
	
	public ChainClient() {
		host = "localhost";
		port = 6262;
		blockNumber = 5;
		blockHash = "0d74eb07e902c8a3aac7d3082799e7caa622464914c4a068e21b4c0bea55d1e5";
		OpenClientSocket();
		WriteToServer();
		ListenToServer();
		CloseClientSocket();
	}
	
	public void OpenClientSocket() {
		try {
			clientSocket = new Socket(host, port);
		} catch (IOException e) {
			System.out.println(e);
		}
		try {
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}
		catch(IOException e) {
			System.out.println(e);
		}
		try {
			output = new DataOutputStream(clientSocket.getOutputStream());
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
	
	public void WriteToServer() {
		if (clientSocket != null && input != null && output != null) {
			try {
				output.writeBytes("BLOCK: " + Integer.toString(blockNumber) + "\n");
				output.writeBytes("HASH: " + blockHash + "\n");
				output.writeBytes("\n\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		}
	}
	
	public void ListenToServer() {
		String responseLine;
		try {
			while ((responseLine = input.readLine()) != null) {
				System.out.println("Previous Hash: " + responseLine);
				if (responseLine.indexOf("OK") != -1) {
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	
	public void CloseClientSocket() {
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		try {
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
}
