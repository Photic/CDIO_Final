package entity.deck;

public class BirthdayCard extends Card {

	/**
	 * Constructor for BirthdayCard, extends Card.
	 * @param description
	 * @param prPlayer
	 */
	public BirthdayCard(String description, int prPlayer) {
		super(description);
		this.actionOrAmount = prPlayer;
	}
}
