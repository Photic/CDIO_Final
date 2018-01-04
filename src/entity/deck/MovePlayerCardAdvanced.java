package entity.deck;

public class MovePlayerCardAdvanced extends Card {

	public MovePlayerCardAdvanced(String description, int moveBack) {
		super(description);
		this.actionOrAmount = moveBack;
	}


}
