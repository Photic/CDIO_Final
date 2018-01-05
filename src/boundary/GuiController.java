package boundary;

import java.awt.Color;
import java.util.Random;

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

public class GuiController {

	private GUI gui;
	private GUI_Player[] gui_players;
	private int playerCount;



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
		String nop = gui.getUserSelection("Hvor mange spillere er vi?", nopArray);
		GUI_Car gui_car;
		String name;

		int index;
		
		this.playerCount = Integer.parseInt(nop);
		String[] names = new String[playerCount];
		this.gui_players = new GUI_Player[playerCount];

		for (int i = 0; i <= playerCount - 1; i++) {
			index = i + 1;
			
			name = gui.getUserString("Spiller " + index + "' navn?");
			
			
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

		String output = "De registrede spillere er: ";
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
	
	
	public void showWinner(Player p) {
		
		String output = p.getName() + " har vundet, Tillykke!!";
		
		gui.showMessage(output);
		
	}
	
	
	
	public boolean territoryDecision(Field field, Player p) {
		gui.showMessage(p.getName() + ", du er landet på en grund der kan købes.");
		
		return gui.getUserLeftButtonPressed(p.getName() + ", du kan enten købe grunden eller lade vær.", "Køb", "Gør intet");
		
	}
	
	public boolean companyDecision(Field field, Player p) {
		
		gui.showMessage(p.getName() + ", du er landet på en virksomhed der kan købes.");
		
		return gui.getUserLeftButtonPressed(p.getName() + ", du kan enten købe virksomheden eller lade vær.", "Køb", "Gør intet");
	}
	
	public boolean shippingDecision(Field field, Player p) {
		
		gui.showMessage(p.getName() + ", du er landet på et rederi der kan købes.");
		
		return gui.getUserLeftButtonPressed(p.getName() + ", du kan enten købe rederiet eller lade vær.", "Køb", "Gør intet");
		
	}
	
	public boolean taxDecision(Field field, Player p) {
		gui.showMessage("Du skal betale indkomstskat.");
		
		return gui.getUserLeftButtonPressed(p.getName() + ", du kan enten betale 4000 kroner eller betale 10% af dine værdier.", "Betal 4000 kroner", "Betal 10% af dine værdier");
		
	}
	
	public void taxMessage(Player p) {
		
		gui.showMessage(p.getName() +", du skal betale 2000 kroner i ekstraordinært skattebidrag.");
		
	}
	
	public void transaction(boolean b, Field field, Player p) {
		
		if (b == true) {
			
			gui.showMessage(p.getName() + ", du har købt " + field.getName() + " for " + field.getPrice() + " kroner.");
			
		} else {
			
			gui.showMessage(p.getName() + ", du valgt at lade vær med at købe " + field.getName());
			
		}
		
	}
	
	public void payRentMessege(Field field, Player p) {
		gui.showMessage(p.getName() + ", du er landet på " + field.getOwner().getName() + "'s grund. Der er bygget " + field.getHouses() + " huse på grunden. Du betaler altså " + field.getCurrentRent() + " kroner i leje.");
		
	}
	
	public void payRentShippingMessege(Field field, Player p) {
		
		gui.showMessage(p.getName() + ", du er landet på " + field.getOwner().getName() + "'s rederi. " + field.getOwner().getName() + " ejer " + field.getOwner().getAccount().getShipping() + " rederier. Derfor betaler du " + field.getRent()[field.getOwner().getAccount().getShipping() - 1] + " kroner i leje.");
		
	}
	
	
	public void setOwnerText(Player p) {
		
		gui.getFields()[p.getPosition()].setDescription(gui.getFields()[p.getPosition()].getSubText());
		
		gui.getFields()[p.getPosition()].setSubText(p.getName());
		
		
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
			gui.showMessage(p.getName() + ", du er passeret start. Dermed modtager du 4000 kroner af banken.");
			
			
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
						Thread.sleep(300);
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
				gui.showMessage(p.getName() + ", du er passeret start. Dermed modtager du 4000 kroner af banken.");
				
				
			}
		}

		

	}
	
	
	
	public void rollDiceMessage() {
		gui.showMessage("Please roll the dice");
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
	
	
	//fix så den virker
	public void updateBalance(PlayerList plist) {

//		for (int i = 0; i < gui_players.length; i++) {
//			if (gui_players[i].getName() == p.getName()) {
//				gui_players[i].setBalance(p.getBalance());
//			}
//		}
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
