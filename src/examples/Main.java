package examples;

import hat.Hat;

public class Main {

	public static void main(String[] args) {

		// Basic usage of the Hat Abstract Data Type:
		
		// Populate a Hat container
		String[] names = {"Able", "Baker", "Charlie", "Delta", "Echo", "Foxtrot"};
		Hat<String> myHat = new Hat<String>(names);
		
		// Draw names from the hat (items can come up again)
		System.out.print("\nRandom sampling with replacement\n");
		for(int i=0; i<10; ++i){
			System.out.print( myHat.get() + "  " );
		}
		
		// Pull names out of the hat (items can only be drawn once)
		/*
		System.out.print("\n\nRandom sampling without replacement\n");
		String buffer = "";
		while(!myHat.isEmpty()){
			System.out.print( myHat.pull(buffer) + "\t");
		}
		*/
		
		// hat.print() function to examine the hat.tree structure
		System.out.println("\n\nhat size: " + myHat.size());
		for(int i = 0; i < myHat.size(); ++i){
			myHat.print(i);
		}	
	}
}
