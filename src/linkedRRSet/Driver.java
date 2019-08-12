/**
 * 
 */
package linkedRRSet;

import java.util.Iterator;
import java.util.Set;

/**
 * @author Justin Yeung
 *
 */
public class Driver {


	public void runTest(Integer first, Integer last, int method)
	{
		LinkedRRSet<Integer> set = new LinkedRRSet<Integer>();
		Set<Integer> retainSet;
		
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
		
		// Retain and remove working properly and tested
		if (method == 0)
		{
			retainSet = set.retain(first, last);
		}
		else
		{
			retainSet = set.remove(first, last);
		}
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
		System.out.println();
		System.out.println();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Driver test = new Driver();
		
		System.out.println("Retain:");
		test.runTest(2, 6, 0); // 0 = retain, other int = remove
		
		System.out.println("Remove:");
		test.runTest(2, 6, 1); // 0 = retain, other int = remove
		
		System.out.println("Remove:");
		test.runTest(4, 5, 1); // 0 = retain, other int = remove
		
		System.out.println("Retain:");
		test.runTest(6, 7, 0); // 0 = retain, other int = remove
		
		System.out.println("Retain:");
		test.runTest(null, 4, 0); // 0 = retain, other int = remove
		
		System.out.println("Retain:");
		test.runTest(4, null, 0); // 0 = retain, other int = remove
		
		System.out.println("Remove:");
		test.runTest(4, null, 0); // 0 = retain, other int = remove
		
		System.out.println("Retain:");
		test.runTest(null, null, 0); // 0 = retain, other int = remove
		
		
		// Testing exceptions
		/*
		System.out.println("Retain:");
		test.runTest(1, 1, 0); // 0 = retain, other int = remove
		
		System.out.println("Retain:");
		test.runTest(3, 1, 0); // 0 = retain, other int = remove
		
		System.out.println("Retain:");
		test.runTest(9, 1, 0); // 0 = retain, other int = remove
		*/
	}
}
