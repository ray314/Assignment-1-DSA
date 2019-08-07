package lab4;

/**
   A class that implements a stack collection using an
   array as the underlying data structure
   @author Andrew Ensor
*/
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Random;

// Inefficient
public class ArrayStackInef<E> implements StackADT<E>
{
   private final int INITIAL_CAPACITY = 20;
   protected int numElements;
   protected E[] elements;
   
   // default constructor that creates a new stack 
   // that is initially empty
   public ArrayStackInef()
   {  numElements = 0;
      elements = (E[])(new Object[INITIAL_CAPACITY]); // unchecked
   }
   
   // constructor for creating a new stack which
   // initially holds the elements in the collection c
   public ArrayStackInef(Collection<? extends E> c)
   {  this();
      for (E element : c)
         push(element);
   }

   // Adds one element to the top of this stack
   public void push(E element)
   {  
	   if (numElements >= elements.length - 1)
		   expandCapacity();
   
   	  
   	  // Shift array to the right
   	  for (int i = numElements; i >= 0; i--)
   	  {
   		  
   		  elements[i + 1] = elements[i];
   	  }
   	  numElements++;
   	  
      elements[0] = element;
   }
   
   // removes and returns the top element from this stack
   public E pop() throws NoSuchElementException
   {  
	   if (numElements > 0)
	   { 
		   E topElement = elements[0];
		   elements[0] = null;
		   numElements--;

		   // Shift array to the left
		   for (int i = 0; i < numElements; i++)
		   {

			   elements[i] = elements[i + 1];
		   }
		   return topElement;
	   }
	   else
		   throw new NoSuchElementException();
   }
   
   // returns without removing the top element of this stack
   public E peek() throws NoSuchElementException
   {  if (numElements > 0)
         return elements[numElements-1];
      else
         throw new NoSuchElementException();
   }
   
   // returns true if this stack contains no elements
   public boolean isEmpty()
   {  return (numElements==0);
   }
   
   // returns the number of elements in this stack
   public int size()
   {  return numElements;
   }
   
   // returns a string representation of the stack from top to bottom
   public String toString()
   {  String output = "[";
      for (int i=numElements-1; i>=0; i--)
      {  output += elements[i];
         if (i>0)
            output += ",";
      }
      output += "]";
      return output;
   }
      
   // helper method which doubles the current size of the array
   private void expandCapacity()
   {  E[] largerArray =(E[])(new Object[elements.length*2]);//unchecked
      // copy the elements array to the largerArray
      for (int i=0; i<numElements; i++)
         largerArray[i] = elements[i];
      elements = largerArray;
   }
   
   public static void main(String[] args)
   {
	   ArrayStackInef<Integer> list = new ArrayStackInef<Integer>();
	   ArrayStack<Integer> eList = new ArrayStack<Integer>(); 
	   Random rand = new Random();
	   int size = 100000;
	   
	   // Efficient
	   long startTime = System.currentTimeMillis();
	   
	   for (int i = 0; i < size; i++)
	   {
		   eList.push(i);
	   }
	   
	   long elapsedTime = System.currentTimeMillis();
	   System.out.println("Efficent array stack push (milliseconds): " + (elapsedTime - startTime));
	   
	   // Inefficient
	   startTime = System.currentTimeMillis();
	   
	   for (int i = 0; i < size; i++)
	   {
		   list.push(i);
	   }
	   
	   
	   elapsedTime = System.currentTimeMillis();
	   System.out.println("Inefficent array stack push (milliseconds): " + (elapsedTime - startTime));
	   
	   
	   // Efficient
	   startTime = System.currentTimeMillis();
	   
	   for (int i = 0; i < size; i++)
	   {
		   eList.pop();
	   }
	   
	   elapsedTime = System.currentTimeMillis();
	   System.out.println("Efficent array stack pop (milliseconds): " + (elapsedTime - startTime));
	   
	   // Inefficient
	   startTime = System.currentTimeMillis();
	   
	   for (int i = 0; i < size; i++)
	   {
		   list.pop();
	   }
	   
	   
	   elapsedTime = System.currentTimeMillis();
	   System.out.println("Inefficent array stack pop (milliseconds): " + (elapsedTime - startTime));
	   
   }
}
