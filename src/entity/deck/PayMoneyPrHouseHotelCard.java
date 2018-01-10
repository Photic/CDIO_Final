package entity.deck;

public class PayMoneyPrHouseHotelCard extends Card {


	/**
	 * Constructor for PayMoneyPrHouseHotelCard, extends Card.
	 * @param description
	 * @param needstopay
	 */
	public PayMoneyPrHouseHotelCard(String description, int[] needstopay) {
		super(description);
		this.housesHotels = needstopay;
	}

}
