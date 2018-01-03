package main;


import entity.deck.Deck;

public class Main {

	public static void main(String[] args) {

		Deck deck = new Deck();

		for (int i = 0; i < deck.getLength(); i++)
			System.out.println(deck.getCard(i).getDescription());
		
		
	}
	

}
