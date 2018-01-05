package entity.deck;

import java.io.IOException;

import boundary.TextReader;

public class Deck {
	
	private String[] description;
	
	public Deck(TextReader text){
		try {
			this.description = text.textFromFile("DescriptionsChanceCards.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Card[] deck = {
			new RecieveMoneyCard(description[1], 200),
			new RecieveMoneyCard(description[2], 500),
			new RecieveMoneyCard(description[3], 1000),
			new RecieveMoneyCard(description[4], 1000),
			new RecieveMoneyCard(description[5], 1000),
			new RecieveMoneyCard(description[6], 1000),
			new RecieveMoneyCard(description[7], 1000),
			new RecieveMoneyCard(description[8], 1000),
			new RecieveMoneyCard(description[9], 3000),
			new RecieveMoneyCardAdvanced(description[0], 200),
			new PayMoneyCard(description[10], 200),
			new PayMoneyCard(description[11], 200),
			new PayMoneyCard(description[12], 1000),
			new PayMoneyCard(description[13], 1000),
			new PayMoneyCard(description[14], 2000),
			new PayMoneyCard(description[15], 3000),
			new PayMoneyCard(description[16], 3000),
			new PayMoneyCardAdvanced(description[18], 800, 2_300),
			new PayMoneyCardAdvanced(description[19], 500, 2_000),
			new PayMoneyCardSuperAdvanced(description[17], 15_000, 40_000),
			new AntiJailCard(description[20]),
			new AntiJailCard(description[21]),
			new GoToJailCard(description[22], 10),
			new GoToJailCard(description[23], 10),
			new MovePlayerCard(description[27], 0),
			new MovePlayerCard(description[28], 25),
			new MovePlayerCard(description[29], 39),
			new MovePlayerCard(description[30], 24),
			new MovePlayerCard(description[31], 11),
			new MovePlayerCardAdvanced(description[26], -3),
			new MovePlayerCardSuperAdvanced(description[24]),
			new MovePlayerCardSuperAdvanced(description[25])
	};
	
	/**
	 * Function to shuffle any object array.
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
	 * Pick a card from the top of the deck.
	 * @param pickCardFromDeck
	 */
	public Card pickACard() {
		Card firstCard = getCard(0);
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

	
}

