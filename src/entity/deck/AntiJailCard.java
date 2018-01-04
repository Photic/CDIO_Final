package entity.deck;

public class AntiJailCard extends Card {

	protected boolean cardIsOwned;
	
	public AntiJailCard(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	public boolean getIsOwnedStatus() {
		return this.cardIsOwned;
	}
	
	public void setOwned(boolean set) {
		this.cardIsOwned = set;
	}
	
}
