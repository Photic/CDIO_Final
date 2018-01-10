package entity.deck;

public class MovePlayerCard extends Card {

	/**
	 * Constructor for MovePlayerCard, extends Card.
	 * @param description
	 * @param posistion
	 */
	public MovePlayerCard(String description, int posistion) {
		super(description);
		this.actionOrAmount = posistion;
	}
	
}
