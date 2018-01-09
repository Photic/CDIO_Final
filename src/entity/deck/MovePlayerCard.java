package entity.deck;

public class MovePlayerCard extends Card {

	public MovePlayerCard(String description, int posistion) {
		super(description);
		this.actionOrAmount = posistion;
	}
	
}
