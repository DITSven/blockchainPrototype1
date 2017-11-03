package blockchain1.mulvey.eoin;

public class IsValidNewBlock {
	private boolean isValid;
	private String result;
	
	public IsValidNewBlock(Block newBlock, Block oldBlock) {
		this.isValid = true;
		this.result = "";
		if(newBlock.getIndex() % 2 == 0) {
			if(newBlock.getBlockHash() != new CalculateBlockHash(oldBlock, new CreateCommands(newBlock.getIndex(), new BaseCommands().getResponseCommands()).getCommands()).getBlockHash()) {
				isValid = false;
				result= result + "Not valid - incorrect block hash.";
			}
		}
		else {
			if(newBlock.getBlockHash() != new CalculateBlockHash(oldBlock, new CreateCommands(newBlock.getIndex(), new BaseCommands().getRequestCommands()).getCommands()).getBlockHash()) {
				isValid = false;
				result= result + "Not valid - incorrect block hash.";
			}
		}
		if(oldBlock.getIndex() + 1 != newBlock.getIndex()) {
			isValid = false;
			result= result + "Not valid - incorrect index. ";
		}
		else if(newBlock.getPreviousHash() != oldBlock.getBlockHash()) {
			isValid = false;
			result= result + "Not valid - incorrect previous hash. ";
		}
		else {
			result = "Is valid";
		}
		
	}
	
	public boolean getIsValid() {
		return isValid;
	}
}
