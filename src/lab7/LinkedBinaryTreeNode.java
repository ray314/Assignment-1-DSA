/**
 * 
 */
package lab7;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * @author fbb3628
 *
 */
public class LinkedBinaryTreeNode<E> implements MutableTreeNode {

	private E element;
	private MutableTreeNode parent;
	private MutableTreeNode leftChild;
	private MutableTreeNode rightChild;
	private LinkedList<MutableTreeNode> children;
	/**
	 * 
	 */
	public LinkedBinaryTreeNode(E element) {
		this.element = element;
		parent = null;
		leftChild = null;
		rightChild = null;
		children = new LinkedList<MutableTreeNode>();
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#children()
	 */
	@Override
	public Enumeration<E> children() {
		// TODO Auto-generated method stub
		class Enumerator<E> implements Enumeration<E>
		{
			private Iterator<E> iterator;
			
			public Enumerator(Iterator<E> iterator)
			{
				this.iterator = iterator;
			}

			public boolean hasMoreElements()
			{  
				return iterator.hasNext();
			}

			public E nextElement()
			{  
				return iterator.next();
			}
		}
		
		return (Enumeration<E>) 
				(new Enumerator(children.iterator()));
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getAllowsChildren()
	 */
	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	@Override
	public TreeNode getChildAt(int index) {
		// TODO Auto-generated method stub
		if (index == 0)
		{
			return leftChild;
		}
		else if (index == 1)
		{
			return rightChild;
		}
		else
		{
			throw new IllegalArgumentException("Invalid index");
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		if (leftChild != null && rightChild != null)
		{
			return 2;
		}
		else if (leftChild != null || rightChild != null)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getIndex(javax.swing.tree.TreeNode)
	 */
	@Override
	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		if (node == leftChild)
		{
			return 0;
		}
		else if (node == rightChild)
		{
			return 1;
		}
		else
		{
			return -1; // Node not found
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		
		if (leftChild == null && rightChild == null)
		{
			return true; // No children
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.MutableTreeNode#insert(javax.swing.tree.MutableTreeNode, int)
	 */
	@Override
	public void insert(MutableTreeNode child, int index) {
		// TODO Auto-generated method stub
		if (child == null)
		{
			throw new IllegalArgumentException("null child");
		}
		child.removeFromParent();
		if (index == 0)
		{
			leftChild = child;
		}
		else if (index == 1)
		{
			rightChild = child;
		}
		else
		{
			throw new IllegalArgumentException("Index must be 0 or 1");
		}
		children.add(child);
		child.setParent(this);
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.MutableTreeNode#remove(int)
	 */
	@Override
	public void remove(int index) {
		// Remove the specified node at index (left or right)
		if (index == 0)
		{
			remove(leftChild);
		}
		else if (index == 1)
		{
			remove(rightChild);
		}
		else
		{
			throw new IllegalArgumentException("Invalid index");
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.MutableTreeNode#remove(javax.swing.tree.MutableTreeNode)
	 */
	@Override
	public void remove(MutableTreeNode node) {
		// Remove the specified node
		if (leftChild == node)
		{
			leftChild = null;
			node.setParent(null);
			children.remove(0);
		}
		else if (rightChild == node)
		{
			rightChild = null;
			node.setParent(null);
			children.remove(1);
		}
		else
		{
			throw new NoSuchElementException("Node not found");
		}
	}
	
	@Override
	public void removeFromParent() {
		// TODO Auto-generated method stub
		if (parent != null)
		{
			parent.remove(this);
			parent = null;
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.MutableTreeNode#setParent(javax.swing.tree.MutableTreeNode)
	 */
	@Override
	public void setParent(MutableTreeNode node) {
		// TODO Auto-generated method stub
		parent = node;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.MutableTreeNode#setUserObject(java.lang.Object)
	 */
	@Override
	public void setUserObject(Object element) {
		
		this.element = (E)element; // unchecked, can throw exception
	}
	
	public E getUserObject()
	{
		return element;
	}

	// returns a string representation of the node and its child nodes
	// in preorder notation
	public String toString()
	{  
		String output = "" + this.element;
		if (!isLeaf())
		{  
			output += "[ ";
			for (MutableTreeNode childNode : children)
				output += childNode + " ";   
			output += "]";
		}
		return output;
	}
}
