/**
 * 
 */
package element;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * @author Justin Yeung
 * JPanel that holds elements
 */
public class ElementPanel extends JPanel implements MouseListener {

	//Frames per second
	public final static int FPS = 60;

	// How many elements
	public final static int SIZE = 20;

	private Element[][] elements; // Store all elements
	private HotPlateGUI parent;
	private Rectangle[][] cells;
	private boolean isMousePressed;

	/**
	 * 
	 */
	public ElementPanel(HotPlateGUI parent) {
		// TODO Auto-generated constructor stub
		super();
		this.parent = parent; // Reference to the HotPlateGUI
		cells = new Rectangle[SIZE][SIZE];
		elements = new Element[SIZE][SIZE];
		isMousePressed = false;
		addMouseListener(this);

		createElements(); // Create all elements
		addNeighbours(); // Add all neighbours
		startThreads(); // Start all threads
		//Thread task = new Thread(this); // Start the thread as soon as the constructor is called
		//task.start();

		// Create TimerTask for repainting this component
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// Repaint the component
				repaint();
			}

		};
		// Create Timer for repaint
		Timer timer = new Timer("Repaint Panel");

		// Schedule the timer to repeatedly repaint component
		timer.scheduleAtFixedRate(task, 0, 1000 / FPS);
	}

	private void addNeighbours() {

		for (int row = 0; row < SIZE; row++)
		{
			for (int col = 0; col < SIZE; col++)
			{
				if (row == 0) // If i is at 0 then add element to the right
				{
					elements[row][col].addNeighbour(elements[row+1][col]);
				}
				else if (row == SIZE - 1) // If i is at max size then add element to the left
				{
					elements[row][col].addNeighbour(elements[row-1][col]);
				}
				else // Default adding
				{
					elements[row][col].addNeighbour(elements[row+1][col]);
					elements[row][col].addNeighbour(elements[row-1][col]);
				}

				// Do it also for top and bottom
				if (col == 0)
				{
					elements[row][col].addNeighbour(elements[row][col+1]);
				}
				else if (col == SIZE - 1)
				{
					elements[row][col].addNeighbour(elements[row][col-1]);
				}
				else
				{
					elements[row][col].addNeighbour(elements[row][col+1]);
					elements[row][col].addNeighbour(elements[row][col-1]);
				}
			}
		}
	}

	private void createElements()
	{
		for (int i = 0; i < SIZE; i++)
		{
			for (int j = 0; j < SIZE; j++)
			{
				elements[i][j] = new Element(0);
			}
		}
		// Add all the neighbours and start the threads once all elements are created
		addNeighbours();
		startThreads();
	}

	// Get the amount of Red needed on temperature
	private int getRedColour(int row, int col) {
		// TODO Auto-generated method stub
		int redValue = 0;
		Double temperature = elements[row][col].getTemperature(); // Get the element temp
		
		if (temperature > 255)
		{
			redValue = 255; // If temperature is more than 255, red will be 255
		}
		else
		{
			redValue = temperature.intValue(); // set it
		}
		
		return redValue;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JSlider tempSlider = parent.getSlider();

		int width = getWidth();
		int height = getHeight();

		int cellWidth = width / SIZE;
		int cellHeight = height / SIZE;

		int xOffset = (width - (SIZE * cellWidth)) / 2;
		int yOffset = (height - (SIZE * cellHeight)) / 2;

		// Check if mouse is on the grid
		if (e.getX() >= xOffset && e.getY() >= yOffset) {

			// Get the row and column based on mouse coords
			int column = (e.getX() - xOffset) / cellWidth; 
			int row = (e.getY() - yOffset) / cellHeight;

			// Check if it is on a square
			if (column >= 0 && row >= 0 && column < SIZE && row < SIZE) {

				isMousePressed = true;

				// Create a thread to apply temperature
				Thread task = new Thread() {
					public void run() {
						while(isMousePressed)
						{
							// Constantly apply temperature to element while mouse is pressed
							elements[row][column].applyTemperature(tempSlider.getValue());

							try {
								Thread.sleep(1000 / FPS);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				};

				task.start();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Set boolean to false when mouse is released
		isMousePressed = false;
	}

	// Paints the cells
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Create Graphics2D object by casting from Graphics object
		Graphics2D g2d = (Graphics2D) g.create();

		// Get the width and height of the panel
		int width = getWidth();
		int height = getHeight();

		// Size of the square is determined by the panel size and number of cells
		int cellWidth = width / SIZE; 
		int cellHeight = height / SIZE;

		// Position the squares
		int xOffset = (width - (SIZE * cellWidth)) / 2;
		int yOffset = (height - (SIZE * cellHeight)) / 2;

		// This will only execute if cell array is empty
		if (cells[0][0] == null) {
			for (int row = 0; row < SIZE; row++) 
			{
				for (int col = 0; col < SIZE; col++) 
				{
					Rectangle cell = new Rectangle(
							xOffset + (col * cellWidth), // Offset increases for every col/row looped
							yOffset + (row * cellHeight),
							cellWidth,
							cellHeight);
					// Add each cell to the array
					cells[row][col] = cell;
				}
			}
		}

		// Set the colour of the square
		g2d.setColor(Color.BLUE);
		for (int row = 0; row < SIZE; row++) 
		{
			for (int col = 0; col < SIZE; col++) 
			{
				int redValue = getRedColour(row, col);
				// Fill blue colour for every square
				// Attempt to set different colour for each square depending on element temp
				// Will be put into a repeating Timer
				g2d.setColor(new Color(redValue, 0, 255 - redValue)); // Subtract blue value from red value));
				g2d.fill(cells[row][col]);
			}
		}

		// Set the colour of the outline
		g2d.setColor(Color.RED);
		// Set the line width
		g2d.setStroke(new BasicStroke(3));
		for (int row = 0; row < SIZE; row++) 
		{
			for (int col = 0; col < SIZE; col++) 
			{
				// Draw outlines for each square
				g2d.draw(cells[row][col]);
			}
		}

		// Dispose the graphics object at the end
		g2d.dispose();
	}

	private void startThreads() {

		for (int i = 0; i < SIZE; i++)
		{
			for (int j = 0; j < SIZE; j++)
			{
				Thread task = new Thread(elements[i][j]);
				task.start(); // Start the element thread
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
