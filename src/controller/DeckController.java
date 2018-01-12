package controller;

import boundary.AudioPlayer;
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

	/**
	 * Local attributes.
	 */
	private Deck deck;
	private boolean firstGameCycle;
	private Card firstCardPicked;

	/**
	 * Constructor for DeckController.
	 * @param text
	 */
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
	public void chanceField(Player p, PlayerList plist, GUIController gui, FieldController fc, AudioPlayer dac) {

		// Picks the first card in a deck, using the helping method form deck "pickACard()".
 		Card cardPicked = this.deck.pickACard();

		// Pick another card if the card picked is already owned by someone.
		if (cardPicked instanceof AntiJailCard && cardPicked.isCardOwned() == true) {
			chanceField(p, plist, gui, fc, dac);
		}

		// Saves the first card picked, If the first card picked is then picked again later in game, then shuffle the deck and pick another card.
		if(!firstGameCycle) {
			firstCardPicked = this.deck.getLastCard();
			firstGameCycle = true;
		} else if (cardPicked == firstCardPicked) {
			this.deck.shuffleCards();
			chanceField(p, plist, gui, fc, dac);
			firstGameCycle = false;
		}

		gui.chanceMessage(cardPicked.getDescription());
		gui.showMessage();
		
		// Logic that looks at which card is picked, and afterwords runs the appopriate function.
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
			goToJailCard(p, cardPicked.getAmount(), gui, dac, fc);
		}
		else if (cardPicked instanceof MovePlayerCard) {
			movePlayerCard(p, plist, cardPicked.getAmount(), gui, fc, dac);
		}
		else if (cardPicked instanceof MovePlayerBackCard) {
			movePlayerBackCard(p, plist, cardPicked.getAmount(), gui, fc, dac);
		}
		else if (cardPicked instanceof MovePlayerToNearestShippingCard) {
			moverPlayerToNearestShippingCard(p, plist, gui, fc, dac);
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
	 * Oil crises or Taxes Card, pay for what you own of houses and hotels. 
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
	 * Checks players worth, then gives the player money if it is lower then the amount, default 15_000.
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
	 * Get an anti Jail card. Store it in the players account.
	 * @param p
	 * @param cardPicked
	 */
	private void antiJailCard(Player p, Card cardPicked) {
		this.deck.getLastCard().addRemoveCardOwner(p.getName(), true);
		p.getAccount().recieveAntiJaulCard(cardPicked);
	}

	/**
	 * Moves the player to prison.
	 * @param p
	 */
	private void goToJailCard(Player p, int newPosition, GUIController gui, AudioPlayer dac, FieldController fc) {
		p.setInJail(true);
		gui.movePlayerInstantly(p, newPosition, false, fc);
		dac.playJailSound();
	}

	/**
	 * Moves the player to a specific location on the gameBoard, described by the card.
	 * @param p
	 * @param newPosition
	 * @param gui
	 */
	private void movePlayerCard(Player p, PlayerList plist, int newPosition, GUIController gui, FieldController fc, AudioPlayer dac) {
		gui.movePlayerInstantly(p, newPosition, true, fc);
		fc.evaluateField(fc.getField(newPosition), gui, p, 0, this, plist, dac);
	}

	/**
	 * Move the player back depending on card value.
	 * @param p
	 * @param newPosition
	 * @param gui
	 */
	private void movePlayerBackCard(Player p, PlayerList plist, int newPosition, GUIController gui, FieldController fc, AudioPlayer dac) {
		gui.movePlayerBackwards(p, newPosition, fc, dac);
		fc.evaluateField(fc.getField(p.getPosition()), gui, p, 0, this, plist, dac);
	}

	/**
	 * Moves the player to the cloeset shipping field,
	 * @param p
	 * @param plist
	 * @param gui
	 * @param fc
	 */
	private void moverPlayerToNearestShippingCard(Player p, PlayerList plist, GUIController gui, FieldController fc, AudioPlayer dac) {
		
		// Value of the location the player needs to move to.
		int iMod = 0;
		
		// The amount of speces between the player and the shipping field.
		int calculateNewPosition = 0;
		
		// Loops the gameboard twice, but breaks if it finds a shipping field.
		for (int i = p.getPosition(); i < fc.getBoardLength()*2; i++) {
			iMod = i%fc.getBoardLength();
			if ((fc.getField(iMod) instanceof Shipping)) {
				break;
			}
			calculateNewPosition++;
		}

		// Uses the calculated new amount to move the player.
		gui.movePlayer(p, calculateNewPosition, fc, dac);

		// If the field is owned, pay the onwer twice the normal amount, if not, the player can buy the field.
		if (fc.getField(iMod).isOwned() == true) {
			int payRecieve = (fc.getField(iMod).getRent()[fc.getField(iMod).getOwner().getAccount().getShipping()]*2);
			p.getAccount().addBalance(-payRecieve);
			fc.getField(iMod).getOwner().getAccount().addBalance(payRecieve);
		} else {
			fc.evaluateField(fc.getField(p.getPosition()), gui, p, 0, this, plist, dac);
		}
	}

	/**
	 * Removes anti Jail card from Player.
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