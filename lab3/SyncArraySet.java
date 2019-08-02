package lab3;

/**
   A class which extends the ArraySet class so that its
   add, remove, and clear methods are synchronized
   @author Andrew Ensor
*/
import java.util.Collection;

public class SyncArraySet<E> extends ArraySet<E>
{
   public SyncArraySet()
   {  super();
   }
   
   public SyncArraySet(Collection<? extends E> c)
   {  super(c);
   }
   
   @Override
   public synchronized boolean add(E o)
   {  return super.add(o);
   }

   @Override
   public synchronized boolean remove(Object o)
   {  return super.remove(o);
   }

   @Override
   public synchronized void clear()
   {  super.clear();
   }
}
