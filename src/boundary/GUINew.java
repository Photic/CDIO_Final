package boundary;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

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

		String output = "De registrede spillere er: ";
		for (int i = 0; i < playerList.getLength(); i++) {
			output = output + playerList.getPlayer(i).getName() + ", ";

		}

		gui.showMessage(output);

		return playerList;

	}


	public void placePlayer() {

		for (int j = 0; j < gui_players.length; j++) {
			gui.getFields()[0].setCar(gui_players[j], true);
			gui.addPlayer(gui_players[j]);
		}



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
