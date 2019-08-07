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
		LinkedRRSet<E> removedSet = new LinkedRRSet<E>();
		Node<E> currentNode = firstNode; // Start from first node
		Node<E> firstPosNode = null; // First position specified by first parameter
		Node<E> lastPosNode = null; // Last position specified by last parameter
		Node<E> tempNode = null;
		
		// Throw exception if first or last are not in the set
		if (first != null && !contains(first))
		{
			throw new NoSuchElementException("First specified element range does not exist in the set");
		}
		else if (last != null && !contains(last))
		{
			throw new NoSuchElementException("Last specified element range does not exist in the set");
		}
		
		//todo: iterate once, save the position of the specified first and last element
		// then use that as range and cut off the rest
		// Will loop while currentNode is not null and first and last nodes are null
		while(currentNode != null && (firstPosNode == null || lastPosNode == null))
		{
			// if first equals to current node element
			// then store the position. This condition is for the first range
			// at the start of the list
			if (first == currentNode.element && first != currentNode.next.element)
			{
				firstPosNode = currentNode;
			}
			// If first equals to the current next node
			// then store the next node into firstPos and currentNode
			// into tempNode
			else if (first == currentNode.next.element)
			{
				firstPosNode = currentNode.next;
				tempNode = currentNode; // Keep reference to the node before the specified one
			}
			// Exclusive
			else if (last == currentNode.next.element)
			{
				lastPosNode = currentNode;
				tempNode.next = currentNode.next; // Link node before first to next after last
			}
		}
		
		/*while (currentNode != null) // Iterate all the elements
		{ 	
			// Only one of these if else blocks will execute
			// If both ranges are null then add everything
			if (first == null && last == null)
			{
				removedSet.add(currentNode.element);
				remove(currentNode.element);
			}
			// Do the open ended range
			else if (first == null && currentNode.element.compareTo(last) < 0)
			{
				removedSet.add(currentNode.element);
				remove(currentNode.element);
			}
			else if (last == null && currentNode.element.compareTo(first) > 0)
			{
				removedSet.add(currentNode.element);
				remove(currentNode.element);
			}
			// If both ranges are not null then proceed to do the normal range
			else if (first != null && last != null)
			{
				if (currentNode.element.compareTo(first) < 0 ||
						currentNode.element.compareTo(last) >= 0)
				{
					removedSet.add(currentNode.element);
					remove(currentNode.element);
				}
			}
			currentNode = currentNode.next; // Next node
		} */
		
		return removedSet;
	}
	
	public LinkedRRSet<E> remove(E first, E last)
	{
		LinkedRRSet<E> removedSet = new LinkedRRSet<E>();
		Node<E> currentNode = firstNode; // Start from first node

		boolean rangeCondition;
		boolean nullCondition;
		// Throw exception if first and last are null
		if (!contains(first) || !contains(last))
		{
			throw new NoSuchElementException("First or last specified element does not exist in the set");
		}
		
		while (currentNode != null) // Iterate all the elements
		{ 
			// Check whether the current element is in range
			rangeCondition = (currentNode.element.compareTo(first) > 0 && 
					currentNode.element.compareTo(last) <= 0);
			// Check whether the first or last are null
			nullCondition = ((first == null && currentNode.element.compareTo(last) <= 0) ||
					(last == null && currentNode.element.compareTo(first) > 0));
			
			// If it is greater than the first or less than the last then add it to the removedSet
			if (rangeCondition || nullCondition)
			{
				removedSet.add(currentNode.element);
				remove(currentNode.element);
			}
			else if (first == null)
			{
				
			}
			else if (last == null)
			{
				
			}
			
			currentNode = currentNode.next; // Next node
		}
		
		return removedSet;
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
		LinkedRRSet<Integer> list = new LinkedRRSet<Integer>();
		LinkedRRSet<Integer> retainList;
		
		for (int i = 7; i >= 1; i--)
		{
			list.add(i);
		}
		
		Iterator<Integer> it = list.iterator();
		System.out.print("List Returned set: ");
		while (it.hasNext())
		{
			System.out.print(it.next() + ", ");
		}
		
		System.out.println();
		
		retainList = list.retain(5, null);
		it = retainList.iterator();
		System.out.print("retainList returned set: ");
		while (it.hasNext())
		{
			System.out.print(it.next() + ", ");
		}
		
		System.out.println();
		
		it = list.iterator();
		System.out.print("List returned set: ");
		while (it.hasNext())
		{
			System.out.print(it.next() + ", ");
		}
	}

}
