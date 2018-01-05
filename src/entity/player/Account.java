package entity.player;


import java.awt.Color;

import entity.deck.Card;
import entity.gameboard.Field;
import entity.gameboard.GameBoard;

public class Account {
	

	private int balance, actives, housesowned, hotelsowned, companies, shipping, territoriesOwned, amountOfCards;


	private Card antiJailCard;
	
	
	private Field[] blueFields;
	

	private Field[] pinkFields;
	private Field[] greenFields;
	private Field[] greyFields;
	private Field[] redFields;
	private Field[] whiteFields;
	private Field[] yellowFields;
	private Field[] purpleFields;

	public Account() {
		this.balance = 30000;
		this.actives = 0;
		this.companies = 0;
		this.shipping = 0;
		this.territoriesOwned = 0;
		this.housesowned = 0;
		this.hotelsowned = 0;
		
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
	
	public void addField(Field field, GameBoard gb) {
		
		if (field.getColor() == gb.getRed()) {
			for (int i = 0; i < redFields.length; i++) {
				if (redFields[i] != null) {
					this.redFields[i] = field;
				}
			}
		} else	if (field.getColor() == gb.getBlue()) {
			for (int i = 0; i < blueFields.length; i++) {
				if (blueFields[i] != null) {
					this.blueFields[i] = field;
				}
			}
		} else	if (field.getColor() == gb.getMagenta()) {
			for (int i = 0; i < pinkFields.length; i++) {
				if (pinkFields[i] != null) {
					this.pinkFields[i] = field;
				}
			}
		} else	if (field.getColor() == gb.getGreen()) {
			for (int i = 0; i < greenFields.length; i++) {
				if (greenFields[i] != null) {
					this.greenFields[i] = field;
				}
			}
		} else	if (field.getColor() == gb.getGrey()) {
			for (int i = 0; i < greyFields.length; i++) {
				if (greyFields[i] != null) {
					this.greyFields[i] = field;
				}
			}
		} else	if (field.getColor() == gb.getRed()) {
			for (int i = 0; i < redFields.length; i++) {
				if (redFields[i] != null) {
					this.redFields[i] = field;
				}
			}
		} else	if (field.getColor() == gb.getWhite()) {
			for (int i = 0; i < whiteFields.length; i++) {
				if (whiteFields[i] != null) {
					this.whiteFields[i] = field;
				}
			}
		} else	if (field.getColor() == gb.getYellow()) {
			for (int i = 0; i < yellowFields.length; i++) {
				if (yellowFields[i] != null) {
					this.yellowFields[i] = field;
				}
			}
		} else	if (field.getColor() == gb.getPurple()) {
			for (int i = 0; i < purpleFields.length; i++) {
				if (purpleFields[i] != null) {
					this.purpleFields[i] = field;
				}
			}
		}
		
		
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
	
//	public void payRent(int rent, Player owner) {
//		
//		owner.getAccount().setBalance(rent + owner.getAccount().getBalance());
//		
//		setBalance(getBalance() - rent);
//		
//	}
	
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


	public void setHousesowned(int housesowned) {
		this.housesowned = this.hotelsowned + housesowned;
	}


	public int getHotelsowned() {
		return hotelsowned;
	}


	public void setHotelsowned(int hotelsowned) {
		this.hotelsowned = this.hotelsowned + hotelsowned;
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
	
	
}
