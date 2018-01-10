package controller;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import boundary.TextReader;
import entity.DiceCup;
import entity.gameboard.Field;
import entity.player.Player;
import entity.player.PlayerList;
import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

public class GUIController {

	private GUI gui;
	private GUI_Player[] gui_players;
	private int playerCount;
	private String[] description;

	/**
	 * The contructor to the guiController.
	 * @param tr
	 * The textreader. It is used to create a String array with all text used in the GUIController.
	 */
	public GUIController(TextReader tr) {
		try {
			this.description = tr.textFromFile("src/main/rsc/guiText.txt");
		} catch (IOException e) {
			System.out.println("Something went wrong in the GUIController constructor");
			e.printStackTrace();
		}
	}

	/**
	 * Defines the gui. It shows the gameboard on the gui.
	 * @param fc
	 * The FieldController
	 */
	public void defineGUI(FieldController fc) {
		GUI_Field[] gui_fields = new GUI_Field[fc.getBoardLength()];

		for (int i = 0; i < fc.getBoardLength(); i++) {

			gui_fields[i] = new GUI_Street();
			gui_fields[i].setTitle(fc.getField(i).getName());
			gui_fields[i].setSubText(fc.getField(i).getDescription());
			gui_fields[i].setDescription(fc.getField(i).getName());
			gui_fields[i].setBackGroundColor(fc.getField(i).getColor());

		}

		this.gui = new GUI(gui_fields);

	}

	/**
	 * This method registers players.
	 * @return
	 * A PlayerList with the registered players.
	 */
	public PlayerList registerPlayerCount() {

		String[] nopArray = {"2", "3", "4", "5", "6"};																// The array with the options of how many players to register
		String nop = this.gui.getUserSelection(this.description[0], nopArray);										// The choice the players make
		GUI_Car gui_car;																							// Get ready to make a car for every player
		String name;																								// Get ready to save a given playername
		Color[] colors = new Color[] {Color.red, Color.black, Color.blue, Color.yellow, Color.white, Color.green};


		int index;																									// A counter.

		this.playerCount = Integer.parseInt(nop);																	// Define the playerCount. Use the choice from the players
		String[] names = new String[this.playerCount];																// Create a String array with the length of playerCount
		this.gui_players = new GUI_Player[this.playerCount];														// Create a GUI_Player array with the length of playerCount

		for (int i = 0; i <= this.playerCount - 1; i++) {															// Loop through the players
			index = i + 1;																							// define the index
			name = this.gui.getUserString(this.description[1] + index + this.description[2]);						// Ask for a name

			if (name.length() <= 0) 																				// if the player doesn't write anything, then let the player be called JaneDoe
				name = "JaneDoe";

			gui_car = new GUI_Car();																				// Create a car for the player
			gui_car.setPrimaryColor(colors[i]);																		// Give the car a color

			if (!(contains(names, name))) {																			// Make sure the same name is not created twice
				names[i] = name;
			} else {
				int count = 2;
				while(true) {
					String newName = name + "_" + count;
					if (!(contains(names, newName))) {
						names[i] = newName;
						break;
					}
					count++;
					if(count == 7) {
						break;
					}
				}
			}


			this.gui_players[i] = new GUI_Player(names[i], 30000, gui_car);											// Create the gui_player with the name, 30000 starting money and the car.

		}

		PlayerList playerList = new PlayerList(this.playerCount, names);											// Create the player list with the registered players

		String output = this.description[3];																		// Prepare for a message that tells who was registered.
		for (int i = 0; i < playerList.getLength(); i++) {
			output = output + playerList.getPlayer(i).getName() + ", ";

		}

		this.gui.showMessage(output);

		return playerList;

	}

