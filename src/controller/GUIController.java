package controller;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import boundary.TextReader;
import entity.DiceCup;
import entity.gameboard.Field;
import entity.gameboard.GameBoard;
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


	public GUIController(TextReader tr) {
		try {
			this.description = tr.textFromFile("guiText");
		} catch (IOException e) {
			System.out.println("Something went wrong in the GUIController constructor");
			e.printStackTrace();
		}
	}

	public void defineGUI(GameBoard gameboard) {
		GUI_Field[] gui_fields = new GUI_Field[gameboard.getLength()];

		for (int i = 0; i < gameboard.getLength(); i++) {

			gui_fields[i] = new GUI_Street();
			gui_fields[i].setTitle(gameboard.getField(i).getName());
			gui_fields[i].setSubText(gameboard.getField(i).getDescription());
			gui_fields[i].setDescription(gameboard.getField(i).getName());
			gui_fields[i].setBackGroundColor(gameboard.getField(i).getColor());

		}

		this.gui = new GUI(gui_fields);

	}

	public PlayerList registerPlayerCount() {

		String[] nopArray = {"2", "3", "4", "5", "6"};
		String nop = this.gui.getUserSelection(this.description[0], nopArray);
		GUI_Car gui_car;
		String name;

		int index;

		this.playerCount = Integer.parseInt(nop);
		String[] names = new String[this.playerCount];
		this.gui_players = new GUI_Player[this.playerCount];

		for (int i = 0; i <= this.playerCount - 1; i++) {
			index = i + 1;

			name = this.gui.getUserString(this.description[1] + index + this.description[2]);


			gui_car = new GUI_Car();


			int[] color = randomColorGenerator();

			gui_car.setPrimaryColor(new Color (color[0], color[1], color[2]));


			if (!(contains(names, name))) {
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


			this.gui_players[i] = new GUI_Player(names[i], 30000, gui_car);

		}
		PlayerList playerList = new PlayerList(this.playerCount, names);

		String output = this.description[3];
		for (int i = 0; i < playerList.getLength(); i++) {
			output = output + playerList.getPlayer(i).getName() + ", ";

		}

		this.gui.showMessage(output);

		return playerList;

	}

	public void removeBankrupted(Player p, GameBoard gb) {

		for (int i = 0; i < this.gui_players.length; i++) {
			if (this.gui_players[i].getName() == p.getName()) {
				this.gui.getFields()[p.getPosition()].setCar(this.gui_players[i], false);
				this.gui_players[i].setBalance(0);


				for (int j = 0; j < gb.getLength(); j++) {

					if (gb.getField(j).getOwner() != null) {
						if (gb.getField(j).getOwner().getName() == p.getName()) {

							gb.getField(j).setOwned(false);
							gb.getField(j).setOwner(null);
							this.gui.getFields()[j].setSubText(this.gui.getFields()[j].getDescription());
							this.gui.getFields()[j].setDescription(this.gui.getFields()[j].getTitle());

						}
					}


				}

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

		int counter = 0;
		for (int i = 0; i < fields.length; i++) 
			if (!(contains(c, fields[i].getColor().toString()))) {
				c[counter] = fields[i].getColor().toString();
				counter++;
			}


		String[] colorCheck = new String[counter];

		for (int i = 0; i < colorCheck.length; i++) 
			colorCheck[i] = c[i];






		// HERE IT STARTS!!!
		for (int k = 0; k < colorCheck.length; k++) {

			int lengthOfArray = 0;
			for (int l = 0; l < fields.length; l++) 
				if (fields[l].getColor().toString().equals(colorCheck[k])) {
					lengthOfArray++;
				}

			Field[] currentFields = new Field[lengthOfArray];
			int count = 0;

			for (int l = 0; l < fields.length; l++) 
				if (fields[l].getColor().toString().equals(colorCheck[k])) {
					currentFields[count] = fields[l];
					count++;
				}

			//First find out what height the highest house is, and if they are all at the same height.
			for (int i = 0; i < currentFields.length; i++) {
				if (currentFields[i].getHouses()>highestHouse)
					highestHouse = currentFields[i].getHouses();

				if (!(currentFields[i].getHouses() == currentFields[0].getHouses())) {
					sameHeight = false;	
				}
			}



			if (highestHouse != 5) {
				
				if (sameHeight == true && highestHouse != 5) {
					System.out.println(currentFields.length);
					territories = new String[currentFields.length];

					for (int i = 0; i < territories.length; i++) 
						territories[i] = currentFields[i].getName() + description[4] + currentFields[i].getHouses() + description[5];	

				} else {
					//If the houses does not have the same heigth, we have to find out how many houses are lower than the highest, and represent only them.
					int counting = 0;
					for (int i = 0; i < currentFields.length; i++) {
						if (currentFields[i].getHouses() < highestHouse) {
							counting++;
						}
					}

					territories = new String[counting];

					int countere = 0;

					for (int i = 0; i < currentFields.length; i++) {
						if (currentFields[i].getHouses() < highestHouse) {
							territories[countere] = currentFields[i].getName() + description[4] + currentFields[i].getHouses() + description[5];
							countere++;
						}
					}


				}



			}


			output = combineStringArrays(output, territories);


		}

		
		
		
		//Now we have fields to be represented ready and ask the user wich one he would like to build on.

		String[] fortryd = new String[] {"Fortryd"};
		output = combineStringArrays(output, fortryd);
		
		String selected = gui.getUserSelection(description[6], output);
		String real = selected.split(",")[0];

		if (!(real.equals(description[55]))) {
			for (int i = 0; i < fields.length; i++) {

				if (fields[i].getName().equals(real)) {
					fields[i].getOwner().getAccount().setHousesowned(fields[i].getOwner().getAccount().getHousesowned() + 1);

					fields[i].getOwner().getAccount().addActives(fields[i].getHousePrice());
					fields[i].getOwner().getAccount().addBalance(-fields[i].getHousePrice());
					updateBalance(fields[i].getOwner());

					fields[i].setHouses(fields[i].getHouses() + 1);
					gui.showMessage(description[7] + fields[i].getName() + description[8] + fields[i].getHousePrice() + description[9] + fields[i].getName() + description[10] + fields[i].getHouses() + description[11]);

				}

			}

		}
	} 

	/**
	 * This method deals with the scenario where the user wants to sell houses.
	 * @param fields
	 * All the player's fields
	 */
	public void sellHouses(Field[] fields) {
		int count = 0;

		//Find out how many fields that has houses
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

			if (!(real.equals(this.description[55]))) {
				for (int i = 0; i < fields.length; i++) {

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

		}

	}


	public void showWinner(Player p) {

		String output = p.getName() + this.description[12];

		this.gui.showMessage(output);

	}


	public int territoryOptions(Player p) {

		String[] options = new String[] {this.description[59], this.description[60], this.description[70],this.description[55]};

		String choice = this.gui.getUserSelection(p.getName() + this.description[58], options);

		int output = 1000;

		if (choice == options[0]) {
			output = 1;
		} else if (choice == options[1]){
			output = 2;
		} else if (choice == options[2]) {
			output = 3;
		} else if (choice == options[3]) {
			output = 4;
		}

		return output;
	}


	
	public String sellTerritory(Player seller, PlayerList plist) {
		String[] playerNames = new String[plist.getLength()];
		
	
		int counter = 0;
		
		playerNames[counter] = "Banken";
		counter++;
		
		for (int i = 0; i < plist.getLength(); i++) 
			if (!(plist.getPlayer(i).getName().equals(seller.getName()))) {
				playerNames[counter] = plist.getPlayer(i).getName();
				counter++;
			}
				
		

			
		
		
		String[] fortryd = new String[] {"fortryd"};
		playerNames = combineStringArrays(playerNames, fortryd);
		
		String output = gui.getUserSelection("Hvem vil du sælge til?", playerNames);
		

		
		return output;
		
	}
	
	public Field sellTerritoryProp(Player p) {
		
		String[] fieldNames = new String[p.getAccount().getFields().length];

		for (int i = 0; i < p.getAccount().getFields().length; i++) 
			fieldNames[i] = p.getAccount().getFields()[i].getName();
		
		String output = gui.getUserSelection("Hvilket territory vil du sælge?", fieldNames);
		Field outField = null;
		
		for (int i = 0; i < p.getAccount().getFields().length; i++) {
			if (p.getAccount().getFields()[i].getName() == output) {
				outField = p.getAccount().getFields()[i];
			}
			
		}
		
		return outField;
	}
	
	public int priceToSell() {
		
		return gui.getUserInteger("Hvad er den aftalte pris?");
	}
	
	

	
	public void updateSubtext(Player newOwner, Field field) {

		for (int i = 0; i < gui.getFields().length; i++) 
			if (gui.getFields()[i].getTitle().equals(field.getName())) {
				gui.getFields()[i].setDescription(gui.getFields()[i].getSubText());
				gui.getFields()[i].setSubText(newOwner.getName());
			}
		
	}
	
	public void updateSubtextReversed(Field field) {

		for (int i = 0; i < gui.getFields().length; i++) 
			if (gui.getFields()[i].getTitle().equals(field.getName())) {
				gui.getFields()[i].setSubText(gui.getFields()[i].getDescription());
				gui.getFields()[i].setDescription(gui.getFields()[i].getTitle());
			}
		
	}
	
	
	public boolean territoryDecision(Player p) {

		this.gui.showMessage(p.getName() + this.description[13]);

		return this.gui.getUserLeftButtonPressed(p.getName() + this.description[14], this.description[15], this.description[16]);

	}

	public boolean companyDecision(Player p) {

		this.gui.showMessage(p.getName() + this.description[17]);

		return this.gui.getUserLeftButtonPressed(p.getName() + this.description[18], this.description[15], this.description[16]);
	}

	public boolean shippingDecision(Player p) {

		this.gui.showMessage(p.getName() + this.description[19]);

		return gui.getUserLeftButtonPressed(p.getName() + this.description[20], this.description[15], this.description[16]);

	}

	public boolean taxDecision(Player p) {
		gui.showMessage(description[21]);

		return gui.getUserLeftButtonPressed(
				p.getName() + this.description[22], this.description[23], this.description[24]);

	}

	public void taxMessage(Player p) {

		gui.showMessage(p.getName() + this.description[25]);

	}

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

	public void payRentMessege(Field field, Player p) {
		this.gui.showMessage(
				p.getName() + this.description[30] + 
				field.getOwner().getName() + this.description[31] + 
				field.getHouses() + this.description[32] + 
				field.getCurrentRent() + this.description[33]
				);
	}

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

	public void payRentShippingMessege(Field field, Player p) {

		this.gui.showMessage (
				p.getName() + this.description[30] + 
				field.getOwner().getName() + this.description[34] + 
				field.getOwner().getName() + this.description[35] + 
				field.getOwner().getAccount().getShipping() + this.description[36] + 
				field.getRent()[field.getOwner().getAccount().getShipping() - 1] + this.description[33]
				);
	}

	public void visitJailMessege(Player p) {
		this.gui.showMessage(p.getName() + this.description[37]);
	}


	public void goToJailMessege(Player p) {

		this.gui.showMessage(p.getName() + this.description[38]);

	}

	public void parkingMessege(Field field, Player p) {

		this.gui.showMessage(p.getName() + this.description[39]);

	}

	public void chanceMessege(String chanceMessege) {
		this.gui.displayChanceCard(chanceMessege);
		this.gui.displayChanceCard();
	}

	public void setOwnerText(Player p) {

		this.gui.getFields()[p.getPosition()].setDescription(this.gui.getFields()[p.getPosition()].getSubText());

		this.gui.getFields()[p.getPosition()].setSubText(p.getName());


	}

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

	public void jailFreePay(Player p) {

		this.gui.showMessage(p.getName() + this.description[44]);

	}

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
	 * The plyaer recieves 4000 if he passes start.
	 * 
	 * 
	 * @param p
	 * The player to be moved.
	 * @param diceSum
	 * The sum of the dice
	 */
	public void movePlayer(Player p, int diceSum) {

		int newPosition = (p.getPosition() + diceSum) % 40;
		int initPosition = (p.getPosition());

		for (int i = 0; i < this.gui_players.length; i++) {

			if (this.gui_players[i].getName() == p.getName()) {

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
			}


		}

		if (initPosition > newPosition) {

			p.getAccount().setBalance(p.getAccount().getBalance() + 4000);
			updateBalance(p);
			this.gui.showMessage(p.getName() + this.description[48]);

		}

	}


	public void doubleDiceMessage(Player p) {

		this.gui.showMessage(p.getName() + this.description[61] + p.getName() + this.description[62] +p.getNumberOfEqualDice() + this.description[63]);

	}

	public void doubleDiceJail(Player p) {
		this.gui.showMessage(p.getName() + this.description[64] + p.getName() + this.description[65]);
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
		int initPosition = (p.getPosition());

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



	public void rollDiceMessage(Player p) {
		//gui.showMessage(p.getName() + " please roll the dice");
		this.gui.getUserButtonPressed(p.getName() + this.description[49], this.description[50]);

	}


	public boolean rollDiceMessageUpdated(Player p) {


		return this.gui.getUserLeftButtonPressed(p.getName() + this.description[51], this.description[50], this.description[52]);
	}

	public void showDice(DiceCup dc) {
		this.gui.setDice(dc.getD1().getValue(), dc.getD2().getValue());
	}




	public void updateBalance(Player p) {

		for (int i = 0; i < this.gui_players.length; i++) {
			if (this.gui_players[i].getName() == p.getName()) {
				this.gui_players[i].setBalance(p.getAccount().getBalance());
			}

		}

	}

	public void setDice(DiceCup dc) {

	}

	public void setOwner(Player p) {

	}

	public void removeBankruptOwner(int index) {

	}

	public void removeBankruptPlayer(Player p) {

	}

	//Generer tilfældige farver til bilerne
	private int[] randomColorGenerator() {
		int[] rgb = new int[3];
		Random rng = new Random();

		for (int i = 0; i < rgb.length; i++) {
			rgb[i] = rng.nextInt(255);
		}

		return rgb;
	}

	public void showMessage() {
		gui.showMessage(this.description[69]);
	}

}
