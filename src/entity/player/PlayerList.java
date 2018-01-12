package entity.player;

/**
 * A playerlist containing all the players. Primary used to loop through players.
 *
 */
public class PlayerList {

	private Player[] players;
	
	/**
	 * Create a list of players, used at the starf of a game.
	 * @param antal
	 * @param names
	 */
	public PlayerList(int antal, String[] names) {
		this.players = new Player[antal];
		
		for (int i = 0;i < antal;i++)
			this.players[i]= new Player(names[i]);
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
	
	public void setList(Player[] playerList) {
		this.players = playerList;
	}

}