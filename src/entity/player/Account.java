package entity.player;

import entity.gameboard.GameBoard;

public class Account {
	
	private int balance;
	private int actives;
	private int antiJailCards;
	

	public Account() {
		this.balance = 30000;
		this.actives = 0;
		this.antiJailCards = 0;
	}
	
	
	public void addJailCard() {
		this.antiJailCards++;
	}
	
	public void removeJailCard() {
		this.antiJailCards--;
	}
	
	/**
	 * Calculates Player Worth and returns the sum.
	 * @param p
	 * @param gameboard
	 * @return
	 */
	public int getPlayerWorth(Player p, GameBoard gameboard) {
		getPlayerActives(p, gameboard);
		return this.balance + this.actives;
	}
	
	/**
	 * Calculate the players actives. Use getActives to get the sum.
	 * @param p
	 * @param gameboard
	 */
	public void getPlayerActives(Player p, GameBoard gameboard) {
		this.actives = 0;
		for (int i = 0; i <= gameboard.getLength(); i++) {
			if (gameboard.getField(i).getOwnerName() == p.getName()) {
				addActives(gameboard.getField(i).getPrice());
				addActives((gameboard.getField(i).getHouses())*(gameboard.getField(i).getHousePrice()));
			}
		}
	}
	
	/**
	 * Supporting function to getPlayerActives(Player p, GameBoard gameboard);
	 * @param credit
	 */
	public void addActives(int credit) {
		this.actives = this.actives + credit;
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

	public void setBalance(int credit) {
		this.balance = this.balance + credit;
	}
	
	public int getAntiJailCards() {
		return antiJailCards;
	}

}
