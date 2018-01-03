package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.deck.Deck;
import entity.player.PlayerList;

class TestMovePlayer {

	String[] names = {"Stephan","Mathias"};
	PlayerList plist = new PlayerList(2, names);
	Deck deck = new Deck();
//	GameBoard gameboard = new GameBoard();
	
	@BeforeEach
	void setUp() {
		
	}

	@Test
	void test() {
		plist.getPlayer(0).setPosition(3);
		
		System.out.println(deck.getCard(24).getDescription());
		
	}

}
