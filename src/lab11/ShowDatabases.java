package lab11;

/**
   A class that demonstrates how to obtain a list of
   databases available on a database server
   @author Andrew Ensor
*/
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner; 

public class ShowDatabases
{
   private static final String DRIVER="com.mysql.jdbc.Driver";
   private static final String DB_URL="jdbc:mysql://raptor2.aut.ac.nz:3306/";

   private static Connection getConnection(String userName,
      String password)
      throws SQLException, ClassNotFoundException, IOException
   {  // load the driver
      Class.forName(DRIVER);
      return DriverManager.getConnection(DB_URL, userName, password);
   }

   public static void main(String[] args)
   {  // obtain user name and password from keyboard
      Scanner keyboardScanner = new Scanner(System.in);
      System.out.print("Please enter user name:");
      String userName = keyboardScanner.nextLine();
      System.out.print("Please enter password:");
      String password = keyboardScanner.nextLine();
      try
      {  System.out.println("Trying to open connection to raptor");
         Connection con = getConnection(userName, password);
         Statement stmt = con.createStatement();
         // obtain result set holding names of current databases
         System.out.println("Listing databases on raptor");
         String command = "SHOW DATABASES"; // SQL command
         ResultSet rs = stmt.executeQuery(command);
         while (rs.next())
         {  // database name string is in first column of result set
            System.out.println(rs.getString(1));
         }
         System.out.println("Closing connection to raptor");
         stmt.close();
         con.close();
      }
      catch (SQLException e)
      {  System.out.println("SQL Exception:" + e);
      }
      catch (ClassNotFoundException e)
      {  System.out.println("ClassNotFoundException:" + e);
      }
      catch (IOException e)
      {  System.out.println("IOException:" + e);
      }
   }
}
