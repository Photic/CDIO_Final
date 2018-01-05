package tests;

import boundary.GuiController;
import boundary.TextReader;
import controller.DeckController;
import entity.deck.Deck;
import entity.gameboard.GameBoard;
import entity.player.PlayerList;

public abstract class ConstructorForJUnit {

	protected String[] names;
	protected PlayerList plist;
	protected int count, ekstraCount;
	protected TextReader textReader;
	protected Deck deck;
	protected DeckController dc;
	protected GuiController gui;
	protected GameBoard gameboard;

	public ConstructorForJUnit() {
		this.names = new String[] {"Stephan","Mathias","Steen","Tobias","Marcus","Tamour"};
		this.plist = new PlayerList(6, names);
		this.textReader = new TextReader();
		this.deck = new Deck(this.textReader);
		this.dc = new DeckController(this.textReader);
		this.gameboard = new GameBoard(this.textReader);
	}
	
}
