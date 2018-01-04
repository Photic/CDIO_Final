package entity.deck;

public class RecieveMoneyCard extends Card {

	protected int getMoney;
	
	public RecieveMoneyCard(String description, int cardId, int getMoney) {
		super(description, cardId);
		this.getMoney = getMoney;
	}

	

}