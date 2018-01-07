package tests;

import org.junit.Before;

import entity.deck.Deck;

public class TestCards extends ConstructorForJUnit {
	
	@Before
	public void setUp() throws Exception {
		this.count = 0;
		this.deck = new Deck(this.textReader);
	}

//	@Test
//	public void description() throws IOException {
//		
//	}
	


}
