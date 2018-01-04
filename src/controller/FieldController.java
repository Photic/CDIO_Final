package controller;

import boundary.GuiController;
import entity.gameboard.Field;
import entity.gameboard.Tax;
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
	public void evaluateField(Field field, GuiController gui, Player p) {



		if (field instanceof Territory) {
			territoryLogic(field, gui, p);
		} else if (field instanceof Tax) {


		}


	}

	private void territoryLogic(Field field, GuiController gui, Player p) {

		if(field.isOwned() == false) {
			boolean decision = gui.territoryDecision(field);

			if (decision == true) {

				p.getAccount().buyField(field.getPrice());
				gui.updateBalance(p);

				field.setOwner(p);
				field.setOwned(true);
				gui.setOwnerText(p);


			} else {

			}

			gui.transaction(decision, field);
		} else {
			
			p.getAccount().setBalance(p.getAccount().getBalance() - field.getRent());
			System.out.println(p.getAccount().getBalance() - field.getRent());
			field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + field.getRent());
			
			
			gui.payRent(field);
			gui.updateBalance(p);
			gui.updateBalance(field.getOwner());
			
		}
	}

	private void taxlogic(Field field, GuiController gui, Player p) {



	}




}
