/*
======================================================================
                       hat container example

  This is an example program, illustrating use of the hat container.

======================================================================
*/
package examples;
import hat.Hat;

public class BasicUses 
{
	public static void run()
	{
		System.out.println("--|Basic Usage|--------------------------------------------------------------");
		System.out.println("Some simple uses of the hat container");
		
		// put some data into a hat and then get a random item
		String[] names = {"Alice", "Betty", "Celia", "Darcy"};
		Hat<String> hatOfNames = new Hat<String>(names);
		for(int i = 0; i < 10; ++i){
			System.out.print( hatOfNames.get() + " " ); // get a random item from hat
		}
		System.out.println();
		
		// assign different chance weights to items in a hat
		Hat<String> weightedNames = new Hat<String>();
		weightedNames.put("Alice", 5); // 5 chances out of the sum of all chances in the hat
		weightedNames.put("Betty", 3); // 3 chances
		weightedNames.put("Celia", 1); // 1 chance
		weightedNames.put("Darcy");    // default behavior of 1 chance
		for(int i = 0; i < 10; ++i){
			System.out.print( weightedNames.get() + " " ); // draw a name
		}
		System.out.println();
		
		// draw names randomly from a pre-existing data set
		Hat<Integer> index = new Hat<Integer>();
		Integer[] chanceWeights = {50, 30, 10, 10}; // using 100 chances to simulate percentages
		for(int i = 0; i < 4; ++i){
			index.put(i, chanceWeights[i]); // assigning chance weights when inserting items in hat
		}
		for(int i = 0; i < 10; ++i){
			System.out.print( names[index.get()] + " " ); // draw a name using a random index
		}		
	}

}
