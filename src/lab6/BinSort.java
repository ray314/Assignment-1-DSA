package lab6;

import java.util.Random;

public class BinSort {

	private final int range = 50;
	private int[] bins;
	// int[] bins
	private int[] list;
	
	public BinSort(int[] list)
	{
		this.list = list;
		bins = new int[range + 1];
		
		for (int i = 0; i < range; i++)
		{
			bins[i] = 0;
		}
	}
	
	public void sort()
	{
		
		for (int i = 0; i < list.length; i++)
		{
			int binToSet = list[i];
		    bins[binToSet]++;
		}
		
		
		int count = 0;
		
		for (int binNumber = 0; binNumber < bins.length; binNumber++)
		{
			int timesToAdd = bins[binNumber];
			
			for (int i = 0; i < timesToAdd; i++)
			{
				list[count] = binNumber;
				count++;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numbers = {17, 2, 23, 7, 41, 29};
		Random rand = new Random();
		BinSort sort = new BinSort(numbers);
		
		//for (int i = 0; i < numbers.length; i++)
		//{
		//	numbers[i] = new Integer(numbers.length - i);
		//}
		
		sort.sort();
		
		for (int i = 0; i < numbers.length; i++)
		{
			System.out.print(numbers[i] + ", ");
		}
	}

}
