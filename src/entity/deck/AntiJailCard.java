package entity.deck;

public class AntiJailCard extends Card {

	protected boolean cardIsOwned;
	
	public AntiJailCard(String description) {
		super(description);
		this.cardIsOwned = false;
	}
	
	public boolean getIsOwnedStatus() {
		return this.cardIsOwned;
	}
	
	public void setOwned(boolean set) {
		this.cardIsOwned = set;
	}
	
}
