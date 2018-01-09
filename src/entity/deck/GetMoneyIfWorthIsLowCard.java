package entity.deck;

public class GetMoneyIfWorthIsLowCard extends Card {

	public GetMoneyIfWorthIsLowCard(String description, int amount, int advancedAmount) {
		super(description);
		this.actionOrAmount = amount;
		this.advancedAmount = advancedAmount;
	}

}
