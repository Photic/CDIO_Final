package entity.gameboard;

import java.awt.Color;

public class Shipping extends Field {

	public Shipping(String navn, String description, Color color, int price, int[] rent, int index, int pawnValue) {
		super(navn, description, color, index);
		this.isOwned = false;
		this.price = price; 
		this.rent = rent;
		this.pawnValue = pawnValue;
	}
}
