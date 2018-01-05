package tests;

import boundary.GuiController;
import boundary.TextReader;
import controller.DeckController;
import entity.deck.Deck;
import entity.player.PlayerList;

public abstract class ConstructorForJUnit {

	protected String[] names;
	protected PlayerList plist;
	protected int count, ekstraCount;
	protected TextReader text;
	protected Deck deck;
	protected DeckController dc;
	protected GuiController gui;

	public ConstructorForJUnit() {
		this.names = new String[] {"Stephan","Mathias","Steen","Tobias","Marcus","Tamour"};
		this.plist = new PlayerList(6, names);
		this.text = new TextReader();
		this.deck = new Deck(this.text);
		this.dc = new DeckController(this.text);
		this.gui = new GuiController();
	}
	
}
