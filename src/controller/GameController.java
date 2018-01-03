package controller;


import java.util.Arrays;

import boundary.GUINew;
import boundary.Gui;
import entity.DiceCup;
import entity.gameboard.GameBoard;
import entity.gameboard.Territory;
import entity.player.Player;
import entity.player.PlayerList;


/**
 * The central gamecontroller, that controls the game.
 *
 */
public class GameController {


	private GUINew gui;
	private PlayerList playerlist;
	private GameBoard gameboard;
	private DiceCup dicecup;
	private int playerCount, newPosition, diceSum;
	private boolean playing;

	/**
	 * Constructor setting up the gamecontroller.
	 */
	public GameController() 
	{
		gui = new GUINew();
		dicecup = new DiceCup();
		gameboard = new GameBoard();
		playing = true;
	}

	/**
	 * The central method controlling the game.
	 */
	public void gameControl() 
	{
		
		gui.defineGUI(gameboard);
		playerlist = gui.registerPlayers();
		

//		setUpGui(); // Set up the gui
//
//		playGame(); // Play the game
	}


//	/**
//	 * Set up the gui.
//	 */
//	private void setUpGui() {
//		//setting up the gui
//		gui.defineGUI(gameboard);
//		gui.setNames(playerList);
//		gui.placePlayers(playerList);
//	}

	/**
	 * Playing the game
	 */
//	private void playGame() {
//
//		while(playing == true) // Keeps playing the game a winner is found.
//		{
//
//			checkWinner(); // First we check if a player has won.
//
//			for (int i = 0; i<playerList.getLength(); i++) { // Loop through the players.
//
//
//				if(playerList.getSpecificPlayer(i).isBankrupt()==true){ //If the player is dead, nothing happens.	
//
//				} else {
//
//					if(playerList.getSpecificPlayer(i).isInJail()) // If the player is in jail, he doesn't get his turn.
//					{
//						inJail(i);
//					} else { //This is the "normal" routine of the game.
//
//
//						rollDiceAndMove(i); // Roll the dice and get ready to move the player
//
//						checkStartPassed(i); // Check if the player passed start and then set his new position
//
//						rulebook.playerLands(gameboard, playerList.getSpecificPlayer(i), playerList, out); // Evaulate square logic on current square.
//
//						gui.movePlayer(playerList.getSpecificPlayer(i)); // Move the player on the GUI.
//
//						checkIfTerritory(i); // Check if its a territory, and do the special territory method if it is.
//
//						updateBalance(); // update player balances.
//						
//						gamelogic.checkIfDead(playerList.getSpecificPlayer(i), playerList);//Check if the current player died.
//
//						updateDead(i); // Update if the player died.
//					}
//				}
//			}
//			checkWinner();
//		}
//	}




	/**
	 * Check if the player is in jail
	 * @param i
	 */
//	private void inJail(int i) {
//		out.jailPrint(playerList.getSpecificPlayer(i));
//		playerList.getSpecificPlayer(i).setInJail(false);
//		playerList.getSpecificPlayer(i).setBalance(playerList.getSpecificPlayer(i).getBalance() - 1);
//	}

//	/**
//	 * Update the players balances
//	 */
//	private void updateBalance() {
//		//Update the balance of the players on the gui.
//		for (int j = 0; j<playerList.getLength(); j++) 
//		{
//			gui.updateBalance(playerList.getPlayer(j));
//		}
//	}

//	/**
//	 * Update if a player has died.
//	 * @param i
//	 */
//	private void updateDead(int i) {
//		if (playerList.getPlayer(i).isBankrupt()) 
//		{
//			amountDead++;
//			removeDead(playerList.getPlayer(i), i);
//		}
//	}

//	/**
//	 * roll dice and calculate new position
//	 * @param i
//	 */
//	private void rollDiceAndMove(int i) {
//
//		//Wait for the player to press 5 to roll the dice.
//		out.wantToRoll(playerList.getPlayer(i));
//		keyboard.waitForInt(5);
//
//
//		diceSum = playerList.getPlayer(i).rollDice(d1, d2); 
//		gui.setDice(this.d1, this.d2);
//		out.evaluateDice(playerList.getPlayer(i).getName(), diceSum);
//
//		//Calculates the new position for the player.
//		newPosition = gamelogic.newPosition(playerList.getPlayer(i).getPosition(), diceSum, gameboard.getSize());
//		out.evaluateNewPos(newPosition, gameboard);
//	}


	/**
	 * This method checks if the player wants to buy a free territory.
	 * @param i
	 * the current iterations variable, to determine player.
	 */
//	public void territorySituation(int i) 
//	{
//		int answer = keyboard.getIntRange(0, 1);
//
//		if (answer == 1) {
//			gui.setOwner(playerList.getPlayer(i));
//			((Territory)gameboard.getField(playerList.getPlayer(i).getPosition())).setOwner(playerList.getPlayer(i));
//			((Territory)gameboard.getField(playerList.getPlayer(i).getPosition())).setOwned(true);
//			out.playerNowOwns(playerList.getPlayer(i), gameboard);
//			playerList.getPlayer(i).setBalance(playerList.getPlayer(i).getBalance() - ((Territory)gameboard.getField(playerList.getPlayer(i).getPosition())).getPrice());
//		} else {
//			out.notBuying();
//		}
//	}

	/**
	 * This method removes dead players from the gameboard
	 * @param player
	 * @param i
//	 */
//	private void removeDead(Player player, int i) 
//	{
//		out.youAreDead(player);
//		gui.removeDeadPlayer(player);
//		for (int p = 0; p<gameboard.getSize();p++) 
//		{
//			if (playerList.getPlayer(i).isBankrupt())
//			{
//				if (gameboard.getField(p) instanceof Territory)
//				{
//					if (((Territory) gameboard.getField(p)).isOwned())
//					{
//						if (((Territory) gameboard.getField(p)).getOwner().getName() == player.getName()) 
//							gui.removeDeadOwner(p);
//					}
//					((Territory) gameboard.getField(p)).removeDeadOwner(playerList.getPlayer(i));
//				}
//			}
//		}
//	}


//	/**
//	 * Check if there is a winner
//	 */
//	private void checkWinner() {
//		// If there is only 1 player left, the winner is announced.
//		if (amountDead == playerList.getLength()-1)
//		{
//			for (int o = 0; o<playerList.getLength(); o++) {
//				if (!(playerList.getPlayer(o).isBankrupt())) {
//					out.announceWinner(playerList.getPlayer(o));
//				}
//			}
//			playing = false;
//		}
//	}

//	/**
//	 * This method is used to test the method used in the game. It therefor takes in a anwer, and does not ask the keyboard.
//	 * @param i
//	 * the current iterations variable, to determine player.
//	 */
//	public void territorySituation(int i, boolean test, int answerTest) 
//	{
//		int answer = 0;
//		if (test == false) {
//			answer = keyboard.getIntRange(0, 1);
//		} else {
//			answer = answerTest;
//		}
//		if (answer == 1) {
//			gui.setOwner(playerList.getPlayer(i));
//			((Territory)gameboard.getField(playerList.getPlayer(i).getPosition())).setOwner(playerList.getPlayer(i));
//			((Territory)gameboard.getField(playerList.getPlayer(i).getPosition())).setOwned(true);
//			out.playerNowOwns(playerList.getPlayer(i), gameboard);
//			playerList.getPlayer(i).setBalance(playerList.getPlayer(i).getBalance() - ((Territory)gameboard.getField(playerList.getPlayer(i).getPosition())).getPrice());
//		} else {
//			out.notBuying();
//		}
//	}

}

