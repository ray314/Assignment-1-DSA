/**
 * 
 */
package linkedRRSet;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import lab3.LinkedSet;

/**
 * @author Justin Yeung
 * @param <E>
 *
 */
public class LinkedRRSet<E extends Comparable<E>> extends LinkedSet<E> {
	
	Node<E> currentNode; // Start from first node
	Node<E> firstPosNode; // First position specified by first parameter
	Node<E> lastPosNode; // Last position specified by last parameter
	Node<E> tempNode; // Temporary node for storing the nodes before firstPosNode and after lastPosNode
	boolean firstNullUsed;
	
	public LinkedRRSet()
	{
		super();
	}

	public LinkedRRSet(Collection<? extends E> c)
	{
		super(c);
	}

	public LinkedRRSet<E> retain(E first, E last) throws NoSuchElementException
	{
		LinkedRRSet<E> removeSet = new LinkedRRSet<E>();
		currentNode = firstNode; // Start from first node                                                 
		firstPosNode = null; // First position specified by first parameter                               
		lastPosNode = null; // Last position specified by last parameter                                  
		tempNode = null; // Temporary node for storing the nodes before firstPosNode and after lastPosNode
		firstNullUsed = false;
		
		// If first is null then set first to firstNode element
		if (first == null && last != null)
		{
			// If last value is equal to first element then keep only the first node
			if (last == firstNode.element)
			{
				removeSet.firstNode = firstNode.next;
				firstNode.next = null;
				return removeSet;
			}
			first = firstNode.element;
		}
		// If first and last are the same or null then return an empty set
		else if (first == last || first == null && last == null)
		{
			return removeSet;
			// Rest of the code gets skipped
		}
		// If first equals to first node element and last is null (covering the whole set)
		else if (first == firstNode.element && last == null)
		{
			return removeSet; // Retain all elements in set
			// Rest of the code gets skipped
		}
		
		// Throw exception if first or last are not in the set
		if (!contains(first))
		{
			throw new NoSuchElementException(first.toString() + " does not exist in the set");
		}
		else if (last != null && !contains(last))
		{
			throw new NoSuchElementException(last.toString() + " does not exist in the set");
		}
		// if first is greater than last element and last is not null
		else if (last != null)
		{
			if (first.compareTo(last) > 0)
			{
				throw new IllegalArgumentException(first.toString() + " is greater than " + last.toString());
			}
		}
		
		
		linkNodes(first, last);
		// Link retainSet first node to firstPosNode
		if (firstNode.element == first)
		{
			removeSet.firstNode = tempNode;
		}
		else 
		{
			removeSet.firstNode = firstNode;
			firstNode = firstPosNode;
		}
		
		if (last == null)
		{
			tempNode.next = null;
		}
		
		
		return removeSet;
	}

	private void linkNodes(E first, E last) {
		// Will loop while currentNode is not null and first and last nodes are null
		while(currentNode != null && (firstPosNode == null || lastPosNode == null))
		{
			// if next node is null then set lastPosNode to currentNode
			// The last node next will always be null
			if (currentNode.next == null)
			{
				lastPosNode = currentNode;
			}
			// If first equals to the current next node
			// then store the next node into firstPos and currentNode
			// into tempNode
			else if (first == currentNode.next.element)
			{
				firstPosNode = currentNode.next;
				tempNode = currentNode; // Keep reference to the node before the specified one
			}

			// If last is not null then
			else if (last != null)
			{
				// Exclusive
				if (last == currentNode.next.element)
				{
					lastPosNode = currentNode;

					if (tempNode == null)
					{
						// If tempNode is null or firstPosNode is at firstNode
						// then set tempNode to lastPosNode.next
						tempNode = lastPosNode.next;
					}
					else
					{
						tempNode.next = lastPosNode.next; // Link node before first to next after last
					}

					// Cut off the next node for currentNode
					currentNode.next = null;

				}
			}
			// if first equals to current node element
			// then store the position. This condition is for the first range
			// at the start of the list
			else if (first == currentNode.element && currentNode == firstNode && tempNode == null)
			{
				// Set firstPosNode at firstNode
				firstPosNode = firstNode;
			}
			

			currentNode = currentNode.next;

		}
	}
	
	// Remove range
	public LinkedRRSet<E> remove(E first, E last)
	{
		LinkedRRSet<E> removeSet = new LinkedRRSet<E>();
		currentNode = firstNode; // Start from first node
		firstPosNode = null; // First position specified by first parameter
		lastPosNode = null; // Last position specified by last parameter
		tempNode = null; // Temporary node for storing the nodes before firstPosNode and after lastPosNode
		
		// If first is null then set first to firstNode element
		if (first == null && last != null)
		{
			if (last == firstNode.element)
			{
				removeSet.firstNode = firstNode;
				firstNode.next = firstNode;
				removeSet.firstNode.next = null;
				return removeSet;
			}
			first = firstNode.element;
			
		}
		// If first and last are the same or null then return an empty set
		else if (first == last || first == null && last == null)
		{
			return removeSet;
			// Rest of the code gets skipped
		}
		
		// Throw exception if first or last are not in the set
		if (!contains(first))
		{
			throw new NoSuchElementException(first.toString() + " does not exist in the set");
		}
		else if (last != null && !contains(last))
		{
			throw new NoSuchElementException(last.toString() + " does not exist in the set");
		}
		// if first is greater than last element and last is not null
		else if (last != null)
		{
			if (first.compareTo(last) > 0)
			{
				throw new IllegalArgumentException(first.toString() + " is greater than " + last.toString());
			}
		}
		
		linkNodes(first, last);

		// Link retainSet first node to firstPosNode
		removeSet.firstNode = firstPosNode;
		
		// If first element is at first node then link firstNode to tempNode (lastPosNode)
		if (firstNode == firstPosNode)
		{
			firstNode = tempNode;
		}
		// If last is null then set tempNode next to null
		else if (last == null)
		{
			tempNode.next = null;
			
		}
		
		return removeSet;
	}

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
		LinkedRRSet<Integer> set = new LinkedRRSet<Integer>();
		LinkedRRSet<Integer> retainSet;
		
		for (int i = 7; i >= 1; i--)
		{
			set.add(i);
		}
		
		Iterator<Integer> it = set.iterator();
		System.out.print("List Returned set: {");
		while (it.hasNext())
		{
			System.out.print(it.next());
			if (it.hasNext())
			{
				System.out.print(", ");
			}
		}
		System.out.print("}");
		
		System.out.println();
		
		retainSet = set.remove(null, 1);
		it = retainSet.iterator();
		System.out.print("retainList returned set: {");
		while (it.hasNext())
		{
			System.out.print(it.next());
			if (it.hasNext())
			{
				System.out.print(", ");
			}
		}
		System.out.print("}");
		
		System.out.println();
		
		it = set.iterator();
		System.out.print("List returned set: {");
		while (it.hasNext())
		{
			System.out.print(it.next());
			if (it.hasNext())
			{
				System.out.print(", ");
			}
		}
		System.out.print("}");
	}

}
