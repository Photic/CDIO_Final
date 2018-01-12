package entity.gameboard;

import java.awt.Color;

public class Start extends Field {

	public Start(String name, String description, Color color, int index, int price) {
		super(name, description, color, index);
		this.price = price;
	}
}
