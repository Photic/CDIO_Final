package controller;

import java.awt.Color;

import boundary.TextReader;
import entity.gameboard.Chance;
import entity.gameboard.Company;
import entity.gameboard.Field;
import entity.gameboard.GameBoard;
import entity.gameboard.GoToJail;
import entity.gameboard.Jail;
import entity.gameboard.Parking;
import entity.gameboard.Shipping;
import entity.gameboard.Tax;
import entity.gameboard.Territory;
import entity.player.Player;
import entity.player.PlayerList;

/**
 * The FieldController controls the gameboard and the main responsibility of the controller, is to evaluate actions to be executed when a player lands on a given field.
 *
 */

public class FieldController {

	private GameBoard gameBoard;
	private HouseController hc;



	public HouseController getHc() {
		return hc;
	}

	public FieldController(TextReader name) {
		this.gameBoard = new GameBoard(name);
		this.hc = new HouseController();
	}

	/**
	 * 
	 * @param field
	 * The current field
	 * @param gui
	 * The gui controller
	 * @param p
	 * The player who landed on the field
	 */
	public void evaluateField(Field field, GUIController gui, Player p, int diceSum, DeckController dc, PlayerList plist) {

		if (field instanceof Territory) {

			territoryLogic(field, gui, plist, p, this.gameBoard);

		} else if (field instanceof Tax) {

			taxLogic(field, gui, p);

		} else if (field instanceof Company) {

			companyLogic(field, gui, p, diceSum);

		} else if (field instanceof Shipping) {

			shippingLogic(field, gui, p);

		} else if (field instanceof Jail) {

			jailLogic(gui, p);

		} else if (field instanceof GoToJail) {

			goToJailLogic(gui, p);

		} else if (field instanceof Parking) {

			parkingLogic(gui, p);

		} else if (field instanceof Chance) {

			chanceLogic(gui, p, dc, plist);

		}

	}

	private void territoryLogic(Field field, GUIController gui, PlayerList plist, Player p, GameBoard gb) {

		if(field.isOwned() == false) {
			boolean decision = gui.territoryDecision(p);

			if (decision == true) {
				p.getAccount().buyField(field.getPrice());
				p.getAccount().setTerritories((p.getAccount().getTerritories() + 1));

				p.getAccount().addField(field, this);

				gui.updateBalance(p);

				field.setOwner(p);
				field.setOwned(true);
				gui.setOwnerText(p);

			} else {

				String buyer = gui.auctionTerritory(p, plist, field);

				
				
				if (!(buyer.equals("Ingen Køber"))) {
					int price = gui.priceToSell();
					for (int i = 0; i < plist.getLength(); i++) 
						if (plist.getPlayer(i).getName().equals(buyer)) {
							plist.getPlayer(i).getAccount().addField(field, this);
							plist.getPlayer(i).getAccount().buyField(field.getPrice() + price);

							field.setOwned(true);
							field.setOwner(plist.getPlayer(i));

							gui.updateBalance(plist.getPlayer(i));
							gui.updateBalance(p);
							gui.updateDescription(field);
							gui.updateSubtext(plist.getPlayer(i), field);

						}
				}








			}

			gui.transaction(decision, field, p);

		} else {

			if (field.getOwner().getName() != p.getName()) {

				boolean checker = checkAllOfAKind(field);

				if (checker == true && field.getHouses() == 0) {
					payRent(p, field, gui, 2);
				} else {
					payRent(p, field, gui);
				}	
			}
		}
	}



	private void payRent(Player p, Field field, GUIController gui) {
		p.getAccount().setBalance(p.getAccount().getBalance() - field.getCurrentRent());
		field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + field.getCurrentRent());


