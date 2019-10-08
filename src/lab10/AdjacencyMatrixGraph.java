package lab10;
/**
   A class that implements an undirected graph using an adjacency
   list as the underlying data structure, and whose vertices
   hold elements of type E (which should have a suitable hash function)
   @author Andrew Ensor
*/
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



public class AdjacencyMatrixGraph
{
	private boolean[][] adjacencyMatrix;
	private int numVertices;
	
	public AdjacencyMatrixGraph(int numVertices)
	{
		this.numVertices = numVertices;
		adjacencyMatrix = new boolean[numVertices][numVertices];
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return adjacencyMatrix.length <= 0;
	}

	public void addEdge(int i, int j) {
		// TODO Auto-generated method stub
		adjacencyMatrix[i][j] = true;
		adjacencyMatrix[j][i] = true;
	}

	public void removeEdge(int i, int j) {
		// TODO Auto-generated method stub
		adjacencyMatrix[i][j] = false;
		adjacencyMatrix[j][i] = false;
	}
   
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < numVertices; i++)
		{
			s.append(i + ": ");
			for (boolean j : adjacencyMatrix[i])
			{
				s.append((j ? 1 : 0) + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}
}

