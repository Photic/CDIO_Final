package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import entity.deck.Deck;

public class TestCards extends ConstructorForJUnit {
	
	@Before
	public void setUp() throws Exception {
		this.count = 0;
		this.deck = new Deck(this.textReader);
	}

	@Test
	public void description() throws IOException {
		
		for (int i = 0; i < this.deck.getLength(); i++) {
			try {
//				System.out.println(this.textReader.textFromFile("DescriptionsChanceCards.txt")[i] + " ----AND---- " + this.deck.pickACard().getDescription());
				if (this.textReader.textFromFile("DescriptionsChanceCards.txt")[i] == this.deck.pickACard().getDescription()) {
					this.count++;
				}
			} catch (IOException e) {
				System.out.println("Something went wrong in TestCards test if cards are the same as tekst reader.");
				e.printStackTrace();
			}
		}
		assertEquals(32, this.count);
	}

}
