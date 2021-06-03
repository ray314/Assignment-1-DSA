/**
 * 
 */
package lab1;

/**
 * @author Justin Yeung
 *
 */
public class SelectionSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] numbers = { 3, 1, 5 ,6 , 9, 8, 2 };
		int length = numbers.length;
		int smallest = 0;
		int smallestIndex = 0;
		int largest = 0;

		for (int i = 0; i < length - 1; i++)
		{
			smallest = numbers[i];
			smallestIndex = i;

			for (int i2 = i + 1; i2 < length; i2++)
			{
				// If smallestIndex is > than i + 1
				if (numbers[smallestIndex] > numbers[i2])
				{
					smallestIndex = i2;
					smallest = numbers[i2];
				}
			}
			largest = numbers[i];

			// Perform swap
			numbers[i] = numbers[smallestIndex];
			numbers[smallestIndex] = largest;

		}

		for (int i = 0; i < length; i++)
		{
			System.out.print(numbers[i] + ", ");

		}
		
	}

}
