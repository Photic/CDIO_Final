package entity.deck;

public class MovePlayerCard extends Card {
	
	public MovePlayerCard(String description, int action, int posistion) {
		super(description, action);
		this.actionOrAmount = posistion;
	}
	
}
