package entity.deck;

public class MovePlayerBackCard extends Card {

	public MovePlayerBackCard(String description, int moveBack) {
		super(description);
		this.actionOrAmount = moveBack;
	}


}
