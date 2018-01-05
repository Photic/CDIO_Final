package entity.gameboard;

import java.awt.Color;
import java.io.IOException;

import boundary.TextReader;

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

	private Field[] fields;
	private String[] fieldName;
	private String[] fieldDescription;

<<<<<<< HEAD
	public GameBoard(TextReader name) {
=======

	public GameBoard(TextReader name, TextReader description) {

>>>>>>> d2b2e4430cdfeab05b707e69abe750ce8eaa32ce
		try {
			this.fieldName = name.textFromFile("FieldNames.txt");
			this.fieldDescription = name.textFromFile("FieldDescriptions.txt");
		} catch (IOException e) {
			System.err.println("Something went wrong when trieng to import Text from TextReader in Deck");
			e.printStackTrace();
		}

		this.fields = new Field[] 
				{
						new Start(fieldName[0], fieldDescription[0], standard),																				// 0
						new Territory(fieldName[1], fieldDescription[1], blue, 1200, 1000, new int[] {50, 250, 750, 2250, 4000, 6000}),						// 1
						new Chance(fieldName[2], fieldDescription[2], standard),																						// 2
						new Territory(fieldName[3], fieldDescription[3], blue, 1200,1000, new int[] {50, 250, 750, 2250, 4000, 6000}),						// 3
						new Tax(fieldName[4], fieldDescription[4], standard, 4000),															// 4
						new Shipping(fieldName[5], fieldDescription[5], standard, 4000, new int[] {500, 1000, 2000, 4000, 0, 0}),															// 5
						new Territory(fieldName[6], fieldDescription[6], pink, 2000, 1000, new int[] {100, 600, 1800, 5400, 8000, 11000}),					// 6
						new Chance(fieldName[7], fieldDescription[7], standard),																						// 7
						new Territory(fieldName[8], fieldDescription[8], pink, 2000, 1000, new int[] {100, 600, 1800, 5400, 8000, 11000}),					// 8
						new Territory(fieldName[9], fieldDescription[9], pink, 2400, 1000, new int[] {150, 800, 2000, 6000, 9000, 12000}),						// 9
						new Jail(fieldName[10], fieldDescription[10], standard),																					// 10
						new Territory(fieldName[11], fieldDescription[11], green, 2800, 2000, new int[] {200, 1000, 3000, 9000, 12500, 15000}),			// 11
						new Company(fieldName[12], fieldDescription[12], standard, 3000),																		// 12
						new Territory(fieldName[13], fieldDescription[13], green, 2800, 2000, new int[] {200, 1000, 3000, 9000, 12500, 15000}),					// 13
						new Territory(fieldName[14], fieldDescription[14], green, 3200, 2000, new int[] {250, 1250, 3750, 10000, 14000, 18000}),				// 14
						new Shipping(fieldName[15], fieldDescription[15], standard, 4000, new int[] {500, 1000, 2000, 4000, 0, 0}),																// 15
						new Territory(fieldName[16], fieldDescription[16], grey, 3600, 2000, new int[] {300, 1400, 4000, 11000, 15000, 19000}),				// 16
						new Chance(fieldName[17], fieldDescription[17], standard),																						// 17
						new Territory(fieldName[18], fieldDescription[18], grey, 3600, 2000, new int[] {300, 1400, 4000, 11000, 15000, 19000}),					// 18
						new Territory(fieldName[19], fieldDescription[19], grey, 4000, 2000, new int[] {350, 1600, 4400, 12000, 16000, 20000}),					// 19
						new Parking(fieldName[20], fieldDescription[20], standard),																		// 20
						new Territory(fieldName[21], fieldDescription[21], red, 4400, 3000, new int[] {350, 1800, 5000, 14000, 17500, 21000}),					// 21
						new Chance(fieldName[22], fieldDescription[22], standard),																						// 22
						new Territory(fieldName[23], fieldDescription[23], red, 4400, 3000, new int[] {350, 1800, 5000, 14000, 17500, 21000}),					// 23
						new Territory(fieldName[24], fieldDescription[24], red, 4400, 3000, new int[] {400, 2000, 6000, 15000, 18500, 22000}),					// 24
						new Shipping(fieldName[25], fieldDescription[25], standard, 4000, new int[] {500, 1000, 2000, 4000, 0, 0}),																	// 25
						new Territory(fieldName[26], fieldDescription[26], white, 5200, 3000, new int [] {450, 2200, 6600, 16000, 19500, 23000}),					// 26
						new Territory(fieldName[27], fieldDescription[27], white, 5200, 3000, new int[] {450, 2200, 6600, 16000, 19500, 23000}),				// 27
						new Company(fieldName[28], fieldDescription[28], standard, 3000),																	// 28
						new Territory(fieldName[29], fieldDescription[29], white, 5600, 3000, new int[] {500, 2400, 7200, 17000, 20500, 24000}),					// 29
						new GoToJail(fieldName[30], fieldDescription[30], standard),																	// 30
						new Territory(fieldName[31], fieldDescription[31], yellow, 6000, 4000, new int[] {550, 2600, 7800, 18000, 22000, 25000}),				// 31
						new Territory(fieldName[32], fieldDescription[32], yellow, 6000, 4000, new int[] {550, 2600, 7800, 18000, 22000, 25000}),				// 32
						new Chance(fieldName[33], fieldDescription[33], standard),																						// 33
						new Territory(fieldName[34], fieldDescription[34], yellow, 6400, 4000, new int[] {600, 3000, 9000, 20000, 24000, 28000}),					// 34
						new Shipping(fieldName[35], fieldDescription[35], standard, 4000, new int[] {500, 1000, 2000, 4000, 0, 0}),														// 35
						new Chance(fieldName[36], fieldDescription[36], standard),																						// 36
						new Territory(fieldName[37], fieldDescription[37], purple, 7000, 4000, new int[] {700, 3500, 10000, 22000, 26000, 30000}),		// 37
						new Tax(fieldName[38], fieldDescription[38], standard, 2000),														// 38
						new Territory(fieldName[39], fieldDescription[39], purple, 8000, 4000, new int[] {1000, 4000, 1000, 28000, 34000, 40000})				// 39
				};
		
	}







	/**
	 * Creates the entire gameboard.
	 */

	public Field getField(int a) {
		return fields[a];
	}

	public int getLength() {
		return fields.length;
	}

	public Color getPurple() {
		return purple;
	}



	public void setPurple(Color purple) {
		this.purple = purple;
	}



	public Color getGrey() {
		return grey;
	}



	public void setGrey(Color grey) {
		this.grey = grey;
	}



	public Color getPink() {
		return pink;
	}



	public void setPink(Color pink) {
		this.pink = pink;
	}



	public Color getOrange() {
		return orange;
	}



	public void setOrange(Color orange) {
		this.orange = orange;
	}



	public Color getRed() {
		return red;
	}



	public void setRed(Color red) {
		this.red = red;
	}



	public Color getYellow() {
		return yellow;
	}



	public void setYellow(Color yellow) {
		this.yellow = yellow;
	}



	public Color getGreen() {
		return green;
	}



	public void setGreen(Color green) {
		this.green = green;
	}



	public Color getBlue() {
		return blue;
	}



	public void setBlue(Color blue) {
		this.blue = blue;
	}



	public Color getTurkies() {
		return turkies;
	}



	public void setTurkies(Color turkies) {
		this.turkies = turkies;
	}



	public Color getMagenta() {
		return magenta;
	}



	public void setMagenta(Color magenta) {
		this.magenta = magenta;
	}



	public Color getWhite() {
		return white;
	}



	public void setWhite(Color white) {
		this.white = white;
	}



	public Color getBrown() {
		return brown;
	}



	public void setBrown(Color brown) {
		this.brown = brown;
	}

}
