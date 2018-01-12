package entity.deck;

public class RecieveMoneyCard extends Card {
	
	/**
	 * Constructor PayMoneyCard, extends Card.
	 * @param description
	 * @param getMoney
	 */
	public RecieveMoneyCard(String description, int getMoney) {
		super(description);
		this.actionOrAmount = getMoney;
	}
}