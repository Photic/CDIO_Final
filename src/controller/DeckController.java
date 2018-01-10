package controller;

import boundary.TextReader;
import entity.deck.AntiJailCard;
import entity.deck.BirthdayCard;
import entity.deck.Card;
import entity.deck.Deck;
import entity.deck.GetMoneyIfWorthIsLowCard;
import entity.deck.GoToJailCard;
import entity.deck.MovePlayerBackCard;
import entity.deck.MovePlayerCard;
import entity.deck.MovePlayerToNearestShippingCard;
import entity.deck.PayMoneyCard;
import entity.deck.PayMoneyPrHouseHotelCard;
import entity.deck.RecieveMoneyCard;
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
	public void chanceField(Player p, PlayerList plist, GUIController gui, FieldController fc) {

 		Card cardPicked = this.deck.pickACard();

		// Pick another card if the card picked is already owned by someone.
		if (cardPicked instanceof AntiJailCard && cardPicked.isCardOwned() == true) {
			chanceField(p, plist, gui, fc);
		}

		// Saves the first card picked, If the first card picked is picked again, shuffle the deck and pick another card.
		if(!firstGameCycle) {
			firstCardPicked = this.deck.getLastCard();
			firstGameCycle = true;
		} else if (cardPicked == firstCardPicked) {
			this.deck.shuffleCards();
			chanceField(p, plist, gui, fc);
			firstGameCycle = false;
		}

		gui.chanceMessage(cardPicked.getDescription());
		gui.showMessage();
		
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
			payMoneyPrHouseHotelCard(p, plist, fc, cardPicked.getHousePrices());
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
			movePlayerCard(p, plist, cardPicked.getAmount(), gui, fc);
		}
		else if (cardPicked instanceof MovePlayerBackCard) {
			moverPlayerBackCard(p, cardPicked.getAmount(), gui);
		}
		else if (cardPicked instanceof MovePlayerToNearestShippingCard) {
			moverPlayerToNearestShippingCard(p, plist, gui, fc);
		}

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
	private void payMoneyPrHouseHotelCard(Player p, PlayerList plist, FieldController fc, int[] housesTotal) {

		for (int i = 0; i < fc.getBoardLength(); i++) {
			if (fc.getField(i).isOwned()) {
				if (p.getName().equals(fc.getField(i).getOwner().getName())) {
					p.getAccount().addBalance(-(housesTotal[p.getAccount().getHousesowned()]));
				}
			}

		}

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
		this.deck.getLastCard().addRemoveCardOwner(p.getName(), true);
		p.getAccount().recieveAntiJaulCard(cardPicked);
	}

	/**
	 * Sents the player to prison.
	 * @param p
	 */
	private void goToJailCard(Player p, int newPosition, GUIController gui) {
		gui.movePlayerInstantly(p, newPosition, false);
		p.setInJail(true);
	}

	/**
	 * Standard move player card.
	 * @param p
	 * @param newPosition
	 * @param gui
	 */
	private void movePlayerCard(Player p, PlayerList plist, int newPosition, GUIController gui, FieldController fc) {
		gui.movePlayerInstantly(p, newPosition, true);
		fc.evaluateField(fc.getField(newPosition), gui, p, 0, this, plist);
	}

	/**
	 * Move the player back depending on card value.
	 * @param p
	 * @param newPosition
	 * @param gui
	 */
	private void moverPlayerBackCard(Player p, int newPosition, GUIController gui) {
		gui.movePlayerBackwards(p, newPosition);
	}


	/**
	 * Moves the player to the cloeset shipping field.
	 * @param p
	 * @param plist
	 * @param gui
	 * @param fc
	 */
	private void moverPlayerToNearestShippingCard(Player p, PlayerList plist, GUIController gui, FieldController fc) {
		int iMod = 0;
		int calculateNewPosition = 0;
		int i;
		for (i = p.getPosition(); i < fc.getBoardLength()*2; i++) {
			iMod = i%fc.getBoardLength();
			if ((fc.getField(iMod) instanceof Shipping)) {
				break;
			}
			calculateNewPosition++;
		}

		gui.movePlayer(p, calculateNewPosition);

		if (fc.getField(iMod).isOwned() == true) {
			int payRecieve = (fc.getField(iMod).getRent()[fc.getField(iMod).getOwner().getAccount().getShipping()]*2);
			p.getAccount().addBalance(-payRecieve);
			fc.getField(iMod).getOwner().getAccount().addBalance(payRecieve);
		} else {
			fc.evaluateField(fc.getField(p.getPosition()), gui, p, 0, this, plist);
		}

	}

	/**
	 * Remove anti Jail card from Player
	 * @param p
	 */
	public void removeAntiJailCard(Player p) {
		for (int i = 0; i <= deck.getLength(); i++) {
			if (this.deck.getCard(i) instanceof AntiJailCard && p.getName() == this.deck.getCard(i).getCardOwner()) {
				this.deck.getCard(i).addRemoveCardOwner(null, false);
				p.getAccount().removeAntiJaulCard();
				break;
			}
		}
	}

}