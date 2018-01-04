package entity.deck;

public class PayMoneyCardSuperAdvanced extends Card {

	public PayMoneyCardSuperAdvanced(String description, int amount, int advancedAmount) {
		super(description);
		this.actionOrAmount = amount;
		this.advancedAmount = advancedAmount;
	}

}
