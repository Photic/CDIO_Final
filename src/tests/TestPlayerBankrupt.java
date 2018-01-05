package tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.player.PlayerList;

public class TestPlayerBankrupt extends ConstructorForJUnit {

	@Before
	public void setup() {
		this.plist = new PlayerList(6, names);
		this.count = 6;
	}
	
	@After
	public void teardown() {
		// No code yet
	}
	
	@Test
	public void testForPlayerZero() {
		System.out.println(Arrays.toString(this.plist.getList()));
		
		for (int i = 0; i <= this.plist.getLength(); i++) {
			this.plist.getPlayer(0).setBankrupt(true);
			System.out.println("Removing player " + this.plist.getPlayer(0).getName());
			this.plist.removePlayer(this.plist);
			System.out.println(Arrays.toString(this.plist.getList()));
			this.count--;
			assertEquals(this.count, this.plist.getLength());
		}
		
		System.out.println("Removing player " + this.plist.getPlayer(0).getName());
		this.plist.getPlayer(0).setBankrupt(true);
		this.plist.removePlayer(this.plist);
		
	}
	
	@Test
	public void testForRandomPlayer() {
		for (int i = 0; i <= this.plist.getLength(); i++) {
			this.plist.getPlayer((int)(Math.random()*this.plist.getLength())).setBankrupt(true);
			this.plist.removePlayer(this.plist);
			this.count--;
			assertEquals(this.count, this.plist.getLength());
		}
		
		int looser = (int)(Math.random()*this.plist.getLength());
		int winner;
		
		if (looser == 1) {
			winner = 0;
		}
		else {
			winner = 1;
		}
		
		this.plist.getPlayer(looser).setBankrupt(true);
		this.plist.removePlayer(this.plist);
		assertEquals(false, this.plist.getPlayer(winner).isBankrupt());
	}

}
