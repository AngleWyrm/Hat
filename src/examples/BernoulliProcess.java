/*
======================================================================
                           hat container example
  BERNOULLI TRIAL SIMULATION:
    A warehouse where departures of trucks are often late.
    Five trucks, each with a probability of 40% of being late.
======================================================================
*/
package examples;
import hat.Hat;

public class BernoulliProcess
{
	public static void run()
	{
		System.out.println("\n\n--|Bernoulli Process|----------------------------------------------------------");
		System.out.println("Simulate five trucks, each with 40% chance of being late");
		
	    // create a truck which can only be on time (60%) or late (40%)
	    Hat<Boolean> late_truck = new Hat<Boolean>();
	    
	    late_truck.put( true,  40 );  // is late  40/100 = 40% = .4
	    late_truck.put( false, 60 );  // not late 60/100 = 60% = .6
	    
	    int count = 0;
	    for( int i= 0; i < 5; i++)
	    {
	         count += (late_truck.get()?1:0);
	    }
	    
	    System.out.println(count + " truck" + ( count == 1 ? " was" : "s were" ) + " late");
	}
}    
