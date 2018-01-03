package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import entity.deck.Deck;
import entity.player.PlayerList;

public class testingCards {
	
	String[] names = {"Stephan","Mathias","Steen"};
	PlayerList plist = new PlayerList(3, names);
	Deck deck = new Deck();
	int countBefore;
	int countAfter;
	
	@Before
	public void setup() {
		this.deck = new Deck();
		this.countBefore = 0;
		this.countAfter = 0;
	}
	
	@After
	public void teardown() {
		for (int i = 1; i < this.deck.getLength(); i++) 
			if (this.deck.getCard(0) != this.deck.getCard(i))
				this.countAfter++;
		
		assertEquals(this.deck.getLength()-1, this.countAfter);
	}
	
	@Test
	public void test01() {
	
		for (int i = 0; i < 10_000; i++) {
			String temp = this.deck.getCard(0).getDescription();
			this.deck.pickACard(this.plist.getSpecificPlayer(0), this.plist);
			
			if (temp == this.deck.getCard(this.deck.getLength()-1).getDescription())
				this.countBefore++;
		}
		
		assertEquals(10_000, this.countBefore);
		
	}
	
	@Test
	void test02()
	{
		
	}

}
