/**
 * 
 */
package lab9;

/**
 * @author fbb3628
 *
 */
public class HashTable<E> {

	//private Student[] table
	private final int INITIAL_CAPACITY = 5;
	protected Node<E>[] table;
	private int numElements;
	/**
	 * 
	 */
	public HashTable() {
		// TODO Auto-generated constructor stub
		table = new Node[INITIAL_CAPACITY];
		numElements = 0;
	}
	
	private void expandCapacity()
	{
		
		Node<E>[] oldTable = table; 
				
		table = new Node[table.length * 2];
		numElements = 0;
		for (Node<E> currentNode : oldTable)
		{
			while(currentNode != null)
			{
				add(currentNode.element);
				
				//int index = currentNode.element.hashCode() % table.length;
				//table[index] = currentNode;
				currentNode = currentNode.next;
			}
		}
	}
	
	public boolean contains(E o)
	{
		int index = Math.abs(o.hashCode() % table.length);
		Node<E> currentNode = table[index];
		
		// If current node's element is not equal, move to next node
		while (currentNode != null)
		{
			if (currentNode.element.equals(o))
			{
				return true;
			}
			// Move to next node
			currentNode = currentNode.next;
		}
		// Not found
		return false;
	}
	
	public void add(E element)
	{
		int index = Math.abs(element.hashCode() % table.length);
		Node<E> newNode = new Node<E>(element);
		
		// Expand capacity if numElements is atleast 75% of table length
		if (numElements >= table.length * 0.75)
		{
			expandCapacity();
		}
		
		// If table entry is null then add the node
		if (table[index] == null)
		{
			table[index] = newNode;
			numElements++;
		}
		else // Collision, add node to next node instead
		{
			// Loop through the nodes until it finds a null position
			while (table[index].next != null)
			{
				table[index] = table[index].next;
			}
			table[index].next = newNode;
			numElements++;
		}
	}
	
	public boolean remove(E element)
	{
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTable<Student> table = new HashTable<Student>();
		Student[] student = new Student[9];
		
		
		student[0] = new Student("Apple");
		student[1] = new Student("Banana");
		student[2] = new Student("Test");
		student[3] = new Student("Lemon");
		student[4] = new Student("Orange");
		student[5] = new Student("Persimmon");
		student[6] = new Student("The");
		student[7] = new Student("Quack");
		student[8] = new Student("Node");
		
		for (int i = 0; i < student.length; i++)
		{
			table.add(student[i]);
		}
		
		//System.out.println(table.contains("C"));
	}

}
