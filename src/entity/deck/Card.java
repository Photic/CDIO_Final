package entity.deck;

public abstract class Card {
	
	/**
	 * Abstract attributes, used by most cards.
	 */
	protected String description, specificCardOwner;
	protected int actionOrAmount, advancedAmount;
	protected boolean cardIsOwned;
	protected int[] housesHotels;

	/**
	 * Abstract class to create a card types.
	 * @param description
	 * @param action
	 */
	public Card(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the text on a given card.
	 * @return
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Gets the first enterede amount on a given card.
	 * @return
	 */
	public int getAmount() {
		return this.actionOrAmount;
	}
	
	/**
	 * Gets the second enterede amount on a given card.
	 * @return
	 */
	public int getAdvancedAmount() {
		return this.advancedAmount;
	}
	
	/**
	 * Gets an array of prices depending on how many houses the player owns.
	 * @return
	 */
	public int[] getHousePrices() {
		return housesHotels;
	}
	
	/**
	 * Gets a boolean, only used on the AntiJailCard.
	 * @return
	 */
	public boolean isCardOwned() {
		return cardIsOwned;
	}
	
	/**
	 * Gets the name of the card owner.
	 * @return
	 */
	public String getCardOwner() {
		return this.specificCardOwner;
	}
	
	/**
	 * Sets the name of the card owner.
	 * @param cardIsOwned
	 */
	public void setCardIsOwned(boolean cardIsOwned) {
		this.cardIsOwned = cardIsOwned;
	}
	
	/**
	 * Adds the name of the card owner.
	 * @param pName
	 * @param owned
	 */
	public void addRemoveCardOwner(String pName, boolean owned) {
		this.specificCardOwner = pName;
		setCardIsOwned(owned);
	}
		
}
