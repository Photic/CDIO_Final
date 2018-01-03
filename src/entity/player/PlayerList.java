package entity.player;

import java.util.Arrays;

/**
 * A playerlist containing all the players. Primary used to loop through players.
 *
 */
public class PlayerList {

	private Player[] players;
	private int count;
	
	/**
	 * Create a list of players, used at the starf of a game.
	 * @param antal
	 * @param names
	 */
	public PlayerList(int antal, String[] names) 
	{
		this.players = new Player[antal];
		
		for (int i = 0;i < antal;i++)
			this.players[i]= new Player(names[i]);
	}	
	
	/**
	 * Remove 1 player from the game.
	 * @param p
	 * @param plist
	 */
	public void removePlayer(Player p, PlayerList plist) {
		Player[] removeOnePlayer = new Player[plist.getLength()-1];
		this.count = 0;
		
		if (removeOnePlayer.length >= 2) {
			for (int i = 0; i <= removeOnePlayer.length; i++) {
				if (plist.getPlayer(i).isDead() == false) {
					removeOnePlayer[this.count] = plist.getPlayer(i);
						this.count++;
				}
			}
			this.players = removeOnePlayer;
		}
		else {
			for (int i = 0; i < plist.getLength(); i++) {
				if (plist.getPlayer(i).isDead() == false)
					winner(plist.getPlayer(i).getName());
			}
		}
		
				
						
						
	}
	
	public void winner(String s) {
		System.out.println("Winner is" + s);
	}
	
	public Player getPlayer(int n) {
		return players[n];
	}
	
	public int getLength() {
		return players.length;
	}
	
	//--------------------------------------------------------
	//
	//                   Getters & Setters!
	//
	//--------------------------------------------------------
	
	public Player[] getList() {
		return players;
	}
	
	public void setList(Player[] playerList)
	{
		this.players = playerList;
	}

}
