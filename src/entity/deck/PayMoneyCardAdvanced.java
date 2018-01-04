package entity.deck;

public class PayMoneyCardAdvanced extends Card {

	public PayMoneyCardAdvanced(String description, int amount, int advancedAmount) {
		super(description);
		this.actionOrAmount = amount;
		this.advancedAmount = advancedAmount;
	}

}
