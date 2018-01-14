package controller;

import java.io.IOException;

import boundary.AudioPlayer;
import boundary.TextReader;
import entity.gameboard.Field;
import entity.player.Player;
import entity.player.PlayerList;
import main.Main;

public class HouseController {

	private String[] description;

	public HouseController(TextReader text){
		try {
			this.description = text.textFromFile(Main.class.getResourceAsStream("rsc/houseController.txt"));
		} catch (IOException e) {
			System.out.println("Something went wrong in the GUIController constructor: " + e);
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
	public void houseControl(PlayerList playerList, int i, GameController gc, GUIController gui, FieldController fc, AudioPlayer dac) {

		boolean decision;
		String option;
		boolean finished = false;

		while(!finished) {

			if (playerList.getPlayer(i).getAc().numberOfTerri() == 0) {																//if player does not own a propperty
				gui.rollDiceMessage(playerList.getPlayer(i));
				gc.takeTurn(playerList.getPlayer(i));
				finished = true;
				
			} else if (playerList.getPlayer(i).getAc().numberOfTerri() > 0) {															//if amount of propperties that player owns is > 0 
				decision = gui.rollDiceMessageUpdated(playerList.getPlayer(i));														//offered the oppertunity to manage houses.

				if (decision) {																										// If he decides to roll dice, do so.
					gc.takeTurn(playerList.getPlayer(i));
					finished = true;
				} 

				else {																												// or if he decides to manage properties, find out exactly what he wants.
					option = this.description[0];
					option = gui.territoryOptions(playerList.getPlayer(i), playerList.getPlayer(i).getAc().hasAllOfAKind());

					if (option.equals(this.description[1])) {																		//if "Buy houses" 
						gui.buyHouses(playerList.getPlayer(i).getAc().allOfAKindFields(), dac);
					} else if (option.equals(this.description[2])) {																	//if "Sell houses to bank"
						gui.sellHouses(playerList.getPlayer(i).getAc().getFields(), dac);
					} else if (option.equals(this.description[3])) {																	//if sell propperty
						sellProp(gui, playerList, fc, i, dac);
					}
				}
			}
		}
	}

	/**
	 * This method uses either of the next 2 methods (sellPropToBank & sellPropToPlayer) by determine which to run 
	 * @param gui
	 * @param pList
	 * @param fc
	 * @param i
	 */
	private void sellProp(GUIController gui, PlayerList pList, FieldController fc, int i, AudioPlayer dac) {
		Field terriToSell = gui.sellTerritoryProp(pList.getPlayer(i));	
		//Field to sell
		if (terriToSell != null) {
			String buyer = gui.sellTerritory(pList.getPlayer(i), pList);
			if (!(buyer.equals(this.description[4]))) {																			//if the seller wants to sell to somebody else than the bank
				int sellPrice = gui.priceToSell();
				dac.playCoinSound();
				
				for (int j = 0; j < pList.getLength(); j++) 																//loops through the playerlist to find the matching buyer
					if (buyer.equals(pList.getPlayer(j).getName())) 									
						sellPropToPlayer(pList.getPlayer(i), pList.getPlayer(j), fc, terriToSell, gui, sellPrice);
			}
			if (buyer.equals(this.description[4])) {																				//if the seller wants to sell to the bank
				dac.playCoinSound();
				sellPropToBank(pList.getPlayer(i), fc, terriToSell, gui);
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
		seller.getAc().removeField(fc, field);															 		//removes field from players index
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
		seller.getAc().removeField(fc, fieldToSell);
		buyer.getAc().addField(fieldToSell, fc);
		fc.getField(fieldToSell.getIndex()).setOwner(buyer);
		gui.updateSubtext(buyer, fieldToSell);
		gui.updateBalance(seller);
		gui.updateBalance(buyer);
	}

}
