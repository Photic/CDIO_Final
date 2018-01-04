package entity.deck;

public abstract class Card {
	
	protected String description;
	protected int cardId, actionOrAmount, advancedAmount;
	
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
	
	public int getAmount() {
		return this.actionOrAmount;
	}
	
	public int getAdvancedAmount() {
		return this.advancedAmount;
	}
	
}
