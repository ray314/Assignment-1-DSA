/**
 * 
 */
package lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lab3.TimeTest;
import lab4.ArrayQueue;
import lab4.QueueADT;

/**
 * @author Justin Yeung
 *
 */
public class JosephusQueue {

	public int josephus(int n, int k)
	{
		if (n == 1)
			return 1;
		else
		{
			return (josephus(n-1, k) + k-1) % n + 1;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numPeople, gap, counter;
	      QueueADT<Integer> queue = new ArrayQueue<Integer>();
	      Scanner in = new Scanner(System.in);
	      TimeTest time = new TimeTest();
	      JosephusQueue test = new JosephusQueue();
	      
	      System.out.println(test.josephus(100, 20));
	      
	      
	      // get the initial number of soldiers
	      System.out.print("Enter the number of soldiers: ");
	      numPeople = in.nextInt();
	      in.nextLine();
	      // get the gap between soldiers
	      System.out.print("Enter the gap between soldiers: ");
	      gap = in.nextInt();
	      // load the initial list of soldiers
	      // note that index i holds soldier i+1
	      for (int count=1; count<=numPeople; count++)
	         queue.enqueue(new Integer(count));
	      counter = gap-1; // first soldier to remove in list
	      // treating the list as circular, remove every nth element
	      // until the list is empty
	      Integer element;
	      
	      System.out.print("The order is: ");
	      time.start();
	      
	      // Start dequeueing
	      while (!queue.isEmpty())
	      {
	    	  // The gap between soldiers
	    	  for (int i = 0; i < counter; i++)
	    	  {
	    		  element = queue.dequeue(); // 
	    		  queue.enqueue(element);
	    	  }
	    	  
	    	  if (queue.size() > 0)
	    	  {
	    		  System.out.print(queue.dequeue());
		    	  System.out.print(", ");
	    	  }
	    	  
	    	  //break;
	      }
	      time.end();
	      System.out.println();
	      time.printTime();
	      
	}

}
