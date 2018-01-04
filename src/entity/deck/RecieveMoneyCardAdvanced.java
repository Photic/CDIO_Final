package entity.deck;

public class RecieveMoneyCardAdvanced extends Card {

	public RecieveMoneyCardAdvanced(String description, int prPlayer) {
		super(description);
		this.actionOrAmount = prPlayer;
	}
	
}
