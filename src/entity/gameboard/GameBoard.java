package entity.gameboard;

import java.awt.Color;

public class GameBoard {

	/**
	 * Colors
	 */
	private Color standard = new Color(208, 251, 230);
	private Color purple = new Color(179, 102, 255);
	private Color grey = new Color(128, 128, 128);
	private Color pink = new Color(255, 153, 255);
	private Color orange = new Color(255, 117, 26);
	private Color red = new Color(255, 51, 51);
	private Color yellow = new Color(255, 255, 77);
	private Color green = new Color(128, 255, 128);
	private Color blue = new Color(102, 217, 255);
	private Color turkies = new Color(31, 94, 90);
	private Color magenta = new Color (229, 70, 0);
	private Color white = new Color(255, 255, 255);
	private Color brown = new Color(153, 102, 0);
	
	

	private final Field[] squares = 
		{
			new Start("Start", "Modtag kr. 4.000", standard),
			new Territory("Rødovervej", "kr. 1.200", blue, 1200, 1000, new int[] {50, 250, 750, 2250, 4000, 6000}),
			new Chance("Prøv Lykken", "", standard),
			new Territory("Hvidovrevej", "kr. 1.200", blue, 1200,1000, new int[] {50, 250, 750, 2250, 4000, 6000}),
			new Tax("Betal inkomstskat", "10% eller kr. 4.000", standard),
			new Shipping("Rederiet Lindinger A/S", "kr. 4.000", standard, 4000),
			new Territory("Roskildevej", "kr. 2.000", pink, 2000, 1000, new int[] {100, 600, 1800, 5400, 8000, 11000}),
			new Chance("Prøv Lykken", "", standard),
			new Territory("Valby Langgade", "kr. 2.000", pink, 2000, 1000, new int[] {100, 600, 1800, 5400, 8000, 11000}),
			new Territory("Allégade", "kr. 2.400", pink, 2400, 1000, new int[] {150, 800, 2000, 6000, 9000, 12000}),
			new Jail("Fængsel", "På Besøg", standard),
			new Territory("Frederiksberg Allé", "kr. 2.800", green, 2800, 2000, new int[] {200, 1000, 3000, 9000, 12500, 15000}),
			new Company("Coca-Cola", "kr. 3.000", standard, 3000),
			new Territory("Büllowsvej", "kr. 2.800", green, 2800, 2000, new int[] {200, 1000, 3000, 9000, 12500, 15000}),
			new Territory("GL. Kongevej", "kr. 3.200", green, 3200, 2000, new int[] {250, 1250, 3750, 10000, 14000, 18000}),
			new Shipping("Grenaa-Hundested", "kr. 4.800", standard, 4000),
			new Territory("Bernstorffsvej", "kr. 3.600", grey, 3600, 2000, new int[] {300, 1400, 4000, 11000, 15000, 19000}),
			new Chance("Prøv Lykken", "", standard),
			new Territory("Hellerupvej", "kr. 3.600", grey, 3600, 2000, new int[] {300, 1400, 4000, 11000, 15000, 19000}),
			new Territory("Strandvej", "kr. 4.000", grey, 4000, 2000, new int[] {350, 1600, 4400, 12000, 16000, 20000}),
			new Parking("Parkering", "Den Danske Bank", standard),
			new Territory("Trianglen", "kr. 4.400", red, 4400, 3000, new int[] {350, 1800, 5000, 14000, 17500, 21000}),
			new Chance("Prøv Lykken", "", standard),
			new Territory("Østerbrogade", "kr. 4.400", red, 4400, 3000, new int[] {350, 1800, 5000, 14000, 17500, 21000}),
			new Territory("Grønningen", "kr. 4.800", red, 4400, 3000, new int[] {400, 2000, 6000, 15000, 18500, 22000}),
			new Shipping("Mols-Linien A/S", "kr. 4.000", standard, 4000),
			new Territory("Bredegade", "kr. 5.200", white, 5200, 3000, new int [] {450, 2200, 6600, 16000, 19500, 23000}),
			new Territory("Kgs. Nytorv", "kr. 5.200", white, 5200, 3000, new int[] {450, 2200, 6600, 16000, 19500, 23000}),
			new Company("Faxe Bryggeri", "kr. 3.000", standard, 3000),
			new Territory("Østergade", "kr. 5.600", white, 5600, 3000, new int[] {500, 2400, 7200, 17000, 20500, 24000}),
			new GoToJail("De Fængsles", "Du mister din tur", standard),
			new Territory("Amagertorv", "kr. 6.000", yellow, 6000, 4000, new int[] {550, 2600, 7800, 18000, 22000, 25000}),
			new Territory("Vimmelskaftet", "kr. 6.000", yellow, 6000, 4000, new int[] {550, 2600, 7800, 18000, 22000, 25000}),
			new Chance("Prøv Lykken", "", standard),
			new Territory("Nygade", "kr. 6.400", yellow, 6400, 4000, new int[] {600, 3000, 9000, 20000, 24000, 28000}),
			new Shipping("Skandinavisk Linietrafik", "kr. 4.000", standard, 4000),
			new Chance("Prøv Lykken", "", standard),
			new Territory("Frederiksberggade", "kr. 7.000", purple, 7000, 4000, new int[] {700, 3500, 10000, 22000, 26000, 30000}),
			new Tax("Ekstraordinær statsskat", "betal kr. 2.000", standard),
			new Territory("Rådhuspladsen", "kr. 8.000", purple, 8000, 4000, new int[] {1000, 4000, 1000, 28000, 34000, 40000})
			
		};

	public Field getField(int a) {
		return squares[a];
	}

	public int getLength() {
		return squares.length;
	}
	
}
