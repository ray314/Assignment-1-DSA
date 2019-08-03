/**
 * 
 */
package linkedRRSet;

import java.util.Collection;

import lab3.LinkedSet;

/**
 * @author Justin Yeung
 * @param <E>
 *
 */
public class LinkedRRSet<E extends Comparable<E>> extends LinkedSet<E> {

	public LinkedRRSet()
	{
		super();
	}

	public LinkedRRSet(Collection<? extends E> c)
	{
		super(c);
	}

	//Node<E> m = new Node target
	// if (firstnode == null || target.compareTo

	@Override
	public boolean add(E o)
	{
		Node<E> newNode = new Node<E>(o);
		Node<E> currentNode = firstNode;


		if (!contains(o))
		{
			// If firstNode is null then add a new one
			if (firstNode == null)
			{
				firstNode = newNode;
				numElements++;
				return true;
			}
			// if new node element is less than the first element
			else if (newNode.element.compareTo(firstNode.element) < 0)
			{
				newNode.next = firstNode; // set newNode.next to the first node, putting it behind first node
				firstNode = newNode; // Set new firstNode position to newNode as that is smallest for now
				numElements++;
				return true; // Successful add
			}
			// if new element is greater than current element
			else
			{
				// Iterate through the list as long as next node is not null
				while (currentNode.next != null)
				{
					// If currentNode is less than newNode and currentNode.next is greater than newNode
					if (currentNode.element.compareTo(newNode.element) < 0 && currentNode.next.element.compareTo(newNode.element) > 0) {
						// Adding the new node between the currentNode and currentNode.next
						newNode.next = currentNode.next;
						currentNode.next = newNode;
						numElements++;
						return true; // Successful add, skip rest of code
					}
					currentNode = currentNode.next; // Next element to compare
				}
				// If next current node is null then add one. This will always be the largest element
				currentNode.next = newNode; 
				numElements++;
				return true; // Successful add
			}
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
