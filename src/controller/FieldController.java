package controller;

import boundary.GUINew;
import entity.gameboard.Field;
import entity.gameboard.Territory;
import entity.player.Player;

public class FieldController {
	
	
	/**
	 * 
	 * @param field
	 * The current field
	 * @param gui
	 * The gui controller
	 * @param p
	 * The player who landed on the field
	 */
	public void evaluateField(Field field, GUINew gui, Player p) {
		
		
		
		if (field instanceof Territory) {
			territoryLogic(field, gui, p);
		}
		
		
	}
	
	private void territoryLogic(Field field, GUINew gui, Player p) {
		boolean decision = gui.territoryDecision(field);

		
		if (decision == true) {
			
			p.getAccount().buyField(field.getPrice());
			
			field.setOwner(p);
			field.setOwned(true);
			gui.setOwnerText(p);
			
		} else {
			
		}
	}
	
	
	

}
