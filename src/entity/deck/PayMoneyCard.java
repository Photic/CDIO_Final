package entity.deck;

public class PayMoneyCard extends Card {
	
	public PayMoneyCard(String description, int cardId, int haveToPay) {
		super(description, cardId);
		this.actionOrAmount = haveToPay;
	}
	

}
