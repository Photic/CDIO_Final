package entity.gameboard;

import java.awt.Color;
import entity.player.Player;
import entity.player.PlayerList;

public class Start extends Square {
	
	public Start(String navn, String description, Color color) {
		super(navn, description, color);
	}

	@Override
	public void squareLogic(Player p) 
	{
		// Går over start
	}

	
	



}
