package entity.deck;

import entity.Player;
import entity.PlayerList;

public class MovePlayer extends Card {

	private int newPosistion;
	
	public MovePlayer(String description, int action) {
		super(description, action);
		this.newPosistion = action;
	}

	@Override
	public void cardFunction(Player p, PlayerList plist) {
		p.setPosition(p.getPosition()+newPosistion);
	}

}
