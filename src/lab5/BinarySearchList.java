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
	
	public BinarySearchList(List<E> list)
	{
		this.list = list;
	}
	
	public int search(E target)
	{
		if (target == null)
		{
			throw new NullPointerException("search target is null");
		}
		return search(target, 0, list.size());
	}
	
	private int search(E target, int start, int end, ListIterator<E> iterator)
	{
		traversals++;
		if (start >= end)
		{
			return -start-1;
		}
		else 
		{
			
			
			int midpoint = (start+end)/2;
			ListIterator<E> it = list.listIterator(midpoint);
			
			if (midpoint < iterator.nextIndex())
			{
				for (int i = iterator.nextIndex(); i < midpoint; i++)
				{
					iterator.next();
				}
			}
			else
			{
				for (int i = iterator.previousIndex(); i >= midpoint; i--)
				{
					iterator.previous();
				}
			}
			
			int comparison = target.compareTo(iterator.next());
			
			if (comparison == 0)
			{
				return midpoint;
			}
			else if (comparison < 0)
			{
				it.previous();
				
				return search(target, start, it.previousIndex());
			}
			else
			{
				it.next();
				//traversals++;
				return search(target, midpoint+1, it.nextIndex()+1);
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list = new LinkedList<Integer>();
		
		for (int i = 1; i <= 100000; i++)
		{
			list.add(i);
		}
		
		BinarySearchList<Integer> listSearch = new BinarySearchList<Integer>(list);
		
		Integer target = 40000;
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
		
		System.out.println(listSearch.traversals);
		
		
	}

}
