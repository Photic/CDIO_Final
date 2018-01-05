package entity.deck;

public class PayMoneyPrHouseHotelCard extends Card {

	public PayMoneyPrHouseHotelCard(String description, int amount, int advancedAmount) {
		super(description);
		this.actionOrAmount = amount;
		this.advancedAmount = advancedAmount;
	}

}
