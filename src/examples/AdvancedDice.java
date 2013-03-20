/*
================================================================================
                            Advanced Dice
  This is an example program, illustrating use of the hat container.
  
  PROBLEM:
      Roll a set of three dice with the appropriate probabilities,
      with only one call to a random number generator.
      
  The number of chances of a given sum coming up can be directly read
  off the polynomial expansion of Sum(x^i)^numDice as the coefficients, 
  where Sum goes from 1 to sidesPerDie. For example, with three six-sided die:
   
      ( x^1 + x^2 + x^3 + x^4 + x^5 + x^6 )^3 expands to:
          
  1x^3 + 3x^4 + 6x^5 + 10x^6 + 15x^7 + 21x^8 + 25x^9 + 27x^10 + 27x^11 + 25x^12 
  + 21x^13 + 15x^14 + 10x^15 + 6x^16 + 3x^17 + 1x^18
  
  The first term says there is 1 way to get a 3, the second says there are
  3 ways to get a 4, the third says there are 6 ways to get a 5, etc.      
================================================================================
*/	    
package examples;

import hat.Hat;

public class AdvancedDice {

	public static void run()
	{	
		System.out.println("\n\n--|Advanced Dice|--------------------------------------------------------------");
		System.out.println("Roll 3d6 with only ONE call to the RNG");
		
	    // load a hat with our chance weights
	    Hat<Integer> dice = new Hat<Integer>();
	    int chances[]={1, 3, 6, 10, 15, 21, 25, 27, 27, 25, 21, 15, 10, 6, 3, 1};
	    for(int sum = 3; sum <= 18; sum++)
	    {
	        dice.put( sum, chances[sum-3] );
	    }
	  
	    // roll 3d6 six times
	    for( int i=0; i<10; i++ ){
	        System.out.print(dice.get() + " ");
	    }
	}

}
