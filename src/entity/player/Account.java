package entity.player;


import entity.deck.Card;
import entity.gameboard.Field;

public class Account {

	private int balance, actives, housesowned, companies, shipping, territoriesOwned, amountOfCards;
	private Card jailCard;
	private Field[] blueFields, pinkFields, greenFields, greyFields, redFields, whiteFields, yellowFields, purpleFields;
	private boolean allblue, allpink, allgreen, allgrey, allred, allwhite, allyellow, allpurple;

	public Account() {
		this.balance = 30000;
		this.actives = 0;
		this.companies = 0;
		this.shipping = 0;
		this.territoriesOwned = 0;
		this.housesowned = 0;
		this.amountOfCards = 0;

		this.blueFields = new Field[] {null, null};
		this.pinkFields = new Field[] {null, null, null};
		this.greenFields = new Field[] {null, null, null};
		this.greyFields = new Field[] {null, null, null};
		this.redFields = new Field[] {null, null, null};
		this.whiteFields = new Field[] {null, null, null};
		this.yellowFields = new Field[] {null, null, null};
		this.purpleFields = new Field[] {null, null};
	}

	public void recieveAntiJailCard(Card cardrecieved) {
		this.setAntiJailCard(cardrecieved);
		this.amountOfCards++;
	}

	public void removeAntiJailCard() {
		if (this.amountOfCards > 1) {
			this.amountOfCards--;
		}
		else {
			this.amountOfCards--;
			this.jailCard = null;
		}
	}	

	/**
	 * Calculates Player Worth and returns the sum.
	 * @param p
	 * @param gameboard
	 * @return
	 */
	public int getPlayerWorth() {
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
		return this.actives;
	}

	public int getBalance() {
		return this.balance;
	}

	public void addBalance(int credit) {
		this.balance = this.balance + credit;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getHousesowned() {
		return this.housesowned;
	}

	public int getCompanies() {
		return this.companies;
	}

	public void setCompanies(int companies) {
		this.companies = companies;
	}

	public Card getAntiJailCard() {
		return this.jailCard;
	}

	public void setAntiJailCard(Card antiJailCard) {
		this.jailCard = antiJailCard;
	}

	public int getShipping() {
		return this.shipping;
	}

	public void setShipping(int shipping) {
		this.shipping = shipping;
	}

	public int getAmountOfCards() {
		return this.amountOfCards;
	}

	public void setAmountOfCards(int amountOfCards) {
		this.amountOfCards = amountOfCards;
	}
	public int getTerritories() {
		return this.territoriesOwned;
	}

	public void setTerritories(int territories) {
		this.territoriesOwned = territories;
	}

	public Field[] getBlueFields() {
		return this.blueFields;
	}

	public void setBlueFields(Field[] blueFields) {
		this.blueFields = blueFields;
	}

	public Field[] getPinkFields() {
		return this.pinkFields;
	}

	public void setPinkFields(Field[] pinkFields) {
		this.pinkFields = pinkFields;
	}

	public Field[] getGreenFields() {
		return this.greenFields;
	}

	public void setGreenFields(Field[] greenFields) {
		this.greenFields = greenFields;
	}

	public Field[] getGreyFields() {
		return this.greyFields;
	}

	public void setGreyFields(Field[] greyFields) {
		this.greyFields = greyFields;
	}

	public Field[] getRedFields() {
		return this.redFields;
	}

	public void setRedFields(Field[] redFields) {
		this.redFields = redFields;
	}

	public Field[] getWhiteFields() {
		return this.whiteFields;
	}

	public void setWhiteFields(Field[] whiteFields) {
		this.whiteFields = whiteFields;
	}

	public Field[] getYellowFields() {
		return this.yellowFields;
	}

	public void setYellowFields(Field[] yellowFields) {
		this.yellowFields = yellowFields;
	}

	public Field[] getPurpleFields() {
		return this.purpleFields;
	}

	public void setPurpleFields(Field[] purpleFields) {
		this.purpleFields = purpleFields;
	}

	public boolean isAllblue() {
		return this.allblue;
	}

	public void setAllblue(boolean allblue) {
		this.allblue = allblue;
	}

	public boolean isAllpink() {
		return this.allpink;
	}

	public void setAllpink(boolean allpink) {
		this.allpink = allpink;
	}

	public boolean isAllgreen() {
		return this.allgreen;
	}

	public void setAllgreen(boolean allgreen) {
		this.allgreen = allgreen;
	}

	public boolean isAllgrey() {
		return this.allgrey;
	}

	public void setAllgrey(boolean allgrey) {
		this.allgrey = allgrey;
	}

	public boolean isAllred() {
		return this.allred;
	}

	public void setAllred(boolean allred) {
		this.allred = allred;
	}

	public boolean isAllwhite() {
		return this.allwhite;
	}

	public void setAllwhite(boolean allwhite) {
		this.allwhite = allwhite;
	}

	public boolean isAllyellow() {
		return this.allyellow;
	}

	public void setAllyellow(boolean allyellow) {
		this.allyellow = allyellow;
	}

	public boolean isAllpurple() {
		return this.allpurple;
	}

	public void setAllpurple(boolean allpurple) {
		this.allpurple = allpurple;
	}
	
	public void setHousesowned(int housesowned) {
		this.housesowned = housesowned;
	}


}
