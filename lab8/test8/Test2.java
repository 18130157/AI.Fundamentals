package test8;

import base8.AlphaBetaSearchAlgo;
import base8.ISearchAlgo;
import base8.Node;

public class Test2 {

	public static void main(String[] args) {
		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D", 0);
		Node E = new Node("E");
		Node F = new Node("F");
		Node G = new Node("G", -5);
		Node H = new Node("H", 3);
		Node I = new Node("I", 8);
		Node J = new Node("J");
		Node K = new Node("K");
		Node L = new Node("L", 2);
		Node M = new Node("M");
		Node N = new Node("N", 4);
		Node O = new Node("O");
		Node P = new Node("P", 9);
		Node Q = new Node("Q", -6);
		Node R = new Node("R", 0);
		Node S = new Node("S", 3);
		Node T = new Node("T", 5);
		Node U = new Node("U", -7);
		Node V = new Node("V", -9);
		Node W = new Node("W", -3);
		Node X = new Node("X", -5);

		A.addChild(B, C, D, E);
		B.addChild(F, G);
		C.addChild(H, I, J);
		E.addChild(K, L, M);
		F.addChild(N, O);
		J.addChild(P, Q, R);
		K.addChild(S, T);
		M.addChild(U, V);
		O.addChild(W, X);

		ISearchAlgo alphaBeta = new AlphaBetaSearchAlgo();
		System.out.println("Alpha — Beta");
		alphaBeta.execute(A, false);
		System.out.println("Best next move for A: " + A.getBestNextMove());
		System.out.println("—————————————————————");
		
		System.out.println("Right To Left");
		alphaBeta.execute(A, true);
		System.out.println("Best next move for A: " + A.getBestNextMove());

	}

}
