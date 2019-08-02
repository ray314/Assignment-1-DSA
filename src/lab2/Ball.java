/**
 * 
 */
package lab2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

/**
 * @author fbb3628
 *
 */
public class Ball implements Runnable{

	private int x;
	private int y;
	
	private int radius;
	private Color colour;
	
	private int dx;
	private int dy;
	
	private int worldW;
	private int worldH;
	private boolean stop;
	
	private Random rand;
	
	private final int UPDATE_RATE = 60; // 60 frames per second 
	private JPanel box;
	
	public Ball(int worldW, int worldH)
	{
		//this.box = box;
		rand = new Random();
		
		this.worldW = worldW;
		this.worldH = worldH;
		radius = rand.nextInt(50) + 50;
		x = rand.nextInt(worldW);
		y = rand.nextInt(worldH);
		colour = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
		
		dx = rand.nextInt(6) + 3;
		dy = rand.nextInt(6) + 3;
		
		stop = false;
		
		//Thread ballThread = new Thread(this);
		//ballThread.start();
	}
	
/*	private void drawBall()
	{	
		Graphics g = box.getGraphics();
		//g.drawOval(x, y, radius * 2, radius * 2);
		g.setColor(colour);
		g.fillOval(x, y, radius, radius);
		g.dispose();
	}*/
	
	public void updateBallCoords()
	{
		// Move the ball
		x += dx;
		y += dy;
		//g.setXORMode(box.getBackground());

		if (x + radius >= worldW)
		{
			x = worldW - radius;
			dx = -dx;
		}
		else if (y + radius >= worldH)
		{
			y = worldH - radius;
			dy = -dy;
		}
		else if (x < 0)
		{
			x = 0;
			dx = -dx;
		}
		else if (y < 0)
		{
			y = 0;
			dy = -dy;
		}
		//g.fillOval(x, y, radius, radius);
		//box.repaint();
	}
	
	public Color getColour()
	{
		return colour;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getRadius()
	{
		return radius;
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
			
			updateBallCoords();
			try {
				Thread.sleep(1000 / UPDATE_RATE);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
