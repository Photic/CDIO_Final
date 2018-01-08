package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.player.PlayerList;

public class TestAccount extends ConstructorForJUnit {

	
	
	@Test
	public void addFieldsTest() {

		
		plist.getPlayer(1).getAccount().addField(gameboard.getField(6), gameboard);
		
		
		String actual = plist.getPlayer(1).getAccount().getPinkFields()[0].getName();
		String expected = "Roskildevej";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void hasAllFieldsTest() {
		plist.getPlayer(1).getAccount().addField(gameboard.getField(1), gameboard);
		plist.getPlayer(1).getAccount().addField(gameboard.getField(3), gameboard);
		
		boolean actual = plist.getPlayer(1).getAccount().hasAllOfAKind();
		boolean expected = true;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void getFieldsTest() {
		plist.getPlayer(1).getAccount().addField(gameboard.getField(1), gameboard);
		plist.getPlayer(1).getAccount().addField(gameboard.getField(3), gameboard);
		//System.out.println(plist.getPlayer(1).getAccount().getBlueFields()[0].getName());
		
		String actual = plist.getPlayer(1).getAccount().getFields()[1].getName();
		String expected = "Hvidovre";
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void allOfAKindFieldsTest() {
		plist.getPlayer(1).getAccount().addField(gameboard.getField(1), gameboard);
		plist.getPlayer(1).getAccount().addField(gameboard.getField(3), gameboard);
		//System.out.println(plist.getPlayer(1).getAccount().getBlueFields()[0].getName());
		
		
		
		String actual = plist.getPlayer(1).getAccount().allOfAKindFields()[0].getName() + plist.getPlayer(1).getAccount().allOfAKindFields()[1].getName();
		String expected = "RødovrevejHvidovre";
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void removeFieldFromPlayerTest(){
		boolean expected = true;
		boolean actual = false;
		plist.getPlayer(1).getAccount().addField(gameboard.getField(1), gameboard);
		System.out.println("SE HER!! \n \n");
		if(!((plist.getPlayer(1).getAccount().getBlueFields()[0])==null)){
			actual = false;
			System.out.println(actual);
			System.out.println(plist.getPlayer(1).getAccount().getBlueFields()[0].getName());
		}
		plist.getPlayer(1).getAccount().removeField(gameboard, gameboard.getField(1));
		
		if((plist.getPlayer(1).getAccount().getBlueFields()[0])==null){
			actual = true;
		}
	System.out.println(actual);
	}

}