/**
 * 
 */
package lab7;

import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;

/**
 * @author fbb3628
 *
 */
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MutableTreeNode root = new LinkedBinaryTreeNode<String>("A");
		MutableTreeNode nodeB = new LinkedBinaryTreeNode<String>("B");
		MutableTreeNode nodeC = new LinkedBinaryTreeNode<String>("C");
		MutableTreeNode nodeD = new LinkedBinaryTreeNode<String>("D");
		MutableTreeNode nodeE = new LinkedBinaryTreeNode<String>("E");
		MutableTreeNode nodeF = new LinkedBinaryTreeNode<String>("F");
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
