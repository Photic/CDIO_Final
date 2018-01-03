package main;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		int a[] = { 1, 2, 3};
		System.out.println(Arrays.toString(a));
		a = Arrays.copyOf(a, 2);
		System.out.println(Arrays.toString(a));

	}
	

}
