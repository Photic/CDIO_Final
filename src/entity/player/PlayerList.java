package entity.player;


/**
 * A playerlist containing all the players. Primary used to loop through players.
 *
 */
public class PlayerList {

	private Player[] players;
	
	public PlayerList(int antal, String[] names) 
	{
		players = new Player[antal];
		
		for (int i = 0;i < antal;i++)
			players[i]= new Player(names[i]);
	}	
	
	public Player getSpecificPlayer(int n) {
		return players[n];
	}
	
	public int getLength() {
		return players.length;
	}
	
	public Player[] getList() {
		return players;
	}
	
	public void setList(Player[] playerList)
	{
		this.players = playerList;
	}
	
}
