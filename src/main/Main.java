package main;

import java.io.IOException;

import controller.GameController;
import entity.deck.Deck;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//new GameController().gameControl();
		
		
		Deck deck = new Deck();
		
		System.out.println(deck.getCard(10).getDescription());
		
		
	}
	
}
