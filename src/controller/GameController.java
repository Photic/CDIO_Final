package controller;


import java.io.IOException;

import boundary.GuiController;
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


	private GuiController gui;
	private PlayerList playerList;
	private FieldController fc;
	private GameBoard gameboard;
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
		gui = new GuiController();
		gameboard = new GameBoard();
		playing = true;
		dicecup = new DiceCup();
		fc = new FieldController();
		textReader = new TextReader();
		dc = new DeckController(textReader);

	}

	/**
	 * The central method controlling the game.
	 */
	public void gameControl() 
	{
		initGui();
		alivePlayers = playerList.getLength();
		
		
		while(playing) {
			if (alivePlayers == 1) {
				
				for (int i = 0; i < playerList.getLength(); i++) {
					if (playerList.getPlayer(i).isBankrupt() == false) {
						gui.showWinner(playerList.getPlayer(i));
					}
					
				}
				
			} else {
				gameLoop();
			}

		}
		
	}
						
				


	private void gameLoop() {
		boolean decision;
		
		for (int i = 0; i < playerList.getLength(); i++) {
			
			
			if (playerList.getPlayer(i).isBankrupt() == false && playerList.getPlayer(i).isInJail() == false) {
				if (playerList.getPlayer(i).getAccount().getTerritories() == 0) {
					gui.rollDiceMessage(playerList.getPlayer(i));
					takeTurn(playerList.getPlayer(i));
					
				} else {
					decision = gui.rollDiceMessageUpdated(playerList.getPlayer(i));
					
					if (decision == true) {
						
						takeTurn(playerList.getPlayer(i));
						
					} else {
						
					}
					
					
				}
				

			}

			if (playerList.getPlayer(i).getAccount().getBalance() <= 0) {
				playerList.getPlayer(i).setBankrupt(true);
				gui.removeBankrupted(playerList.getPlayer(i), gameboard);
				alivePlayers--;
				
			}

		}
	}


	private void initGui() {
		gui.defineGUI(gameboard);
		playerList = gui.registerPlayerCount();
		gui.placePlayer();
	}
	
	private void takeTurn(Player p) {
		Field currentField;
		dicecup.shake();
		gui.showDice(dicecup);
		gui.movePlayer(p, dicecup.sum());
		
		currentField = gameboard.getField(p.getPosition());
		
		fc.evaluateField(currentField, gui, p, dicecup.sum(), dc, gameboard, playerList);
	}


}

