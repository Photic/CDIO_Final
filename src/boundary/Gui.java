package boundary;


import java.awt.Color;

import desktop_codebehind.Car;
import desktop_fields.Field;
import desktop_fields.Street;
import desktop_resources.GUI;
import entity.Die;
import entity.Player;
import entity.PlayerList;
import entity.squares.GameBoard;



/**
 * 
 * This class controls the interaction with the GUI provided by DTU.
 * @author milter
 *
 */

public class Gui {
	
	
	/**
	 * Use the GUI given by the teachers to create a GUI.
	 * @param gameboard
	 * An instance of the gameboard class, used to set up the gameboard visually
	 */
	public void defineGUI(GameBoard gameboard) {
		Field[] fields = new Field[gameboard.getSize()];
		
		
		
		for (int i = 0; i <= gameboard.getSize()-1; i++) {
			fields[i] = new Street.Builder()
					.setTitle(gameboard.getField(i).getNavn())
					.setDescription(gameboard.getField(i).getNavn())
					.setSubText(gameboard.getField(i).getDescription())
					.setBgColor(gameboard.getField(i).getColor())
					.build();
		}
            
       
		GUI.create(fields);
		GUI.setDice(1, 1);
	}
	
	
	/**
	 * This method initially places players on the gameboard, when the game is beginning.
	 * @param playerlist
	 * An instance of the playerlist, containing all the players
	 */
	public void placePlayers(PlayerList playerlist) {
		for (int i = 0; i < playerlist.getLength(); i++) {
			GUI.setCar(1, playerlist.getSpecificPlayer(i).getName());
		}
	}
	
	
	/**
	 * Updates the players balance on the gameboard.
	 * @param player
	 * instance of the player class
	 */
	public void updateBalance(Player player) {
		GUI.setBalance(player.getName(), player.getBalance());
	}
	
	/**
	 * Visually setting the dice on the gui
	 * @param d1
	 * @param d2
	 */
	public void setDice(Die d1, Die d2) {
		GUI.setDice(d1.getFaceValue(), d2.getFaceValue());
	}
	
	/**
	 * set the player names on the gameboard. It also makes sure that everybody gets different car colors.
	 * @param playerList
	 */
	public void setNames(PlayerList playerList) {
        Car blue = new Car.Builder()
        		.typeUfo()
                .primaryColor(Color.BLUE)
                .build();
        Car red = new Car.Builder()
        		.typeUfo()
                .primaryColor(Color.RED)
                .build();
        Car yellow = new Car.Builder()
        		.typeUfo()
                .primaryColor(Color.YELLOW)
                .build();
        Car green = new Car.Builder()
        		.typeUfo()
                .primaryColor(Color.GREEN)
                .build();
        
        Car[] cars = {blue, red, yellow, green};

		
		int count = 0;
		for (Player p : playerList.getList()) {
			GUI.addPlayer(p.getName(), p.getBalance(), cars[count]);
			count++;
		}
	}

	/**
	 * Moves a player by removing his car, and placing a new at his position. 
	 * @param player 
	 * An instance of player
	 */
	public void movePlayer(Player player) {
		GUI.removeAllCars(player.getName());
		GUI.setCar(player.getPosition()+1, player.getName());
	}
	
	
	/**
	 * Set an territory owner visually.
	 * @param player
	 * Player instance.
	 */
	public void setOwner(Player player) {
		GUI.setOwner(player.getPosition()+1, player.getName());
	}
	
	/**
	 * Removes an owner on a position. Should only be used if the player is dead.
	 * @param position
	 * position on the gameboard.
	 */
	public void removeDeadOwner(int position) 
	{
		GUI.removeOwner(position+1);
	}
	
	/**
	 * Remove the car of a dead player.
	 * @param player
	 * Player instance.
	 */
	public void removeDeadPlayer(Player player) {
		GUI.removeAllCars(player.getName());
	}
	
	
	
	
	
	
	
	
}
