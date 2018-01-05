package tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.deck.Card;
import entity.deck.Deck;

public class TestDeck extends ConstructorForJUnit {
	
	@Before
	public void setup() {
		this.deck = new Deck(this.textReader);
		this.count = 0;
		this.ekstraCount = 0;
	}
	
	@After
	public void teardown() {
		// Not sure what to put here yet.
	}
	
	/**
	 * Testing the pickACard function, and ending the test with seeing if all cards are still unique.
	 */
	@Test
	public void testpickACard() {

		Card firstCard = this.deck.getCard(0);
		
		for (int i = 0; i < 32; i++) {
			this.deck.pickACard();
		}
		
		assertEquals(firstCard, this.deck.getCard(0));
		
		for (int i = 1; i <= this.deck.getLength()-1; i++) 
			if (this.deck.getCard(0) != this.deck.getCard(i))
				this.ekstraCount++;
		
		assertEquals(this.deck.getLength()-1, this.ekstraCount);
		
	}
	
	/**
	 * Testing the shuffleCards function, and ending the test with seeing if all cards are still unique.
	 */
	@Test
	public void testshuffleCards()
	{
		for (int i = 0; i < 10_000; i++) {
			this.deck.shuffleCards();
		}
		
		for (int i = 1; i < this.deck.getLength(); i++) 
			if (this.deck.getCard(0) != this.deck.getCard(i))
				this.ekstraCount++;
		
		assertEquals(this.deck.getLength()-1, this.ekstraCount);
	
	}

}
