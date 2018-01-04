package entity.deck;

public class MovePlayerCardAdvanced extends Card {

	public MovePlayerCardAdvanced(String description, int cardId, int moveBack) {
		super(description, cardId);
		this.actionOrAmount = moveBack;
	}

}
