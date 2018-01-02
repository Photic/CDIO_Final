package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import entity.deck.Deck;
import entity.player.PlayerList;

class testingCards {
	
	@Test
	void test() {
		boolean actual = false;
		boolean expected = true;
		
		String[] names = {"Stephan","Mathias","Steen"};
		PlayerList plist = new PlayerList(3, names);
		Deck hans = new Deck();
		
		for (int i = 0; i < 10_000; i++) {
			String temp = hans.getCard(0).getDescription();
			hans.pickACard(plist.getSpecificPlayer(0), plist);
			
			if (temp == hans.getCard(hans.deck.length-1).getDescription())
				actual = true;
		}
		assertTrue(actual == expected);
	}

}
