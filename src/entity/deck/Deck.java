package entity.deck;

import java.io.IOException;

import boundary.TextReader;
import main.Main;

public class Deck {


	private String[] description;
	private Card[] deck;

	/**
	 * Method that creates the a Deck of cards.
	 * @param text
	 */
	public Deck(TextReader text) {
		try {
			this.description = text.textFromFile(Main.class.getResourceAsStream("rsc/DescriptionsChanceCards.txt"));
		} catch (IOException e) {
			System.err.println("Something went wrong when trying to import Text from TextReader in Deck: " + e);
			e.printStackTrace();
		}
		
		// Every card in game.
		this.deck = new Card[] {
				new BirthdayCard(description[0], 200),
				new RecieveMoneyCard(description[1], 200),
				new RecieveMoneyCard(description[2], 500),
				new RecieveMoneyCard(description[3], 1000),
				new RecieveMoneyCard(description[4], 1000),
				new RecieveMoneyCard(description[5], 1000),
				new RecieveMoneyCard(description[6], 1000),
				new RecieveMoneyCard(description[7], 1000),
				new RecieveMoneyCard(description[8], 1000),
				new RecieveMoneyCard(description[9], 3000),
				new PayMoneyCard(description[10], 200),
				new PayMoneyCard(description[11], 200),
				new PayMoneyCard(description[12], 1000),
				new PayMoneyCard(description[13], 1000),
				new PayMoneyCard(description[14], 2000),
				new PayMoneyCard(description[15], 3000),
				new PayMoneyCard(description[16], 3000),
				new GetMoneyIfWorthIsLowCard(description[17], 15_000, 40_000),
				new PayMoneyPrHouseHotelCard(description[18], new int[] {800*0, 800*2, 800*3, 800*4, 2_300}),
				new PayMoneyPrHouseHotelCard(description[19], new int[] {500*0, 500*2, 500*3, 800*4, 2_000}),
				new AntiJailCard(description[20]),
				new AntiJailCard(description[21]),
				new GoToJailCard(description[22], 10),
				new GoToJailCard(description[23], 10),
				new MovePlayerToNearestShippingCard(description[24]),
				new MovePlayerToNearestShippingCard(description[25]),
				new MovePlayerBackCard(description[26], -3),
				new MovePlayerCard(description[27], 0),
				new MovePlayerCard(description[28], 25),
				new MovePlayerCard(description[29], 39),
				new MovePlayerCard(description[30], 24),
				new MovePlayerCard(description[31], 11)		
		};
	}

	/**
	 * Function to shuffle the cards in the Deck.
	 * @param collectionOfCards
	 */
	public void shuffleCards() {
		for (int i = 0; i < this.deck.length; i++) {
			int s = i + (int)(Math.random() * (this.deck.length - i));
			Card temp = this.deck[s];
			this.deck[s] = this.deck[i];
			this.deck[i] = temp;
		}
	}

	/**
	 * Pick a card from the top of the deck. Puts it into the bottom of the deck.
	 * @param pickCardFromDeck
	 */
	public Card pickACard() {
		Card firstCard = this.deck[0];
		Card[] tempCards = new Card[getLength()];

		for (int i = 1; i < getLength(); i++) {
			tempCards[i-1] = this.deck[i];
		}

		tempCards[getLength()-1] = firstCard;
		this.deck = tempCards;

		return firstCard;
	}

	/**
	 * Gives a specifik card.
	 * @param a is and integer describing a specific card.
	 * @param Takes a number 'a' and returns a specific card.
	 * @return
	 */
	public Card getCard(int a) {
		return this.deck[a];
	}

	/**
	 * Get length of array.
	 * @return
	 */
	public int getLength() {
		return this.deck.length;
	}

	/**
	 * Gives the last card in the deck.
	 * @return
	 */
	public Card getLastCard() {
		return this.deck[getLength()-1];
	}
	
}

