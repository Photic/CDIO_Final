package entity.deck;

public class AntiJailCard extends Card {

	protected boolean cardIsOwned;
	
	public AntiJailCard(String description, int cardId) {
		super(description, cardId);
	}

	public boolean getIsOwnedStatus() {
		return this.cardIsOwned;
	}
	
	public void setOwned(boolean set) {
		this.cardIsOwned = set;
	}
	
}
