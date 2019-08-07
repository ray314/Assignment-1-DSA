/**
 * 
 */
package lab4;

import java.util.NoSuchElementException;

/**
 * @author fbb3628
 *
 */
public class ArrayQueue<E> implements QueueADT<E> {

	private int numElements; // Number of elements
	private E[] elements; // Array that holds elements
	private final int INITIAL_CAPACITY = 5; // Initial capacity
	private int front;
	private int rear;
	
	@SuppressWarnings("unchecked")
	public ArrayQueue()
	{
		numElements = 0;
		front = 0;
		rear = 0;
		elements =  (E[]) new Object[INITIAL_CAPACITY]; // Give the initial capacity
	}
	
	private void expandCapacity()
	{
		@SuppressWarnings("unchecked")
		E[] largerArray =(E[])(new Object[elements.length*2]);//unchecked
		// copy the elements array to the largerArray
		for (int i=0; i<numElements; i++)
		{
			largerArray[i] = elements[front];
			front++;
		}

		front = 0;
		rear = numElements;
		elements = largerArray;
	}
	
	@Override
	public void enqueue(E element) {
		// TODO Auto-generated method stub
		if (numElements >= elements.length)
		{
			expandCapacity();
		}
		
		
		elements[rear++] = element; // Add element to queue
		
		numElements++;
		
		if (rear >= elements.length)
		{
			rear = 0;
		}
	}
	
	@Override
	public E dequeue() throws NoSuchElementException {
		// TODO Work on dequeue, front variable
		if (numElements > 0)
		{
			E element = elements[front];
			elements[front] = null;
			front++;
			return element;
		}

		else
		{
			throw new NoSuchElementException();
		}
		//front--;
		//return null;
	}

	@Override
	public E first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (numElements > 0)
		{
			return elements[front];
		}

		else
		{
			throw new NoSuchElementException();
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>();

		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
				
	}


}
