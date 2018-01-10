package entity.deck;

public class AntiJailCard extends Card {
	
	/**
	 * Constructor for AntiJailCard, extends Card.
	 * @param description
	 */
	public AntiJailCard(String description) {
		super(description);
		this.cardIsOwned = false;
	}
}
	
