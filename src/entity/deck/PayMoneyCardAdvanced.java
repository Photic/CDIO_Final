package entity.deck;

public class PayMoneyCardAdvanced extends Card {

	public PayMoneyCardAdvanced(String description, int getMoney, int advanced) {
		super(description);
		this.actionOrAmount = getMoney;
		this.advancedAmount = advanced;
	}

}
