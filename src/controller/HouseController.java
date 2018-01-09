package controller;

import entity.gameboard.Field;
import entity.gameboard.GameBoard;
import entity.player.Player;
import entity.player.PlayerList;

public class HouseController {
	
	
	
	public void houseControl(PlayerList playerList, int i, GameController gc, GUIController gui, GameBoard gameboard) {
		
		boolean decision;
		int option;
		
	
		// If the player does own all of the same kind of territories he should just roll the dice normally.
		if (playerList.getPlayer(i).getAccount().hasAllOfAKind() == false) {
			gui.rollDiceMessage(playerList.getPlayer(i));
			gc.takeTurn(playerList.getPlayer(i));
			
		} else {
			// If the player does have all of a kind, he should be offered the oppertunity to manage houses.
			decision = gui.rollDiceMessageUpdated(playerList.getPlayer(i));
			
			// If he decides to roll dice, do so.
			if (decision == true) {
				gc.takeTurn(playerList.getPlayer(i));
			} else {
				// or if he decides to manage properties, find out exactly what he wants.
				
				option = gui.territoryOptions(playerList.getPlayer(i));
				
				if (option == 1) {
					gui.buyHouses(playerList.getPlayer(i).getAccount().allOfAKindFields());
				} else if (option == 2) {
					gui.sellHouses(playerList.getPlayer(i).getAccount().getFields());
				} else if (option == 3) {
					Field terriToSell = gui.sellTerritoryProp(playerList.getPlayer(i));
					
					String buyer = gui.sellTerritory(playerList);
					
					int sellPrice = gui.priceToSell();
					
					for (int j = 0; j < playerList.getLength(); j++) {
						
						if (buyer.equals(playerList.getPlayer(j).getName())) {
							
							
							
							
							
							sellPropToPlayer(playerList.getPlayer(i), playerList.getPlayer(j), gameboard, terriToSell, gui, sellPrice);
							
							
						}
						
					}
					
					
				}
				

				gui.rollDiceMessage(playerList.getPlayer(i));
				gc.takeTurn(playerList.getPlayer(i));
				
			}
			
			
		}
	}
	

	public void sellPropToPlayer(Player seller, Player buyer, GameBoard gameboard, Field fieldToSell, GUIController gui, int price){
		
		
		seller.getAccount().sellField(price);
		buyer.getAccount().buyField(price);
		
		seller.getAccount().removeField(gameboard, fieldToSell);
		
		buyer.getAccount().addField(fieldToSell, gameboard);
		
		
		gui.updateSubtext(buyer, fieldToSell);
		gui.updateBalance(seller);
		gui.updateBalance(buyer);
		
		
		
		
		
		
		
		
		
//		p1.getAccount().removeField(gameboard, gameboard.getField(field.getIndex()));
//		p2.getAccount().buyField(price);
//		p1.getAccount().buyField(-price);
//		
//		gui.updateBalance(p1);
//		gui.updateBalance(p2);
	}
	
	

}
