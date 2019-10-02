package lab9;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

public class StudentsGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Hashtable<String,StudentCustom> table;
	private JTextPane txtpnTextpane;
	private JButton btnDisplayStudents;
	private JScrollPane scrollPane;
	private JButton btnFindStudent;
	private JPanel southPanel;
	private JTextField txtStudentid;
	private JButton btnEnrolStudent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentsGUI frame = new StudentsGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if (source == btnDisplayStudents)
		{
			displayAllStudents();
		}
		else if (source == btnFindStudent)
		{
			findStudent();
		}
		else if (source == btnEnrolStudent)
		{
			enrolStudent();
		}
	}
	
	private void enrolStudent()
	{
		CreateStudent dialog = new CreateStudent(this);
		dialog.setVisible(true);
	}
	
	private void findStudent()
	{
		String key = txtStudentid.getText();
		
		if (key.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Field is empty", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (table.containsKey(key))
		{
			JOptionPane.showMessageDialog(this, table.get(key), "Found", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Student not found", "Not found", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void displayAllStudents()
	{
		Enumeration<StudentCustom> list = table.elements();
		String text = new String();
		while (list.hasMoreElements())
		{
			text += list.nextElement().toString();
		}
		
		txtpnTextpane.setText(text);
	}

	private void setStudents()
	{
		table.put("STUD1111", new StudentCustom("Apple", 20, "STUD1111"));
		table.put("STUD1112", new StudentCustom("Banana", 15, "STUD1112"));
		table.put("STUD1113", new StudentCustom("Test", 22, "STUD1113"));
		table.put("STUD1114", new StudentCustom("Lemon", 14, "STUD1114"));
		table.put("STUD1115", new StudentCustom("Orange", 10, "STUD1115"));
		table.put("STUD1116", new StudentCustom("Persimmon", 21, "STUD1116"));
		table.put("STUD1117", new StudentCustom("The", 12, "STUD1117"));
		table.put("STUD1118", new StudentCustom("Quack", 13, "STUD1118"));
		table.put("STUD1119", new StudentCustom("Node", 16, "STUD1119"));
	}
	/**
	 * Create the frame.
	 */
	public StudentsGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		txtpnTextpane = new JTextPane();
		txtpnTextpane.setEditable(false);
		txtpnTextpane.setText("textPane");
		contentPane.add(txtpnTextpane, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane(txtpnTextpane);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		
		btnEnrolStudent = new JButton("Enrol Student");
		southPanel.add(btnEnrolStudent);
		btnEnrolStudent.addActionListener(this);
		
		btnDisplayStudents = new JButton("Display Students");
		southPanel.add(btnDisplayStudents);
		
		btnFindStudent = new JButton("Find Student");
		southPanel.add(btnFindStudent);
		
		txtStudentid = new JTextField();
		southPanel.add(txtStudentid);
		txtStudentid.setColumns(10);
		btnFindStudent.addActionListener(this);
		btnDisplayStudents.addActionListener(this);
		
		table = new Hashtable<String, StudentCustom>();
		setStudents();
	}

	
	private class StudentCustom extends Student
	{
		private String id;
		
		public StudentCustom(String name, int age, String id) {
			super(name, age);
			this.id = id;
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public int hashCode()
		{
			return id.hashCode();
		}
		
		@Override
		public String toString()
		{
			String originText = super.toString();
			
			return String.format("Student ID: %s\n", id) + originText;
		}
	}
}
