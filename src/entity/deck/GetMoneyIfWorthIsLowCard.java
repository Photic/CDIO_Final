package entity.deck;

public class GetMoneyIfWorthIsLowCard extends Card {
	
	/**
	 * Constructor for GetMonetIfWirthIsLowCard, extends Card.
	 * @param description
	 * @param amount
	 * @param advancedAmount
	 */
	public GetMoneyIfWorthIsLowCard(String description, int amount, int advancedAmount) {
		super(description);
		this.actionOrAmount = amount;
		this.advancedAmount = advancedAmount;
	}
}
