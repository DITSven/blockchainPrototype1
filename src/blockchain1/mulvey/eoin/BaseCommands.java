package blockchain1.mulvey.eoin;

import java.util.ArrayList;

public class BaseCommands {
	
	private ArrayList<String> requestCommands;
	private ArrayList<String> responseCommands;
	
	public BaseCommands() {
		this.requestCommands = new ArrayList<String>();
		requestCommands.add("Request Command 1");
		requestCommands.add("Request Command 2");
		requestCommands.add("Request Command 3");
		requestCommands.add("Request Command 4");
		requestCommands.add("Request Command 5");
		
		this.responseCommands = new ArrayList<String>();
		responseCommands.add("Response Command 1");
		responseCommands.add("Response Command 2");
		responseCommands.add("Response Command 3");
		responseCommands.add("Response Command 4");
		responseCommands.add("Response Command 5");
	}
	
	public ArrayList<String> getRequestCommands(){
		return requestCommands;
	}
	
	public ArrayList<String> getResponseCommands(){
		return responseCommands;
	}
}
