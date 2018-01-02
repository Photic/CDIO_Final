package entity.gameboard;

import java.awt.Color;

import boundary.Gui;
import boundary.Out;
import entity.Player;
import entity.PlayerList;

/**
 * The abstract class square, used through polymorphism to loop through squares on the gameboard.
 *
 */
public abstract class Square {

	private String name;
	private String description;
	private Color color;
	
	public Square(String navn, String description, Color color)
	{
		this.name = navn;
		this.description = description;
		this.color = color;
	}

	public abstract void squareLogic(Player p);
	
	public String getNavn() {
		return name;
	}

	public void setNavn(String navn) {
		this.name = navn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	
}
