package controller;

import boundary.GuiController;
import entity.gameboard.Chance;
import entity.gameboard.Company;
import entity.gameboard.Field;
import entity.gameboard.GameBoard;
import entity.gameboard.GoToJail;
import entity.gameboard.Jail;
import entity.gameboard.Parking;
import entity.gameboard.Shipping;
import entity.gameboard.Tax;
import entity.gameboard.Territory;
import entity.player.Player;
import entity.player.PlayerList;

public class FieldController {


	
	public FieldController() {
		
		
	}
	
	
	
	/**
	 * 
	 * @param field
	 * The current field
	 * @param gui
	 * The gui controller
	 * @param p
	 * The player who landed on the field
	 */
	public void evaluateField(Field field, GuiController gui, Player p, int diceSum, DeckController dc, GameBoard gameboard, PlayerList plist) {

		

		if (field instanceof Territory) {
			
			territoryLogic(field, gui, p);
			
		} else if (field instanceof Tax) {

			taxLogic(field, gui, p);
			
		} else if (field instanceof Company) {
			
			companyLogic(field, gui, p, diceSum);
			
		} else if (field instanceof Shipping) {
			
			shippingLogic(field, gui, p);
			
		} else if (field instanceof Jail) {
			
			jailLogic(field, gui, p);
			
		} else if (field instanceof GoToJail) {
			
			goToJailLogic(field, gui, p);
			
		} else if (field instanceof Parking) {
			
			parkingLogic(field, gui, p);
			
		} else if (field instanceof Chance) {
			
			chanceLogic(field, gui, p, dc, plist, gameboard);
			
		}


	}

	private void territoryLogic(Field field, GuiController gui, Player p) {

		if(field.isOwned() == false) {
			boolean decision = gui.territoryDecision(field, p);

			if (decision == true) {

				p.getAccount().buyField(field.getPrice());
				gui.updateBalance(p);

				field.setOwner(p);
				field.setOwned(true);
				gui.setOwnerText(p);


			} else {

			}

			gui.transaction(decision, field, p);
			
		} else {
			
			if (field.getOwner().getName() != p.getName()) {
				
				//Vi skal huske at tjekke for om ejeren ejer alle i farve gruppen --> 2x getRent.
			
			p.getAccount().setBalance(p.getAccount().getBalance() - field.getCurrentRent());
			field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + field.getCurrentRent());
			
			
			gui.payRentMessege(field, p);
			gui.updateBalance(p);
			gui.updateBalance(field.getOwner());
			}
		}
	}

	private void taxLogic(Field field, GuiController gui, Player p) {

		if (field.getPrice() == 4000) {
			
			boolean dicision = gui.taxDecision(field, p);
			
			if (dicision == true) {
				
				p.getAccount().setBalance(p.getAccount().getBalance() - field.getPrice());
				gui.updateBalance(p);
				
			} else {
				
				p.getAccount().setBalance(p.getAccount().getBalance() - (int)(p.getAccount().getPlayerWorth(p) * 0.1));
				gui.updateBalance(p);
				
			}
			
		} else {
			
			gui.taxMessage(p);
			p.getAccount().setBalance(p.getAccount().getBalance() - field.getPrice());
			gui.updateBalance(p);
			
		}

	}
	
	private void companyLogic(Field field, GuiController gui, Player p, int diceSum) {
		
		if(field.isOwned() == false) {
			boolean decision = gui.companyDecision(field, p);

			if (decision == true) {

				p.getAccount().buyField(field.getPrice());
				gui.updateBalance(p);

				p.getAccount().setCompanies(p.getAccount().getCompanies() + 1);
				field.setOwner(p);
				field.setOwned(true);
				gui.setOwnerText(p);


			} else {

			}

			gui.transaction(decision, field, p);
			
		} else {
			
			if (field.getOwner().getName() != p.getName()) {
				
				int payment;
				if (field.getOwner().getAccount().getCompanies() == 1) {
					payment = (100 * diceSum);
					p.getAccount().setBalance(p.getAccount().getBalance() - payment);
					field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + payment);				
				} else {
					payment = (200 * diceSum);
					p.getAccount().setBalance(p.getAccount().getBalance() - payment);
					field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + payment);
				}
				
				gui.updateBalance(p);
				gui.updateBalance(field.getOwner());
				
			}
			

			
		}
		
	}

	
	private void shippingLogic(Field field, GuiController gui, Player p) {
		
		if(field.isOwned() == false) {
			boolean decision = gui.shippingDecision(field, p);

			if (decision == true) {

				p.getAccount().buyField(field.getPrice());
				gui.updateBalance(p);

				p.getAccount().setShipping(p.getAccount().getShipping() + 1);
				field.setOwner(p);
				field.setOwned(true);
				gui.setOwnerText(p);


			} else {

			}

			gui.transaction(decision, field, p);
			
		} else {
			
			if (field.getOwner().getName() != p.getName()) {
				
				if (field.getOwner().getAccount().getShipping() == 1) {
					
					gui.payRentShippingMessege(field, p);
					p.getAccount().setBalance(p.getAccount().getBalance() - field.getRent()[0]);
					field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + field.getRent()[0]);
					
				} else if (field.getOwner().getAccount().getShipping() == 2) {
					
					gui.payRentShippingMessege(field, p);
					p.getAccount().setBalance(p.getAccount().getBalance() - field.getRent()[1]);
					field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + field.getRent()[1]);
					
				} else if (field.getOwner().getAccount().getShipping() == 3) {
					
					gui.payRentShippingMessege(field, p);
					p.getAccount().setBalance(p.getAccount().getBalance() - field.getRent()[2]);
					field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + field.getRent()[2]);
					
				} else if (field.getOwner().getAccount().getShipping() == 4) {
					
					gui.payRentShippingMessege(field, p);
					p.getAccount().setBalance(p.getAccount().getBalance() - field.getRent()[3]);
					field.getOwner().getAccount().setBalance(field.getOwner().getAccount().getBalance() + field.getRent()[3]);
				}
			
				
				gui.updateBalance(p);
				gui.updateBalance(field.getOwner());
				
			} 
		
		}
		
	
		
	}


	private void jailLogic(Field field, GuiController gui, Player p) {
		
		gui.visitJailMessege(field, p);
		
	}
	
	private void goToJailLogic(Field field, GuiController gui, Player p) {
		
		gui.goToJailMessege(field, p);
		p.setInJail(true);
		gui.movePlayerInstantly(p, 10, false);
		
	}
	
	private void parkingLogic(Field field, GuiController gui, Player p) {
		
		gui.parkingMessege(field, p);
		
	}
	
	private void chanceLogic(Field field, GuiController gui, Player p, DeckController dc, PlayerList plist, GameBoard gameboard) {
		
		gui.chanceMessege(dc.chanceField(p, plist, gameboard, gui));
		dc.chanceField(p, plist, gameboard, gui);
		
		for (int i = 0; i < plist.getLength(); i++) {
			
			gui.updateBalance(plist.getPlayer(i));
			
		}
		
		
	}
 
}
