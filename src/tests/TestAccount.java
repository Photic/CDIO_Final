package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.deck.AntiJailCard;
import entity.deck.Card;
import entity.player.PlayerList;

public class TestAccount extends ConstructorForJUnit {

	
	
	@Test
	public void addFieldsTest() {

		
		plist.getPlayer(1).getAccount().addField(fc.getField(6), this.fc);	
		String actual = plist.getPlayer(1).getAccount().getPinkFields()[0].getName();
		String expected = "Roskildevej";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void hasAllFieldsTest() {
		plist.getPlayer(1).getAccount().addField(fc.getField(1), this.fc);
		plist.getPlayer(1).getAccount().addField(fc.getField(3), this.fc);
		
		boolean actual = plist.getPlayer(1).getAccount().hasAllOfAKind();
		boolean expected = true;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void getFieldsTest() {
		plist.getPlayer(1).getAccount().addField(this.fc.getField(1), this.fc);
		plist.getPlayer(1).getAccount().addField(this.fc.getField(3), this.fc);
		//System.out.println(plist.getPlayer(1).getAccount().getBlueFields()[0].getName());
		
		String actual = this.plist.getPlayer(1).getAccount().getFields()[1].getName();
		String expected = "Hvidovre";
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void allOfAKindFieldsTest() {
		plist.getPlayer(1).getAccount().addField(this.fc.getField(1), this.fc);
		plist.getPlayer(1).getAccount().addField(this.fc.getField(3), this.fc);
		//System.out.println(plist.getPlayer(1).getAccount().getBlueFields()[0].getName());
		
		
		
		String actual = plist.getPlayer(1).getAccount().allOfAKindFields()[0].getName() + plist.getPlayer(1).getAccount().allOfAKindFields()[1].getName();
		String expected = "RødovrevejHvidovre";
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void removeFieldFromPlayerTest(){
		boolean expected = true;
		boolean actual = false;
		plist.getPlayer(1).getAccount().addField(fc.getField(1), fc);
		plist.getPlayer(1).getAccount().removeField(fc, fc.getField(1));
		
		if((plist.getPlayer(1).getAccount().getBlueFields()[0])==null){
			actual = true;
		}
		assertEquals(expected, actual);
	
	}
	
	
	@Test
	public void numberOfTerriTest(){
		
		plist.getPlayer(1).getAccount().addField(fc.getField(1), fc);
		plist.getPlayer(1).getAccount().addField(fc.getField(3), fc);
		
		int actual = plist.getPlayer(1).getAccount().numberOfTerri();
		int expected = 2;
		
		assertEquals(expected, actual);	
	}
	
	@Test
	public void recieveAntiJailCardTest() {
		boolean test = false;
		Card card = new AntiJailCard(" ");
		
		p.getAccount().recieveAntiJaulCard(card);
	
		
		if (p.getAccount().getAmountOfCards() == 1)
			test = true;
		
		assertTrue(test);
	}
	
	@Test
	public void removeAntiJailCardTest() {
		boolean test = false; 
		Card card = new AntiJailCard(" ");
		
		p.getAccount().recieveAntiJaulCard(card);
		
		p.getAccount().removeAntiJaulCard();
		
		if (p.getAccount().getAmountOfCards() == 0)
			test = true;
		
		assertTrue(test);
		
		
		
		
	}
	
	
	

}