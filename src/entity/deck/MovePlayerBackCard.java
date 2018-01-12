package entity.deck;

public class MovePlayerBackCard extends Card {

	/**
	 * Constructor for MoevePlayerBackCard, extends Card.
	 * @param description
	 * @param moveBack
	 */
	public MovePlayerBackCard(String description, int moveBack) {
		super(description);
		this.actionOrAmount = moveBack;
	}
}