		gui.payRentMessege(field, p);
		gui.updateBalance(p);
		gui.updateBalance(field.getOwner());
	}


	private void payRent(Player p, Field field, GUIController gui, int multiplier) {
		p.getAccount().setBalance(p.getAccount().getBalance() - (field.getCurrentRent() * multiplier));
		field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + (field.getCurrentRent()*multiplier));


		gui.payRentMessege(field, p, multiplier);
		gui.updateBalance(p);
		gui.updateBalance(field.getOwner());
	}

	/**
	 * Checks if the owner of a fields, owns the other of that color.
	 * @param field
	 * The field to be investigated
	 * @return
	 */
	private boolean checkAllOfAKind(Field field) {
		boolean checker = false;
		if (field.getColor() == this.gameBoard.getRed())
			checker = field.getOwner().getAccount().isAllred();

		if (field.getColor() == this.gameBoard.getBlue())
			checker = field.getOwner().getAccount().isAllblue();

		if (field.getColor() == this.gameBoard.getPink())
			checker = field.getOwner().getAccount().isAllpink();

		if (field.getColor() == this.gameBoard.getGreen())
			checker = field.getOwner().getAccount().isAllgreen();

		if (field.getColor() == this.gameBoard.getGrey())
			checker = field.getOwner().getAccount().isAllgrey();

		if (field.getColor() == this.gameBoard.getWhite())
			checker = field.getOwner().getAccount().isAllwhite();

		if (field.getColor() == this.gameBoard.getYellow())
			checker = field.getOwner().getAccount().isAllyellow();

		if (field.getColor() == this.gameBoard.getPurple())
			checker = field.getOwner().getAccount().isAllpurple();


		return checker;
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}

	private void taxLogic(Field field, GUIController gui, Player p) {

		if (field.getPrice() == 4000) {

			boolean dicision = gui.taxDecision(p);

			if (dicision == true) {

				p.getAccount().setBalance(p.getAccount().getBalance() - field.getPrice());
				gui.updateBalance(p);

			} else {

				p.getAccount().setBalance(p.getAccount().getBalance() - (int)(p.getAccount().getPlayerWorth(p) * 0.1));
				gui.updateBalance(p);

			}

		} else {

			gui.taxMessage(p);
			p.getAccount().setBalance(p.getAccount().getBalance() - field.getPrice());
			gui.updateBalance(p);

		}

	}

	private void companyLogic(Field field, GUIController gui, Player p, int diceSum) {

		if(field.isOwned() == false) {
			boolean decision = gui.companyDecision(p);

			if (decision == true) {

				p.getAccount().buyField(field.getPrice());
				gui.updateBalance(p);

				p.getAccount().setCompanies(p.getAccount().getCompanies() + 1);
				field.setOwner(p);
				field.setOwned(true);
				gui.setOwnerText(p);


			}

			gui.transaction(decision, field, p);

		} else {

			if (field.getOwner().getName() != p.getName()) {

				int payment;
				if (field.getOwner().getAccount().getCompanies() == 1) {
					payment = (100 * diceSum);
					p.getAccount().setBalance(p.getAccount().getBalance() - payment);
					field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + payment);				
				} else {
					payment = (200 * diceSum);
					p.getAccount().setBalance(p.getAccount().getBalance() - payment);
					field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + payment);
				}

				gui.updateBalance(p);
				gui.updateBalance(field.getOwner());

			}



		}

	}

	/**
	 * Service method to deal with the logic connected to a shipping field.
	 * @param field
	 * The current field
	 * @param gui
	 * The GUIController
	 * @param p
	 * The player who landed on the field.
	 */
	private void shippingLogic(Field field, GUIController gui, Player p) {

		if(field.isOwned() == false) {
			boolean decision = gui.shippingDecision(p);

			if (decision == true) {

				p.getAccount().buyField(field.getPrice());
				gui.updateBalance(p);

				p.getAccount().setShipping(p.getAccount().getShipping() + 1);
				field.setOwner(p);
				field.setOwned(true);
				gui.setOwnerText(p);

			}
			gui.transaction(decision, field, p);

		} else {

			if (field.getOwner().getName() != p.getName()) {


				for (int i = 1; i < 5; i++) 
					if (field.getOwner().getAccount().getShipping() == i) {
						gui.payRentShippingMessege(field, p);
						p.getAccount().setBalance(p.getAccount().getBalance() - field.getRent()[i-1]);
						field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + field.getRent()[i-1]);
					}

				gui.updateBalance(p);
				gui.updateBalance(field.getOwner());

			} 

		}

	}


	private void jailLogic(GUIController gui, Player p) {
		gui.visitJailMessege(p);
	}

	private void goToJailLogic(GUIController gui, Player p) {
		gui.goToJailMessege(p);
		p.setInJail(true);
		gui.movePlayerInstantly(p, 10, false);
	}

	private void parkingLogic(GUIController gui, Player p) {

		gui.parkingMessege(p);

	}

	private void chanceLogic(GUIController gui, Player p, DeckController dc, PlayerList plist) {

		dc.chanceField(p, plist, gui, this);

		for (int i = 0; i < plist.getLength(); i++) {

			gui.updateBalance(plist.getPlayer(i));

		}

	}

	/**
	 * Get a specific field.
	 * @param i
	 * index
	 * @return
	 */
	public Field getField(int i) {
		return this.gameBoard.getField(i);
	}

	/**
	 * Get the length of the gameboard.
	 * @return
	 */
	public int getBoardLength() {
		return this.gameBoard.getLength();
	}

	/**
	 * Get all Colors.
	 */
	public Color getBlue() {
		return this.gameBoard.getBlue();
	}

	public Color getBrown() {
		return this.gameBoard.getBrown();
	}

	public Color getGreen() {
		return this.gameBoard.getGreen();
	}

	public Color getGrey() {
		return this.gameBoard.getGrey();
	}

	public Color getMagenta() {
		return this.gameBoard.getMagenta();
	}

	public Color getOrange() {
		return this.gameBoard.getOrange();
	}

	public Color getPink() {
		return this.gameBoard.getPink();
	}

	public Color getPurple() {
		return this.gameBoard.getPurple();
	}

	public Color getRed() {
		return this.gameBoard.getRed();
	}

	public Color getTurkies() {
		return this.gameBoard.getTurkies();
	}

	public Color getWhite() {
		return this.gameBoard.getWhite();
	}

	public Color getYellow() {
		return this.gameBoard.getYellow();
	}
}
