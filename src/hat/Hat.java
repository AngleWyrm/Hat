package hat;
import java.util.Random;
import java.util.Vector;

public class Hat<T> 
{
	// Public Interface -------------------------------------------------------
	
	public Hat(){
		rng = new Random();
		tree = new Vector<Node>();
	}
	public Hat(T[] _inputArray){
		rng = new Random();
		tree = new Vector<Node>();
		
		for(T item : _inputArray){
			tree.add(new Node(item));
			update_weights(tree.size()-1);
		}
	}
	
	public int size(){
		return tree.size();
	}
	
	public void put(T in){
		put(in, 1);
	}
	public void put(T in, int chances){
		Node newNode = new Node(in, chances);
		tree.add(newNode);
		update_weights(tree.size()-1);
	}
	public T get(){
	    // roll a random point on the sum of all chance weights [0,family_weight)
	    int target = rng.nextInt(tree.elementAt(0).family_weight);

	    // find the matching index in the tree
	    int index = find_index(0, target);
	    return tree.elementAt(index).item;
	}

	public T pull()
	{
	    // get random item
	    int random_weight = rng.nextInt(tree.elementAt(0).family_weight);
	    int index = find_index( 0, random_weight );
	    
	    T buffer = tree.elementAt(index).item;
	    erase(index);
	    return buffer;
	};	

	public void print(int index)
	{
	    System.out.println("\r" + tree.elementAt(index).item
	         + "(" + tree.elementAt(index).chance_weight
	         + "/" + tree.elementAt(index).family_weight + ")");

	    if(2*index+1 < tree.size()){ // has son
	        System.out.println("\tson[" + (2*index+1) + "]:" + tree.elementAt(2*index+1).item);
	    }
	    if(2*(index+1) < tree.size()) { // also has daughter
	        System.out.println("\tdaughter[" + (2*(index+1)) + "]:" + tree.elementAt(2*(index+1)).item);
	    }
	};

	public boolean isEmpty(){
		return tree.isEmpty();
	}
	
	// Erase a given index [0,tree.size) by over-writing with last item
	public void erase(int index)
	{
		// quick clear if removing only entry in tree
		if(tree.size() <= 1){
			tree.clear();
			return;
		}
		
		// Copy last item into current item
	    tree.elementAt(index).copy(tree.elementAt(tree.size()-1));
	    update_weights(index);
	    
	    tree.removeElementAt(tree.size()-1); 

	    // update replacement node's old parents
	    if( (tree.size() % 2) >= 0 ) { // then odd number, old size was even (female node)
	        update_weights( (tree.size()-1)/2 ); // update old parent of female
	    }
	    else { // was a male node
	        update_weights( tree.size()/2 - 1 ); // update old parent of male
	    }
	}

	
	// Internal functions -----------------------------------------------------
	
	private int find_index( int index, int target ){
	    // divide weight into three groups: Self, Son & Daughter, and test each

	    // Testing self
	    int sum = tree.elementAt(index).chance_weight;
	    if( target < sum )
	    {
	        return index; // found within self
	    } // else outside self, must be in children

	    // Testing son (first-born is always a son)
	    sum += tree.elementAt( 2*index+1 ).family_weight;
	    if( target < sum )
	    {
	        return find_index( 2*index+1, target - tree.elementAt(index).chance_weight );
	    } // else not in son's range

	    // Testing daughter
	    sum += tree.elementAt( 2*(index+1) ).family_weight;
	    if( target < sum )
	    {
	        return find_index( 2*(index+1),
	         target - tree.elementAt(index).chance_weight - tree.elementAt( 2*index+1 ).family_weight );
	    }

	    // possible to reach here if a custom random number generator is used,
	    // and it fails to deliver a number in the correct range
	    return 0;
	};

	private void update_weights(int index){
		   // update the weight of the currently indexed node
	    tree.elementAt(index).family_weight =  tree.elementAt(index).chance_weight;

	    // First, update self with children's weights
	    // daughters are found at 2(x+1)
	    if( 2*(index+1) < tree.size() )
	    {   // add daughter's weight to family
	        tree.elementAt(index).family_weight += tree.elementAt(2*(index+1)).family_weight;
	    } // else no daughter

	    // sons are found at 2x+1
	    if( 2*index+1 < tree.size() )
	    {   // add son
	        tree.elementAt(index).family_weight += tree.elementAt( 2*index+1 ).family_weight;
	    } // else no son

	    // Second, update parents with self
	    // check for parents, and update them
	    if( index > 0 ) // not the root mother (has parent)
	    {
	        // calculate distance to parent
	        if( (index % 2)>=0 ) { // is a male node
	            update_weights( (index-1)/2 ); // update parent
	        }
	        else { // is a female node
	            update_weights( index/2 - 1 ); // update parent
	        }
	    } // else this is the root mother (no parents)

	}
	
	
	// Variables --------------------------------------------------------------
	
	class Node{
		T item;
		int chance_weight;
		int family_weight;
		
		Node(T in){
			item = in;
			chance_weight = family_weight = 1;
		}
		Node(T in, int chances){
			item = in;
			chance_weight = family_weight = chances;
		}
		
		public void copy (Node other){
			item = other.item;
			chance_weight = other.chance_weight;
			family_weight = other.family_weight;
		}
	}	
	Vector<Node> tree;
	Random rng; 
}
