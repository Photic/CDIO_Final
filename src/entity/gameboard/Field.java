package entity.gameboard;

import java.awt.Color;

import entity.player.Player;

/**
 * The abstract class square, used through polymorphism to loop through squares on the gameboard.
 *
 */
public abstract class Field {

	private String name;
	private String description;
	private Color color;
	protected Player owner;
	protected boolean isOwned;
	protected int price, housePrice, houses, hotels;
	protected int[] rent;
	
	public Field(String name, String description, Color color) {
		this.name = name;
		this.description = description;
		this.color = color;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	/*
	 * Fields you are able to own.
	 */
	public void setOwned(boolean isOwned) {
		this.isOwned = isOwned;
	}
	
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public Player getOwner() {
		return owner;
	}

	public Boolean isOwned() {
		return this.isOwned;
	}

	public void removeOwner() {
		this.isOwned = false;
	}
	
	
	/*
	 * Get Prices on able price fields.
	 */
	public int getPrice() {
		return this.price;
	}

	public int[] getRent() {
		return this.rent;
	}
	
	public int getCurrentRent() {
		return this.rent[0];
	}
	
	public int getHouses() {
		return this.houses;
	}
	
	public int getHousePrice() {
		return housePrice;
	}
	
}
