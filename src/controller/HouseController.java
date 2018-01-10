package controller;

import java.io.IOException;

import boundary.TextReader;
import entity.gameboard.Field;
import entity.player.Player;
import entity.player.PlayerList;

public class HouseController {
	String[] description;
	
	
	public HouseController(TextReader tr){
		try {
			this.description = tr.textFromFile("src/main/rsc/houseController.txt");
		} catch (IOException e) {
			System.out.println("Something went wrong in the GUIController constructor");
			e.printStackTrace();
		}
	}
	
	
	
	
/**
 * 
 * @param playerList
 * @param i
 * @param gc
 * @param gui
 * @param fc
 */

	public void houseControl(PlayerList playerList, int i, GameController gc, GUIController gui, FieldController fc) {

		boolean decision;
		String option;
		boolean finished = false;
		
		
		while(finished != true) {
			
			if (playerList.getPlayer(i).getAccount().numberOfTerri() == 0) {									//if player does not own a propperty
				gui.rollDiceMessage(playerList.getPlayer(i));
				gc.takeTurn(playerList.getPlayer(i));
				finished = true;
				
			} else if (playerList.getPlayer(i).getAccount().numberOfTerri() > 0) {								//if amount of propperties that player owns is > 0 
				decision = gui.rollDiceMessageUpdated(playerList.getPlayer(i));									//offered the oppertunity to manage houses.
				

				
				if (decision == true) {																			// If he decides to roll dice, do so.
					gc.takeTurn(playerList.getPlayer(i));
					finished = true;
				} 
				
				else {																							// or if he decides to manage properties, find out exactly what he wants.
					option = description[0];
					option = gui.territoryOptions(playerList.getPlayer(i), playerList.getPlayer(i).getAccount().hasAllOfAKind());

					if (option.equals(description[1])) {															//if "Buy houses" 
						gui.buyHouses(playerList.getPlayer(i).getAccount().allOfAKindFields());
					} else if (option.equals(description[2])) {													//if "Sell houses to bank"
						gui.sellHouses(playerList.getPlayer(i).getAccount().getFields());
					} else if (option.equals(description[3])) {													//if sell propperty
						sellProp(gui, playerList, fc, i);
					}
				}
			}
		}
	}

	
	

	
	/**
	 * This method uses either of the next 2 methods (sellPropToBank & sellPropToPlayer) by determine which to run 
	 * 
	 * @param gui
	 * @param playerList
	 * @param fc
	 * @param i
	 */

		private void sellProp(GUIController gui, PlayerList playerList, FieldController fc, int i) {
			Field terriToSell = gui.sellTerritoryProp(playerList.getPlayer(i));									//field to sell
			if (terriToSell != null) {
				String buyer = gui.sellTerritory(playerList.getPlayer(i), playerList);
				if (!(buyer.equals(description[4]))) {																//if the seller wants to sell to somebody else than the bank
					int sellPrice = gui.priceToSell();
					
					for (int j = 0; j < playerList.getLength(); j++) 											//loops through the playerlist to find the matching buyer
						if (buyer.equals(playerList.getPlayer(j).getName())) 									
							sellPropToPlayer(playerList.getPlayer(i), playerList.getPlayer(j), fc, terriToSell, gui, sellPrice);
				}
				if (buyer.equals(description[4])) {																	//if the seller wants to sell to the bank
					sellPropToBank(playerList.getPlayer(i), fc, terriToSell, gui);
				}
			}

		}
	
	
		/**
		 * This method resets the field back to a point with no owner, and houses = 0, and gives the seller 50% of the value of the prop
		 * @param seller
		 * @param fc
		 * @param field
		 * @param gui
		 */
		
	private void sellPropToBank(Player seller, FieldController fc, Field field, GUIController gui) {

		int price = field.getPrice() + (field.getHouses() * field.getHousePrice());								//calculates value of the propperty
		seller.getAccount().removeField(fc, field);																//removes field from players index
		seller.getAccount().sellField((int)(price * 0.5));														//sells field to a reduced price of 50%
		
		//resets current field
		field.setOwned(false);
		field.setOwner(null);
		field.setHouses(0);


		gui.updateSubtextReversed(field);
		gui.updateBalance(seller);


	}





	/**
	 * Sells a territory to a player, by resetting the field, and buying it for the new player
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
		seller.getAccount().removeField(fc, fieldToSell);
		buyer.getAccount().addField(fieldToSell, fc);
		gui.updateSubtext(buyer, fieldToSell);
		gui.updateBalance(seller);
		gui.updateBalance(buyer);
	}



}
