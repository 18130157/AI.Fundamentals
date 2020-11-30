package test8;
import base8.AlphaBetaSearchAlgo;
import base8.ISearchAlgo;
import base8.MiniMaxSearchAlgo;
import base8.Node;

public class Test1 {

	public static void main(String[] args) {
		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E", 3);
		Node F = new Node("F", 12);
		Node G = new Node("G", 8);
		Node H = new Node("H", 2);
		Node I = new Node("I", 4);
		Node J = new Node("J", 6);
		Node K = new Node("K", 14);
		Node L = new Node("L", 5);
		Node M = new Node("M", 2);
		A.addChild(B, C, D);
		B.addChild(E, F, G);
		C.addChild(H, I, J);
		D.addChild(K, L, M);

		ISearchAlgo minimax = new MiniMaxSearchAlgo();
		System.out.println("MiniMax");
		minimax.execute(A, false);
		System.out.println("Best next move for A: " + A.getBestNextMove());
		System.out.println("—————————————————————————");
		
		System.out.println("Alpha — Beta");
		ISearchAlgo alphaBeta = new AlphaBetaSearchAlgo();
		alphaBeta.execute(A, false);
		System.out.println("Best next move for A: " + A.getBestNextMove());
		System.out.println("——————————————————");
		
		System.out.println("Right To Left");
		alphaBeta.execute(A, true);
		System.out.println("Best next move for A: " + A.getBestNextMove());
	}

}
