package lab11;

/**
   A class that demonstrates how a JTable can be used to display and 
   edit a ResultSet from a database and updates the database
   @author Andrew Ensor
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner; // Java 1.5 equivalent of cs1.Keyboard
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.WindowConstants;

public class ResultSetDisplayer extends JPanel
{
   private static final String DRIVER = "com.mysql.jdbc.Driver";
   private static final String DB_URL 
      = "jdbc:mysql://raptor2:3306/testUnrestricted";
   
   public ResultSetDisplayer(ResultSet rs)
   {  super(new BorderLayout());
      TableModel tableModel = new ResultSetTableModel(rs);
      JTable table = new JTable(tableModel);
      add(new JScrollPane(table), BorderLayout.CENTER);
   }
   
   public static void main(String[] args)
   {  // obtain user name and password from keyboard
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
         JFrame frame = new JFrame("ResultSetDisplayer");
         frame.setDefaultCloseOperation
            (WindowConstants.DISPOSE_ON_CLOSE);
         frame.addWindowListener(new WindowAdapter()
            {  public void windowClosing(WindowEvent we)
               {  // close the database connection when frame closes
                  System.out.println("Closing connection to raptor");
                  try
                  {  if (stmt != null) stmt.close();
                     if (con != null) con.close();
                     System.exit(0);
                  }
                  catch (SQLException e)
                  {  System.out.println("SQL Exception:" + e);
                  }
               }
            });
         frame.getContentPane().add(new ResultSetDisplayer(rs));
         frame.pack();
         // position the frame in the middle of the screen
         Toolkit tk = Toolkit.getDefaultToolkit();
         Dimension screenDimension = tk.getScreenSize();
         Dimension frameDimension = frame.getSize();
         frame.setLocation((screenDimension.width-frameDimension.width)
            /2, (screenDimension.height-frameDimension.height)/2);
         frame.setVisible(true);
      }
      catch (SQLException e)
      {  System.out.println("SQL Exception:" + e);
      }
      catch (ClassNotFoundException e)
      {  System.out.println("ClassNotFoundException:" + e);
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