	/**
	 * This method removes a bankrupted player on the gui.
	 * @param p
	 * The player to be removed
	 * @param fc
	 * The fieldController
	 */
	public void removeBankrupted(Player p, FieldController fc) {

		for (int i = 0; i < this.gui_players.length; i++) 
			if (this.gui_players[i].getName() == p.getName()) {
				this.gui.getFields()[p.getPosition()].setCar(this.gui_players[i], false);
				this.gui_players[i].setBalance(0);

				for (int j = 0; j < fc.getBoardLength(); j++) 
					if (fc.getField(j).getOwner() != null) 
						if (fc.getField(j).getOwner().getName() == p.getName()) {
							fc.getField(j).setOwned(false);
							fc.getField(j).setOwner(null);
							this.gui.getFields()[j].setSubText(this.gui.getFields()[j].getDescription());
							this.gui.getFields()[j].setDescription(this.gui.getFields()[j].getTitle());
						}
			}
	}

	/**
	 * This method lets the user select owned fields to build houses on.
	 * 
	 * It regulates, so that people has to build "jævnt".
	 * @param fields
	 * The owned fields that the player has all of the kind in.
	 */
	public void buyHouses(Field[] fields) {
		boolean sameHeight = true;
		int highestHouse = 0;
		String[] output = new String[] {};
		String[] territories = new String[] {};
		String[] c = new String[fields.length];

		//First create an array of unique colors
		int counter = 0;
		for (int i = 0; i < fields.length; i++) 
			if (!(contains(c, fields[i].getColor().toString()))) {
				c[counter] = fields[i].getColor().toString();
				counter++;
			}

		String[] colorCheck = new String[counter];

		for (int i = 0; i < colorCheck.length; i++) 
			colorCheck[i] = c[i];

		for (int k = 0; k < colorCheck.length; k++) {																						// Loop through the unique colors

			int lengthOfArray = 0;
			for (int l = 0; l < fields.length; l++) 																						// Loop through the fields
				if (fields[l].getColor().toString().equals(colorCheck[k])) 																	// I the current field's color is the current color in the unique color list
					lengthOfArray++;


			Field[] currentFields = new Field[lengthOfArray];																				// Now it creates an array with fields of the current color
			int count = 0;

			for (int l = 0; l < fields.length; l++) 
				if (fields[l].getColor().toString().equals(colorCheck[k])) {
					currentFields[count] = fields[l];
					count++;
				}


			for (int i = 0; i < currentFields.length; i++) {																				// Loop through the current fields
				if (currentFields[i].getHouses()>highestHouse)																				// Find the highest house
					highestHouse = currentFields[i].getHouses();																			

				if (!(currentFields[i].getHouses() == currentFields[0].getHouses())) 														// If the current house is different from the first in the array
					sameHeight = false;																										// Then they are not the same height.
			}

			// Now we need to figure out which options the player should have. The player should not be able to build uneven, and thus same heigth defines the options

			if (highestHouse != 5) {																										// If the highest House is not 5 (else nothing happens)

				if (sameHeight == true) {																									// If they are the same heigth
					territories = new String[currentFields.length];																			// All the current fields should be presented as a option to the player

					for (int i = 0; i < territories.length; i++) 
						territories[i] = currentFields[i].getName() + description[4] + currentFields[i].getHouses() + description[5];	

				} else {																													// If they are not the same heigth, only the lower ones should be an option

					int counting = 0;
					for (int i = 0; i < currentFields.length; i++) 																			// Figure out how many of the current fields have lower houses
						if (currentFields[i].getHouses() < highestHouse) 
							counting++;

					territories = new String[counting];																						// Create a new array with that amount.

					int countere = 0;
					for (int i = 0; i < currentFields.length; i++) 																			// Put the fields with low houses in that array
						if (currentFields[i].getHouses() < highestHouse) {
							territories[countere] = currentFields[i].getName() + description[4] + currentFields[i].getHouses() + description[5];
							countere++;
						}
				}
			}

			output = combineStringArrays(output, territories);																				// Combine the current territories into the output array. (So that different colors are handled seperately)

		}

		//Now we have fields to be represented ready and ask the user which one he would like to build on.

		String[] fortryd = new String[] {description[55]};																					// Create a return button.
		output = combineStringArrays(output, fortryd);

		String selected = gui.getUserSelection(description[6], output);																		// Ask for user selection
		String real = selected.split(",")[0];																								// Split the string (so that we only have the name of the territory in a string.

		if (!(real.equals(description[55]))) 																								// If the return button is not pressed
			for (int i = 0; i < fields.length; i++) 																						// find the field chosen, and but a house on it and make the owner pay for it.
				if (fields[i].getName().equals(real)) {
					fields[i].getOwner().getAccount().setHousesowned(fields[i].getOwner().getAccount().getHousesowned() + 1);

					fields[i].getOwner().getAccount().addActives(fields[i].getHousePrice());
					fields[i].getOwner().getAccount().addBalance(-fields[i].getHousePrice());
					updateBalance(fields[i].getOwner());

					fields[i].setHouses(fields[i].getHouses() + 1);
					gui.showMessage(description[7] + fields[i].getName() + description[8] + fields[i].getHousePrice() + description[9] + fields[i].getName() + description[10] + fields[i].getHouses() + description[11]);

				}
	} 

