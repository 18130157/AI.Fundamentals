package test2_3;
import base2_3.DepthLimitedSearchAlgo;
import base2_3.Node;
import base2_3.NodeUtils;

public class TestDLS {

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
		
		DepthLimitedSearchAlgo DLS = new DepthLimitedSearchAlgo();
		Node nodeSG = DLS.executeRecursively(nodeS, "G", 2);
		System.out.println("Recursive limitedDepth = 2");
		NodeUtils.print(nodeSG);
		
		nodeSG = DLS.execute(nodeS, "G", 3);
		System.out.println("Loop limitedDepth = 3");
		NodeUtils.print(nodeSG);

	}

}
