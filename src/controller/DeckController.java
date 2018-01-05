package controller;

import boundary.GuiController;
import boundary.TextReader;
import entity.deck.AntiJailCard;
import entity.deck.Card;
import entity.deck.Deck;
import entity.deck.GoToJailCard;
import entity.deck.MovePlayerCard;
import entity.deck.MovePlayerCardAdvanced;
import entity.deck.MovePlayerCardSuperAdvanced;
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

	public DeckController(TextReader text) {
		this.deck = new Deck(text);
		this.deck.shuffleCards();
	}

	/**
	 * Use when landing on a chanceField.
	 * @param p
	 * @param plist
	 * @param gameboard
	 * @return
	 */
	public String chanceField(Player p, PlayerList plist, GameBoard gameboard, GuiController gui) {

		Card cardPicked = this.deck.pickACard();

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
			antiJailCard(p, cardPicked);
		}
		else if (cardPicked instanceof GoToJailCard) {
			goToJail(p, cardPicked.getAmount(), gui);
		}
		else if (cardPicked instanceof MovePlayerCard) {
			moverPlayerCard(p, cardPicked.getAmount(), gui);
		}
		else if (cardPicked instanceof MovePlayerCardAdvanced) {
			moverPlayerCardAdvanced(p, cardPicked.getAmount(), gui);
		}
		else if (cardPicked instanceof MovePlayerCardSuperAdvanced) {
			moverPlayerCardSuperAdvanced(p, gameboard, gui);
		}

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
	private void recieveMoneyCardAdvanced(Player p, PlayerList plist, int amount) {
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
	private void payMoneyCardAdvanced(Player p, PlayerList plist, int amount, int advancedAmount) {
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
	private void payMoneyCardSuperAdvanced(Player p, PlayerList plist, int amount, int advancedAmount) {
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
	private void goToJail(Player p, int newPosition, GuiController gui) {
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
	private void moverPlayerCardAdvanced(Player p, int newPosition, GuiController gui) {
		gui.movePlayerBackwards(p, newPosition);
	}
	
	
	/**
	 * Move player to the nearest Shipping field.
	 * @param p
	 * @param gameboard
	 * @param gui
	 */
	public void moverPlayerCardSuperAdvanced(Player p, GameBoard gameboard, GuiController gui) {
		int calculateNewPosition = 0;
		int i = 0;
		for (i = p.getPosition(); i < gameboard.getLength(); i++) {
			if (!(gameboard.getField(i%gameboard.getLength()) instanceof Shipping)) {
				calculateNewPosition++;
			}
		}
		calculateNewPosition++;
		System.out.println(calculateNewPosition);
		gui.movePlayer(p, calculateNewPosition);
		
		if (gameboard.getField(i).isOwned() == true) {
			int payRecieve = (gameboard.getField(i).getRent()[gameboard.getField(i).getOwner().getAccount().getShipping()]*2);
			p.getAccount().addBalance(-payRecieve);
			gameboard.getField(i).getOwner().getAccount().addBalance(payRecieve);
		}
		
	}

	/**
	 * Remove anti Jail card from Player
	 * @param p
	 */
	public void removeantiJailCard(Player p) {
		for (int i = 0; i <= deck.getLength(); i++) {
			if (deck.getCard(i) instanceof AntiJailCard && p.getName() == deck.getCard(i).getCardOwner()) {
				this.deck.getCard(i).addRemoveCardOwner(null, false);
				p.getAccount().removeAntiJaulCard();
				break;
			}
		}
	}

}