package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import gui_tests.PlayerTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	TestAccount.class, 
	TestDeck.class, 
	TestDice.class, 
	PlayerTest.class, 
	TestGameLoop.class, 
	TestJail.class, 
	TestPlayerBankrupt.class
	})

public class AllTest {
	// Just to actually have a class.
}
