package lab3;

/**
   A class that demonstrates the problems with using unsynchronized
   add and remove operations of an ArraySet when there are multiple
   threads manipulating the elements
   @author Andrew Ensor
*/
import java.util.Set;

public class ArraySetJumbler implements Runnable
{
   private Set<Integer> set;
   
   public ArraySetJumbler()
   {  set = new ArraySet<Integer>();
      Thread threadA = new Thread(this);
      Thread threadB = new Thread(this);
      threadA.start();
      threadB.start();
   }
   
   // method which continually adds and removes a specific integer
   @Override
   public void run()
   {  while (true) // infinite loop
      {  set.add(new Integer(1));
         set.remove(new Integer(1));
      }
   }

   public static void main(String[] args)
   {  new ArraySetJumbler();      
   }
}
