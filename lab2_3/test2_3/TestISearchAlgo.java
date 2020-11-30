package test2_3;

import base2_3.BreadthFirstSearchAlgo;
import base2_3.DepthFirstSearchAlgo;
import base2_3.ISearchAlgo;
import base2_3.Node;
import base2_3.NodeUtils;

public class TestISearchAlgo {

	public static void main(String[] args) {
		Node nodeS = new Node("S");
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");
		Node nodeF = new Node("F");
		Node nodeG = new Node("G");
		Node nodeH = new Node("H");
		nodeS.addEdge(nodeA, 5);
		nodeS.addEdge(nodeB, 2);
		nodeS.addEdge(nodeC, 4);
		nodeA.addEdge(nodeD, 9);
		nodeA.addEdge(nodeE, 4);
		nodeB.addEdge(nodeG, 6);
		nodeC.addEdge(nodeF, 2);
		nodeD.addEdge(nodeH, 7);
		nodeE.addEdge(nodeG, 6);
		nodeF.addEdge(nodeG, 1);

		ISearchAlgo BFS = new BreadthFirstSearchAlgo();
		Node nodeSGb = BFS.execute(nodeS, "G");
		Node nodeAGb = BFS.execute(nodeS, "A", "G");
		System.out.println("BFS Tree seach approach");
		NodeUtils.print(nodeSGb);
		NodeUtils.print(nodeAGb);
		System.out.println("————————————————————————————————");
		
		ISearchAlgo DFS = new DepthFirstSearchAlgo();
		Node nodeSGd = DFS.execute(nodeS, "G");
		Node nodeAGd = DFS.execute(nodeS, "A", "G");
		System.out.println("DFS Tree seach approach");
		NodeUtils.print(nodeSGd);
		NodeUtils.print(nodeAGd);

	}

	

}
