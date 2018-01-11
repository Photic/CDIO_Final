package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.player.PlayerList;

public class TestPlayerList {

	@Test
	public void testPlayerList() {
		boolean expected = true;
		boolean actual = false; 
		String[] plistPlayers = new String[]{"Tobias","Mathias","Stephan","Marcus","Steen","jacob"};
		PlayerList plist = new PlayerList(6, plistPlayers);
		
			for (int i = 0; i < plistPlayers.length; i++) {
				if (plist.getPlayer(i).getName() == plistPlayers[i]){
					actual = true;
				}
				assertEquals(expected, actual);
			}
	}
	
}
