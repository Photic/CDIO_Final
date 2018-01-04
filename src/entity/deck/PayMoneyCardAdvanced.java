package entity.deck;

public class PayMoneyCardAdvanced extends Card {

	public PayMoneyCardAdvanced(String description, int cardId, int getMoney, int advanced) {
		super(description, cardId);
		this.actionOrAmount = getMoney;
		this.advancedAmount = advanced;
	}

}
