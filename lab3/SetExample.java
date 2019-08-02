package lab3;

/**
   A simple class which demonstrates how a set can be used
*/
import java.util.Set;

public class SetExample
{
   public static void main(String[] args)
   {  Set<String> bookTitles = new ArraySet<String>();
      bookTitles.add("Java Software Structures");
      bookTitles.add("Computer Graphics Using OpenGL");
      bookTitles.add("Java Software Structures");
      bookTitles.add("Introduction to Algorithms");
      bookTitles.add(null);
      bookTitles.remove("Computer Graphics Using OpenGL");
      System.out.println(bookTitles);
   }
}