	/**
	 * This method deals with the scenario where the user wants to sell houses. It works in much the same way as buy houses, but it handles the selling instead.
	 * @param fields
	 * All the player's fields
	 */
	public void sellHouses(Field[] fields) {
		int count = 0;

		for (int i = 0; i < fields.length; i++) 
			if (fields[i].getHouses() > 0)
				count++;

		if (count == 0) {
			gui.showMessage(this.description[54]);
		} else {
			// Add the fields that has houses to a new array
			String[] hasHouses = new String[count + 1];
			int counter = 0;

			for (int i = 0; i < fields.length; i++) 
				if (fields[i].getHouses()>0) {
					hasHouses[counter] = fields[i].getName() + this.description[4] + fields[i].getHouses() + this.description[5];
					counter++;
				}

			hasHouses[counter] = this.description[55];

			//Find out which territory the player wants to sell a house on
			String selected = this.gui.getUserSelection(this.description[56], hasHouses);
			String real = selected.split(",")[0];

			if (!(real.equals(this.description[55]))) 
				for (int i = 0; i < fields.length; i++) 
					//sell a house on the selected territory
					if (fields[i].getName().equals(real)) {
						fields[i].getOwner().getAccount().setHousesowned(fields[i].getOwner().getAccount().getHousesowned() - 1);

						fields[i].getOwner().getAccount().addActives(-fields[i].getHousePrice());
						fields[i].getOwner().getAccount().addBalance(fields[i].getHousePrice());
						updateBalance(fields[i].getOwner());

						fields[i].setHouses(fields[i].getHouses() - 1);

						this.gui.showMessage(
								this.description[57] + fields[i].getName() + 
								this.description[8] + fields[i].getHousePrice() + 
								this.description[9] + fields[i].getName() + 
								this.description[10] + fields[i].getHouses() + 
								this.description[11]
								);

					}
		}

	}

	/**
	 * Use the gui to show a winner message
	 * @param p
	 */
	public void showWinner(Player p) {

		String output = p.getName() + this.description[12];

		this.gui.showMessage(output);

	}

	/**
	 * Show the different options that the player can chose from when handling his territory
	 * @param p
	 * The current player
	 * @param hasAll
	 * The current players' has all boolean
	 * @return
	 * The choice the player made. A string
	 */
	public String territoryOptions(Player p, boolean hasAll) {
		String[] options;
		if (hasAll == true) {
			options = new String[] {this.description[59], this.description[60], this.description[70],this.description[55]};
		} else {
			options = new String[] {this.description[70],this.description[55]};
		}

		String choice = this.gui.getUserSelection(p.getName() + this.description[58], options);

		return choice;
	}

