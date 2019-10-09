package lab11;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.JTextArea;

public class ShowTablesGUI extends JFrame implements ListSelectionListener{

	private JPanel contentPane;
	private JTable table;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://raptor2:3306/testUnrestricted";
	DefaultListModel<String> listModel;
	private JTextArea textArea;
	private Connection conn;
	private JList<String> list;
	private JScrollPane scrollPane;
	private JScrollPane listScrollPane;
	private JSplitPane splitPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
				{
					@Override
					public void run() {
						// TODO Auto-generated method stub
						// obtain user name and password from keyboard
						Scanner keyboardScanner = new Scanner(System.in);
						System.out.print("Please enter user name:");
						String userName = keyboardScanner.nextLine();
						System.out.print("Please enter password:");
						String password = keyboardScanner.nextLine();
						
						try
						{  Class.forName(DRIVER); // load the database driver for MySQL
						System.out.println
						("Trying to open connection to raptor/universitydb");
						final Connection con
						= DriverManager.getConnection(DB_URL, userName, password);
						// create statement that will be sensitive to database changes
						// if supported by database
						final Statement stmt = con.createStatement
								(ResultSet.TYPE_SCROLL_SENSITIVE,
										ResultSet.CONCUR_UPDATABLE);
						// obtain sample result set holding contents of Students table
						String command = "SELECT * FROM Courses"; // SQL command
						ResultSet rs = stmt.executeQuery(command);
						// prepare a ResultSetDisplayer in a JFrame
						ShowTablesGUI frame = new ShowTablesGUI(rs, con);
						
						Statement stmt2 = con.createStatement(); 
						command = "SHOW TABLES";
						rs = stmt2.executeQuery(command);
						frame.loadTableList(rs);
						
						frame.addWindowListener(new WindowAdapter()
						{  
							public void windowClosing(WindowEvent we)

							{  // close the database connection when frame closes
								System.out.println("Closing connection to raptor");
								try
								{  
									if (stmt != null) 
										{
											stmt.close();
											stmt2.close();
										}

									if (con != null) con.close();
									System.exit(0);
								}
								catch (SQLException e)
								{  System.out.println("SQL Exception:" + e);
								}
							}
						});
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
	public ShowTablesGUI(ResultSet rs, Connection conn) {
		this.conn = conn;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		listModel = new DefaultListModel<String>();
		
		TableModel model = new ResultSetTableModel(rs);
		table = new JTable(model);
		
		
		scrollPane = new JScrollPane(table);
		
		list = new JList<String>(listModel);
		list.addListSelectionListener(this);
		listScrollPane = new JScrollPane(list);
		
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		contentPane.add(textArea, BorderLayout.SOUTH);
		
		splitPane = new JSplitPane();
		splitPane.setLeftComponent(listScrollPane);
		splitPane.setRightComponent(scrollPane);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		String text = new String();
		String text2 = text;
	}

	public void loadTableList(ResultSet rs)
	{
		try {
			while (rs.next())
			{
				String tablename = rs.getString(1);
				listModel.addElement(tablename);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (!e.getValueIsAdjusting())
		{
			try {
				Statement stmt = conn.createStatement();
				String selected = list.getSelectedValue();
				String command = String.format("SELECT * FROM %s", selected);
				
				System.out.println(command);
				ResultSet rs = stmt.executeQuery(command);

				ResultSetTableModel newModel = new ResultSetTableModel(rs);
				table.setModel(newModel);
				
				//stmt.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	/** 
    An inner class that is the table data model for an updateable 
    result set from a database query
	 */
	private class ResultSetTableModel extends AbstractTableModel
	{  
		private ResultSet rs;
		private ResultSetMetaData rsmd;

		public ResultSetTableModel(ResultSet rs)
		{  this.rs = rs;
		try
		{  rsmd = rs.getMetaData();
		}
		catch (SQLException e)
		{  System.out.println(e);
		}
		}

		public String getColumnName(int col)
		{  try
		{  return rsmd.getColumnName(col + 1);
		}
		catch (SQLException e)
		{  System.out.println(e);
		return "";
		}
		}

		public int getColumnCount()
		{  try
		{  return rsmd.getColumnCount();
		}
		catch (SQLException e)
		{  System.out.println(e);
		return 0;
		}
		}

		public int getRowCount()
		{  try
		{  rs.last();
		return rs.getRow();
		}
		catch(SQLException e)
		{  System.out.println(e);
		return 0;
		}
		}

		public Object getValueAt(int row, int col)
		{  try // use scrolling cursor to retrieve value from result set
		{  rs.absolute(row + 1); // position cursor at the row
		return rs.getObject(col + 1);
		}
		catch(SQLException e)
		{  System.out.println(e);
		return null;
		}
		}

		public boolean isCellEditable(int row, int col)
		{  return true; // all cells are presumed editable
		}

		public void setValueAt(Object obj, int row, int col)
		{  try // use scrolling cursor to update value in result set
		{  rs.absolute(row + 1); // position cursor at the row
		rs.updateObject(col + 1, obj); // updates rowset
		rs.updateRow(); // updates database
		}
		catch(SQLException e)
		{  System.out.println(e);
		}
		}
	}
}
