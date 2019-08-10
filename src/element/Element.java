/**
 * 
 */
package element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Justin Yeung
 *
 */
public class Element implements Runnable {

	private List<Element> neighbours;
	private double currentTemp;
	public static double heatConstant = 0.1;
	private boolean stopRequested;
	public String name;

	public final static double EPSILON = 0.01; // For comparing two doubles

	public Element(double currentTemp)
	{
		this.currentTemp = currentTemp;
		stopRequested = false;
		neighbours = new ArrayList<Element>();
	}

	public Element(double currentTemp, String name)
	{
		this(currentTemp);
		this.name = name; // Set name of element
	}

	public void start()
	{
		Thread task = new Thread(this);
		task.start();
	}

	public double getTemperature()
	{
		return currentTemp;
	}

	// Stop the element thread
	public void requestStop()
	{
		stopRequested = true;
	}

	// Add neighbouring element
	public void addNeighbour(Element element)
	{
		neighbours.add(element);
	}

	// Apply temperature to element
	public void applyTemperature(double appliedTemp)
	{
		currentTemp += (appliedTemp - currentTemp) * heatConstant;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		double averageTemps = 0;

		while (!stopRequested)
		{
			// Synchronized thread
			synchronized (this)
			{
				for (int i = 0; i < neighbours.size(); i++)
				{

					averageTemps += neighbours.get(i).getTemperature();
				}
				averageTemps /= neighbours.size() + 1; // Divide by the number of elements including itself
			}

			currentTemp += (averageTemps - currentTemp) * heatConstant;

			try {
				Thread.sleep(1000 / ElementPanel.FPS);
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Element element1 = new Element(300, "Element A");
		Element element2 = new Element(0, "Element B");

		element1.addNeighbour(element2); // Need to add to neighbor list itself
		element2.addNeighbour(element1);	

		element1.start();
		element2.start();

		double eTemp[] = new double[2];
		eTemp[0] = element1.getTemperature();
		eTemp[1] = element2.getTemperature();

		//element2.applyTemperature(500);

		while ((Math.abs(eTemp[0] - eTemp[1]) > Element.EPSILON))
		{
			eTemp[0] = element1.getTemperature();
			eTemp[1] = element2.getTemperature();
			System.out.println(element1.name + " temperature: " + eTemp[0]);
			System.out.println(element2.name + " temperature: " + eTemp[1]);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		element1.requestStop();
		element2.requestStop(); 
		
		System.out.println("Temperatures are now equal");
	}
}
