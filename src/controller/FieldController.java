package controller;

import java.awt.Color;
import java.io.IOException;

import boundary.AudioPlayer;
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
import main.Main;

/**
 * The FieldController controls the gameboard and the main responsibility of the controller, is to evaluate actions to be executed, when a player lands on a given field.
 *
 */

public class FieldController {

	private GameBoard gameBoard;
	private HouseController hc;
	private String[] description;
	
	/**
	 * FieldControllers contructor 
	 * @param text
	 * a text reader loaded from a TextReader class
	 */
	public FieldController(TextReader text) {
		this.gameBoard = new GameBoard(text);
		this.hc = new HouseController(text);
		
		try {
			this.description = text.textFromFile(Main.class.getResourceAsStream("rsc/FieldController.txt"));
		} catch (IOException e) {
			System.err.println("Something went wrong when trying to import Text from TextReader in FieldController: " + e);
			e.printStackTrace();
		}
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
	public void evaluateField(Field field, GUIController gui, Player p, int diceSum, DeckController dc, PlayerList plist, AudioPlayer dac) {

		//Denne if/else if statement tager højde for hvilket felt der bliver landet på, og udfører den givne logik på feltet.
		if (field instanceof Territory) {
			territoryLogic(field, gui, plist, p, dac);
		} else if (field instanceof Tax) {
			taxLogic(field, gui, p, dac);
		} else if (field instanceof Company) {
			companyLogic(field, gui, p, diceSum, dac);
		} else if (field instanceof Shipping) {
			shippingLogic(field, gui, p, dac);
		} else if (field instanceof Jail) {
			jailLogic(gui, p);
		} else if (field instanceof GoToJail) {
			goToJailLogic(gui, p, dac);
		} else if (field instanceof Parking) {
			parkingLogic(gui, p);
		} else if (field instanceof Chance) {
			chanceLogic(gui, p, dc, plist, dac);
		}

	}
	
	/**
	 * makes the territory logic, when the player lands on a territory.
	 * @param field
	 * Current field
	 * @param gui
	 * Instance of GUIController
	 * @param plist
	 * Instance of PlayerList
	 * @param p
	 * The current playing player
	 * @param gb
	 * Instance of GameBoard
	 */
	private void territoryLogic(Field field, GUIController gui, PlayerList plist, Player p, AudioPlayer dac) {
		
		//is the field owned?
		if(field.isOwned() == false) {
			//boolean take the decision from the user. True = buy, False = place on auction
			boolean decision = gui.territoryDecision(p); 															

			//make the logic if the user want to buy the current field.
			if (decision == true) {
				dac.playCoinSound();
				p.getAccount().buyField(field.getPrice()); 														
				p.getAccount().setTerritories((p.getAccount().getTerritories() + 1)); 							
				p.getAc().addField(field, this); 															
				gui.updateBalance(p); 																			
				field.setOwner(p); 																				
				field.setOwned(true); 																			
				gui.setOwnerText(p); 																			

			} else {

				String buyer = gui.auctionTerritory(p, plist, field);

				//is there a owner?
				if (!(buyer.equals(this.description[0]))) {
					//the input price from the user
					int price = gui.priceToSell();//ændring her
					dac.playCoinSound();
					//loop through the playerlist, and when the player(i) matches the buyer, then begin all the buy field things.
					for (int i = 0; i < plist.getLength(); i++) 
						if (plist.getPlayer(i).getName().equals(buyer)) {
							plist.getPlayer(i).getAc().addField(field, this);
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

			//gui messege about the transaction
			gui.transaction(decision, field, p);

		} else {

			//is the player not the same as the field owner
			if (field.getOwner().getName() != p.getName()) {

				//checker = have all the fields in one color code
				boolean checker = checkAllOfAKind(field);

				//if you have all fields in a color code, and have no houses rent * 2. Else pay the normal rent.
				if (checker == true && field.getHouses() == 0) {
					payRent(p, field, gui, 2); 
					dac.playCoinSound();
				} else {
					payRent(p, field, gui);
					dac.playCoinSound();
				}	
			}
		}
	}


	/**
	 * Pays the rent from the current playing player to the field owner.
	 * @param p
	 * The current playing player
	 * @param field
	 * Instans of Field
	 * @param gui
	 * instans of the GUI
	 */
	private void payRent(Player p, Field field, GUIController gui) {
		p.getAccount().setBalance(p.getAccount().getBalance() - field.getCurrentRent());
		field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + field.getCurrentRent());

		gui.payRentMessage(field, p);
		gui.updateBalance(p);
		gui.updateBalance(field.getOwner());
	}


	/**
	 * Pays double rent from the current playing player to the field owner
	 * @param p
	 * The current playing player
	 * @param field
	 * Instans of Field
	 * @param gui
	 * Instans of the GUI
	 * @param multiplier
	 * A int that multiply the rent
	 */ 
	private void payRent(Player p, Field field, GUIController gui, int multiplier) {
		p.getAccount().setBalance(p.getAccount().getBalance() - (field.getCurrentRent() * multiplier));
		field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + (field.getCurrentRent()*multiplier));

		gui.payRentMessage(field, p, multiplier);
		gui.updateBalance(p);
		gui.updateBalance(field.getOwner());
	}

	/**
	 * Checks if the owner of a fields, owns all the other of that color.
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


	/**
	 * makes the tax logic, when the player lands on a tax field.
	 * @param field
	 * The current field
	 * @param gui
	 * Instance of GUIController
	 * @param p
	 * The current playing PLayer
	 */
	private void taxLogic(Field field, GUIController gui, Player p, AudioPlayer dac) {

		//if you land on the field "indkomstskat" you have two decisions. True = pay x amount, False = pay 10% of playerworth
		if (field == this.getField(4)) {

			boolean dicision = gui.taxDecision(p);

			if (dicision == true) {
				//pays tax
				p.getAccount().setBalance(p.getAccount().getBalance() - field.getPrice());
				gui.updateBalance(p);

			} else {
				//pays 10% of playerworth
				p.getAccount().setBalance(p.getAccount().getBalance() - (int)(p.getAccount().getPlayerWorth() * 0.1)); 
				gui.updateBalance(p);

			}

		} else {
			//if you land on 2000 - tax field. Then you pay 2000.
			gui.taxMessage(p);
			p.getAccount().setBalance(p.getAccount().getBalance() - field.getPrice());
			gui.updateBalance(p);

		}
		dac.playCoinSound();

	}

	/**
	 * makes the company logic, when the player lands on a company field.
	 * @param field
	 * The current field
	 * @param gui
	 * Instance of GUIController
	 * @param p
	 * The current playing player
	 * @param diceSum
	 * A int that takes in the dicesum.
	 */
	private void companyLogic(Field field, GUIController gui, Player p, int diceSum, AudioPlayer dac) {

		//if the field isn´t owned, the current playing player can buy it.
		if(field.isOwned() == false) {
			boolean decision = gui.companyDecision(p);

			//if the player wants to buy it, then the action below runs.
			if (decision == true) {
				dac.playCoinSound();
				p.getAccount().buyField(field.getPrice());
				gui.updateBalance(p);

				p.getAccount().setCompanies(p.getAccount().getCompanies() + 1);
				field.setOwner(p);
				field.setOwned(true);
				gui.setOwnerText(p);

			}
			//GUI messege about the transaction
			gui.transaction(decision, field, p);

		} else {

			//if the current player isn´t the owner, the player pays rent. There are 2 options of rent. 100*dicesum and 200*dicesum. 
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

				dac.playCoinSound();
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
	private void shippingLogic(Field field, GUIController gui, Player p, AudioPlayer dac) {

		//if the field isn´t owned, the current playing player can buy it.
		if(field.isOwned() == false) {
			boolean decision = gui.shippingDecision(p);

			//if the player wants to buy it, then the action below runs.
			if (decision == true) {
				dac.playCoinSound();
				p.getAccount().buyField(field.getPrice());
				gui.updateBalance(p);

				p.getAccount().setShipping(p.getAccount().getShipping() + 1);
				field.setOwner(p);
				field.setOwned(true);
				gui.setOwnerText(p);

			}
			//GUI message about the transaction
			gui.transaction(decision, field, p);

		} else {

			//if the current player isn´t the owner, the player pays rent. There are 4 options of rent defined by how many the owner own.
			if (field.getOwner().getName() != p.getName()) {

				//the loop loops through 1-4 and applies the action acording to the number of owned shipping fields.
				for (int i = 1; i < 5; i++) 
					if (field.getOwner().getAccount().getShipping() == i) {
						gui.payRentShippingMessage(field, p);
						p.getAccount().setBalance(p.getAccount().getBalance() - field.getRent()[i-1]);
						field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + field.getRent()[i-1]);
					}
				dac.playCoinSound();
				gui.updateBalance(p);
				gui.updateBalance(field.getOwner());

			} 

		}

	}

	/**
	 * Give a visit jail message
	 * @param gui
	 * Instance of GUIController
	 * @param p
	 * The current playing player
	 */
	private void jailLogic(GUIController gui, Player p) {
		gui.visitJailMessage(p);
	}

	/**
	 * Move the player to jail and place the setInJail to true
	 * @param gui
	 * Instance og GUIController
	 * @param p
	 * The current playing player
	 */
	private void goToJailLogic(GUIController gui, Player p, AudioPlayer dac) {
		gui.goToJailMessage(p);
		p.setInJail(true);
		gui.movePlayerInstantly(p, 10, false, this);
		dac.playJailSound();
	}

	/**
	 * Give a parking message
	 * @param gui
	 * Instance of GUIController
	 * @param p
	 * The current playing player
	 */
	private void parkingLogic(GUIController gui, Player p) {
		gui.parkingMessage(p);
	}

	/**
	 * make the chance card logic, when you land on a chance field
	 * @param gui
	 * Instance of GUIController
	 * @param p
	 * The current playing player 
	 * @param dc
	 * Instance of DeckController
	 * @param plist
	 * Instance of PlayerList
	 */
	private void chanceLogic(GUIController gui, Player p, DeckController dc, PlayerList plist, AudioPlayer dac) {

		//pick a card and do the logic from the card
		dc.chanceField(p, plist, gui, this, dac);

		//loops through the playerlist and update the balance.
		for (int i = 0; i < plist.getLength(); i++) 
			gui.updateBalance(plist.getPlayer(i));

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
	 * Get HouseController
	 * @return
	 */
	public HouseController getHc() {
		return this.hc;
	}

	//--------------------------------------------------------//
	//														 //
	//                   Getters & Setters!					 //
	//														 //
	//--------------------------------------------------------//
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
