package tests;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import entity.deck.Card;
import entity.deck.Deck;
import entity.player.PlayerList;

class TestDeck {
	
	String[] names = {"Stephan","Mathias","Steen"};
	PlayerList plist = new PlayerList(3, names);
	Deck deck;
	int countBefore;
	int countAfter;
	
	@Before
	void setup() {
		this.deck = new Deck();
		this.countBefore = 0;
		this.countAfter = 0;
	}
	
	@After
	void teardown() {
		// Not sure what to put here yet.
	}
	
	/**
	 * Testing the pickACard function, and ending the test with seeing if all cards are still unique.
	 */
	@Test
	void testpickACard() {

		Card firstCard = this.deck.getCard(0);
		
		for (int i = 0; i < 32; i++) {
			this.deck.pickACard();
		}
		
		assertEquals(firstCard, this.deck.getCard(0));
		
		for (int i = 1; i <= this.deck.getLength()-1; i++) 
			if (this.deck.getCard(0) != this.deck.getCard(i))
				this.countAfter++;
		
		assertEquals(this.deck.getLength()-1, this.countAfter);
		
	}
	
	/**
	 * Testing the shuffleCards function, and ending the test with seeing if all cards are still unique.
	 */
	@Test
	void testshuffleCards()
	{
		for (int i = 0; i < 10_000; i++) {
			this.deck.shuffleCards();
		}
		
		for (int i = 1; i < this.deck.getLength(); i++) 
			if (this.deck.getCard(0) != this.deck.getCard(i))
				this.countAfter++;
		
		assertEquals(this.deck.getLength()-1, this.countAfter);
	
	}

}
