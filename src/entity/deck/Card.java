package entity.deck;

public abstract class Card {
	
	protected String description;
	protected int cardId;
	
	/**
	 * Abstract class to create a card types.
	 * @param description
	 * @param action
	 */
	public Card(String description, int cardId) {
		this.description = description;
		this.cardId = cardId;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public int getCardId() {
		return this.cardId;
	}
	
}
