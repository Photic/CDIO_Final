package entity.deck;

public class RecieveMoneyCard extends Card {
	
	public RecieveMoneyCard(String description, int cardId, int getMoney) {
		super(description, cardId);
		this.actionOrAmount = getMoney;
	}

	

}