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

	private GUIController gui;
	private PlayerList playerList;
	private FieldController fc;
	private DiceCup dicecup;
	private DeckController dc;
	private boolean playing;
	private TextReader textReader;
	private int alivePlayers;
	private AudioPlayer dac;

	/**
	 * Constructor setting up the gamecontroller.
	 * @throws IOException 
	 * Exceptions fromt he textreader.
	 */
	public GameController() throws IOException 
	{
		this.textReader = new TextReader();
		this.gui = new GUIController(this.textReader);
		this.fc = new FieldController(this.textReader);
		this.dc = new DeckController(this.textReader);
		this.playing = true;
		this.dicecup = new DiceCup();
		this.dac = new AudioPlayer();
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
//		fc.evaluateField(fc.getField(30), gui, playerList.getPlayer(0), 0, dc, playerList, dac);
		if (this.alivePlayers == 1) {														// If there is only 1 player alive
			for (int i = 0; i < this.playerList.getLength(); i++) 							// Loop through the playerlist
				if (this.playerList.getPlayer(i).isBankrupt() == false) 						// Check if the current player is bankrupt
					this.gui.showWinner(this.playerList.getPlayer(i));						// If he is not, he is the winner.
		} else {
			// Now starts the "normal game"
			int j = 0;
			while (j < this.playerList.getLength()) {										// Loop through the playerlist


				if (this.playerList.getPlayer(j).isBankrupt() == false 						// If the player is not bankrupt
						&& this.playerList.getPlayer(j).isInJail() == false) {				// and if the player is not in jail

					fc.getHc().houseControl(this.playerList, j, this, this.gui, this.fc);		// Let the fieldController handle the turn.

				} else if (this.playerList.getPlayer(j).isBankrupt() == false 				// If the player is not bankrupt
						&& this.playerList.getPlayer(j).isInJail() == true) {					// and the player IS in jail
					jailDecision(this.playerList.getPlayer(j), j);							// apply the jail logic
				}

				checkForBankruptPlayers();													// After the player has taken his turn, check if there is any bankrupt players, and apply logic

				checker = checkForDoubleDice(j);												// Check if the player rolled a double

				if (checker == false) {														// If the player did not roll a double
					this.playerList.getPlayer(j).setNumberOfEqualDice(0);						// Set the players doubleDice counter to 0
					j++;																		// and increase the index (so that it is the next players turn)
				}

			}
		}


	}
	
	/**
	 * Initialize the gui
	 */
	private void initGui() {
		this.gui.defineGUI(this.fc);
		this.playerList = this.gui.registerPlayerCount();
		this.gui.placePlayer();
	}

	/**
	 * Checks if a player has become bankrupt
	 */
	private void checkForBankruptPlayers() {

		int alivePlayers = 0;																// Set a counter to 0

		for (int j = 0; j < this.playerList.getLength(); j++)									// Loop through the playerlist
			if (this.playerList.getPlayer(j).getAccount().getBalance() < 0) {					// If the current players has a balance below 0:
				this.playerList.getPlayer(j).setBankrupt(true);								// Set his boolean Bankrupt to true
				this.gui.removeBankrupted(playerList.getPlayer(j), this.fc);					// And use the guiController to remove him from the gui
			} else {																			// if the player is not bankrupt
				alivePlayers++;																// Proceed to the next player.
			}

		this.alivePlayers = alivePlayers;													// set the alivePlayer counter to the new count.

	}

	/**
	 * Check if the player rolled a double with the dice
	 * @param j
	 * The current index in the main whileloop
	 * @return
	 * A boolean stating whether the player rolled double or not.
	 */
	private boolean checkForDoubleDice(int j) {
		
		if (this.dicecup.equalsDice()) {
			this.playerList.getPlayer(j).setNumberOfEqualDice(this.playerList.getPlayer(j).getNumberOfEqualDice() + 1);

			if (this.playerList.getPlayer(j).getNumberOfEqualDice() == 3) {

				this.gui.doubleDiceJail(this.playerList.getPlayer(j));
				this.playerList.getPlayer(j).setInJail(true);
				this.gui.movePlayerInstantly(this.playerList.getPlayer(j), 10, false, this.fc);
				this.dac.playJailSound();
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

	
	/**
	 * This method checks if a player rolls a double in jail. He is only allowed to fail doing so 3 times. 	
	 * @param j
	 */
	public void checkForDoubleDiceJail(int j) {

		if(!this.dicecup.equalsDice()) {
			this.playerList.getPlayer(j).setJailCounterDice(this.playerList.getPlayer(j).getJailCounterDice() + 1);

			if(this.playerList.getPlayer(j).getJailCounterDice() == 3) {

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

	/**
	 * lets the player take a turn. It uses the fieldController to evaluate an action associated with the field the player lands on.
	 * @param p
	 * The player to take a turn
	 */
	public void takeTurn(Player p) {
		Field currentField;
		this.dicecup.shake();
		this.gui.showDice(this.dicecup);
		this.gui.movePlayer(p, this.dicecup.sum(), this.fc);

		currentField = this.fc.getField(p.getPosition());

		fc.evaluateField(currentField, this.gui, p, this.dicecup.sum(), this.dc, this.playerList, this.dac);
	}

	/**
	 * The jail logic. This method handles the logic when the player is in jail.
	 * @param p
	 * The player in jail.
	 * @param j
	 * The current index in the main gameloop
	 */
	private void jailDecision(Player p, int j) {

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

		checkForDoubleDiceJail(j);

	}


}

