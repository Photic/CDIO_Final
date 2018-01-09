package entity.deck;

public class GoToJailCard extends Card {

	public GoToJailCard(String description, int posistion) {
		super(description);
		this.actionOrAmount = posistion;
	}
	
}
