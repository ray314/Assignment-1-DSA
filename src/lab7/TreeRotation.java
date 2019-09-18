/**
 * 
 */
package lab7;

import javax.swing.tree.MutableTreeNode;

/**
 * @author Justin Yeung
 *
 */
public class TreeRotation {
	
	public static MutableTreeNode leftRotation(MutableTreeNode node)
	{
		// Store reference of the broken right node
		MutableTreeNode brokenNodeRight = (MutableTreeNode) node.getChildAt(1);
		MutableTreeNode brokenNodeLeftChild = null;;
		
		// Null the right child
		node.remove(brokenNodeRight);
		
		// If broken node's left child is not null then break that link
		if (brokenNodeRight.getChildAt(0) != null)
		{
			brokenNodeLeftChild = (MutableTreeNode) brokenNodeRight.getChildAt(0);
			brokenNodeRight.remove(0);
		}
		
		// Attach current node to broken node as its left child
		brokenNodeRight.insert(node, 0);
		
		// Reattach the left child to current node's left if it is not null
		if (brokenNodeLeftChild != null)
		{
			node.insert(brokenNodeLeftChild, 1);
		}
		
		return brokenNodeRight;
	}
	
	public static MutableTreeNode rightRotation(MutableTreeNode node)
	{
		MutableTreeNode brokenNodeLeft = (MutableTreeNode) node.getChildAt(0);
		MutableTreeNode brokenNodeRightChild = null;
		
		// Null the left child
		node.remove(brokenNodeLeft);
		// If right child is not null then remove it
		if (brokenNodeLeft.getChildAt(1) != null)
		{
			brokenNodeRightChild = (MutableTreeNode) brokenNodeLeft.getChildAt(1);
			brokenNodeLeft.remove(brokenNodeRightChild);
		}
		
		// Attach current node to broken node as its right child
		brokenNodeLeft.insert(node, 1);
		
		if (brokenNodeRightChild != null)
		{
			node.insert(brokenNodeRightChild, 0);
		}
		return brokenNodeLeft;
	}
	
	public static MutableTreeNode rightleftRotation(MutableTreeNode node)
	{
		rightRotation((MutableTreeNode) node.getChildAt(1));
		MutableTreeNode newNode = leftRotation(node);
		
		return newNode;
	}
	
	public static MutableTreeNode leftRightRotation(MutableTreeNode node)
	{
		MutableTreeNode newNode;
		newNode = leftRotation((MutableTreeNode) node.getChildAt(0));
		//MutableTreeNode newNode = rightRotation(node);
		
		return newNode;
	}
}
