package lab2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BallGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	//private LinkedList<Ball> ballList;
	private BallPanel ballPanel;
	private JPanel buttonPanel;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnExit;
	private JButton btnClear;
	
	//Ball b = new Ball(ballPanel, 1280, 680);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BallGUI frame = new BallGUI();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public BallGUI() {
		//ballList = new LinkedList<Ball>();
		//ballList.add(new Ball(1280, 680));
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		ballPanel = new BallPanel(this);
		contentPane.add(ballPanel, BorderLayout.CENTER);
		ballPanel.setLayout(null);
		
		buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		btnAdd = new JButton("Add ball");
		btnAdd.addActionListener(this);
		buttonPanel.add(btnAdd);
		
		btnDelete = new JButton("Delete ball");
		btnDelete.addActionListener(this);
		buttonPanel.add(btnDelete);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		
		btnClear = new JButton("Clear");
		buttonPanel.add(btnClear);
		btnClear.addActionListener(this);
		buttonPanel.add(btnExit);
	
		Thread t1 = new Thread(ballPanel);
		t1.start();
		
		//ballList.add(new Ball(1280, 680));
		//Ball ball = ballList.getLast();
		//ballPanel.add(ball);
		
		//Thread task = new Thread(ball);
		//task.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		
		if (source == btnAdd)
		{
			//ballList.add(new Ball(1280, 680));
			//ballPanel.repaint();
			//Ball ball = ballList.getLast();
			
			ballPanel.createBall(1280, 680);
			//System.out.println("Test");
			
		}
		else if (source == btnDelete)
		{
			ballPanel.deleteBall();
		}
		else if (source == btnClear) 
		{
			ballPanel.clear();
		}
		else if (source == btnExit)
		{
			ballPanel.clear();
			dispose();
		}
	}
}
