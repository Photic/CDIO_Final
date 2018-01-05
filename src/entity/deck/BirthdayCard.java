package entity.deck;

public class BirthdayCard extends Card {

	public BirthdayCard(String description, int prPlayer) {
		super(description);
		this.actionOrAmount = prPlayer;
	}
	
}
