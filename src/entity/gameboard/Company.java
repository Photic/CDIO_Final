package entity.gameboard;

import java.awt.Color;

public class Company extends Field {

	public Company(String navn, String description, Color color, int price, int index) {
		super(navn, description, color, index);
		this.isOwned = false;
		this.price = price; 
	}
	
}
