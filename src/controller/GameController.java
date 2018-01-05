package controller;


import java.io.IOException;

import boundary.GuiController;
import boundary.TextReader;
import entity.DiceCup;
import entity.gameboard.Field;
import entity.gameboard.GameBoard;
import entity.gameboard.Territory;
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
		textReader = new TextReader();
		gui = new GuiController();
		gameboard = new GameBoard(textReader);
		playing = true;
		dicecup = new DiceCup();
		fc = new FieldController(textReader);

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
				

			} else if (playerList.getPlayer(i).isBankrupt() == false && playerList.getPlayer(i).isInJail() == true) {
				jailDecision(gui, playerList.getPlayer(i));
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

	private void jailDecision(GuiController gui, Player p) {

		if (p.isInJail() == true) {
			int decision = gui.inJailDecision(p);

			if (decision == 1) {

				p.getAccount().addBalance(-1000);
				p.setInJail(false);
				gui.updateBalance(p);

			} else if (decision == 2) {

				dicecup.shake();

				if (dicecup.equalsDice() == true) {
					p.setInJail(false);

				}

			} else if (decision == 3) {
				p.getAccount().removeAntiJaulCard();
				p.setInJail(false);
			}

		}

	}


}

