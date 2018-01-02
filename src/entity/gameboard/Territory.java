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

	private int price, rentInt;
	private String rent;
	private boolean isOwned;
	private Player owner;

	public Territory(String name, String description, Color color, String rent) 
	{
		super(name, description, color);
		this.price = Integer.parseInt(description);
		this.rent = rent;
		this.rentInt = Integer.parseInt(rent);
		this.isOwned = false;
	}

	/**
	 * If the territory is owned, get and the owner is not the player landing there, make him pay rent.
	 */
	@Override
	public void squareLogic(Player p) 
	{

	}

	public int getPrice() 
	{
		return price;
	}

	public String getRent()
	{
		return rent;
	}

	public boolean isOwned() {
		return isOwned;
	}

	public void setOwned(boolean isOwned) 
	{
		this.isOwned = isOwned;
	}
	public Player getOwner() 
	{
		return owner;
	}

	public void setOwner(Player owner) 
	{
		this.owner = owner;
	}

	/**
	 * remove the owner of the square. Used if the player dies.
	 * @param player
	 * The dead player.
	 */
	public void removeDeadOwner(Player player) 
	{
		if (isOwned == true) 
		{
			if (player.getName() == owner.getName()) 
			{
				owner = null;
				isOwned = false;
			}
		}

	}




}
