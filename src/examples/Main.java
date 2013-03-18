package examples;

import hat.Hat;

public class Main {

	public static void main(String[] args) {

		// Basic usage of a Hat
		
		// Populate a Hat container
		String[] names = {"Able", "Baker", "Charlie", "Delta", "Echo", "Foxtrot"};
		Hat<String> myHat = new Hat<String>(names);
		
		showTree(myHat); // just to display the hat's internal storage structure
		
		// Get names from the hat (items can come up again)
		System.out.print("\n\nRandom sampling with replacement\n");
		for(int i=0; i<10; ++i){
			System.out.print( myHat.get() + "  " );
		}
		
		// Pull names out of the hat (items can only be drawn once)
		System.out.print("\n\nRandom sampling without replacement\n");
		while(!myHat.isEmpty()){
			System.out.print( myHat.pull() + "  ");
		}
		
		
		//------------------------------------------------------------------------------
		
		// Put items in a hat, along with their chances of occurrence
		int[] chances = {1, 1, 2, 3, 1, 1};
		for(int i=0; i<6; i++){
			myHat.put(names[i], chances[i]);
		}
		
		showTree(myHat);
		
		// Get names from the hat (items can come up again)
		System.out.print("\n\nRandom weighted sampling with replacement\n");
		for(int i=0; i<10; ++i){
			System.out.print( myHat.get() + "  " );
		}	
		
		//------------------------------------------------------------------------------
		
	}
	
	// examine the hat.tree internal data structure
	public static void showTree(Hat<?> someHat)
	{
		System.out.println("\n--|hat size: " + someHat.size() + "|--------------------------");
		
		for(int i = 0; i < someHat.size(); i++){
			someHat.print(i);
		}		
		System.out.print("--------------------------------------------");
	}
}