	/**
	 * Shows the options to the player, when he wants to sell a territory
	 * @param seller
	 * The seller
	 * @param plist
	 * The player lits
	 * @return
	 * The choice he made. A String
	 */
	public String sellTerritory(Player seller, PlayerList plist) {
		String[] playerNames = new String[plist.getLength()];

		int counter = 0;

		playerNames[counter] = description[71];
		counter++;

		for (int i = 0; i < plist.getLength(); i++) 
			if (!(plist.getPlayer(i).getName().equals(seller.getName()))) {
				playerNames[counter] = plist.getPlayer(i).getName();
				counter++;
			}

		String[] fortryd = new String[] {description[55]};
		playerNames = combineStringArrays(playerNames, fortryd);
		String output = gui.getUserSelection(description[72], playerNames);

		return output;

	}

	/**
	 * Deals with the scenario where the territory goes on auction.
	 * @param p
	 * The player who landed on the field
	 * @param plist
	 * The playerList
	 * @param field
	 * The field to be set on auction
	 * @return
	 * The choice made
	 */
	public String auctionTerritory(Player p, PlayerList plist, Field field) {
		String[] playerNames = new String[plist.getLength() - 1];

		int counter = 0;

		for (int i = 0; i < plist.getLength(); i++) 
			if (!(plist.getPlayer(i).getName().equals(p.getName()))) {
				playerNames[counter] = plist.getPlayer(i).getName();
				counter++;
			}

		String[] fortryd = new String[] {description[73]};
		playerNames = combineStringArrays(playerNames, fortryd);

		String output = gui.getUserSelection(field.getName() + description[74], playerNames);

		return output;
	}


	/**
	 * Deals with the scenario where the player chooses to sell a territory
	 * @param p
	 * The player who wants to sell
	 * @return
	 * The field that the player wants to sell
	 */
	public Field sellTerritoryProp(Player p) {

		String[] fieldNames = new String[p.getAccount().getFields().length + 1];

		for (int i = 0; i < p.getAccount().getFields().length; i++) 
			fieldNames[i] = p.getAccount().getFields()[i].getName();

		fieldNames[p.getAccount().getFields().length] = description[55];
		String output = gui.getUserSelection(description[75], fieldNames);
		Field outField = null;

		if (!(output.equals(description[55]))) 
			for (int i = 0; i < p.getAccount().getFields().length; i++) 
				if (p.getAccount().getFields()[i].getName() == output) 
					outField = p.getAccount().getFields()[i];

		return outField;
	}

	/**
	 * A message to ask for how much a player will pay + the fields price
	 * @return
	 */
	public int priceToSell() {

		return gui.getUserInteger(description[76]);
	}

	/**
	 * A message when you want to sell to another player
	 * @return
	 */
	public int priceToSellToOther() {

		return gui.getUserInteger(description[77]);
	}

	/**
	 * Updates the gui descirption on a field.
	 * @param field
	 */
	public void updateDescription(Field field) {

		for (int i = 0; i < gui.getFields().length; i++) 
			if (gui.getFields()[i].getTitle().equals(field.getName())) 
				gui.getFields()[i].setDescription(gui.getFields()[i].getSubText());

	}

	/**
	 * Updates the subtext on the field to the newOwners name
	 * @param newOwner
	 * @param field
	 */
	public void updateSubtext(Player newOwner, Field field) {

		for (int i = 0; i < gui.getFields().length; i++) 
			if (gui.getFields()[i].getTitle().equals(field.getName())) 
				gui.getFields()[i].setSubText(newOwner.getName());

	}

	/**
	 * Updates the subtext back the fields price (if it is on the market again
	 * @param field
	 */
	public void updateSubtextReversed(Field field) {

		for (int i = 0; i < gui.getFields().length; i++) 
			if (gui.getFields()[i].getTitle().equals(field.getName())) {
				gui.getFields()[i].setSubText(gui.getFields()[i].getDescription());
				gui.getFields()[i].setDescription(gui.getFields()[i].getTitle());
			}

	}

