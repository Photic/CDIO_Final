package entity.deck;

import entity.gameboard.GameBoard;
import entity.player.Player;
import entity.player.PlayerList;

public class MovePlayer extends Card {
	
	public MovePlayer(String description, int action) {
		super(description, action);
	}

	/**
	 * Abstract override, extended from Card.
	 */
	@Override
	public void cardFunction(Player p, PlayerList plist, GameBoard gameboard) 
	{
		switch (this.action) {
		case 1:
			moveToNearestShipping(p, null, gameboard);
			break;
		case 2:
			
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		default:
			System.out.println("Something went wrong in Card/MovePlayer");
			break;
		}
	}
	
	/**
	 * Makes sure to move the player to the nearest Shipping.
	 * @param p
	 * @param plist TODO
	 * @param gameboard
	 */
	public void moveToNearestShipping(Player p, PlayerList plist, GameBoard gameboard) {
		int countFields = 0;
		for (int i = p.getPosition(); i < gameboard.getLength(); i++) {
			if (gameboard.getField(i).getClass() != gameboard.getField(5).getClass()) {
				countFields++;
			}
			else {
				countFields++;
				p.setPosition(countFields);
			}
				
		}
		
		if (gameboard.getField(countFields).isOwned() == true) {
			p.setBalance(p.getBalance()-(gameboard.getField(countFields).getRent()*2));
			
			for (int i = 0; i < plist.getLength(); i++)
				if (plist.getPlayer(i).getName() == gameboard.getField(countFields).getOwnerName())
					plist.getPlayer(i).setBalance(p.getBalance()+(gameboard.getField(countFields).getRent()*2));
			
		}
		
	}

}
