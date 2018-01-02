package entity.deck;

import entity.player.Player;
import entity.player.PlayerList;

public class MovePlayer extends Card {

	private String description;
	private int newPosistion;
	
	public MovePlayer(String description, int action) {
		super(description, action);
		this.newPosistion = action;
		this.description = description;
	}

	/**
	 * Abstract override, extended from Card.
	 */
	@Override
	public void cardFunction(Player p, PlayerList plist) {
		p.setPosition(p.getPosition()+newPosistion);
	}

	/**
	 * GUI Description
	 * @return
	 */
	public String getDescription() {
		return description;
	}
}
