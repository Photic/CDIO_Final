package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.player.PlayerList;

class TestPlayerBankrupt {

	String[] names = {"Stephan","Mathias","Steen","Tobias","Marcus","Tamour"};
	PlayerList plist;
	int count;
	
	@BeforeEach
	void setup() {
		this.plist = new PlayerList(6, names);
		this.count = 6;
	}
	
	@After
	void teardown() {
		// No code yet
	}
	
	@Test
	void testForPlayerZero() {
		System.out.println(Arrays.toString(plist.getList()));
		
		for (int i = 0; i <= plist.getLength(); i++) {
			plist.getPlayer(0).setDead(true);
			System.out.println("Removing player " + plist.getPlayer(0).getName());
			plist.removePlayer(plist);
			System.out.println(Arrays.toString(plist.getList()));
			this.count--;
			assertEquals(this.count, plist.getLength());
		}
		
		System.out.println("Removing player " + plist.getPlayer(0).getName());
		plist.getPlayer(0).setDead(true);
		plist.removePlayer(plist);
		
	}
	
	@Test
	void testForRandomPlayer() {
		
		for (int i = 0; i < plist.getLength(); i++) {
			plist.getPlayer((int)(Math.random()*plist.getLength())).setDead(true);
			assertEquals(this.count, plist.getLength());
		}
		
	}

}
