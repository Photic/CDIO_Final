package controller;

import entity.deck.Card;
import entity.deck.Deck;
import entity.gameboard.GameBoard;
import entity.gameboard.Shipping;
import entity.player.Player;
import entity.player.PlayerList;

public class DeckController {

	protected Deck deck;
	
	protected DeckController() {
		deck = new Deck();
		deck.shuffleCards();
	}
	
	public void chanceField(Player p, PlayerList plist) {
		
		Card picked = deck.pickACard();
		
	}

	
	/**
	 * Makes sure to move the player to the nearest Shipping.
	 * @param p
	 * @param plist TODO
	 * @param gameboard
	 */
	public void moveToNearestShipping(Player p, PlayerList plist, GameBoard gameboard) {
		int countFields = p.getPosition();
		for (int i = p.getPosition(); i < gameboard.getLength(); i++) {
			if (gameboard.getField(i) instanceof Shipping) {
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
	
	/**
	 * Abstract override, extended from Card.
	 */
	@Override
	public void getCardFunction(Player p, PlayerList plist, GameBoard gameboard) {
		if (this.action < 4)
		{
			switch (this.action) {
			case 1: // MatadorGrant
				if (p.getAccount().getPlayerWorth(p) <= 15_000)
					p.setBalance(40_000);
				break;
			case 2: // Increase in taxes
				p.setBalance(-(p.getAccount().getHousesowned()*800));
				p.setBalance(-(p.getAccount().getHotelsowned()*2300));
				break;
			case 3: // Increase in Oil
				p.setBalance(-(p.getAccount().getHousesowned()*500));
				p.setBalance(-(p.getAccount().getHotelsowned()*2000));
				break;
			default:
				System.err.println("Something went wrong in Card Sub Class PayMoney");
				break;
			}
		}
		else
		{
			p.addBalance(p.getBalance()-this.action);
		}
	}
	
	/**
	 * Abstract override, extended from Card.
	 */
	@Override
	public void getCardFunction(Player p, PlayerList plist, GameBoard gameboard) {
		if (this.action == 0)
		{
			birthday(p, plist);
		}
		else
		{
			p.addBalance(this.action);
		}
	}

	/**
	 * If the player, taken from the PlayerList is not the player recieving the card, 
	 * give money to the card holder and remove money from the PlayerList player. 
	 * @param p
	 * @param plist
	 */
	private void birthday(Player p, PlayerList plist) {
		for (int i = 0; i < plist.getLength(); i++) {
			if (plist.getPlayer(i).getName() != p.getName()) {
				p.setBalance(200);
				plist.getPlayer(i).setBalance(-200);
			}
		}
	}

	
}
