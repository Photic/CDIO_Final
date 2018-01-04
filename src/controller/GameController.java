package controller;


import boundary.GuiController;
import entity.DiceCup;
import entity.gameboard.Field;
import entity.gameboard.GameBoard;
import entity.gameboard.Territory;
import entity.player.PlayerList;


/**
 * The central gamecontroller, that controls the game.
 *
 */
public class GameController {


	private GuiController gui;
	private PlayerList playerList;
	private FieldController fc;
	private GameBoard gameboard;
	private DiceCup dicecup;
	private boolean playing;

	/**
	 * Constructor setting up the gamecontroller.
	 */
	public GameController() 
	{
		gui = new GuiController();
		gameboard = new GameBoard();
		playing = true;
		dicecup = new DiceCup();
		fc = new FieldController();

	}

	/**
	 * The central method controlling the game.
	 */
	public void gameControl() 
	{
		initGui();
		
		while(playing) {
			gameLoop();
		}
		
	}
						
				


	private void gameLoop() {
		Field currentField;
		for (int i = 0; i < playerList.getLength(); i++) {
			gui.rollDiceMessage();
			dicecup.shake();
			gui.showDice(dicecup);
			gui.movePlayer(playerList.getPlayer(i), dicecup.sum());
			
			currentField = gameboard.getField(playerList.getPlayer(i).getPosition());
			
			fc.evaluateField(currentField, gui, playerList.getPlayer(i), dicecup.sum());
			

		}
	}


	private void initGui() {
		gui.defineGUI(gameboard);
		playerList = gui.registerPlayerCount();
		gui.placePlayer();
	}


