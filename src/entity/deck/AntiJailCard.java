package entity.deck;

public class AntiJailCard extends Card {

	protected boolean cardIsOwned;
	
	public AntiJailCard(String description, int action) {
		super(description);
		this.cardIsOwned = false;
	}
	
	
	
}
