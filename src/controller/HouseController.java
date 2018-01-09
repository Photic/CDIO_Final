package controller;

import entity.gameboard.Field;
import entity.gameboard.GameBoard;
import entity.gameboard.Territory;
import entity.player.Player;
import entity.player.PlayerList;

public class HouseController {



	public void houseControl(PlayerList playerList, int i, GameController gc, GUIController gui, GameBoard gameboard) {

		boolean decision;
		String option;


		// If the player does own all of the same kind of territories he should just roll the dice normally.
		if (playerList.getPlayer(i).getAccount().numberOfTerri() == 0) {
			gui.rollDiceMessage(playerList.getPlayer(i));
			gc.takeTurn(playerList.getPlayer(i));
		} else if (playerList.getPlayer(i).getAccount().numberOfTerri() > 0){
			// If the player does have all of a kind, he should be offered the oppertunity to manage houses.
			decision = gui.rollDiceMessageUpdated(playerList.getPlayer(i));

			// If he decides to roll dice, do so.
			if (decision == true) {
				gc.takeTurn(playerList.getPlayer(i));
			} else {
				// or if he decides to manage properties, find out exactly what he wants.
				
				option = "fortryd";
				
				option = gui.territoryOptions(playerList.getPlayer(i), playerList.getPlayer(i).getAccount().hasAllOfAKind());


				if (option.equals("Køb huse")) {
					gui.buyHouses(playerList.getPlayer(i).getAccount().allOfAKindFields());
				} else if (option.equals("Sælg huse")) {
					gui.sellHouses(playerList.getPlayer(i).getAccount().getFields());
				} else if (option.equals("Sælg grund")) {
					sellProp(gui, playerList, gameboard, i);
				} else if (option.equals("Pantsæt grund")){
					//TBC
				}


				gui.rollDiceMessage(playerList.getPlayer(i));
				gc.takeTurn(playerList.getPlayer(i));

			}


		}
	}

	private void sellPropToBank(Player seller, Field field, GUIController gui) {
		int price = field.getPrice() + (field.getHouses() * field.getHousePrice());
		
		seller.getAccount().sellField((int)(price * 0.5));
		field.setOwned(false);
		field.setOwner(null);
		field.setHouses(0);
		

		gui.updateSubtextReversed(field);
		gui.updateBalance(seller);


	}

	
	private void sellProp(GUIController gui, PlayerList playerList, GameBoard gameboard, int i) {
		Field terriToSell = gui.sellTerritoryProp(playerList.getPlayer(i));

		if (terriToSell != null) {
			String buyer = gui.sellTerritory(playerList.getPlayer(i), playerList);

			if (!(buyer.equals("Banken"))) {
				int sellPrice = gui.priceToSell();
				for (int j = 0; j < playerList.getLength(); j++) 
					if (buyer.equals(playerList.getPlayer(j).getName())) 
						sellPropToPlayer(playerList.getPlayer(i), playerList.getPlayer(j), gameboard, terriToSell, gui, sellPrice);
			}
			if (buyer.equals("Banken")) {
				sellPropToBank(playerList.getPlayer(i), terriToSell, gui);
			}
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
	private void sellPropToPlayer(Player seller, Player buyer, GameBoard gameboard, Field fieldToSell, GUIController gui, int price){
		seller.getAccount().sellField(price);
		buyer.getAccount().buyField(price);

		seller.getAccount().removeField(gameboard, fieldToSell);

		buyer.getAccount().addField(fieldToSell, gameboard);


		gui.updateSubtext(buyer, fieldToSell);
		gui.updateBalance(seller);
		gui.updateBalance(buyer);
	}

	public void pawnPropToBank(Territory territory){
		
		if(territory.getHouses()>0){
			
		}
		
		else{
			
		}
			
		
	}


}
