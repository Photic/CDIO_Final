package entity.deck;

public class GoToJailCard extends Card {

	/**
	 * Constructor for GoToJailCard, extends Card.
	 * @param description
	 * @param posistion
	 */
	public GoToJailCard(String description, int posistion) {
		super(description);
		this.actionOrAmount = posistion;
	}
	
}
