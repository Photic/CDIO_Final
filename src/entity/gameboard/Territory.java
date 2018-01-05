package entity.gameboard;

import java.awt.Color;

import entity.player.Player;

/**
 * The territory describes the squares players can own and pay rent to.
 *
 */
public class Territory extends Field {

	
	public Territory(String name, String description, Color color, int price, int housePrice, int[] rent) {
		super(name, description, color);
		this.isOwned = false;
		this.price = price;
		this.housePrice = housePrice; 
		this.rent = rent;
	}
	
	@Override
	public int getCurrentRent() {
		return this.rent[houses];
	}
	
	public void addHouse() {
		this.houses++;
	}
	
	public void removeHouse() {
		this.houses--;
	}
	
	//--------------------------------------------------------
	//
	//                   Getters & Setters!
	//
	//--------------------------------------------------------


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
	
	public int getHousePrice() {
		return housePrice;
	}
	
}
