package entity.deck;

public abstract class Card {
	
	protected String description;
	
	/**
	 * Abstract class to create a card types.
	 * @param description
	 * @param action
	 */
	public Card(String description) {
		this.description = description;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
}
