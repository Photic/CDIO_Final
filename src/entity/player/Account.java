package entity.player;


import entity.deck.Card;
import entity.gameboard.Field;
import entity.gameboard.GameBoard;

public class Account {



	private int balance, actives, housesowned, companies, shipping, territoriesOwned;

	private int amountOfCards = 0;

	private Card antiJailCard;
	private Field[] blueFields;
	private Field[] pinkFields;
	private Field[] greenFields;
	private Field[] greyFields;
	private Field[] redFields;
	private Field[] whiteFields;
	private Field[] yellowFields;
	private Field[] purpleFields;

	private boolean allblue;
	private boolean allpink;
	private boolean allgreen;
	private boolean allgrey;
	private boolean allred;
	private boolean allwhite;
	private boolean allyellow;
	private boolean allpurple;


	public Account() {
		this.balance = 30000;
		this.actives = 0;
		this.companies = 0;
		this.shipping = 0;
		this.territoriesOwned = 0;
		this.housesowned = 0;

		blueFields = new Field[] {null, null};
		pinkFields = new Field[] {null, null, null};
		greenFields = new Field[] {null, null, null};
		greyFields = new Field[] {null, null, null};
		redFields = new Field[] {null, null, null};
		whiteFields = new Field[] {null, null, null};
		yellowFields = new Field[] {null, null, null};
		purpleFields = new Field[] {null, null};


	}

	public void recieveAntiJaulCard(Card cardrecieved) {
		this.setAntiJailCard(cardrecieved);
		this.amountOfCards++;
	}

	public void removeAntiJaulCard() {
		if (this.amountOfCards > 1) {
			this.amountOfCards--;
		}
		else {
			this.amountOfCards--;
			this.antiJailCard = null;
		}
	}

