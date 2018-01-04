package entity.deck;

public class RecieveMoneyCard extends Card {
	
	public RecieveMoneyCard(String description, int getMoney) {
		super(description);
		this.actionOrAmount = getMoney;
	}

	

}