package test2_3;
import base2_3.Node;
import base2_3.NodeUtils;
import base2_3.UniformCostSearchAlgo;

public class TestUCS2 {

	public static void main(String[] args) {
		Node nodeS = new Node("START");
		Node nodeA = new Node("a");
		Node nodeB = new Node("b");
		Node nodeC = new Node("c");
		Node nodeD = new Node("d");
		Node nodeE = new Node("e");
		Node nodeF = new Node("f");
		Node nodeG = new Node("GOAL");
		Node nodeH = new Node("h");
		Node nodeP = new Node("p");
		Node nodeQ = new Node("q");
		Node nodeR = new Node("r");

		nodeS.addEdge(nodeD, 3);
		nodeS.addEdge(nodeE, 9);
		nodeS.addEdge(nodeP, 1);
		nodeB.addEdge(nodeA, 2);
		nodeC.addEdge(nodeA, 2);
		nodeD.addEdge(nodeB, 1);
		nodeD.addEdge(nodeC, 8);
		nodeD.addEdge(nodeE, 2);
		nodeE.addEdge(nodeH, 1);
		nodeE.addEdge(nodeR, 9);
		nodeF.addEdge(nodeC, 5);
		nodeF.addEdge(nodeG, 5);
		nodeH.addEdge(nodeP, 4);
		nodeH.addEdge(nodeQ, 4);
		nodeP.addEdge(nodeQ, 15);
		nodeQ.addEdge(nodeR, 3);
		nodeR.addEdge(nodeF, 5);
		
		UniformCostSearchAlgo UCS = new UniformCostSearchAlgo();
		Node nodeSG = UCS.execute(nodeS, "GOAL");
		NodeUtils.print(nodeSG);
		
		Node nodeEG = UCS.execute(nodeS, "e", "GOAL");
		NodeUtils.print(nodeEG);
	}
}