	/**
	 * This method adds a field to the account's portfolio, in the correct array, based on the colors of the fields
	 * @param field
	 * A field to be added
	 * @param gb
	 * The gameboard, that is instantiated in the gamecontroller.
	 */
	public void addField(Field field, GameBoard gb) {

		int count = 0;
		while(true) {
			if (field.getColor() == gb.getRed())
				if (redFields[count] == null) {
					this.redFields[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == gb.getBlue())
				if (blueFields[count] == null) {
					this.blueFields[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == gb.getPink())
				if (pinkFields[count] == null) {
					this.pinkFields[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == gb.getGreen())
				if (greenFields[count] == null) {
					this.greenFields[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == gb.getGrey())
				if (greyFields[count] == null) {
					this.greyFields[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == gb.getWhite())
				if (whiteFields[count] == null) {
					this.whiteFields[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == gb.getYellow())
				if (yellowFields[count] == null) {
					this.yellowFields[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == gb.getPurple())
				if (purpleFields[count] == null) {
					this.purpleFields[count] = field;
					break;
				} else {
					count++;
				}

			if (count == 4) {
				break;
			}

		}

	}



	public void removeField(GameBoard gameBoard, Field field){
		field = gameBoard.getField(field.getIndex());
		if(field.getColor() == gameBoard.getRed()){
			int count = this.redFields.length-1;
			while(true){
				if(this.redFields[count]==null){
					count--;	
				}
				else{
					this.redFields[count]=null;
					break;
				}
			}
			System.out.println("Fjernet rød");
		}

		else if(field.getColor() == gameBoard.getBlue()){
			int count = this.blueFields.length-1;
			while(true){
				if(this.blueFields[count]==null){
					count--;	
				}
				else{
					this.blueFields[count]=null;
					break;
				}
			}
			System.out.println("Fjernet blue");
		}

		else if(field.getColor() == gameBoard.getPink()){
			int count = this.pinkFields.length-1;
			while(true){
				if(this.pinkFields[count]==null){
					count--;	
				}
				else{
					this.pinkFields[count]=null;
					break;
				}
			}
			System.out.println("Fjernet pink");
		}


		else if(field.getColor() == gameBoard.getGreen()){
			int count = this.greenFields.length-1;
			while(true){
				if(this.greenFields[count]==null){
					count--;	
				}
				else{
					this.greenFields[count]=null;
					break;
				}
			}
			System.out.println("Fjernet grøn");
		}


		else if(field.getColor() == gameBoard.getGrey()){
			int count = this.greyFields.length-1;
			while(true){
				if(this.greyFields[count]==null){
					count--;	
				}
				else{
					this.greyFields[count]=null;
					break;
				}
			}
			System.out.println("Fjernet grå");
		}

		else if(field.getColor() == gameBoard.getWhite()){
			int count = this.whiteFields.length-1;
			while(true){
				if(this.whiteFields[count]==null){
					count--;	
				}
				else{
					this.whiteFields[count]=null;
					break;
				}
			}
			System.out.println("Fjernet hvid");
		}


		else if(field.getColor() == gameBoard.getYellow()){
			int count = this.yellowFields.length-1;
			while(true){
				if(this.yellowFields[count]==null){
					count--;	
				}
				else{
					this.yellowFields[count]=null;
					break;
				}
			}
			System.out.println("Fjernet gul");
		}

		else if(field.getColor() == gameBoard.getPurple()){
			int count = this.purpleFields.length-1;
			while(true){
				if(this.purpleFields[count]==null){
					count--;	
				}
				else{
					this.purpleFields[count]=null;
					break;
				}
			}
			System.out.println("Fjernet purple");
		}



		//		field.setOwner(null);
		//		field.setOwned(false);
		//		

	}// end of function


	public Field[] allOfAKindFields() {

		int length = 0;
		if (hasAllOfAKind()) {
			if (allblue) 
				length += blueFields.length;
			if (allpink)
				length += pinkFields.length;
			if (allgreen)
				length += greenFields.length;
			if (allgrey)
				length += greyFields.length;
			if (allred)
				length += redFields.length;
			if (allwhite)
				length += whiteFields.length;
			if (allyellow)
				length += yellowFields.length;
			if (allpurple)
				length += purpleFields.length;

			Field[] output = new Field[length];

			int count = 0;
			if (allblue) {
				output[count] = blueFields[0];
				count++;
				output[count] = blueFields[1];
				count++;
			}

			if (allpink) {
				output[count] = pinkFields[0];
				count++;
				output[count] = pinkFields[1];
				count++;
				output[count] = pinkFields[2];
				count++;
			}

			if (allgreen) {
				output[count] = greenFields[0];
				count++;
				output[count] = greenFields[1];
				count++;
				output[count] = greenFields[2];
				count++;
			}


			if (allgrey) {
				output[count] = greyFields[0];
				count++;
				output[count] = greyFields[1];
				count++;
				output[count] = greyFields[2];
				count++;
			}


			if (allred) {
				output[count] = redFields[0];
				count++;
				output[count] = redFields[1];
				count++;
				output[count] = redFields[2];
				count++;
			}

			if (allwhite) {
				output[count] = whiteFields[0];
				count++;
				output[count] = whiteFields[1];
				count++;
				output[count] = whiteFields[2];
				count++;
			}



			if (allyellow) {
				output[count] = yellowFields[0];
				count++;
				output[count] = yellowFields[1];
				count++;
				output[count] = yellowFields[2];
				count++;
			}

			if (allpurple) {
				output[count] = purpleFields[0];
				count++;
				output[count] = purpleFields[1];
				count++;
			}
			return output;
		} else {
			return null;
		}

	}



	/**
	 * This method check all the field arrays in the account, and tell if the player has all of a kind of field.
	 * @return
	 */
	public boolean hasAllOfAKind() {

		int count;
		boolean output = false;

		count = 0;
		for (int i = 0; i < blueFields.length; i++) {
			if (blueFields[i] != null) {
				count++;
			}
		}
		if (count == blueFields.length) {
			output = true;
			setAllblue(true);
		}

		count = 0;
		for (int i = 0; i < pinkFields.length; i++) {
			if (pinkFields[i] != null) {
				count++;
			}
		}
		if (count == pinkFields.length) {
			output = true;
			setAllpink(true);
		}

		count = 0;
		for (int i = 0; i < greenFields.length; i++) {
			if (greenFields[i] != null) {
				count++;
			}
		}
		if (count == greenFields.length) {
			output = true;
			setAllgreen(true);
		}

		count = 0;
		for (int i = 0; i < greyFields.length; i++) {
			if (greyFields[i] != null) {
				count++;
			}
		}
		if (count == greyFields.length) {
			output = true;
			setAllgrey(true);
		}

		count = 0;
		for (int i = 0; i < redFields.length; i++) {
			if (redFields[i] != null) {
				count++;
			}
		}
		if (count == redFields.length) {
			output = true;
			setAllred(true);
		}

		count = 0;
		for (int i = 0; i < whiteFields.length; i++) {
			if (whiteFields[i] != null) {
				count++;
			}
		}
		if (count == whiteFields.length) {
			output = true;
			setAllwhite(true);
		}

		count = 0;
		for (int i = 0; i < yellowFields.length; i++) {
			if (yellowFields[i] != null) {
				count++;
			}
		}
		if (count == yellowFields.length) {
			output = true;
			setAllyellow(true);
		}

		count = 0;
		for (int i = 0; i < purpleFields.length; i++) {
			if (purpleFields[i] != null) {
				count++;
			}
		}
		if (count == purpleFields.length) {
			output = true;
			setAllpurple(true);
		}

		return output;

	}
	/**
	 * This method returns all the fields the player owns.
	 * @return
	 */
	public Field[] getFields() {

		Field[] temp = new Field[] {blueFields[0], blueFields[1], 
				pinkFields[0], pinkFields[1], pinkFields[2], 
				greenFields[0],greenFields[1], greenFields[2],
				greyFields[0], greyFields[1], greyFields[2],
				redFields[0], redFields[1], redFields[2],
				whiteFields[0], whiteFields[1], whiteFields[2],
				yellowFields[0], yellowFields[1], yellowFields[2],
				purpleFields[0],purpleFields[1]};

		int count = 0;
		for (int i = 0; i < temp.length; i++) 
			if (temp[i] != null) 
				count++;



		Field[] output = new Field[count];
		count = 0;
		for (int i = 0; i < temp.length; i++) 
			if (temp[i] != null) {
				output[count] = temp[i];
				count++;
			}

		return output;
	}
	
	
	public int numberOfTerri() {
		
		
		
		return getFields().length;
	}







	/**
	 * Calculates Player Worth and returns the sum.
	 * @param p
	 * @param gameboard
	 * @return
	 */
	public int getPlayerWorth(Player p) {
		return this.balance + this.actives;
	}

	public void addActives(int money) {
		this.actives = this.actives + money;
	}

	public void buyField(int price) {
		addBalance(-price);
		addActives(price);
	}
	
	public void sellField(int price) {
		addBalance(price);
		addActives(-price);
	}


	//--------------------------------------------------------
	//
	//                   Getters & Setters!
	//
	//--------------------------------------------------------
	public int getActives() {
		return actives;
	}

	public int getBalance() {
		return balance;
	}

	public void addBalance(int credit) {
		this.balance = this.balance + credit;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getHousesowned() {
		return housesowned;
	}

	public int getCompanies() {
		return companies;
	}

	public void setCompanies(int companies) {
		this.companies = companies;
	}

	public Card getAntiJailCard() {
		return antiJailCard;
	}

	public void setAntiJailCard(Card antiJailCard) {
		this.antiJailCard = antiJailCard;
	}

	public int getShipping() {
		return shipping;
	}

	public void setShipping(int shipping) {
		this.shipping = shipping;
	}


	public int getAmountOfCards() {
		return amountOfCards;
	}

	public void setAmountOfCards(int amountOfCards) {
		this.amountOfCards = amountOfCards;
	}
	public int getTerritories() {
		return territoriesOwned;
	}

	public void setTerritories(int territories) {
		this.territoriesOwned = territories;
	}

	public Field[] getBlueFields() {
		return blueFields;
	}

	public void setBlueFields(Field[] blueFields) {
		this.blueFields = blueFields;
	}

	public Field[] getPinkFields() {
		return pinkFields;
	}

	public void setPinkFields(Field[] pinkFields) {
		this.pinkFields = pinkFields;
	}

	public Field[] getGreenFields() {
		return greenFields;
	}

	public void setGreenFields(Field[] greenFields) {
		this.greenFields = greenFields;
	}

	public Field[] getGreyFields() {
		return greyFields;
	}

	public void setGreyFields(Field[] greyFields) {
		this.greyFields = greyFields;
	}

	public Field[] getRedFields() {
		return redFields;
	}

	public void setRedFields(Field[] redFields) {
		this.redFields = redFields;
	}

	public Field[] getWhiteFields() {
		return whiteFields;
	}

	public void setWhiteFields(Field[] whiteFields) {
		this.whiteFields = whiteFields;
	}

	public Field[] getYellowFields() {
		return yellowFields;
	}

	public void setYellowFields(Field[] yellowFields) {
		this.yellowFields = yellowFields;
	}

	public Field[] getPurpleFields() {
		return purpleFields;
	}

	public void setPurpleFields(Field[] purpleFields) {
		this.purpleFields = purpleFields;
	}

	public boolean isAllblue() {
		return allblue;
	}

	public void setAllblue(boolean allblue) {
		this.allblue = allblue;
	}

	public boolean isAllpink() {
		return allpink;
	}

	public void setAllpink(boolean allpink) {
		this.allpink = allpink;
	}

	public boolean isAllgreen() {
		return allgreen;
	}

	public void setAllgreen(boolean allgreen) {
		this.allgreen = allgreen;
	}

	public boolean isAllgrey() {
		return allgrey;
	}

	public void setAllgrey(boolean allgrey) {
		this.allgrey = allgrey;
	}

	public boolean isAllred() {
		return allred;
	}

	public void setAllred(boolean allred) {
		this.allred = allred;
	}

	public boolean isAllwhite() {
		return allwhite;
	}

	public void setAllwhite(boolean allwhite) {
		this.allwhite = allwhite;
	}

	public boolean isAllyellow() {
		return allyellow;
	}

	public void setAllyellow(boolean allyellow) {
		this.allyellow = allyellow;
	}

	public boolean isAllpurple() {
		return allpurple;
	}

	public void setAllpurple(boolean allpurple) {
		this.allpurple = allpurple;
	}
	
	public void setHousesowned(int housesowned) {
		this.housesowned = housesowned;
	}
	public Field[] noHouses(){
		int count = 0; 
		int count2 = 0;
		for (int i = 0; i < getFields().length; i++) 
			if(getFields()[i].getHouses() == 0)
				count++;
			
		Field[] fields = new Field[count];
		
		for (int i = 0; i < getFields().length; i++)
			if(getFields()[i].getHouses() == 0){
				fields[count2] = getFields()[i];
				count2++;
			}

		return fields;
	}

}