	/**
	 * Asks the player if he wants to buy a field
	 * @param p
	 * @return
	 * If he wants to buy or not.
	 */
	public boolean territoryDecision(Player p) {

		this.gui.showMessage(p.getName() + this.description[13]);

		return this.gui.getUserLeftButtonPressed(p.getName() + this.description[14], this.description[15], this.description[16]);

	}

	/**
	 * Asks if the player wants to buy a territory
	 * @param p
	 * @return
	 */
	public boolean companyDecision(Player p) {

		this.gui.showMessage(p.getName() + this.description[17]);

		return this.gui.getUserLeftButtonPressed(p.getName() + this.description[18], this.description[15], this.description[78]);
	}

	/**
	 * Aks if the player wants to buy a shipping field
	 * @param p
	 * @return
	 */
	public boolean shippingDecision(Player p) {

		this.gui.showMessage(p.getName() + this.description[19]);

		return gui.getUserLeftButtonPressed(p.getName() + this.description[20], this.description[15], this.description[78]);

	}

	/**
	 * Asks if the player wishes to pay 10% og 4000 in tax
	 * @param p
	 * @return
	 */
	public boolean taxDecision(Player p) {
		gui.showMessage(description[21]);

		return gui.getUserLeftButtonPressed(
				p.getName() + this.description[22], this.description[23], this.description[24]);
	}

	/**
	 * Show a tax message
	 * @param p
	 */
	public void taxMessage(Player p) {

		gui.showMessage(p.getName() + this.description[25]);

	}

	/**
	 * If the player buys a field, show a message, else show another message
	 * @param b
	 * @param field
	 * @param p
	 */
	public void transaction(boolean b, Field field, Player p) {

		if (b == true) {

			this.gui.showMessage(
					p.getName() + this.description[26] + 
					field.getName() + this.description[27] + 
					field.getPrice() + this.description[28]
					);

		} else {
			this.gui.showMessage(p.getName() + this.description[29] + field.getName());
		}

	}

	/**
	 * Show a message when a player pays rent
	 * @param field
	 * @param p
	 */
	public void payRentMessege(Field field, Player p) {
		this.gui.showMessage(
				p.getName() + this.description[30] + 
				field.getOwner().getName() + this.description[31] + 
				field.getHouses() + this.description[32] + 
				field.getCurrentRent() + this.description[33]
				);
	}

	/**
	 * Show a payrent message, but with a multiplier of the rent
	 * @param field
	 * @param p
	 * @param multiplier
	 */
	public void payRentMessege(Field field, Player p, int multiplier) {
		int rent = multiplier*field.getCurrentRent();
		this.gui.showMessage (
				p.getName() + this.description[30] + 
				field.getOwner().getName() + this.description[31] + 
				field.getHouses() + this.description[66] + 
				field.getOwner().getName() + this.description[67] + 
				p.getName() + this.description[68] + 
				rent + this.description[33]
				);
	}

	/**
	 * Show a message when you pay shipping rent
	 * @param field
	 * @param p
	 */
	public void payRentShippingMessege(Field field, Player p) {

		this.gui.showMessage (
				p.getName() + this.description[30] + 
				field.getOwner().getName() + this.description[34] + 
				field.getOwner().getName() + this.description[35] + 
				field.getOwner().getAccount().getShipping() + this.description[36] + 
				field.getRent()[field.getOwner().getAccount().getShipping() - 1] + this.description[33]
				);
	}

	/**
	 * A message to play when you visit jail.
	 * @param p
	 */
	public void visitJailMessege(Player p) {
		this.gui.showMessage(p.getName() + this.description[37]);
	}

	/**
	 * A message to play when you are put in jail.
	 * @param p
	 */
	public void goToJailMessege(Player p) {
		this.gui.showMessage(p.getName() + this.description[38]);
	}

