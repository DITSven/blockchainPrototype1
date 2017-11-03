package blockchain1.mulvey.eoin;

import java.util.ArrayList;

public class CreateCommands {

	private ArrayList<String> commands;
	
	public CreateCommands(ArrayList<Block> blockchain, ArrayList<String> commandsList) {
		this.commands = new ArrayList<String>();
		int index = blockchain.size();
		for (int i = 0; i < commandsList.size(); i++) {
			commands.add(commandsList.get(i) + Integer.toString(index));
		}
	}
	
	//Second constructor for use in validation
	public CreateCommands(int index, ArrayList<String> commandsList) {
		this.commands = new ArrayList<String>();
		for (int i = 0; i < commandsList.size(); i++) {
			commands.add(commandsList.get(i) + Integer.toString(index));
		}
	}
	
	public ArrayList<String> getCommands(){
		return commands;
	}
}