	//	/**
	//	 * Check if the player passed start
	//	 * @param i
	//	 * current player index
	//	 */
	//	private void checkStartPassed(int i) {
	//		//check if the player passed start
	//		if (rulebook.checkIfPassedStart(playerList.getSpecificPlayer(i), gameboard) == true) {
	//
	//			//the player recieves $2 and a message is presented.
	//			playerList.getSpecificPlayer(i).setBalance(playerList.getSpecificPlayer(i).getBalance() + 2);
	//			out.passedStart(playerList.getSpecificPlayer(i));
	//
	//			//Update the players balance on the gui.
	//			gui.updateBalance(playerList.getSpecificPlayer(i));
	//		}
	//		playerList.getSpecificPlayer(i).setPosition(newPosition);
	//	}
	//
	//	/**
	//	 * check if the square is a territory
	//	 * @param i
	//	 */
	//	private void checkIfTerritory(int i) {
	//
	//		if (gameboard.getField(playerList.getSpecificPlayer(i).getPosition()).getClass() == gameboard.getField(1).getClass()) 
	//		{
	//			if (((Territory)gameboard.getField(playerList.getSpecificPlayer(i).getPosition())).isOwned() == false) 
	//			{
	//				territorySituation(i);	
	//			}
	//		}
	//	}
	//
	//	/**
	//	 * Check if the player is in jail
	//	 * @param i
	//	 */
	//	private void inJail(int i) {
	//		out.jailPrint(playerList.getSpecificPlayer(i));
	//		playerList.getSpecificPlayer(i).setInJail(false);
	//		playerList.getSpecificPlayer(i).setBalance(playerList.getSpecificPlayer(i).getBalance() - 1);
	//	}
	//
	//	/**
	//	 * Update the players balances
	//	 */
	//	private void updateBalance() {
	//		//Update the balance of the players on the gui.
	//		for (int j = 0; j<playerList.getLength(); j++) 
	//		{
	//			gui.updateBalance(playerList.getSpecificPlayer(j));
	//		}
	//	}
	//
	//	/**
	//	 * Update if a player has died.
	//	 * @param i
	//	 */
	//	private void updateDead(int i) {
	//		if (playerList.getSpecificPlayer(i).isBankrupt()) 
	//		{
	//			amountDead++;
	//			removeDead(playerList.getSpecificPlayer(i), i);
	//		}
	//	}
	//
	//	/**
	//	 * roll dice and calculate new position
	//	 * @param i
	//	 */
	//	private void rollDiceAndMove(int i) {
	//
	//		//Wait for the player to press 5 to roll the dice.
	//		out.wantToRoll(playerList.getSpecificPlayer(i));
	//		keyboard.waitForInt(5);
	//
	//
	//		diceSum = playerList.getSpecificPlayer(i).rollDice(d1, d2); 
	//		gui.setDice(this.d1, this.d2);
	//		out.evaluateDice(playerList.getSpecificPlayer(i).getName(), diceSum);
	//
	//		//Calculates the new position for the player.
	//		newPosition = gamelogic.newPosition(playerList.getSpecificPlayer(i).getPosition(), diceSum, gameboard.getSize());
	//		out.evaluateNewPos(newPosition, gameboard);
	//	}
	//
	//
	//	/**
	//	 * This method checks if the player wants to buy a free territory.
	//	 * @param i
	//	 * the current iterations variable, to determine player.
	//	 */
	//	public void territorySituation(int i) 
	//	{
	//		int answer = keyboard.getIntRange(0, 1);
	//
	//		if (answer == 1) {
	//			gui.setOwner(playerList.getSpecificPlayer(i));
	//			((Territory)gameboard.getField(playerList.getSpecificPlayer(i).getPosition())).setOwner(playerList.getSpecificPlayer(i));
	//			((Territory)gameboard.getField(playerList.getSpecificPlayer(i).getPosition())).setOwned(true);
	//			out.playerNowOwns(playerList.getSpecificPlayer(i), gameboard);
	//			playerList.getSpecificPlayer(i).setBalance(playerList.getSpecificPlayer(i).getBalance() - ((Territory)gameboard.getField(playerList.getSpecificPlayer(i).getPosition())).getPrice());
	//		} else {
	//			out.notBuying();
	//		}
	//	}
	//
	//	/**
	//	 * This method removes dead players from the gameboard
	//	 * @param player
	//	 * @param i
	//	 */
	//	private void removeDead(Player player, int i) 
	//	{
	//		out.youAreDead(player);
	//		gui.removeDeadPlayer(player);
	//		for (int p = 0; p<gameboard.getSize();p++) 
	//		{
	//			if (playerList.getSpecificPlayer(i).isBankrupt())
	//			{
	//				if (gameboard.getField(p) instanceof Territory)
	//				{
	//					if (((Territory) gameboard.getField(p)).isOwned())
	//					{
	//						if (((Territory) gameboard.getField(p)).getOwner().getName() == player.getName()) 
	//							gui.removeDeadOwner(p);
	//					}
	//					((Territory) gameboard.getField(p)).removeDeadOwner(playerList.getSpecificPlayer(i));
	//				}
	//			}
	//		}
	//	}
	//
	//
	//	/**
	//	 * Check if there is a winner
	//	 */
	//	private void checkWinner() {
	//		// If there is only 1 player left, the winner is announced.
	//		if (amountDead == playerList.getLength()-1)
	//		{
	//			for (int o = 0; o<playerList.getLength(); o++) {
	//				if (!(playerList.getSpecificPlayer(o).isBankrupt())) {
	//					out.announceWinner(playerList.getSpecificPlayer(o));
	//				}
	//			}
	//			playing = false;
	//		}
	//	}
	//
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
	//			gui.setOwner(playerList.getSpecificPlayer(i));
	//			((Territory)gameboard.getField(playerList.getSpecificPlayer(i).getPosition())).setOwner(playerList.getSpecificPlayer(i));
	//			((Territory)gameboard.getField(playerList.getSpecificPlayer(i).getPosition())).setOwned(true);
	//			out.playerNowOwns(playerList.getSpecificPlayer(i), gameboard);
	//			playerList.getSpecificPlayer(i).setBalance(playerList.getSpecificPlayer(i).getBalance() - ((Territory)gameboard.getField(playerList.getSpecificPlayer(i).getPosition())).getPrice());
	//		} else {
	//			out.notBuying();
	//		}
	//	}

}

