package tests;

import org.junit.Before;
import org.junit.Test;

public class TestDeckController extends ConstructorForJUnit {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testChanceField() {
		for (int i = 0; i < 1000; i++) {
			dc.chanceField(p, plist, gameboard, gui, fc);
		}
	}

}
