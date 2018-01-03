package boundary;

import entity.gameboard.GameBoard;
import gui_fields.GUI_Field;

public class GUINew {
	
	public void defineGUI(GameBoard gameboard) {
		GUI_Field[] gui_fields = new GUI_Field[gameboard.getSize()];
		
		for (int i = 0; i < gameboard.getSize() - 1; i++) {
			
			gui_fields[i].setTitle(gameboard.getField(i).getNavn());
			gui_fields[i].setSubText(gameboard.getField(i).getDescription());
			gui_fields[i].setDescription(gameboard.getField(i).getNavn());
			gui_fields[i].setBackGroundColor(gameboard.getField(i).getColor());
			
		}
            
      
	}

}
