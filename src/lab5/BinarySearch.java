package lab5;

import java.util.Collections;

/**
   A class which demonstrates binary search of an array
   @author Andrew Ensor
*/

public class BinarySearch<E extends Comparable>
{
   private E[] elements;
   
   public BinarySearch(E[] elements)
   {  this.elements = elements;
   }
   
   
   public int search(E target)
   {  if (target == null)
         throw new NullPointerException("search target is null");
      return search(target, 0, elements.length);
   }
   
   // recursive method which searches through the elements array
   // between start index (inclusive) and end index (exclusive) for the
   // index of specified target, or returns -(insertion)-1 if not found
   private int search(E target, int start, int end)
   {  if (start >= end)
         return -start-1; // negative value
      else
      {  int midpoint = (start+end)/2; // midpoint of search region
         int comparison = target.compareTo(elements[midpoint]);
         if (comparison == 0)
            return midpoint;
         else if (comparison < 0)
            return search(target, start, midpoint);
         else // comparison > 0
            return search(target, midpoint+1, end);
      }
   }
   
   public static void main(String[] args)
   {  //String[] list = {"ant", "bat", "cat", "cow", "dog", "eel",
      //   "fly", "fox", "owl", "pig", "rat"};
	   
	  Integer[] list = new Integer[10000000];
	  for (int i = 0; i < 10000000; i++)
	  {
		  list[i] = i+1;
	  }
	  
      BinarySearch<Integer> bin = new BinarySearch<Integer>(list);
      Integer target = 50000;
      int index = bin.search(target);
      if (index >= 0)
         System.out.println(target + " found at index " + index);
      else
         System.out.println(target + " not at index " + (-index-1));
   }
}
