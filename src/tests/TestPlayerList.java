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

	
	@Test
	public void testRemovePlayer(){
		
		boolean expected = true;
		boolean actual = false; 
		String[] plistPlayers = new String[]{"Tobias","Mathias","Stephan","Marcus","Steen","jacob"};
		PlayerList plist = new PlayerList(6, plistPlayers);
		
		//Sætter bankrupt til true for player 1, 3 og 5
		
			plist.getPlayer(0).setBankrupt(true);
			plist.getPlayer(1).setBankrupt(true);
			plist.removePlayer(plist);

	
		if(plist.getPlayer(0).getName() == plistPlayers[2]){
			actual = true;
			assertEquals(expected, actual);
			actual = false; 
		}
		if(plist.getPlayer(1).getName() == plistPlayers[3]){
			actual = true;
			assertEquals(expected, actual);
			actual = false; 
		}
		if(plist.getPlayer(2).getName() == plistPlayers[4]){
			actual = true;
			assertEquals(expected, actual);
			actual = false; 
		}
		if(plist.getPlayer(3).getName() == plistPlayers[5]){
			actual = true;
			assertEquals(expected, actual);
			actual = false; 
		}

		
	}
	
}
