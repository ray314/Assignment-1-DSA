/**
 * 
 */
package lab6;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fbb3628
 *
 */
public class QuickSort<E extends Comparable<E>> extends RecursiveAction {

	private List<E> data;
	private int left;
	private int right;
	
	/**
	 * 
	 */
	public QuickSort(List<E> data) {
		this.data = data;
		this.left = 0;
		this.right = data.size() - 1;
	}
	
	private QuickSort(List<E> data, int left, int right)
	{
		this.data = data;
		this.left = left;
		this.right = right;
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.RecursiveAction#compute()
	 */
	@Override
	protected void compute() {
		if (left < right)
		{
			int pivotIndex = left + ((right - left)/2);
			
			pivotIndex = partition(pivotIndex);
			
			invokeAll(new QuickSort<E>(data, left, pivotIndex - 1),
					new QuickSort<E>(data, pivotIndex + 1, right));
		}

	}

	private int partition(int pivotIndex)
	{
		E pivotValue = data.get(pivotIndex);
		
		swap(pivotIndex, right);
		
		int storeIndex = left;
		for (int i = left; i < right; i++)
		{
			if (data.get(i).compareTo(pivotValue) < 0)
			{
				swap(i, storeIndex);
				storeIndex++;
			}
		}
		
		swap(storeIndex, right);
		return storeIndex;
	}
	
	private void swap(int i, int j)
	{
		if (i != j)
		{
			E iValue = data.get(i);
			
			data.set(i, data.get(j));
			data.set(j, iValue);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size = 1000000;
		Integer[] intArray = new Integer[size];
		
		List<Integer> myList = new ArrayList<Integer>(size);
		
		for (int i = 0; i < size; i++)
		{
			int value = (int) (Math.random() * 100);
			myList.add(value);
		}
		// ForkJoin quicksort
		QuickSort<Integer> quicksort = new QuickSort<Integer>(myList);
		
		ForkJoinPool pool = ForkJoinPool.commonPool();
		
		long start = System.currentTimeMillis();
		pool.invoke(quicksort);
		long end = System.currentTimeMillis();
		
		long elapsedTime = end - start;
		
		System.out.println("New quick sort time: " + elapsedTime);
		
		System.out.println();
		
		// Original quicksort
		myList = new ArrayList<Integer>(size);
		
		for (int i = 0; i < size; i++)
		{
			int value = (int) (Math.random() * 100);
			intArray[i] = value;
		}
		
		ArraySorter<Integer> sorter = new ArraySorter<Integer>();
		
		start = System.currentTimeMillis();
		sorter.quickSort(intArray);
		end = System.currentTimeMillis();
		
		elapsedTime = end - start;
		
		System.out.println("Original quick sort time: " + elapsedTime);
		
		/*Iterator<Integer> it = myList.iterator();
		System.out.print(it.next() + ", ");
		while (it.hasNext())
		{
			System.out.print(it.next() + ", ");
		}*/
	}

}
