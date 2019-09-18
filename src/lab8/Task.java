/**
 * 
 */
package lab8;

import java.util.Comparator;

/**
 * @author fbb3628
 *
 */
public class Task<E> implements Comparator<Task<E>>{

	private E element;
	private int priority;
	
	public Task()
	{
		
	}
	
	public Task(E element, int priority)
	{
		this.element = element;
		this.priority = priority;
	}
	
	public int getPriority()
	{
		return priority;
	}
	
	@Override
	public String toString()
	{
		return element.toString() + " (" + priority + ")";
	}

	@Override
	public int compare(Task<E> t1, Task<E> t2) {
		// TODO Auto-generated method stub
		return t1.priority - t2.priority;
	}
}
