package entity.deck;

public class PayMoneyCard extends Card {
	
	public PayMoneyCard(String description, int haveToPay) {
		super(description);
		this.actionOrAmount = haveToPay;
	}
	
}
