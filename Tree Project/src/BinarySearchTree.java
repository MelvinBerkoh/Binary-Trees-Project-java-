import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Professor Bamford
 *
 */
public class BinarySearchTree<T extends Comparable<T>> implements BinarySearchTreeInterface<T>
{
	protected BinarySearchTreeNode<T> root;
	protected ArrayList<T> inOrderList;
	protected ArrayList<T> preOrderList;
	protected ArrayList<T> postOrderList;
	
	public BinarySearchTree()
	{
		root = null;
	
	}
	
	/*
	 * Adds an element to this Binary Search Tree.
	 * The tree retains its Binary Search Tree property.
	 */
	public void add(T element)
	{
		BinarySearchTreeNode<T> newNode = new BinarySearchTreeNode<T>(element);
		if(root == null)
			root = newNode;
		else
			root.addNode(newNode);
	}

	public void add(T element, int first, String code)
	{
		BinarySearchTreeNode<T> newNode = new
				BinarySearchTreeNode<T>(element);
		if(root == null)
			root = newNode;
		else
			root.addNode(newNode, first, code);
	}

	/*
	 * @return - returns an element e from this Binary Search Tree such that e.compareTo(element) == 0
	 * if no such element exists, returns null
	 */
	public T get(T element)
	{
		BinarySearchTreeNode<T> current = root;
		while(current != null)
		{
			int comp = current.getInfo().compareTo(element);
			if(comp == 0)
				return current.getInfo();  // element found
			else if(comp > 0)
				current = current.getLeft();  // get the left subtree
			else
				current = current.getRight();  //get the right subtree
		}
		return null;  //element not found
	}
	
	public BinarySearchTreeNode<T> getRoot()
	{
		return root;
	}
	/*
	 * @return -returns true if this Binary Search Tree is empty
	 * otherwise returns false
	 */
	public boolean isEmpty()
	{
		return (root == null);
	}
	
	/*
	 * @return - returns number of elements on this Binary Search Tree
	 */
	public int size()
	{
		return recSize(root);
	}
	/*
	 * Recursive method returns number of elements in tree
	 * @param tree with elements
	 * @return base case if tree is empty 0 otherwise, count left and right
	 */
	private int recSize(BinarySearchTreeNode<T> tree)
	{
		if(tree == null)
			return 0;
		else
			return recSize(tree.getLeft()) + recSize(tree.getRight()) + 1;
	}
	
	/*
	 * @return - returns true if this Binary Search Tree contains an element
	 * e such that e.compareTo(element) == 0; otherwise, returns false
	 */
	public boolean contains(T element)
	{
		BinarySearchTreeNode<T> current = root;
		while(current != null)
		{
			int d = current.getInfo().compareTo(element);
			if(d == 0)
				return true;
			else if(d > 0)
				current = current.left;
			else
				current = current.right;
		}
		return false;
	}
	
	/*
	 * Removes an element e from this Binary Search Tree such that e.compareTo(element) == 0
	 * @param element to be removed
	 */
	public void remove(T element)
	{
		BinarySearchTreeNode<T> toBeRemoved = root;
		BinarySearchTreeNode<T> parent = null;
		boolean found = false;
		while(!found && toBeRemoved != null)
		{
			int comp = toBeRemoved.getInfo().compareTo(element);
			if(comp == 0)
			{
				found = true;
			}
			else
			{
				parent = toBeRemoved;
				if(comp > 0)
					toBeRemoved = toBeRemoved.left;
				else
					toBeRemoved = toBeRemoved.right;
			}
		}
		
		if(!found)
			return;
		
		//toBeRemoved contains element
		
		//if one of the children is empty, use the other
		if(toBeRemoved.left == null || toBeRemoved.right == null)
		{
			BinarySearchTreeNode<T> newChild;
			if(toBeRemoved.left == null)
				newChild = toBeRemoved.right;
			else
				newChild = toBeRemoved.left;
			
			if(parent == null)  // found in root
				root = newChild;
			else if(parent.left == toBeRemoved)
				parent.left = newChild;
			else
				parent.right = newChild;
			return;
		}
		//Neither subtree is empty
		//Find the smallest element of the right subtree
		
		BinarySearchTreeNode<T> smallestParent = toBeRemoved;
		BinarySearchTreeNode<T> smallest = toBeRemoved.right;
		
		while(smallest.left != null)
		{
			smallestParent = smallest;
			smallest = smallest.left;
		}
		//smallest contains smallest child in right subtree
		//move contents, unlink child
		
		toBeRemoved.info = smallest.info;
		smallestParent.left = smallest.right;

	}
	
	
	
	/*
	 * Initializes current position for an iteration through this Binary Search Tree
	 * in orderType order.  
	 * @return - returns current number of nodes in the Binary Search Tree
	 */
	public int reset(int orderType)
	{
		int numNodes = size();
		
		if(orderType == INORDER)
		{
			inOrderList = new ArrayList<T>(numNodes);
			inOrder(root);
		}
		else if(orderType == PREORDER)
		{
			preOrderList = new ArrayList<T>(numNodes);
			preOrder(root);
		}
		else if(orderType == POSTORDER)
		{
			postOrderList = new ArrayList<T>(numNodes);
			postOrder(root);
		}
		return numNodes;
	}
	
	
	/*
	 * Initializes inOrderList with tree elements in inOrder order
	 */
	private void inOrder(BinarySearchTreeNode<T> tree)
	{
		if(tree != null)
		{
			inOrder(tree.getLeft());
			inOrderList.add(tree.getInfo());
			inOrder(tree.getRight());
		}
	}
	
	/*
	 * Initializes preOrderList with tree elements in preOrder order
	 */
	private void preOrder(BinarySearchTreeNode<T> tree)
	{
		if(tree != null)
		{
			preOrderList.add(tree.getInfo());
			preOrder(tree.getLeft());
			preOrder(tree.getRight());
		}
	}
	
	/*
	 * Initializes preOrderList with tree elements in preOrder order
	 */
	private void postOrder(BinarySearchTreeNode<T> tree)
	{
		if(tree != null)
		{
			postOrder(tree.getLeft());
			postOrder(tree.getRight());
			postOrderList.add(tree.getInfo());
		}
	}
	
	/*
	 * Prints the Binary Search Tree
	 */
	public void printBinarySearchTree()
	{
		root.printNodes();
	}
	
	/*
	 * List contains tree using inOrder traversal
	 * @return - returns inOrderList
	 */
	public ArrayList<T> getInOrderList()
	{
		return inOrderList;
	}
	
	/*
	 * List contains tree using preOrder traversal
	 * @return - returns preOrderList
	 */
	public ArrayList<T> getPreOrderList()
	{
		return preOrderList;
	}
	
	/*
	 * List contains tree using postOrder traversal
	 * @return - returns postOrderList
	 */
	public ArrayList<T> getPostOrderList()
	{
		return postOrderList;
	}
}