	/**
	 * A message to play when you are parking.
	 * @param p
	 */
	public void parkingMessege(Player p) {

		this.gui.showMessage(p.getName() + this.description[39]);

	}

	/**
	 * Players a message whe landing on a chance
	 * @param chanceMessege
	 */
	public void chanceMessage(String chanceMessege) {
		this.gui.displayChanceCard(chanceMessege);
		this.gui.displayChanceCard();
	}

	/**
	 * Set the owner text on a field. Requires the player to stand on the field.
	 * @param p
	 */
	public void setOwnerText(Player p) {
		this.gui.getFields()[p.getPosition()].setDescription(this.gui.getFields()[p.getPosition()].getSubText());
		this.gui.getFields()[p.getPosition()].setSubText(p.getName());
	}

	/**
	 * Asks for decisions when the player is in jail.
	 * @param p
	 * @return
	 */
	public int inJailDecision(Player p) {

		int decision = 0;

		if(p.getAccount().getAmountOfCards()>0) {
			String[] jailDecision = {this.description[40], this.description[41], this.description[42]};
			String decisionMade = this.gui.getUserSelection(this.description[43], jailDecision);

			if (decisionMade == this.description[40]) {
				decision = 1;
			} else if (decisionMade == this.description[41]) {
				decision = 2;
			} else if (decisionMade == this.description[42]) {
				decision = 3;
			}

		}
		else {
			String[] jailDecision = {this.description[40], this.description[41]};
			String decisionMade = this.gui.getUserSelection(p.getName() + this.description[43], jailDecision);

			if (decisionMade == this.description[40]) {
				decision = 1;
			} else if (decisionMade == this.description[41]) {
				decision = 2;
			}
		}
		return decision;
	}

	/**
	 * A message when the player is sat free.
	 * @param p
	 */
	public void jailFreePay(Player p) {
		this.gui.showMessage(p.getName() + this.description[44]);
	}

	/**
	 * A message when you are in jail.
	 * @param p
	 */
	public void jailEqualsTrue(Player p) {

		this.gui.showMessage(p.getName() + this.description[45]);

	}

	public void jailEqualsFalse(Player p) {

		this.gui.showMessage(p.getName() + this.description[46]);

	}

	public void antiJailUsed(Player p) {

		this.gui.showMessage(p.getName() + this.description[47]);

	}


	/**
	 * Check if the name is in the array of names
	 * @param names
	 * the array of names
	 * @param name
	 * the name to be checked
	 * @return
	 * True or False
	 */
	private boolean contains(String[] names, String name) {

		boolean output = false;

		for (int i = 0; i < names.length; i++) {
			if (names[i] != null) {
				if (names[i].toLowerCase().equals(name.toLowerCase())) {
					output = true;
				}
			}
		}
		return output;
	}


	/**
	 * Combines to string arrays
	 * @param first
	 * @param second
	 * @return
	 */
	public String[] combineStringArrays(String[] first, String[] second) {

		String[] output = new String[first.length + second.length];
		int counter = 0;

		for (int i = 0; i < first.length; i++) {
			output[counter] = first[i];
			counter++;
		}

		for (int i = 0; i < second.length; i++) {
			output[counter] = second[i];
			counter++;
		}

		return output;
	}


	/**
	 * Places all the players on start.
	 */
	public void placePlayer() {

		for (int j = 0; j < this.gui_players.length; j++) {
			this.gui.getFields()[0].setCar(this.gui_players[j], true);
			this.gui.addPlayer(this.gui_players[j]);
		}
	}


