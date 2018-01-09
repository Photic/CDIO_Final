package entity.gameboard;

import java.awt.Color;

public class Tax extends Field {

	public Tax(String navn, String description, Color color, int price, int index) {
		super(navn, description, color, index);
		
		this.price = price;
		// TODO Auto-generated constructor stub
	}
	
}
