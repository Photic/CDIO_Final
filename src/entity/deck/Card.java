package entity.deck;

public abstract class Card {
	
	protected String description;
	protected int actionOrAmount, advancedAmount;
	
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
	
	public int getAmount() {
		return this.actionOrAmount;
	}
	
	public int getAdvancedAmount() {
		return this.advancedAmount;
	}
	
}
