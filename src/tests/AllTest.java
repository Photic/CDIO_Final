package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	TestAccount.class, 
	TestDeck.class, 
	TestDice.class,
	TestGameLoop.class, 
	TestJail.class, 
	TestDeckController.class
	})

public class AllTest {
	// Just to actually have a class.
}
