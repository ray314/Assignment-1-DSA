/**
 * 
 */
package lab5;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author fbb3628
 *
 */
public class BinarySearchList<E extends Comparable<E>> {

	private List<E> list;
	public int traversals = 0;
	public int compares = 0;
	
	public BinarySearchList(List<E> list)
	{
		this.list = list;
	}
	
	public int search(E target)
	{
		ListIterator<E> it = list.listIterator();
		if (target == null)
		{
			throw new NullPointerException("search target is null");
		}
		return search(target, 0, list.size(), it);
	}
	
	private int search(E target, int start, int end, ListIterator<E> iterator)
	{
		
		if (start >= end)
		{
			return -start-1;
		}
		else 
		{
			int midpoint = (start+end)/2;
			//iterator = list.listIterator(midpoint);
			
			if (iterator.nextIndex() < midpoint)
			{
				for (int i = iterator.nextIndex(); i <= midpoint; i++)
				{
					iterator.next();
					traversals++;
				}
			}
			else
			{
				for (int i = iterator.previousIndex(); i > midpoint; i--)
				{
					iterator.previous();
					traversals++;
				}
			}
			
			int comparison = target.compareTo(iterator.next());
			compares++;
			
			if (comparison == 0)
			{
				return midpoint;
			}
			else if (comparison < 0)
			{
				iterator.previous();
				
				return search(target, start, iterator.previousIndex(), iterator);
			}
			else
			{
				//traversals++;
				return search(target, midpoint+1, iterator.nextIndex(), iterator);
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list = new LinkedList<Integer>();
		
		for (int i = 0; i < 100; i++)
		{
			list.add(i);
		}
		
		BinarySearchList<Integer> listSearch = new BinarySearchList<Integer>(list);
		
		Integer target = 10;
		int index = listSearch.search(target);
		
		if (index >= 0)
		{
			System.out.println(target + " found at index " + index);
		}
		else
		{
			System.out.println(target + " not found at index " + -index);
		}
		
		//ListIterator<Integer> it = list.listIterator(7);
		
		System.out.println("Number of traversals: " + listSearch.traversals);
		System.out.println("Number of compares: " + listSearch.compares);
		
	}

}
