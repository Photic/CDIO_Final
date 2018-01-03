package entity.gameboard;

import java.awt.Color;
import entity.player.Player;
import entity.player.PlayerList;

/**
 * The abstract class square, used through polymorphism to loop through squares on the gameboard.
 *
 */
public abstract class Field {

	private String name;
	private String description;
	private Color color;
	
	public Field(String name, String description, Color color)
	{
		this.name = name;
		this.description = description;
		this.color = color;
	}

	public abstract void squareLogic(Player p);
	
	public String getName() {
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
