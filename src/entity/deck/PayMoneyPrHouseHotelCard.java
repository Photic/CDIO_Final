package entity.deck;

public class PayMoneyPrHouseHotelCard extends Card {

	public PayMoneyPrHouseHotelCard(String description, int amount, int[] needstopay) {
		super(description);
		this.actionOrAmount = amount;
		this.housesHotels = needstopay;
	}

}
