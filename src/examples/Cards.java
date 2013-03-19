/*
======================================================================
                       hat container example

  This is an example program, illustrating use of the hat container.

  PROBLEM:
    draw 5-card hand from a regular deck of playing cards,
    and put them back so that they may be drawn again.

======================================================================
*/
package examples;
import hat.Hat;

public class Cards
{
	public static void run()
	{
		System.out.println("\n\n--|Cards|----------------------------------------------------------------------");
		System.out.println();
		
	    String value[] = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
	    String suit[] = { "diamonds", "clubs", "hearts", "spades" };
	   
	    // load a hat with cards
	    Hat<String> full_deck = new Hat<String>();
	    Hat<String> playing_deck = new Hat<String>();
	    String one_card;
	    for( int v=0; v<13; v++ )
	    {
	        for( int s=0; s<4; s++)
	        {
	            // assemble a card string like "Queen of hearts" etc
	            one_card = value[v];
	            one_card += " of ";
	            one_card += suit[s];
	
	            full_deck.put(one_card); // put card in hat
	        }
	    }
	    playing_deck = full_deck;
	
	    // now a 5-card hand and then return them to the deck
	    for( int h=0; h<3; h++)
	    {
	        for( int c=0; c<5; c++ )
	        {
	            System.out.println(playing_deck.pull());
	        }
	        System.out.println("");
	        
	        playing_deck = full_deck;
	    }
	}
}