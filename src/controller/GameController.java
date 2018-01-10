package controller;


import java.io.IOException;

import boundary.AudioPlayer;
import boundary.TextReader;
import entity.DiceCup;
import entity.gameboard.Field;
import entity.player.Player;
import entity.player.PlayerList;


/**
 * The central gamecontroller, that controls the game.
 *
 */
public class GameController {

	private AudioPlayer dac;
	private GUIController gui;
	private PlayerList playerList;
	private FieldController fc;
	private DiceCup dicecup;
	private DeckController dc;
	private boolean playing;
	private TextReader textReader;
	private int alivePlayers;

	/**
	 * Constructor setting up the gamecontroller.
	 * @throws IOException 
	 */
	public GameController() throws IOException 
	{
		this.textReader = new TextReader();
		this.gui = new GUIController(this.textReader);
		this.fc = new FieldController(this.textReader);
		this.dc = new DeckController(this.textReader);
		this.playing = true;
		this.dicecup = new DiceCup();

	}

	/**
	 * The central method controlling the game.
	 */
	public void gameControl() 
	{
		initGui();
		this.alivePlayers = this.playerList.getLength();

		while(this.playing) {
			gameLoop();
		}
	}

	/**
	 * Controls the flow of the game.
	 */
	private void gameLoop() {
		boolean checker;
//		//det her er bare til mathias. jeg bruger det til at teste det med at købe huse.
//		fc.evaluateField(gameboard.getField(1), gui, playerList.getPlayer(0), 0, dc, gameboard, playerList);
//		fc.evaluateField(gameboard.getField(3), gui, playerList.getPlayer(0), 0, dc, gameboard, playerList);
//		
//		gameboard.getField(1).setHouses(1);

		
		if (this.alivePlayers == 1) {
			for (int i = 0; i < this.playerList.getLength(); i++) 
				if (this.playerList.getPlayer(i).isBankrupt() == false) 
					this.gui.showWinner(this.playerList.getPlayer(i));
		} else {
			
			int j = 0;
			
			while (j < this.playerList.getLength()) {
				
				//The game can be played normally if the player is not bankrupt or in jail.
				if (this.playerList.getPlayer(j).isBankrupt() == false && this.playerList.getPlayer(j).isInJail() == false) {

					fc.getHc().houseControl(this.playerList, j, this, this.gui, this.fc);

				} else if (this.playerList.getPlayer(j).isBankrupt() == false && this.playerList.getPlayer(j).isInJail() == true) {
					jailDecision(this.playerList.getPlayer(j));
					checkForDoubleDiceJail(j);
				}

				checkForLostPlayers();
				
				checker = checkForDoubleDice(j);
				
				if (checker == false) {
					this.playerList.getPlayer(j).setNumberOfEqualDice(0);
					j++;
				}
				
			}
		}


	}

	private void initGui() {
		this.gui.defineGUI(this.fc);
		this.playerList = this.gui.registerPlayerCount();
		this.gui.placePlayer();
	}

	/**
	 * Checks if the player
	 * @param i
	 */
	private void checkForLostPlayers() {

		int stillAliveLost = 0;
		
		for (int j = 0; j < this.playerList.getLength(); j++)
			if (this.playerList.getPlayer(j).getAccount().getBalance() < 0) {
				this.playerList.getPlayer(j).setBankrupt(true);
				this.gui.removeBankrupted(playerList.getPlayer(j), this.fc);
			} else {
				stillAliveLost++;
			}

		this.alivePlayers = stillAliveLost;

	}
	
	private boolean checkForDoubleDice(int j) {
		if (this.dicecup.equalsDice()) {
			this.playerList.getPlayer(j).setNumberOfEqualDice(this.playerList.getPlayer(j).getNumberOfEqualDice() + 1);
			
			if (this.playerList.getPlayer(j).getNumberOfEqualDice() == 3) {
				
				this.gui.doubleDiceJail(this.playerList.getPlayer(j)); 
				
				this.gui.movePlayerInstantly(this.playerList.getPlayer(j), 10, false);
				
				this.playerList.getPlayer(j).setInJail(true);
				this.playerList.getPlayer(j).setNumberOfEqualDice(0);
				return false;
				
				
			} else {
				gui.doubleDiceMessage(this.playerList.getPlayer(j));
				return true;
			}
			
		} else {
			this.playerList.getPlayer(j).setNumberOfEqualDice(0);
			return false;
		}
	}
	
public void checkForDoubleDiceJail(int j) {
		
		if(!this.dicecup.equalsDice()) {
			
			this.playerList.getPlayer(j).setJailCounterDice(this.playerList.getPlayer(j).getJailCounterDice() + 1);
			
			if(this.playerList.getPlayer(j).getJailCounterDice() == 3) {
				
				//besked til gui
				
				this.playerList.getPlayer(j).setInJail(false);
				this.playerList.getPlayer(j).getAccount().addBalance(-1000);
				this.playerList.getPlayer(j).setJailCounterDice(0);
				this.gui.updateBalance(this.playerList.getPlayer(j));
				
				
			} else {
				//gui besked
					
			}
			
		} else {
			
			//gui besked
			this.playerList.getPlayer(j).setInJail(false);
			
		}
		
	
	}
	
	public void takeTurn(Player p) {
		Field currentField;
		this.dicecup.shake();
		this.gui.showDice(this.dicecup);
		this.gui.movePlayer(p, this.dicecup.sum());

		currentField = this.fc.getField(p.getPosition());

		fc.evaluateField(currentField, this.gui, p, this.dicecup.sum(), this.dc, this.playerList , this.dac);
	}

	private void jailDecision(Player p) {

		if (p.isInJail() == true) {
			int decision = this.gui.inJailDecision(p);

			if (decision == 1) {

				p.getAccount().addBalance(-1000);
				p.setInJail(false);
				this.gui.updateBalance(p);
				this.gui.jailFreePay(p);

			} else if (decision == 2) {

				this.dicecup.shake();
				this.gui.showDice(this.dicecup);

				if (this.dicecup.equalsDice() == true) {

					p.setInJail(false);
					this.gui.jailEqualsTrue(p);

				} else {

					this.gui.jailEqualsFalse(p);

				}

			} else if (decision == 3) {

				p.getAccount().removeAntiJaulCard();
				p.setInJail(false);
				this.gui.antiJailUsed(p);

			}

		}

	}


}

