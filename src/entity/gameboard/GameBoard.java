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
	Color white = new Color(255, 255, 255);
	Color brown = new Color(153, 102, 0);

	private final Square[] squares = 
		{
			new Start("Start", "Modtag kr. 4.000", standard),
			new Territory("Rødovervej", "kr. 1.200", blue),
			new Chance("Prøv Lykken", "", standard),
			new Territory("Hvidovrevej", "kr. 1.200", blue),
			new Tax("Betal inkomstskat", "10% eller kr. 4.000", standard),
			new ShippingCompany("Rederiet Lindinger A/S", "kr. 4.000", standard),
			new Territory("Roskildevej", "kr. 2.000", pink),
			new Chance("Prøv Lykken", "", standard),
			new Territory("Valby Langgade", "kr. 2.000", pink),
			new Territory("Allégade", "kr. 2.400", pink),
			new Jail("Fængsel", "På Besøg", standard),
			new Territory("Frederiksberg Allé", "kr. 2.800", green),
			new Company("Coca-Cola", "kr. 3.000", standard),
			new Territory("Büllowsvej", "kr. 2.800", green),
			new Territory("GL. Kongevej", "kr. 3.200", green),
			new ShippingCompany("Grenaa-Hundested", "kr. 4.800", standard),
			new Territory("Bernstorffsvej", "kr. 3.600", grey),
			new Chance("Prøv Lykken", "", standard),
			new Territory("Hellerupvej", "kr. 3.600", grey),
			new Territory("Strandvej", "kr. 4.000", grey),
			new Parking("Parkering", "Den Danske Bank", standard),
			new Territory("Trianglen", "kr. 4.400", red),
			new Chance("Prøv Lykken", "", standard),
			new Territory("Østerbrogade", "kr. 4.400", red),
			new Territory("Grønningen", "kr. 4.800", red),
			new ShippingCompany("Mols-Linien A/S", "kr. 4.000", standard),
			new Territory("Bredegade", "kr. 5.200", white),
			new Territory("Kgs. Nytorv", "kr. 5.200", white),
			new Company("Faxe Bryggeri", "kr. 3.000", standard),
			new Territory("Østergade", "kr. 5.600", white),
			new GoToJail("De Fængsles", "Du mister din tur", standard),
			new Territory("Amagertorv", "kr. 6.000", yellow),
			new Territory("Vimmelskaftet", "kr. 6.000", yellow),
			new Chance("Prøv Lykken", "", standard),
			new Territory("Nygade", "kr. 6.400", yellow),
			new ShippingCompany("Skandinavisk Linietrafik", "kr. 4.000", standard),
			new Chance("Prøv Lykken", "", standard),
			new Territory("Frederiksberggade", "kr. 7.000", purple),
			new Tax2_0("Ekstraordinær statsskat", "betal kr. 2.000", standard),
			new Territory("Rådhuspladsen", "kr. 8.000", purple)
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