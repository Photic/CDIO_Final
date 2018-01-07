package entity.deck;

public abstract class Card {
	
	protected String description, specificCardOwner;
	protected int actionOrAmount, advancedAmount;
	protected boolean cardIsOwned;

	/**
	 * Abstract class to create a card types.
	 * @param description
	 * @param action
	 */
	public Card(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getAmount() {
		return this.actionOrAmount;
	}
	
	public int getAdvancedAmount() {
		return this.advancedAmount;
	}
	
	public boolean isCardOwned() {
		return cardIsOwned;
	}
	
	public String getCardOwner() {
		return this.specificCardOwner;
	}
	
	public void addRemoveCardOwner(String pName, boolean owned) {
		this.specificCardOwner = pName;
		setCardIsOwned(owned);
	}

	public void setCardIsOwned(boolean cardIsOwned) {
		this.cardIsOwned = cardIsOwned;
	}
	
	
}
