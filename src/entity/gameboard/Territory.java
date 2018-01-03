package entity.gameboard;

import java.awt.Color;

import entity.player.Player;

/**
 * The territory describes the squares players can own and pay rent to.
 *
 */
public class Territory extends Field {

	private int[] rent;
	private int price, houses;
	private boolean isOwned;
	private String ownerName;

	public Territory(String name, String description, Color color, int price, int[] rent) {
		super(name, description, color);
		this.isOwned = false;
		this.price = price;
		this.rent = rent;
	}

	@Override
	public void squareLogic(Player p) {
		
	}

	public void addHouse() {
		this.houses++;
	}
	
	public void removeHouse() {
		this.houses--;
	}
	
	public void setOwner(Player p) {
		this.ownerName = p.getName();
		setOwned(true);
	}
	
	public int getRent() {
		return this.rent[houses];
	}
	
	//--------------------------------------------------------
	//
	//                   Getters & Setters!
	//
	//--------------------------------------------------------
	public String getOwner() {
		return ownerName;
	}

	public int getHouses() {
		return houses;
	}

	public void setHouses(int houses) {
		this.houses = houses;
	}

	public boolean isOwned() {
		return isOwned;
	}

	public void setOwned(boolean isOwned) {
		this.isOwned = isOwned;
	}

	public int getPrice() {
		return price;
	}

	public String getOwnerName() {
		return ownerName;
	}
	
}
