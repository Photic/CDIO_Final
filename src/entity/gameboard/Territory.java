package entity.gameboard;

import java.awt.Color;

import boundary.Gui;
import boundary.Out;
import entity.Player;
import entity.PlayerList;

/**
 * The territory describes the squares players can own and pay rent to.
 *
 */
public class Territory extends Square {

	private String rent;
	private boolean isOwned;
	private Player owner;

	public Territory(String name, String description, Color color) 
	{
		super(name, description, color);
		this.isOwned = false;
	}

	/**
	 * If the territory is owned, get and the owner is not the player landing there, make him pay rent.
	 */
	@Override
	public void squareLogic(Player p) 
	{
		
	}
	
}
