/**
 * 
 */
package lab8;

import java.util.Comparator;

/**
 * @author fbb3628
 *
 */
public class PriorityQueue<E> {

	private ArrayHeap<Task<E>> taskHeap;
	/**
	 * 
	 */
	public PriorityQueue() {
		// TODO Auto-generated constructor stub
		
		taskHeap = new ArrayHeap<Task<E>>(new Task<E>());
	}
	
	public void enqueue(Task<E> task)
	{
		taskHeap.add(task);
	}
	
	public Task<E> dequeueMin()
	{
		return taskHeap.removeMin();
	}

	public Task<E> findMin()
	{
		return taskHeap.getMin();
	}
}
