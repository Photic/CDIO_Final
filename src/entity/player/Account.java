package entity.player;

public class Account {
	
	private int balance = 30_000;

	public int getBalance() {
		return balance;
	}

	public void setBalance(int credit) {
		this.balance = this.balance + credit;
	}
	

}
