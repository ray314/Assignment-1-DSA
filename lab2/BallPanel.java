/**
 * 
 */
package lab2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Justin Yeung
 *
 */
public class BallPanel extends JPanel implements Runnable {

	// Create linked list
	private LinkedList<Ball> balls;
	private boolean stop = false;
	private final int UPDATE_RATE = 60;
	
	private JFrame parent;
	
	public BallPanel(JFrame parent)
	{
		this.parent = parent;
		balls = new LinkedList<Ball>();
	}
	
	public void createBall(int x, int y)
	{
		balls.add(new Ball(x, y));
		
		Thread task = new Thread(balls.getLast());
		task.start();
	}
	
	/**
	 * Deletes all balls in the list
	 */
	public void clear()
	{
		if (!balls.isEmpty()) // List must not be empty
		{
			Iterator<Ball> it = balls.iterator();
			
			while (it.hasNext()) // Stop all threads
			{
				it.next().requestStop();
			}
			balls.clear(); // Removes all balls
		}
	}
	
	/**
	 * Deletes the previous ball created
	 */
	public void deleteBall()
	{
		if (balls.isEmpty())
		{
			JOptionPane.showMessageDialog(parent, "There are no balls", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			Ball ball = balls.getLast();
			
			ball.requestStop(); // Stop the thread
			balls.removeLast();
		}
	}
	
	public void moveBall()
	{
		repaint();
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(1280, 680);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		for (Ball ball : balls)
		{
			g.setColor(ball.getColour());
			g.fillOval(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
		}
	}

	public void requestStop()
	{
		stop = true;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!stop)
		{
			moveBall();
			try {
				Thread.sleep(1000 / UPDATE_RATE);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
