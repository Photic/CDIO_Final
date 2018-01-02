package entity.gameboard;

import java.awt.Color;

public class GameBoard {

	Color standard = new Color(208, 251, 230);
	Color purple = new Color(179, 102, 255);
	Color grey = new Color(230, 230, 230);
	Color pink = new Color(255, 153, 255);
	Color orange = new Color(255, 117, 26);
	Color red = new Color(255, 51, 51);
	Color yellow = new Color(255, 255, 77);
	Color green = new Color(128, 255, 128);
	Color blue = new Color(102, 217, 255);
	Color turkies = new Color(31, 94, 90);
	Color magenta = new Color (229, 70, 0);

	private final Square[] squares = 
		{
			new Start("Start", "Recieve 2", standard),
			new Territory("BurgerJoint", "1", orange, "1"),
			new Territory("Pizza Place", "1", orange, "1"),
			new Chance("CHANCE", "Pick a chance card", standard),
			new Territory("Candystore", "1", yellow, "1"),
			new Territory("Icecream Store", "1", yellow, "1"),
			new Jail("Locked up, or visiting", "You are either in jail, or locked up", standard),
			new Territory("Museum", "2", pink, "2"),
			new Territory("Library", "2", pink, "2"),
			new Chance("CHANCE", "Pick a chance card", standard),
			new Territory("Skatepark", "2", blue, "2"),
			new Territory("Swimming Pool", "2", blue, "2"),
			new FreeParking("Free Parking", "Enjoy a relaxed moment", standard),
			new Territory("Gaming Hall", "3", turkies, "3"),
			new Territory("Movie Theater", "3", turkies, "3"),
			new Chance("CHANCE", "Pick a chance card", standard),
			new Territory("Toy Store", "3", red, "3"),
			new Territory("Animal Store", "3", red, "3"),
			new GoToJail("GoToJail", "You are goin to jail. Pay a fine next turn to get released", standard),
			new Territory("Bowling Ally", "4", grey, "4"),
			new Territory("The Zoo", "4", grey, "4"),
			new Chance("CHANCE", "Pick a chance card", standard),
			new Territory("The Water Park", "4", purple, "4"),
			new Territory("The Beach House", "4", purple, "4"),
		};

	public Square getField(int a)
	{
		return squares[a];
	}

	public int getSize() 
	{
		return squares.length;
	}
	
	
	
	
	

}
