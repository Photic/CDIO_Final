package controller;

import entity.gameboard.Field;
import entity.player.Player;
import entity.player.PlayerList;

public class HouseController {



	public void houseControl(PlayerList playerList, int i, GameController gc, GUIController gui, FieldController fc) {

		boolean decision;
		String option;
		boolean finished = false;		
		
		while(finished != true) {
			
			if (playerList.getPlayer(i).getAc().numberOfTerri() == 0) {
				gui.rollDiceMessage(playerList.getPlayer(i));
				gc.takeTurn(playerList.getPlayer(i));
				finished = true;
			} else if (playerList.getPlayer(i).getAc().numberOfTerri() > 0) {
				// If the player does have all of a kind, he should be offered the oppertunity to manage houses.
				decision = gui.rollDiceMessageUpdated(playerList.getPlayer(i));

				// If he decides to roll dice, do so.
				if (decision == true) {
					gc.takeTurn(playerList.getPlayer(i));
					finished = true;
				} else {
					// or if he decides to manage properties, find out exactly what he wants.

					option = "fortryd";
					
					option = gui.territoryOptions(playerList.getPlayer(i), playerList.getPlayer(i).getAc().hasAllOfAKind());

					if (option.equals("Køb huse")) {
						gui.buyHouses(playerList.getPlayer(i).getAc().allOfAKindFields());
					} else if (option.equals("Sælg huse")) {
						gui.sellHouses(playerList.getPlayer(i).getAc().getFields());
					} else if (option.equals("Sælg grund")) {
						sellProp(gui, playerList, fc, i);
					}
				}
			}
		}
	}

	private void sellPropToBank(Player seller, FieldController fc, Field field, GUIController gui) {

		int price = field.getPrice() + (field.getHouses() * field.getHousePrice());
		seller.getAc().removeField(fc, field);
		seller.getAccount().sellField((int)(price * 0.5));
		field.setOwned(false);
		field.setOwner(null);
		field.setHouses(0);


		gui.updateSubtextReversed(field);
		gui.updateBalance(seller);


	}



	private void sellProp(GUIController gui, PlayerList playerList, FieldController fc, int i) {
		Field terriToSell = gui.sellTerritoryProp(playerList.getPlayer(i));

		if (terriToSell != null && !(terriToSell.equals("Fortryd"))) {
			String buyer = gui.sellTerritory(playerList.getPlayer(i), playerList);

			if (!(buyer.equals("Banken")) && !(buyer.equals("Fortryd"))) {
				int sellPrice = gui.priceToSellToOther();
				for (int j = 0; j < playerList.getLength(); j++) 
					if (buyer.equals(playerList.getPlayer(j).getName())) 
						sellPropToPlayer(playerList.getPlayer(i), playerList.getPlayer(j), fc, terriToSell, gui, sellPrice);
			}
			
			if (buyer.equals("Banken")) 
				sellPropToBank(playerList.getPlayer(i), fc, terriToSell, gui);
			
		}

	}



	/**
	 * Sels a territory to a player
	 * @param seller
	 * @param buyer
	 * @param gameboard
	 * @param fieldToSell
	 * @param gui
	 * @param price
	 */

	private void sellPropToPlayer(Player seller, Player buyer, FieldController fc, Field fieldToSell, GUIController gui, int price){
		seller.getAccount().sellField(price);
		buyer.getAccount().buyField(price);

		seller.getAc().removeField(fc, fieldToSell);

		buyer.getAc().addField(fieldToSell, fc);


		gui.updateSubtext(buyer, fieldToSell);
		gui.updateBalance(seller);
		gui.updateBalance(buyer);
	}



}
