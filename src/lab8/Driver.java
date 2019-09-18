package lab8;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityQueue<String> queue = new PriorityQueue<String>();
		
		queue.enqueue(new Task<String>("Cat", 9));
		queue.enqueue(new Task<String>("Dog", 4));
		queue.enqueue(new Task<String>("Apple", 2));
		queue.enqueue(new Task<String>("Test", 7));
		queue.enqueue(new Task<String>("Frog", 8));
		queue.enqueue(new Task<String>("Anteater", 6));
		queue.enqueue(new Task<String>("Banana", 1));
		queue.enqueue(new Task<String>("Pineapple", 3));
		queue.enqueue(new Task<String>("Orange", 5));
		
		for (int i = 0; i < 9; i++)
		{
			System.out.println(queue.dequeueMin());
		}
		
	}

}
