package controller;

import entity.deck.AntiJailCard;
import entity.deck.Card;
import entity.deck.Deck;
import entity.deck.PayMoneyCard;
import entity.deck.PayMoneyCardAdvanced;
import entity.deck.PayMoneyCardSuperAdvanced;
import entity.deck.RecieveMoneyCard;
import entity.deck.RecieveMoneyCardAdvanced;
import entity.gameboard.GameBoard;
import entity.gameboard.Shipping;
import entity.player.Player;
import entity.player.PlayerList;

public class DeckController {

	private Deck deck;
	
	private DeckController() {
		deck = new Deck();
		deck.shuffleCards();
	}
	
	public String chanceField(Player p, PlayerList plist) {
		
		Card cardPicked = deck.pickACard();
		
		if (cardPicked instanceof RecieveMoneyCard) {
			recieveMoneyCard(p, cardPicked.getAmount());
		}
		else if (cardPicked instanceof RecieveMoneyCardAdvanced) {
			recieveMoneyCardAdvanced(p, plist, cardPicked.getAmount());
		}
		else if (cardPicked instanceof PayMoneyCard) {
			payMoneyCard(p, cardPicked.getAmount());
		}
		else if (cardPicked instanceof PayMoneyCardAdvanced) {
			payMoneyCardAdvanced(p, plist, cardPicked.getAmount(), cardPicked.getAdvancedAmount());
		}
		else if (cardPicked instanceof PayMoneyCardSuperAdvanced) {
			payMoneyCardSuperAdvanced(p, plist, cardPicked.getAmount(), cardPicked.getAdvancedAmount());
		}
		else if (cardPicked instanceof AntiJailCard) {
			
		}
		
		return cardPicked.getDescription();
		
	}

	/**
	 * Gives the player an amount of money.
	 * @param p
	 * @param amount
	 */
	public void recieveMoneyCard(Player p, int amount) {
		p.getAccount().addBalance(amount);
	}
	
	/**
	 * It is Player1's Birthday, get money from everyone else.
	 * @param p
	 * @param plist
	 * @param amount
	 */
	public void recieveMoneyCardAdvanced(Player p, PlayerList plist, int amount) {
		for (int i = 0; i < plist.getLength(); i++) {
			if (plist.getPlayer(i).getName() != p.getName()) {
				p.getAccount().addBalance(amount);
				plist.getPlayer(i).getAccount().addBalance(-amount);
			}
		}
	}

	/**
	 * Burn that sweet cash yo.
	 * @param p
	 * @param amount
	 */
	public void payMoneyCard(Player p, int amount) {
		p.getAccount().addBalance(-amount);
	}
	
	/**
	 * Oil crises or Taxes
	 * @param p
	 * @param plist
	 * @param amount
	 * @param advancedAmount
	 */
	public void payMoneyCardAdvanced(Player p, PlayerList plist, int amount, int advancedAmount) {
		p.getAccount().addBalance(-(p.getAccount().getHousesowned()*amount));
		p.getAccount().addBalance(-(p.getAccount().getHotelsowned()*advancedAmount));
	}
	
	/**
	 * Checks players worth, then gives the player money if it is lower then the amount.
	 * @param p
	 * @param plist
	 * @param amount
	 * @param advancedAmount
	 */
	public void payMoneyCardSuperAdvanced(Player p, PlayerList plist, int amount, int advancedAmount) {
		if (p.getAccount().getPlayerWorth(p) <= amount)
			p.getAccount().addBalance(advancedAmount);
	}
	
	
//	if (this.action < 4)
//	{
//		switch (this.action) {
//		case 1: // MatadorGrant

//			break;
//		case 2: // Increase in taxes
//		
//			break;
//		case 3: // Increase in Oil
//			p.setBalance(-(p.getAccount().getHousesowned()*500));
//			p.setBalance(-(p.getAccount().getHotelsowned()*2000));
//			break;
//		default:
//			System.err.println("Something went wrong in Card Sub Class PayMoney");
//			break;
//		}
//	}
//	else
//	{
//		p.addBalance(p.getBalance()-this.action);
//	}
	
	
	
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
	

	


	
}
