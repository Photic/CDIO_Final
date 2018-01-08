package controller;


import java.io.IOException;

import boundary.TextReader;
import entity.DiceCup;
import entity.gameboard.Field;
import entity.gameboard.GameBoard;
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
	private GameBoard gameboard;
	private DiceCup dicecup;
	private DeckController dc;
	private HouseController hc;
	private boolean playing;
	private TextReader textReader;

	private int alivePlayers;

	/**
	 * Constructor setting up the gamecontroller.
	 * @throws IOException 
	 */
	public GameController() throws IOException 
	{
		textReader = new TextReader();
		gui = new GUIController(textReader);
		gameboard = new GameBoard(textReader);
		fc = new FieldController(textReader);
		dc = new DeckController(textReader);
		hc = new HouseController();
		playing = true;
		dicecup = new DiceCup();

	}

	/**
	 * The central method controlling the game.
	 */
	public void gameControl() 
	{
		initGui();
		alivePlayers = playerList.getLength();


		while(playing) {
			gameLoop();
		}
	}



//	/**
//	 * This is the gameloop.
//	 */
//	private void gameLoop() {
//
//		//det her er bare til mathias. jeg bruger det til at teste det med at købe huse.
////		fc.evaluateField(gameboard.getField(1), gui, playerList.getPlayer(0), 0, dc, gameboard, playerList);
////		fc.evaluateField(gameboard.getField(3), gui, playerList.getPlayer(0), 0, dc, gameboard, playerList);
//		
//		if (alivePlayers == 1) {
//			for (int i = 0; i < playerList.getLength(); i++) 
//				if (playerList.getPlayer(i).isBankrupt() == false) 
//					gui.showWinner(playerList.getPlayer(i));
//		} else {
//			
//			for (int i = 0; i < playerList.getLength(); i++) {
//				//The game can be played normally if the player is not bankrupt or in jail.
//				if (playerList.getPlayer(i).isBankrupt() == false && playerList.getPlayer(i).isInJail() == false) {
//
//					hc.houseControl(playerList, i, this, gui);
//
//				} else if (playerList.getPlayer(i).isBankrupt() == false && playerList.getPlayer(i).isInJail() == true) {
//					jailDecision(gui, playerList.getPlayer(i));
//				}
//
//				checkForLostPlayers(playerList);
//			}
//		}
//
//
//	}
	
	private void gameLoop() {
		boolean checker;
		//det her er bare til mathias. jeg bruger det til at teste det med at købe huse.
//		fc.evaluateField(gameboard.getField(1), gui, playerList.getPlayer(0), 0, dc, gameboard, playerList);
//		fc.evaluateField(gameboard.getField(3), gui, playerList.getPlayer(0), 0, dc, gameboard, playerList);

		
		if (alivePlayers == 1) {
			for (int i = 0; i < playerList.getLength(); i++) 
				if (playerList.getPlayer(i).isBankrupt() == false) 
					gui.showWinner(playerList.getPlayer(i));
		} else {
			
			int j = 0;
			while (j < playerList.getLength()) {
				
				//The game can be played normally if the player is not bankrupt or in jail.
				if (playerList.getPlayer(j).isBankrupt() == false && playerList.getPlayer(j).isInJail() == false) {

					hc.houseControl(playerList, j, this, gui);

				} else if (playerList.getPlayer(j).isBankrupt() == false && playerList.getPlayer(j).isInJail() == true) {
					jailDecision(gui, playerList.getPlayer(j));
				}

				checkForLostPlayers(playerList);
				
				checker = checkForDoubleDice(j);
				
				if (checker == false) {
					j++;
				}
				
			}
		}


	}




	private void initGui() {
		gui.defineGUI(gameboard);
		playerList = gui.registerPlayerCount();
		gui.placePlayer();
	}

	/**
	 * Checks if the player
	 * @param i
	 */
	private void checkForLostPlayers(PlayerList playerList) {

		for (int j = 0; j < playerList.getLength(); j++)
			if (playerList.getPlayer(j).getAccount().getBalance() < 0) {
				playerList.getPlayer(j).setBankrupt(true);
				gui.removeBankrupted(playerList.getPlayer(j), gameboard);
				this.alivePlayers--;
			}


	}
	
	private boolean checkForDoubleDice(int j) {
		if (dicecup.equalsDice()) {
			playerList.getPlayer(j).setNumberOfEqualDice(playerList.getPlayer(j).getNumberOfEqualDice() + 1);
			
			if (playerList.getPlayer(j).getNumberOfEqualDice() == 3) {
				
				gui.doubleDiceJail(playerList.getPlayer(j)); 
				
				gui.movePlayerInstantly(playerList.getPlayer(j), 10, false);
				
				playerList.getPlayer(j).setInJail(true);
				playerList.getPlayer(j).setNumberOfEqualDice(0);
				return false;
				
				
			} else {
				gui.doubleDiceMessage(playerList.getPlayer(j));
				return true;
			}
			
		} else {
			playerList.getPlayer(j).setNumberOfEqualDice(0);
			return false;
		}
	}
	
	public void takeTurn(Player p) {
		Field currentField;
		dicecup.shake();
		gui.showDice(dicecup);
		gui.movePlayer(p, dicecup.sum());

		currentField = gameboard.getField(p.getPosition());

		fc.evaluateField(currentField, gui, p, dicecup.sum(), dc, gameboard, playerList);
	}

	private void jailDecision(GUIController gui, Player p) {

		if (p.isInJail() == true) {
			int decision = gui.inJailDecision(p);

			if (decision == 1) {

				p.getAccount().addBalance(-1000);
				p.setInJail(false);
				gui.updateBalance(p);
				gui.jailFreePay(p);

			} else if (decision == 2) {

				dicecup.shake();
				gui.showDice(dicecup);

				if (dicecup.equalsDice() == true) {

					p.setInJail(false);
					gui.jailEqualsTrue(p);

				} else {

					gui.jailEqualsFalse(p);

				}

			} else if (decision == 3) {

				p.getAccount().removeAntiJaulCard();
				p.setInJail(false);
				gui.antiJailUsed(p);

			}

		}

	}


}

