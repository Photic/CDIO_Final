package boundary;

import java.awt.Color;
import java.util.Arrays;

import entity.DiceCup;
import entity.gameboard.GameBoard;
import entity.player.Player;
import entity.player.PlayerList;
import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

public class GUINew {

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
		String[] carColors = {"Rød", "Gul", "Grøn", "Blå", "Pink", "Sort"};
		String currentCarColor;
		String nop = gui.getUserSelection("Hvor mange spillere er vi?", nopArray);
		GUI_Car gui_car;

		playerCount = Integer.parseInt(nop);

		String[] names = new String[playerCount];
		gui_players = new GUI_Player[playerCount];

		for (int i = 0; i <= playerCount - 1; i++) {
			int index = i + 1;
			String name = gui.getUserString("Spiller " + index + "' navn?");
			gui_car = new GUI_Car();

			currentCarColor = carColors[i];
			if (currentCarColor == "rød") {
				gui_car.setPrimaryColor(Color.red);
			} else if (currentCarColor == "Gul"){
				gui_car.setPrimaryColor(Color.yellow);			
			} else if (currentCarColor == "Grøn"){
				gui_car.setPrimaryColor(Color.green);			
			} else if (currentCarColor == "Blå"){
				gui_car.setPrimaryColor(Color.blue);			
			} else if (currentCarColor == "Pink"){
				gui_car.setPrimaryColor(Color.pink);			
			} else if (currentCarColor == "Sort"){
				gui_car.setPrimaryColor(Color.black);			
			}




			// lav array selv
			if (!(Arrays.asList(names).contains(name))) {
				names[i] = name;
			} else {
				int count = 2;
				while(true) {
					if (!(Arrays.asList(names).contains(name + "_" + count))) {
						names[i] = name + "_" + count;
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

		String output = "";
		for (int i = 0; i < playerList.getLength(); i++) {
			output = output + playerList.getPlayer(i).getName() + ", ";

		}

		gui.showMessage(output);

		return playerList;

	}


	public void placePlayer(PlayerList plist) {



	}



	public void updateBalance(Player p) {

		for (int i = 0; i < gui_players.length; i++) {
			if (gui_players[i].getName() == p.getName()) {
				gui_players[i].setBalance(p.getBalance());
			}
		}



	}

	public void setDice(DiceCup dc) {



	}

	public void setNames(PlayerList plist) {



	}

	public void movePlayer(Player p, int index) {



	}

	public void setOwner(Player p) {



	}

	public void removeBankruptOwner(int index) {



	}

	public void removeBankruptPlayer(Player p) {



	}

}
