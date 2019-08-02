/**
 * 
 */
package lab2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * @author Justin Yeung
 *
 */
public class RandomObtainableList<E> extends ArrayList<E> implements RandomObtainable {

	private Random rand;
	public int length;
	
	public RandomObtainableList()
	{
		super();
		
		rand = new Random();
	}
	
	//@Override
	public E getRandom() throws NoSuchElementException {
		// TODO Auto-generated method stub
		int randomNum = rand.nextInt(size());;
		
		if (get(randomNum) == null)
		{
			throw new NoSuchElementException("Element does not exist.");
		}
		
		return get(randomNum); // Return a random element
	}

	//@Override
	public boolean removeRandom() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		int randomNum = rand.nextInt(size());
		
		remove(randomNum);
		
		return true;
	}
		
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomObtainableList<String> list = new RandomObtainableList<String>();
		
		
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		list.add("five");
		list.add("six");
		list.add("seven");
		list.add("eight");
		list.add("nine");
		list.add("ten");
		list.length = list.size();
		
		Iterator<String> it = list.iterator();
		while (it.hasNext())
		{
			System.out.print(it.next() + ", ");
		}
		
		System.out.println();
		System.out.println(list.getRandom());
		System.out.println();
		System.out.println();
		
		//list.removeRandom();
		
		it = list.iterator();
		while (it.hasNext())
		{
			for (String num : list)
			{
				System.out.print(num + ", ");
			}
			list.removeRandom();
			System.out.println();
		}
	}

	

}
