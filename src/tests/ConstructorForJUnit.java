package tests;

import boundary.TextReader;
import controller.DeckController;
import controller.FieldController;
import controller.GUIController;
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
	protected GUIController gui;
	protected GameBoard gameboard;
	protected FieldController fieldController;

	public ConstructorForJUnit() {
		this.names = new String[] {"Stephan","Mathias","Steen","Tobias","Marcus","Tamour"};
		this.plist = new PlayerList(6, names);
		this.textReader = new TextReader();
		this.deck = new Deck(this.textReader);
		this.dc = new DeckController(this.textReader);
		this.gameboard = new GameBoard(this.textReader);
		this.fieldController = new FieldController(this.textReader);
	}
	
}
