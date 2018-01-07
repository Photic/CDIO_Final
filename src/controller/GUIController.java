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
	private String[] descriptions;


	public GUIController(TextReader tr) {
		try {
			this.descriptions = tr.textFromFile("guiText");
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

		gui = new GUI(gui_fields);

	}

	public PlayerList registerPlayerCount() {

		String[] nopArray = {"2", "3", "4", "5", "6"};
		String nop = gui.getUserSelection(descriptions[0], nopArray);
		GUI_Car gui_car;
		String name;

		int index;

		this.playerCount = Integer.parseInt(nop);
		String[] names = new String[playerCount];
		this.gui_players = new GUI_Player[playerCount];

		for (int i = 0; i <= playerCount - 1; i++) {
			index = i + 1;

			name = gui.getUserString(descriptions[1] + index + descriptions[2]);


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


			gui_players[i] = new GUI_Player(names[i], 30000, gui_car);

		}
		PlayerList playerList = new PlayerList(playerCount, names);

		String output = descriptions[3];
		for (int i = 0; i < playerList.getLength(); i++) {
			output = output + playerList.getPlayer(i).getName() + ", ";

		}

		gui.showMessage(output);

		return playerList;

	}

	public void removeBankrupted(Player p, GameBoard gb) {

		for (int i = 0; i < gui_players.length; i++) {
			if (gui_players[i].getName() == p.getName()) {
				gui.getFields()[p.getPosition()].setCar(gui_players[i], false);
				gui_players[i].setBalance(0);


				for (int j = 0; j < gb.getLength(); j++) {

					if (gb.getField(j).getOwner() != null) {
						if (gb.getField(j).getOwner().getName() == p.getName()) {

							gb.getField(j).setOwned(false);
							gb.getField(j).setOwner(null);
							gui.getFields()[j].setSubText(gui.getFields()[j].getDescription());
							gui.getFields()[j].setDescription(gui.getFields()[j].getTitle());

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
		String[] territories;

		//First find out what height the highest house is, and if they are all at the same height.
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getHouses()>highestHouse)
				highestHouse = fields[i].getHouses();

			if (!(fields[i].getHouses() == fields[0].getHouses())) {
				sameHeight = false;	
			}
		}

		//Hvis alle huse er samme højde, og hvis det højeste punkt er 5, så kan spillere ikke bygge højere.
		if (sameHeight == true && highestHouse == 5) {
			gui.showMessage(descriptions[53]);
		} else {
			//ellers:
			//If the houses are at same heigth, then all of the fields should be visible for action.
			if (sameHeight == true && highestHouse != 5) {
				territories = new String[fields.length + 1];

				for (int i = 0; i < territories.length-1; i++) 
					territories[i] = fields[i].getName() + descriptions[4] + fields[i].getHouses() + descriptions[5];	


				//tilføj en fortryd-knap
				territories[fields.length] = descriptions[55];

			} else {
				//If the houses does not have the same heigth, we have to find out how many houses are lower than the highest, and represent only them.
				int count = 0;
				for (int i = 0; i < fields.length; i++) {
					if (fields[i].getHouses() < highestHouse) {
						count++;
					}
				}

				territories = new String[count];

				int counter = 0;
				for (int i = 0; i < fields.length; i++) {
					if (fields[i].getHouses() < highestHouse) {
						territories[counter] = fields[i].getName() + descriptions[4] + fields[i].getHouses() + descriptions[5];
						counter++;
					}
				}

				//tilføj en fortryd knap
				territories[counter] = descriptions[55];
			}



			//Now we have fields to be represented ready and ask the user wich one he would like to build on.

			String selected = gui.getUserSelection(descriptions[6], territories);
			String real = selected.split(",")[0];

			if (!(real.equals(descriptions[55]))) {
				for (int i = 0; i < fields.length; i++) {

					if (fields[i].getName().equals(real)) {
						fields[i].getOwner().getAccount().setHousesowned(fields[i].getOwner().getAccount().getHousesowned() + 1);

						fields[i].getOwner().getAccount().addActives(fields[i].getHousePrice());
						fields[i].getOwner().getAccount().addBalance(-fields[i].getHousePrice());
						updateBalance(fields[i].getOwner());

						fields[i].setHouses(fields[i].getHouses() + 1);
						gui.showMessage(descriptions[7] + fields[i].getName() + descriptions[8] + fields[i].getHousePrice() + descriptions[9] + fields[i].getName() + descriptions[10] + fields[i].getHouses() + descriptions[11]);

					}

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
			gui.showMessage(descriptions[54]);
		} else {
			// Add the fields that has houses to a new array
			String[] hasHouses = new String[count + 1];
			int counter = 0;

			for (int i = 0; i < fields.length; i++) 
				if (fields[i].getHouses()>0) {
					hasHouses[counter] = fields[i].getName() + descriptions[4] + fields[i].getHouses() + descriptions[5];
					counter++;
				}

			hasHouses[counter] = descriptions[55];

			//Find out which territory the player wants to sell a house on
			String selected = gui.getUserSelection(descriptions[56], hasHouses);
			String real = selected.split(",")[0];

			if (!(real.equals(descriptions[55]))) {
				for (int i = 0; i < fields.length; i++) {

					//sell a house on the selected territory
					if (fields[i].getName().equals(real)) {
						fields[i].getOwner().getAccount().setHousesowned(fields[i].getOwner().getAccount().getHousesowned() - 1);

						fields[i].getOwner().getAccount().addActives(-fields[i].getHousePrice());
						fields[i].getOwner().getAccount().addBalance(fields[i].getHousePrice());
						updateBalance(fields[i].getOwner());

						fields[i].setHouses(fields[i].getHouses() - 1);

						gui.showMessage(descriptions[57] + fields[i].getName() + descriptions[8] + fields[i].getHousePrice() + descriptions[9] + fields[i].getName() + descriptions[10] + fields[i].getHouses() + descriptions[11]);



					}

				}

			}

		}

	}


	public void showWinner(Player p) {

		String output = p.getName() + descriptions[12];

		gui.showMessage(output);

	}


	public int territoryOptions(Player p) {

		String[] options = new String[] {descriptions[59], descriptions[60], descriptions[55]};

		String choice = gui.getUserSelection(p.getName() + descriptions[58], options);

		int output = 1000;

		if (choice == options[0]) {
			output = 1;
		} else if (choice == options[1]){
			output = 2;
		} else if (choice == options[2]) {
			output = 3;
		}

		return output;
	}



	public boolean territoryDecision(Player p) {

		gui.showMessage(p.getName() + descriptions[13]);

		return gui.getUserLeftButtonPressed(p.getName() + descriptions[14], descriptions[15], descriptions[16]);

	}

	public boolean companyDecision(Player p) {

		gui.showMessage(p.getName() + descriptions[17]);

		return gui.getUserLeftButtonPressed(p.getName() + descriptions[18], descriptions[15], descriptions[16]);
	}

	public boolean shippingDecision(Player p) {

		gui.showMessage(p.getName() + descriptions[19]);

		return gui.getUserLeftButtonPressed(p.getName() + descriptions[20], descriptions[15], descriptions[16]);

	}

	public boolean taxDecision(Field field, Player p) {
		gui.showMessage(descriptions[21]);

		return gui.getUserLeftButtonPressed(p.getName() + descriptions[22], descriptions[23], descriptions[24]);

	}

	public void taxMessage(Player p) {

		gui.showMessage(p.getName() + descriptions[25]);

	}

	public void transaction(boolean b, Field field, Player p) {

		if (b == true) {

			gui.showMessage(p.getName() + descriptions[26] + field.getName() + descriptions[27] + field.getPrice() + descriptions[28]);

		} else {

			gui.showMessage(p.getName() + descriptions[29] + field.getName());

		}

	}

	public void payRentMessege(Field field, Player p) {
		gui.showMessage(p.getName() + descriptions[30] + field.getOwner().getName() + descriptions[31] + field.getHouses() + descriptions[32] + field.getCurrentRent() + descriptions[33]);

	}

	public void payRentShippingMessege(Field field, Player p) {

		gui.showMessage(p.getName() + descriptions[30] + field.getOwner().getName() + descriptions[34] + field.getOwner().getName() + descriptions[35] + field.getOwner().getAccount().getShipping() + descriptions[36 ]+ field.getRent()[field.getOwner().getAccount().getShipping() - 1] + descriptions[33]);

	}

	public void visitJailMessege(Field field, Player p) {
		gui.showMessage(p.getName() + descriptions[37]);
	}


	public void goToJailMessege(Field field, Player p) {

		gui.showMessage(p.getName() + descriptions[38]);

	}

	public void parkingMessege(Field field, Player p) {

		gui.showMessage(p.getName() + descriptions[39]);

	}

	public void chanceMessege(String chanceMessege) {
		gui.displayChanceCard(chanceMessege);
	}

	public void setOwnerText(Player p) {

		gui.getFields()[p.getPosition()].setDescription(gui.getFields()[p.getPosition()].getSubText());

		gui.getFields()[p.getPosition()].setSubText(p.getName());


	}

	public int inJailDecision(Player p) {

		int decision = 0;

		if(p.getAccount().getAmountOfCards()>0) {

			String[] jailDecision = {descriptions[40], descriptions[41], descriptions[42]};

			String decisionMade = gui.getUserSelection(descriptions[43], jailDecision);

			if (decisionMade == descriptions[40]) {

				decision = 1;

			} else if (decisionMade == descriptions[41]) {

				decision = 2;

			} else if (decisionMade == descriptions[42]) {

				decision = 3;

			}

		}
		else {
			String[] jailDecision = {descriptions[40], descriptions[41]};

			String decisionMade = gui.getUserSelection(p.getName() + descriptions[43], jailDecision);

			if (decisionMade == descriptions[40]) {

				decision = 1;

			} else if (decisionMade == descriptions[41]) {

				decision = 2;
			}
		}
		return decision;

	}

	public void jailFreePay(Player p) {

		gui.showMessage(p.getName() + descriptions[44]);

	}

	public void jailEqualsTrue(Player p) {

		gui.showMessage(p.getName() + descriptions[45]);

	}

	public void jailEqualsFalse(Player p) {

		gui.showMessage(p.getName() + descriptions[46]);

	}

	public void antiJailUsed(Player p) {

		gui.showMessage(p.getName() + descriptions[47]);

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
	 * Places all the players on start.
	 */
	public void placePlayer() {

		for (int j = 0; j < gui_players.length; j++) {
			gui.getFields()[0].setCar(gui_players[j], true);
			gui.addPlayer(gui_players[j]);
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

		for (int i = 0; i < gui_players.length; i++) {

			if (gui_players[i].getName() == p.getName()) {

				while(p.getPosition() != newPosition) {
					gui.getFields()[p.getPosition()].setCar(gui_players[i], false);
					p.setPosition(p.getPosition()+1);
					gui.getFields()[p.getPosition()].setCar(gui_players[i], true);

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
			gui.showMessage(p.getName() + descriptions[48]);


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
		int initPosition = (p.getPosition());

		for (int i = 0; i < gui_players.length; i++) {

			if (gui_players[i].getName() == p.getName()) {

				while(p.getPosition() != newPosition) {
					gui.getFields()[p.getPosition()].setCar(gui_players[i], false);
					if (p.getPosition() != 0) {
						p.setPosition(p.getPosition()-1);
					} else {
						p.setPosition(39);
					}
					gui.getFields()[p.getPosition()].setCar(gui_players[i], true);

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
		for (int i = 0; i < gui_players.length; i++) {

			if (gui_players[i].getName() == p.getName()) {

				while(p.getPosition() != newPosition) {
					gui.getFields()[p.getPosition()].setCar(gui_players[i], false);
					p.setPosition(p.getPosition()+1);
					gui.getFields()[p.getPosition()].setCar(gui_players[i], true);

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
				gui.showMessage(p.getName() + descriptions[48]);


			}
		}



	}



	public void rollDiceMessage(Player p) {
		//gui.showMessage(p.getName() + " please roll the dice");
		gui.getUserButtonPressed(p.getName() + descriptions[49], descriptions[50]);

	}


	public boolean rollDiceMessageUpdated(Player p) {


		return gui.getUserLeftButtonPressed(descriptions[51], descriptions[50], descriptions[52]);
	}

	public void showDice(DiceCup dc) {
		gui.setDice(dc.getD1().getValue(), dc.getD2().getValue());
	}




	public void updateBalance(Player p) {

		for (int i = 0; i < gui_players.length; i++) {
			if (gui_players[i].getName() == p.getName()) {
				gui_players[i].setBalance(p.getAccount().getBalance());
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

}
