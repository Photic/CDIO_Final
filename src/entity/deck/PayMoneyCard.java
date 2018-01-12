package entity.deck;

public class PayMoneyCard extends Card {
	
	/**
	 * Constructor PayMoneyCard, extends Card.
	 * @param description
	 * @param haveToPay
	 */
	public PayMoneyCard(String description, int haveToPay) {
		super(description);
		this.actionOrAmount = haveToPay;
	}
}
