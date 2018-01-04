package entity.gameboard;

import java.awt.Color;

import entity.player.Player;

public class Shipping extends Field {

	public Shipping(String navn, String description, Color color, int price) {
		super(navn, description, color);
		this.price = price; 
	}

	@Override
	public void squareLogic(Player p) {
		// TODO Auto-generated method stub
		
	}

	
	
}
