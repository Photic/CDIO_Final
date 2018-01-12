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
				if (this.p.getAccount().getRedFields()[count] == null) {
					this.p.getAccount().getRedFields()[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == fc.getBlue())
				if (this.p.getAccount().getBlueFields()[count] == null) {
					this.p.getAccount().getBlueFields()[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == fc.getPink())
				if (this.p.getAccount().getPinkFields()[count] == null) {
					this.p.getAccount().getPinkFields()[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == fc.getGreen())
				if (this.p.getAccount().getGreenFields()[count] == null) {
					this.p.getAccount().getGreenFields()[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == fc.getGrey())
				if (this.p.getAccount().getGreyFields()[count] == null) {
					this.p.getAccount().getGreyFields()[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == fc.getWhite())
				if (this.p.getAccount().getWhiteFields()[count] == null) {
					this.p.getAccount().getWhiteFields()[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == fc.getYellow())
				if (this.p.getAccount().getYellowFields()[count] == null) {
					this.p.getAccount().getYellowFields()[count] = field;
					break;
				} else {
					count++;
				}
			if (field.getColor() == fc.getPurple())
				if (this.p.getAccount().getPurpleFields()[count] == null) {
					this.p.getAccount().getPurpleFields()[count] = field;
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
			int count = this.p.getAccount().getRedFields().length-1;
			while(true){
				if(this.p.getAccount().getRedFields()[count]==null){
					count--;	
				}
				else{
					this.p.getAccount().getRedFields()[count]=null;
					break;
				}
			}
		}

		else if(field.getColor() == fc.getBlue()){
			int count = this.p.getAccount().getBlueFields().length-1;
			while(true){
				if(this.p.getAccount().getBlueFields()[count]==null){
					count--;	
				}
				else{
					this.p.getAccount().getBlueFields()[count]=null;
					break;
				}
			}
		}

		else if(field.getColor() == fc.getPink()){
			int count = this.p.getAccount().getPinkFields().length-1;
			while(true){
				if(this.p.getAccount().getPinkFields()[count]==null){
					count--;	
				}
				else{
					this.p.getAccount().getPinkFields()[count]=null;
					break;
				}
			}
		}


		else if(field.getColor() == fc.getGreen()){
			int count = this.p.getAccount().getGreenFields().length-1;
			while(true){
				if(this.p.getAccount().getGreenFields()[count]==null){
					count--;	
				}
				else{
					this.p.getAccount().getGreenFields()[count]=null;
					break;
				}
			}
		}


		else if(field.getColor() == fc.getGrey()){
			int count = this.p.getAccount().getGreyFields().length-1;
			while(true){
				if(this.p.getAccount().getGreyFields()[count]==null){
					count--;	
				}
				else{
					this.p.getAccount().getGreyFields()[count]=null;
					break;
				}
			}
		}

		else if(field.getColor() == fc.getWhite()){
			int count = this.p.getAccount().getWhiteFields().length-1;
			while(true){
				if(this.p.getAccount().getWhiteFields()[count]==null){
					count--;	
				}
				else{
					this.p.getAccount().getWhiteFields()[count]=null;
					break;
				}
			}
		}

		else if(field.getColor() == fc.getYellow()){
			int count = this.p.getAccount().getYellowFields().length-1;
			while(true){
				if(this.p.getAccount().getYellowFields()[count]==null){
					count--;	
				}
				else{
					this.p.getAccount().getYellowFields()[count]=null;
					break;
				}
			}
		}

		else if(field.getColor() == fc.getPurple()){
			int count = this.p.getAccount().getPurpleFields().length-1;
			while(true){
				if(this.p.getAccount().getPurpleFields()[count]==null){
					count--;	
				}
				else{
					this.p.getAccount().getPurpleFields()[count]=null;
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
			if (this.p.getAccount().isAllblue()) 
				length += this.p.getAccount().getBlueFields().length;
			if (this.p.getAccount().isAllpink())
				length += this.p.getAccount().getPinkFields().length;
			if (this.p.getAccount().isAllgreen())
				length += this.p.getAccount().getGreenFields().length;
			if (this.p.getAccount().isAllgrey())
				length += this.p.getAccount().getGreyFields().length;
			if (this.p.getAccount().isAllred())
				length += this.p.getAccount().getRedFields().length;
			if (this.p.getAccount().isAllwhite())
				length += this.p.getAccount().getWhiteFields().length;
			if (this.p.getAccount().isAllyellow())
				length += this.p.getAccount().getYellowFields().length;
			if (this.p.getAccount().isAllpurple())
				length += this.p.getAccount().getPurpleFields().length;

			Field[] output = new Field[length];

			int count = 0;
			if (this.p.getAccount().isAllblue()) 
				for (int i = 0; i<2; i++) {
					output[count] = this.p.getAccount().getBlueFields()[i];
					count++;
				}
			
			if (this.p.getAccount().isAllpink()) 
				for (int i = 0; i<3; i++) {
					output[count] = this.p.getAccount().getPinkFields()[i];
					count++;
				}
			
			if (this.p.getAccount().isAllgreen()) 
				for (int i = 0; i<3; i++) {
					output[count] = p.getAccount().getGreenFields()[i];
					count++;
				}

			if (this.p.getAccount().isAllgrey()) 
				for (int i = 0; i<3; i++) {
					output[count] = this.p.getAccount().getGreyFields()[i];
					count++;
				}
			
			if (this.p.getAccount().isAllred()) 
				for (int i = 0; i<3; i++) {
					output[count] = this.p.getAccount().getRedFields()[i];
					count++;
				}
			
			if (this.p.getAccount().isAllwhite()) 
				for (int i = 0; i<3; i++) {
					output[count] = this.p.getAccount().getWhiteFields()[i];
					count++;
				}

			if (this.p.getAccount().isAllyellow()) {
				for (int i = 0; i<3; i++) {
					output[count] = this.p.getAccount().getYellowFields()[i];
					count++;
				}
			}

			if (this.p.getAccount().isAllpurple()) {
				for (int i = 0; i<2; i++) {
					output[count] = this.p.getAccount().getPurpleFields()[i];
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
		for (int i = 0; i < this.p.getAccount().getBlueFields().length; i++) 
			if (this.p.getAccount().getBlueFields()[i] != null) 
				count++;
			
		
		if (count == this.p.getAccount().getBlueFields().length) {
			output = true;
			this.p.getAccount().setAllblue(true);
		}

		count = 0;
		for (int i = 0; i < this.p.getAccount().getPinkFields().length; i++) 
			if (this.p.getAccount().getPinkFields()[i] != null) 
				count++;
			
		
		if (count == this.p.getAccount().getPinkFields().length) {
			output = true;
			this.p.getAccount().setAllpink(true);
		}

		count = 0;
		for (int i = 0; i < this.p.getAccount().getGreenFields().length; i++) 
			if (this.p.getAccount().getGreenFields()[i] != null) 
				count++;
			
		if (count == this.p.getAccount().getGreenFields().length) {
			output = true;
			this.p.getAccount().setAllgreen(true);
		}

		count = 0;
		for (int i = 0; i < this.p.getAccount().getGreyFields().length; i++) 
			if (this.p.getAccount().getGreyFields()[i] != null) 
				count++;
			
		
		if (count == this.p.getAccount().getGreyFields().length) {
			output = true;
			this.p.getAccount().setAllgrey(true);
		}

		count = 0;
		for (int i = 0; i < this.p.getAccount().getRedFields().length; i++) 
			if (this.p.getAccount().getRedFields()[i] != null) 
				count++;
			
		
		if (count == this.p.getAccount().getRedFields().length) {
			output = true;
			this.p.getAccount().setAllred(true);
		}

		count = 0;
		for (int i = 0; i < this.p.getAccount().getWhiteFields().length; i++) 
			if (this.p.getAccount().getWhiteFields()[i] != null) 
				count++;
			
		
		if (count == this.p.getAccount().getWhiteFields().length) {
			output = true;
			this.p.getAccount().setAllwhite(true);
		}

		count = 0;
		for (int i = 0; i < this.p.getAccount().getYellowFields().length; i++) 
			if (this.p.getAccount().getYellowFields()[i] != null) 
				count++;
			
		
		if (count == this.p.getAccount().getYellowFields().length) {
			output = true;
			this.p.getAccount().setAllyellow(true);
		}

		count = 0;
		for (int i = 0; i < this.p.getAccount().getPurpleFields().length; i++) {
			if (this.p.getAccount().getPurpleFields()[i] != null) {
				count++;
			}
		}
		if (count == this.p.getAccount().getPurpleFields().length) {
			output = true;
			this.p.getAccount().setAllpurple(true);
		}

		return output;

	}
	
	/**
	 * This mehod combines all the colors field arrays in the players account.
	 * @return
	 * All the field a player owns.
	 */
	public Field[] getFields() {

		Field[] temp = new Field[] {this.p.getAccount().getBlueFields()[0], this.p.getAccount().getBlueFields()[1], 
				this.p.getAccount().getPinkFields()[0], this.p.getAccount().getPinkFields()[1], this.p.getAccount().getPinkFields()[2], 
				this.p.getAccount().getGreenFields()[0],this.p.getAccount().getGreenFields()[1], this.p.getAccount().getGreenFields()[2],
				this.p.getAccount().getGreyFields()[0], this.p.getAccount().getGreyFields()[1], this.p.getAccount().getGreyFields()[2],
				this.p.getAccount().getRedFields()[0], this.p.getAccount().getRedFields()[1], this.p.getAccount().getRedFields()[2],
				this.p.getAccount().getWhiteFields()[0], this.p.getAccount().getWhiteFields()[1], this.p.getAccount().getWhiteFields()[2],
				this.p.getAccount().getYellowFields()[0], this.p.getAccount().getYellowFields()[1], this.p.getAccount().getYellowFields()[2],
				this.p.getAccount().getPurpleFields()[0], this.p.getAccount().getPurpleFields()[1]};

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
