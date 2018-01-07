package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.FieldController;
import entity.player.Player;

public class TestJail extends ConstructorForJUnit {

	@Test
	public void test() {

		boolean actual = true; 
		boolean expected = true;
		
		boolean notLockedUpToBeginWith = plist.getPlayer(0).isInJail();
		assertNotEquals(notLockedUpToBeginWith, expected);
		
		//fieldController.evaluateField(field, gui, p, diceSum, dc, gameboard, plist);
		

		
	}


}
