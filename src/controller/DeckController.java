package controller;

import boundary.GuiController;
import boundary.TextReader;
import entity.deck.AntiJailCard;
import entity.deck.Card;
import entity.deck.Deck;
import entity.deck.GoToJailCard;
import entity.deck.MovePlayerCard;
import entity.deck.MovePlayerBackCard;
import entity.deck.MovePlayerToNearestShippingCard;
import entity.deck.PayMoneyCard;
import entity.deck.PayMoneyPrHouseHotelCard;
import entity.deck.GetMoneyIfWorthIsLowCard;
import entity.deck.RecieveMoneyCard;
import entity.deck.BirthdayCard;
import entity.gameboard.GameBoard;
import entity.gameboard.Shipping;
import entity.player.Player;
import entity.player.PlayerList;

public class DeckController {

	private Deck deck;
	private boolean firstGameCycle;
	private Card firstCardPicked;

	public DeckController(TextReader text) {
		this.deck = new Deck(text);
		this.deck.shuffleCards();
	}

	/**
	 * Code needed by Chancefields.
	 * @param p Needs a specific player
	 * @param plist Needs the player list.
	 * @param gameboard Needs a gameboard.
	 * @param gui Needs the gui to move the player.
	 * @return
	 */
	public String chanceField(Player p, PlayerList plist, GameBoard gameboard, GuiController gui) {

		Card cardPicked = this.deck.pickACard();
		
		// Pick another card if the card picked is already owned by someone.
		if (cardPicked instanceof AntiJailCard && cardPicked.isCardOwned() == true) {
			chanceField(p, plist, gameboard, gui);
		}
		
		// Saves the first card picked, If the first card picked is picked again, shuffle the deck and pick another card.
		if(!firstGameCycle) {
		    firstCardPicked = this.deck.getCard(this.deck.getLength()-1);
		    firstGameCycle = true;
		} else if (cardPicked == firstCardPicked) {
			this.deck.shuffleCards();
			cardPicked = this.deck.pickACard();
			firstGameCycle = false;
		}
		
		// Logic that look at which card is picked, and afterwords runs the appopriate function.
		if (cardPicked instanceof RecieveMoneyCard) {
			recieveMoneyCard(p, cardPicked.getAmount());
		}
		else if (cardPicked instanceof BirthdayCard) {
			birthdayCard(p, plist, cardPicked.getAmount());
		}
		else if (cardPicked instanceof PayMoneyCard) {
			payMoneyCard(p, cardPicked.getAmount());
		}
		else if (cardPicked instanceof PayMoneyPrHouseHotelCard) {
			payMoneyPrHouseHotelCard(p, plist, cardPicked.getAmount(), cardPicked.getAdvancedAmount());
		}
		else if (cardPicked instanceof GetMoneyIfWorthIsLowCard) {
			GetMoneyIfWorthIsLowCard(p, plist, cardPicked.getAmount(), cardPicked.getAdvancedAmount());
		}
		else if (cardPicked instanceof AntiJailCard) {
			antiJailCard(p, cardPicked);
		}
		else if (cardPicked instanceof GoToJailCard) {
			goToJailCard(p, cardPicked.getAmount(), gui);
		}
		else if (cardPicked instanceof MovePlayerCard) {
			moverPlayerCard(p, cardPicked.getAmount(), gui);
		}
		else if (cardPicked instanceof MovePlayerBackCard) {
			moverPlayerBackCard(p, cardPicked.getAmount(), gui);
		}
		else if (cardPicked instanceof MovePlayerToNearestShippingCard) {
			moverPlayerToNearestShippingCard(p, gameboard, gui);
		}

		// returns the Description of a card to be displayed in the GUI
		return cardPicked.getDescription();

	}

	/**
	 * Gives the player an amount of money.
	 * @param p
	 * @param amount
	 */
	private void recieveMoneyCard(Player p, int amount) {
		p.getAccount().addBalance(amount);
	}

	/**
	 * It is Player1's Birthday, get money from everyone else.
	 * @param p
	 * @param plist
	 * @param amount
	 */
	private void birthdayCard(Player p, PlayerList plist, int amount) {
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
	private void payMoneyCard(Player p, int amount) {
		p.getAccount().addBalance(-amount);
	}

	/**
	 * Oil crises or Taxes
	 * @param p
	 * @param plist
	 * @param amount
	 * @param advancedAmount
	 */
	private void payMoneyPrHouseHotelCard(Player p, PlayerList plist, int amount, int advancedAmount) {
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
	private void GetMoneyIfWorthIsLowCard(Player p, PlayerList plist, int amount, int advancedAmount) {
		if (p.getAccount().getPlayerWorth(p) <= amount)
			p.getAccount().addBalance(advancedAmount);
	}

	/**
	 * Get an anti Jail card.
	 * @param p
	 * @param cardPicked
	 */
	private void antiJailCard(Player p, Card cardPicked) {
		this.deck.getCard(this.deck.getLength()).addRemoveCardOwner(p.getName(), true);
		p.getAccount().recieveAntiJaulCard(cardPicked);
	}

	/**
	 * Sents the player to prison.
	 * @param p
	 */
	private void goToJailCard(Player p, int newPosition, GuiController gui) {
		gui.movePlayerInstantly(p, newPosition, false);
		p.setInJail(true);
	}

	/**
	 * Standard move player card.
	 * @param p
	 * @param newPosition
	 * @param gui
	 */
	private void moverPlayerCard(Player p, int newPosition, GuiController gui) {
		gui.movePlayerInstantly(p, newPosition, true);
	}

	/**
	 * Move the player back depending on card value.
	 * @param p
	 * @param newPosition
	 * @param gui
	 */
	private void moverPlayerBackCard(Player p, int newPosition, GuiController gui) {
		gui.movePlayerBackwards(p, newPosition);
	}
	
	
	/**
	 * Move player to the nearest Shipping field.
	 * @param p
	 * @param gameboard
	 * @param gui
	 */
	public void moverPlayerToNearestShippingCard(Player p, GameBoard gameboard, GuiController gui) {
		int iMod = 0;
		int calculateNewPosition = 0;
		int i;
		for (i = p.getPosition(); i < gameboard.getLength()*2; i++) {
			iMod = i%gameboard.getLength();
			if ((gameboard.getField(iMod) instanceof Shipping)) {
				break;
			}
			calculateNewPosition++;
		}
		System.out.println(calculateNewPosition);
		gui.movePlayer(p, calculateNewPosition);
		
		if (gameboard.getField(iMod).isOwned() == true) {
			int payRecieve = (gameboard.getField(iMod).getRent()[gameboard.getField(iMod).getOwner().getAccount().getShipping()]*2);
			p.getAccount().addBalance(-payRecieve);
			gameboard.getField(iMod).getOwner().getAccount().addBalance(payRecieve);
		}
		
	}

	/**
	 * Remove anti Jail card from Player
	 * @param p
	 */
	public void removeAntiJailCard(Player p) {
		for (int i = 0; i <= deck.getLength(); i++) {
			if (deck.getCard(i) instanceof AntiJailCard && p.getName() == deck.getCard(i).getCardOwner()) {
				this.deck.getCard(i).addRemoveCardOwner(null, false);
				p.getAccount().removeAntiJaulCard();
				break;
			}
		}
	}

}