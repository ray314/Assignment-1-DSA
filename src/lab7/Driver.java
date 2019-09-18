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
		MutableTreeNode nodeG = new LinkedBinaryTreeNode<String>("G");
		MutableTreeNode nodeH = new LinkedBinaryTreeNode<String>("H");
		MutableTreeNode nodeI = new LinkedBinaryTreeNode<String>("I");
		MutableTreeNode nodeJ = new LinkedBinaryTreeNode<String>("J");
		MutableTreeNode leftRotatedNode;
		MutableTreeNode rightRotatedNode;
		MutableTreeNode rightLeftNode;
		MutableTreeNode leftRightNode;
		// build the tree
		nodeB.insert(nodeD, 0);
		nodeB.insert(nodeE, 1);
		nodeC.insert(nodeF, 0);
		nodeC.insert(nodeG, 1);
		nodeD.insert(nodeH, 0);
		nodeD.insert(nodeI, 1);
		nodeE.insert(nodeJ, 0);
		root.insert(nodeB, 0);
		root.insert(nodeC, 1);
		System.out.println("Original Tree: " + root);
		//root.remove(nodeC);
		//nodeC.insert(nodeC, 1);
		System.out.println("Modified Tree: " + root);

		//leftRotatedNode = TreeRotation.leftRotation(root);
		//rightRotatedNode = TreeRotation.rightRotation(root);
		rightLeftNode = TreeRotation.rightleftRotation(root);
		
		System.out.println("Left rotation on node B: " + rightLeftNode);
		
		//@SuppressWarnings("unchecked")
		//Enumeration<MutableTreeNode> list = root.children();

		//while (list.hasMoreElements())
		//{
		//	System.out.print(list.nextElement() + ", ");
		//}
	}

}
