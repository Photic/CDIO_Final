package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import entity.player.PlayerList;

class TestPlayerBankrupt {

	String[] names = {"Stephan","Mathias","Steen","Tobias","Marcus","Tamour"};
	PlayerList plist = new PlayerList(6, names);
	int count = 6;
	
	@Test
	void testForPlayerZero() {
		System.out.println(Arrays.toString(plist.getList()));
		
		for (int i = 0; i <= plist.getLength(); i++) {
			plist.getPlayer(0).setDead(true);
			System.out.println("Removing player " + plist.getPlayer(0).getName());
			plist.removePlayer(plist.getPlayer(0), plist);
			System.out.println(Arrays.toString(plist.getList()));
			this.count--;
			assertEquals(this.count, plist.getLength());
		}
		
		plist.getPlayer(0).setDead(true);
		plist.removePlayer(plist.getPlayer(1), plist);
		
	}
	
	@Test
	void testForRandomPlayer() {
		
		for (int i = 0; i < plist.getLength(); i++) {
			Int(Math.random());
		}
		
	}

}
