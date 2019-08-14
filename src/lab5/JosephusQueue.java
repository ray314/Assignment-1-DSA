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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numPeople, gap, counter;
	      QueueADT<Integer> queue = new ArrayQueue<Integer>();
	      Scanner in = new Scanner(System.in);
	      TimeTest time = new TimeTest();
	      
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
	      
	      System.out.print("The order is: ");
	      time.start();
	      
	      // Start dequeueing
	      while (!queue.isEmpty())
	      {
	    	  // The gap between soldiers
	    	  for (int i = 0; i < counter; i++)
	    	  {
	    		  Integer element = queue.dequeue(); // 
	    		  queue.enqueue(element);
	    	  }
	    	  
	    	  numPeople--;
	    	  if (numPeople > 0)
	    	  {
	    		  System.out.print(queue.dequeue());
		    	  System.out.print(", ");
	    	  }
	    	  
	    	  //break;
	      }
	      time.end();
	      time.printTime();
	      
	}

}
