package tests;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class TestJail extends ConstructorForJUnit {

	@Test
	public void test() {
 
		boolean expected = true;
		
		boolean notLockedUpToBeginWith = plist.getPlayer(0).isInJail();
		System.out.println(fc.getField(30).getDescription());
		assertNotEquals(notLockedUpToBeginWith, expected);
		//fieldController.evaluateField(gameboard.getField(30), gui, plist.getPlayer(0), 2, dc, gameboard, plist);
		//boolean lockedUpNow = plist.getPlayer(0).isInJail();
		//assertEquals(expected, lockedUpNow);

		
	}


}
