package entity.deck;

public class RecieveMoneyCardAdvanced extends Card {

	public RecieveMoneyCardAdvanced(String description, int cardId, int prPlayer) {
		super(description, cardId);
		this.actionOrAmount = prPlayer;
	}
	
}