	/**
	 * This is the main move method. It moves the player to a new position given the dicesum.
	 * 
	 * The player recives 400 if he passes start
	 * @param p
	 * The player to be moved.
	 * @param diceSum
	 * The sum of the dice
	 */
	public void movePlayer(Player p, int diceSum) {

		int newPosition = (p.getPosition() + diceSum) % 40;
		int initPosition = (p.getPosition());

		for (int i = 0; i < this.gui_players.length; i++) 
			if (this.gui_players[i].getName() == p.getName()) 
				while(p.getPosition() != newPosition) {
					this.gui.getFields()[p.getPosition()].setCar(this.gui_players[i], false);
					p.setPosition(p.getPosition()+1);
					this.gui.getFields()[p.getPosition()].setCar(this.gui_players[i], true);

					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			

		if (initPosition > newPosition) {
			p.getAccount().setBalance(p.getAccount().getBalance() + 4000);
			updateBalance(p);
			this.gui.showMessage(p.getName() + this.description[48]);
		}

	}





	/**
	 * This move methods moves the player backwards. The player does not recieve money if he passes start.
	 * 
	 * 
	 * @param p
	 * The player to be moved.
	 * @param diceSum
	 * The amount to be moved
	 */
	public void movePlayerBackwards(Player p, int diceSum) {

		int newPosition = (p.getPosition() + diceSum) % 40;

		for (int i = 0; i < this.gui_players.length; i++) {

			if (this.gui_players[i].getName() == p.getName()) {

				while(p.getPosition() != newPosition) {
					this.gui.getFields()[p.getPosition()].setCar(this.gui_players[i], false);
					if (p.getPosition() != 0) {
						p.setPosition(p.getPosition()-1);
					} else {
						p.setPosition(39);
					}
					this.gui.getFields()[p.getPosition()].setCar(this.gui_players[i], true);

					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}


		}
	}


	/**
	 * This is the second move method. The player moves to a given square
	 * 
	 * 
	 * @param p
	 * The player to be moved.
	 * @param newPosition
	 * The square position that the player needs to go to.
	 * @param recieveStartMoney
	 * A boolean describing wether or not the player should recievemoney if he passes start.
	 */
	public void movePlayerInstantly(Player p, int newPosition, boolean recieveStartMoney) {

		int initPosition = p.getPosition();
		for (int i = 0; i < this.gui_players.length; i++) {

			if (this.gui_players[i].getName() == p.getName()) {

				while(p.getPosition() != newPosition) {
					this.gui.getFields()[p.getPosition()].setCar(this.gui_players[i], false);
					p.setPosition(p.getPosition()+1);
					this.gui.getFields()[p.getPosition()].setCar(this.gui_players[i], true);

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}


		}
		if (recieveStartMoney == true) {
			if (initPosition > newPosition) {


				p.getAccount().setBalance(p.getAccount().getBalance() + 4000);
				updateBalance(p);
				this.gui.showMessage(p.getName() + this.description[48]);


			}
		}



	}
	/**
	 * A message when the player rolls a double.
	 * @param p
	 */
	public void doubleDiceMessage(Player p) {
		this.gui.showMessage(p.getName() + this.description[61] + p.getName() + this.description[62] +p.getNumberOfEqualDice() + this.description[63]);
	}

	public void doubleDiceJail(Player p) {
		this.gui.showMessage(p.getName() + this.description[64] + p.getName() + this.description[65]);
	}

	public void rollDiceMessage(Player p) {
		this.gui.getUserButtonPressed(p.getName() + this.description[49], this.description[50]);
	}

	public boolean rollDiceMessageUpdated(Player p) {
		return this.gui.getUserLeftButtonPressed(p.getName() + this.description[51], this.description[50], this.description[52]);
	}

	public void showDice(DiceCup dc) {
		this.gui.setDice(dc.getD1().getValue(), dc.getD2().getValue());
	}

	public void updateBalance(Player p) {

		for (int i = 0; i < this.gui_players.length; i++) 
			if (this.gui_players[i].getName() == p.getName()) 
				this.gui_players[i].setBalance(p.getAccount().getBalance());
	}

	public void showMessage() {
		gui.showMessage(this.description[69]);
	}

}
