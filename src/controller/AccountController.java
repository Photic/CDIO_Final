package controller;

import entity.gameboard.Field;
import entity.player.Player;

public class AccountController {
	
	private Player p;
	public AccountController(Player p) {
		this.p = p;
	}
	
	/**
	 * This method adds a field to the account's portfolio, in the correct array, based on the colors of the fields
	 * @param field
	 * A field to be added
	 * @param gb
	 * The gameboard, that is instantiated in the gamecontroller.
	 */
	public void addField(Field field, FieldController fc) {

		int count = 0;
		while(true) {
			if (field.getColor() == fc.getRed())
				if (p.getAccount().getRedFields()[count] == null) {
					p.getAccount().getRedFields()[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == fc.getBlue())
				if (p.getAccount().getBlueFields()[count] == null) {
					p.getAccount().getBlueFields()[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == fc.getPink())
				if (p.getAccount().getPinkFields()[count] == null) {
					p.getAccount().getPinkFields()[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == fc.getGreen())
				if (p.getAccount().getGreenFields()[count] == null) {
					p.getAccount().getGreenFields()[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == fc.getGrey())
				if (p.getAccount().getGreyFields()[count] == null) {
					p.getAccount().getGreyFields()[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == fc.getWhite())
				if (p.getAccount().getWhiteFields()[count] == null) {
					p.getAccount().getWhiteFields()[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == fc.getYellow())
				if (p.getAccount().getYellowFields()[count] == null) {
					p.getAccount().getYellowFields()[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == fc.getPurple())
				if (p.getAccount().getPurpleFields()[count] == null) {
					p.getAccount().getPurpleFields()[count] = field;
					break;
				} else {
					count++;
				}

			if (count == 4) {
				break;
			}

		}

	}
	
	/**
	 * This method take a field and removes it from the owners field array.
	 * @param fc
	 * The field Controller
	 * @param field
	 * The field to be removed
	 */
	public void removeField(FieldController fc, Field field){
		field = fc.getField(field.getIndex());
		if(field.getColor() == fc.getRed()){
			int count = p.getAccount().getRedFields().length-1;
			while(true){
				if(p.getAccount().getRedFields()[count]==null){
					count--;	
				}
				else{
					p.getAccount().getRedFields()[count]=null;
					break;
				}
			}
		}

		else if(field.getColor() == fc.getBlue()){
			int count = p.getAccount().getBlueFields().length-1;
			while(true){
				if(p.getAccount().getBlueFields()[count]==null){
					count--;	
				}
				else{
					p.getAccount().getBlueFields()[count]=null;
					break;
				}
			}
		}

		else if(field.getColor() == fc.getPink()){
			int count = p.getAccount().getPinkFields().length-1;
			while(true){
				if(p.getAccount().getPinkFields()[count]==null){
					count--;	
				}
				else{
					p.getAccount().getPinkFields()[count]=null;
					break;
				}
			}
		}


		else if(field.getColor() == fc.getGreen()){
			int count = p.getAccount().getGreenFields().length-1;
			while(true){
				if(p.getAccount().getGreenFields()[count]==null){
					count--;	
				}
				else{
					p.getAccount().getGreenFields()[count]=null;
					break;
				}
			}
		}


		else if(field.getColor() == fc.getGrey()){
			int count = p.getAccount().getGreyFields().length-1;
			while(true){
				if(p.getAccount().getGreyFields()[count]==null){
					count--;	
				}
				else{
					p.getAccount().getGreyFields()[count]=null;
					break;
				}
			}
		}

		else if(field.getColor() == fc.getWhite()){
			int count = p.getAccount().getWhiteFields().length-1;
			while(true){
				if(p.getAccount().getWhiteFields()[count]==null){
					count--;	
				}
				else{
					p.getAccount().getWhiteFields()[count]=null;
					break;
				}
			}
		}

		else if(field.getColor() == fc.getYellow()){
			int count = p.getAccount().getYellowFields().length-1;
			while(true){
				if(p.getAccount().getYellowFields()[count]==null){
					count--;	
				}
				else{
					p.getAccount().getYellowFields()[count]=null;
					break;
				}
			}
		}

		else if(field.getColor() == fc.getPurple()){
			int count = p.getAccount().getPurpleFields().length-1;
			while(true){
				if(p.getAccount().getPurpleFields()[count]==null){
					count--;	
				}
				else{
					p.getAccount().getPurpleFields()[count]=null;
					break;
				}
			}
		}
			

	}
	
	/**
	 * This method fiends all the fieldarray where the players has all of a color. If the player has all blue and white fields, it return a field[] containing both the blue and white fields.
	 * @return
	 */
	public Field[] allOfAKindFields() {

		int length = 0;
		if (hasAllOfAKind()) {
			if (p.getAccount().isAllblue()) 
				length += p.getAccount().getBlueFields().length;
			if (p.getAccount().isAllpink())
				length += p.getAccount().getPinkFields().length;
			if (p.getAccount().isAllgreen())
				length += p.getAccount().getGreenFields().length;
			if (p.getAccount().isAllgrey())
				length += p.getAccount().getGreyFields().length;
			if (p.getAccount().isAllred())
				length += p.getAccount().getRedFields().length;
			if (p.getAccount().isAllwhite())
				length += p.getAccount().getWhiteFields().length;
			if (p.getAccount().isAllyellow())
				length += p.getAccount().getYellowFields().length;
			if (p.getAccount().isAllpurple())
				length += p.getAccount().getPurpleFields().length;

			Field[] output = new Field[length];

			int count = 0;
			if (p.getAccount().isAllblue()) 
				for (int i = 0; i<2; i++) {
					output[count] = p.getAccount().getBlueFields()[i];
					count++;
				}
			

			if (p.getAccount().isAllpink()) 
				for (int i = 0; i<3; i++) {
					output[count] = p.getAccount().getPinkFields()[i];
					count++;
				}
			

			if (p.getAccount().isAllgreen()) 
				for (int i = 0; i<3; i++) {
					output[count] = p.getAccount().getGreenFields()[i];
					count++;
				}
			


			if (p.getAccount().isAllgrey()) 
				for (int i = 0; i<3; i++) {
					output[count] = p.getAccount().getGreyFields()[i];
					count++;
				}
			

			if (p.getAccount().isAllred()) 
				for (int i = 0; i<3; i++) {
					output[count] = p.getAccount().getRedFields()[i];
					count++;
				}
			

			if (p.getAccount().isAllwhite()) 
				for (int i = 0; i<3; i++) {
					output[count] = p.getAccount().getWhiteFields()[i];
					count++;
				}


			if (p.getAccount().isAllyellow()) {
				for (int i = 0; i<3; i++) {
					output[count] = p.getAccount().getYellowFields()[i];
					count++;
				}
			}

			if (p.getAccount().isAllpurple()) {
				for (int i = 0; i<2; i++) {
					output[count] = p.getAccount().getPurpleFields()[i];
					count++;
				}
			}
			return output;
		} else {
			return null;
		}

	}
	
	/**
	 * This method check all the field arrays in the account, and tell if the player has all of a kind of field.
	 * @return
	 */
	public boolean hasAllOfAKind() {

		int count;
		boolean output = false;

		count = 0;
		for (int i = 0; i < p.getAccount().getBlueFields().length; i++) 
			if (p.getAccount().getBlueFields()[i] != null) 
				count++;
			
		
		if (count == p.getAccount().getBlueFields().length) {
			output = true;
			p.getAccount().setAllblue(true);
		}

		count = 0;
		for (int i = 0; i < p.getAccount().getPinkFields().length; i++) 
			if (p.getAccount().getPinkFields()[i] != null) 
				count++;
			
		
		if (count == p.getAccount().getPinkFields().length) {
			output = true;
			p.getAccount().setAllpink(true);
		}

		count = 0;
		for (int i = 0; i < p.getAccount().getGreenFields().length; i++) 
			if (p.getAccount().getGreenFields()[i] != null) 
				count++;
			
		
		if (count == p.getAccount().getGreenFields().length) {
			output = true;
			p.getAccount().setAllgreen(true);
		}

		count = 0;
		for (int i = 0; i < p.getAccount().getGreyFields().length; i++) 
			if (p.getAccount().getGreyFields()[i] != null) 
				count++;
			
		
		if (count == p.getAccount().getGreyFields().length) {
			output = true;
			p.getAccount().setAllgrey(true);
		}

		count = 0;
		for (int i = 0; i < p.getAccount().getRedFields().length; i++) 
			if (p.getAccount().getRedFields()[i] != null) 
				count++;
			
		
		if (count == p.getAccount().getRedFields().length) {
			output = true;
			p.getAccount().setAllred(true);
		}

		count = 0;
		for (int i = 0; i < p.getAccount().getWhiteFields().length; i++) 
			if (p.getAccount().getWhiteFields()[i] != null) 
				count++;
			
		
		if (count == p.getAccount().getWhiteFields().length) {
			output = true;
			p.getAccount().setAllwhite(true);
		}

		count = 0;
		for (int i = 0; i < p.getAccount().getYellowFields().length; i++) 
			if (p.getAccount().getYellowFields()[i] != null) 
				count++;
			
		
		if (count == p.getAccount().getYellowFields().length) {
			output = true;
			p.getAccount().setAllyellow(true);
		}

		count = 0;
		for (int i = 0; i < p.getAccount().getPurpleFields().length; i++) {
			if (p.getAccount().getPurpleFields()[i] != null) {
				count++;
			}
		}
		if (count == p.getAccount().getPurpleFields().length) {
			output = true;
			p.getAccount().setAllpurple(true);
		}

		return output;

	}
	
	/**
	 * This mehod combines all the colors field arrays in the players account.
	 * @return
	 * All the field a player owns.
	 */
	public Field[] getFields() {

		Field[] temp = new Field[] {p.getAccount().getBlueFields()[0], p.getAccount().getBlueFields()[1], 
				p.getAccount().getPinkFields()[0], p.getAccount().getPinkFields()[1], p.getAccount().getPinkFields()[2], 
				p.getAccount().getGreenFields()[0],p.getAccount().getGreenFields()[1], p.getAccount().getGreenFields()[2],
				p.getAccount().getGreyFields()[0], p.getAccount().getGreyFields()[1], p.getAccount().getGreyFields()[2],
				p.getAccount().getRedFields()[0], p.getAccount().getRedFields()[1], p.getAccount().getRedFields()[2],
				p.getAccount().getWhiteFields()[0], p.getAccount().getWhiteFields()[1], p.getAccount().getWhiteFields()[2],
				p.getAccount().getYellowFields()[0], p.getAccount().getYellowFields()[1], p.getAccount().getYellowFields()[2],
				p.getAccount().getPurpleFields()[0], p.getAccount().getPurpleFields()[1]};

		int count = 0;
		for (int i = 0; i < temp.length; i++) 
			if (temp[i] != null) 
				count++;


		Field[] output = new Field[count];
		count = 0;
		for (int i = 0; i < temp.length; i++) 
			if (temp[i] != null) {
				output[count] = temp[i];
				count++;
			}

		return output;
	}
	
	
	public int numberOfTerri() {
		return getFields().length;
	}
	
	
	
	
	

}
