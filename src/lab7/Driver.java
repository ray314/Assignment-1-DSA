/**
 * 
 */
package lab7;

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
		LinkedBinaryTreeNode<Integer>[] node = new LinkedBinaryTreeNode[5];
		node[0] = new LinkedBinaryTreeNode<Integer>(1);
		node[1] = new LinkedBinaryTreeNode<Integer>(2);
		node[2] = new LinkedBinaryTreeNode<Integer>(3);
		
		node[0].insert(node[1], 0);
		node[0].insert(node[2], 1);
		
		System.out.println("Number of nodes: " + node[0].getChildCount());
		
		node[2].removeFromParent();
	}

}
