package tests;

import boundary.TextReader;
import controller.DeckController;
import controller.FieldController;
import controller.GUIController;
import entity.DiceCup;
import entity.deck.Deck;
import entity.player.Player;
import entity.player.PlayerList;

public abstract class ConstructorForJUnit {

	protected String[] names;
	protected PlayerList plist;
	protected Player p;
	protected int count, ekstraCount;
	protected TextReader textReader;
	protected Deck deck;
	protected DeckController dc;
	protected GUIController gui;
	protected FieldController fc;
	protected DiceCup diceCup;

	public ConstructorForJUnit() {
		this.names = new String[] {"Stephan","Mathias","Steen","Tobias","Marcus","Tamour"};
		this.plist = new PlayerList(6, names);
		this.textReader = new TextReader();
		this.deck = new Deck(this.textReader);
		this.dc = new DeckController(this.textReader);
		this.fc = new FieldController(this.textReader);
		this.diceCup = new DiceCup();
		this.p = this.plist.getPlayer(0);
	}
	
}
