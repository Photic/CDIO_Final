package controller;

import entity.player.PlayerList;

public class HouseController {
	
	
	
	public void houseControl(PlayerList playerList, int i, GameController gc, GUIController gui) {
		
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
				}
				

				gui.rollDiceMessage(playerList.getPlayer(i));
				gc.takeTurn(playerList.getPlayer(i));
				
			}
			
			
		}
	}
	
	
	

}
