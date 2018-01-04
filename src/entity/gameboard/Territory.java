package entity.gameboard;

import java.awt.Color;

import entity.player.Player;

/**
 * The territory describes the squares players can own and pay rent to.
 *
 */
public class Territory extends Field {
	
	private int houses;

	public Territory(String name, String description, Color color) {
		super(name, description, color);
		this.isOwned = false;
		this.price = price;
		this.rent = rent;
	}

	@Override
	public void squareLogic(Player p) {
		
	}
	
	@Override
	public int getRent() {
		return this.rent[houses];
	}
	

	public void addHouse() {
		this.houses++;
	}
	
	public void removeHouse() {
		this.houses--;
	}
	
	public void setOwner(Player p) {
		this.ownerName = p.getName();
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

	public int getPrice() {
		return price;
	}

	public String getOwnerName() {
		return ownerName;
	}
	
}
