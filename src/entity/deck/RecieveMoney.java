package entity.deck;

import entity.Player;
import entity.PlayerList;

public class RecieveMoney extends Card {

	private String description;
	private final int money;
	
	public RecieveMoney(String description, int action) {
		
		super(description, action);
		this.money = action;
		this.description = description;
	}

	@Override
	public void cardFunction(Player p, PlayerList plist) {
		if (money == 0)
		{
			birthday(p, plist);
		}
		else
		{
			p.setBalance(p.getBalance()+money);
		}
	}

	/**
	 * If the player, taken from the PlayerList is not the player recieving the card, 
	 * give money to the card holder and remove money from the PlayerList player. 
	 * @param p
	 * @param plist
	 */
	private void birthday(Player p, PlayerList plist) {
		for (int i = 0; i < plist.getLength(); i++) {
			if (plist.getSpecificPlayer(i).getName() != p.getName()) {
				p.setBalance(p.getBalance()+200);
				plist.getSpecificPlayer(i).setBalance(plist.getSpecificPlayer(i).getBalance()-200);
			}
		}
	}

	/**
	 * GUI Description
	 * @return
	 */
	public String getDescription() {
		return description;
	}
}
