package entity.gameboard;

import java.awt.Color;

import entity.player.Player;

public class Company extends Field {

	public Company(String navn, String description, Color color, int price) {
		super(navn, description, color);
		this.price = price; 
	}

	@Override
	public void squareLogic(Player p) {
		// TODO Auto-generated method stub
		
	}
	
	

}
