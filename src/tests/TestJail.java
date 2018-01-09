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
		System.out.println(fc.getField(30).getDescription());
		assertNotEquals(notLockedUpToBeginWith, expected);
		//fieldController.evaluateField(gameboard.getField(30), gui, plist.getPlayer(0), 2, dc, gameboard, plist);
		//boolean lockedUpNow = plist.getPlayer(0).isInJail();
		//assertEquals(expected, lockedUpNow);

		
	}


}
