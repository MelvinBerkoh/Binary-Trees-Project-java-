import java.util.ArrayList;
import java.util.Queue;

/**
 * 
 */

/**
 * @author Professor Bamford
 *
 */
public interface BinarySearchTreeInterface<T extends Comparable<T>> 
{
	// used to specify traversal order
	static final int INORDER = 1;
	static final int PREORDER = 2;
	static final int POSTORDER = 3;
	
	
	/*
	 * @return -returns true if this Binary Search Tree is empty
	 * otherwise returns false
	 */
	public boolean isEmpty();
	
	/*
	 * @return - returns number of elements on this Binary Search Tree
	 */
	public int size();
	
	/*
	 * @return - returns true if this Binary Search Tree contains an element
	 * e such that e.compareTo(element) == 0; otherwise, returns false
	 */
	public boolean contains(T element);
	
	/*
	 * Removes an element e from this Binary Search Tree such that e.compareTo(element) == 0
	 */
	public void remove(T element);
	
	/*
	 * @return - returns an element e from this Binary Search Tree such that e.compareTo(element) == 0
	 * if no such element exists, returns null
	 */
	public T get(T element);
	
	/*
	 * Adds an element to this Binary Search Tree.
	 * The tree retains its Binary Search Tree property.
	 */
	public void add(T element);
	
	/*
	 * Initializes current position for an iteration through this Binary Search Tree
	 * in orderType order.  
	 * @return - returns current number of nodes in the Binary Search Tree
	 */
	public int reset(int orderType);
	
	/*
	 * Prints the Binary Search Tree
	 */
	public void printBinarySearchTree();
	
	/*
	 * List contains tree using inOrder traversal
	 * @return - returns inOrderList
	 */
	public ArrayList<T> getInOrderList();
	
	/*
	 * List contains tree using preOrder traversal
	 * @return - returns preOrderList
	 */
	public ArrayList<T> getPreOrderList();
	
	/*
	 * List contains tree using postOrder traversal
	 * @return - returns postOrderList
	 */
	public ArrayList<T> getPostOrderList();
}
