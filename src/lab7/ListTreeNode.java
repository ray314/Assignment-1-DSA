package lab7;
/**
   A class that implements a tree node using a list
   to store references to the child nodes
   @author Andrew Ensor
*/
import java.util.ArrayList;
import java.util.Enumeration; // old version of an Iterator
import java.util.Iterator;
import java.util.List;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class ListTreeNode<E> implements MutableTreeNode
{
   private E element;
   private MutableTreeNode parent;
   private List<MutableTreeNode> children;
   
   public ListTreeNode()
   {  this(null);
   }
   
   public ListTreeNode(E element)
   {  this.element = element;
      parent = null;
      children = new ArrayList<MutableTreeNode>();//no initial children
   }
   
   // returns an Enumeration of the child nodes
   public Enumeration<E> children()
   {  return (Enumeration<E>)
         (new Enumerator(children.iterator())); // unchecked
   }
   
   // returns that this node allows children
   public boolean getAllowsChildren()
   {  return true;
   }
   
   // return the child at specified index
   public TreeNode getChildAt(int childIndex)
      throws IndexOutOfBoundsException
   {  return children.get(childIndex);
   }
   
   // returns the number of children of this node
   public int getChildCount()
   {  return children.size();
   }
   
   // returns the index of node or -1 if node not found
   public int getIndex(TreeNode node)
   {  if (node==null)
         throw new IllegalArgumentException();
      else
         return children.indexOf(node);
   }
   
   // return the parent node or null if this node is the root
   public TreeNode getParent()
   {  return parent;
   }

   // returns whether this node is a leaf   
   public boolean isLeaf()
   {  return (getChildCount()==0);
   }
   
   // add the child node at specified index, updating this node 
   // and child node to reflect the change
   public void insert(MutableTreeNode child, int index)
      throws IllegalArgumentException
   {  if (child==null)
         throw new IllegalArgumentException("null child");
      child.removeFromParent(); // remove child from its existing parent
      children.add(index, child); // update list of child nodes
      child.setParent(this); // update the child node
   }
   
   // removes the child at index from this node, updating this
   // node and child node to reflect the change
   public void remove(int index)
   {  MutableTreeNode child = children.get(index); // update list
      if (child!=null)
         remove(child);
   }
   
   // remove the specified child from this node, updating this
   // node and child node to reflect the change
   public void remove(MutableTreeNode node)
   {  if (children.remove(node)) // node found and removed
         node.setParent(null);
   }
   
   // remove this node from its parent, updating this
   // node and its parent node
   public void removeFromParent()
   {  if (this.parent!=null)
      {  parent.remove(this);
         this.parent = null;
      }
   }
   
   // sets the parent of this node to be newParent
   // but does not update newParent
   public void setParent(MutableTreeNode newParent)
   {  removeFromParent(); // remove this node from its existing parent
      this.parent = newParent;
   }
   
   // set the element held in this node
   public void setUserObject(Object object)
   {  this.element = (E)object; // unchecked, could throw exception
   }
   
   public E getUserObject()
   {  return element;
   }
   
   // returns a string representation of the node and its child nodes
   // in preorder notation
   public String toString()
   {  String output = "" + this.element;
      if (!isLeaf())
      {  output += "[ ";
         for (MutableTreeNode childNode : children)
            output += childNode + " ";   
         output += "]";
      }
      return output;
   }
   
   public static void main(String[] args)
   {  // create some sample nodes
      MutableTreeNode root = new ListTreeNode<String>("A");
      MutableTreeNode nodeB = new ListTreeNode<String>("B");
      MutableTreeNode nodeC = new ListTreeNode<String>("C");
      MutableTreeNode nodeD = new ListTreeNode<String>("D");
      MutableTreeNode nodeE = new ListTreeNode<String>("E");
      MutableTreeNode nodeF = new ListTreeNode<String>("F");
      // build the tree
      nodeB.insert(nodeD, 0);
      nodeB.insert(nodeE, 1);
      nodeC.insert(nodeF, 0);
      root.insert(nodeB, 0);
      root.insert(nodeC, 1);
      System.out.println("Original Tree: " + root);
      root.remove(nodeC);
      nodeB.insert(nodeC, 1);
      System.out.println("Modified Tree: " + root);
      
      @SuppressWarnings("unchecked")
	  Enumeration<MutableTreeNode> list = root.children();
      
      while (list.hasMoreElements())
      {
    	  System.out.print(list.nextElement() + ", ");
      }
      
   }
}

// utility class to wrap an Iterator object as an Enumeration object
class Enumerator<E> implements Enumeration<E>
{
   private Iterator<E> iterator;
   
   public Enumerator(Iterator<E> iterator)
   {  this.iterator = iterator;
   }
   
   public boolean hasMoreElements()
   {  return iterator.hasNext();
   }
   
   public E nextElement()
   {  return iterator.next();
   }
}
