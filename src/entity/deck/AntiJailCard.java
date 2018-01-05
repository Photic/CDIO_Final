package entity.deck;

public class AntiJailCard extends Card {
	
	public AntiJailCard(String description) {
		super(description);
		this.cardIsOwned = false;
	}
}
	
