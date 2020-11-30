package test8;

import base8.AlphaBetaSearchAlgo;
import base8.ISearchAlgo;
import base8.Node;

public class Test3 {

	public static void main(String[] args) {
		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		Node F = new Node("F");
		Node G = new Node("G");
		Node H = new Node("H");
		Node I = new Node("I");
		Node J = new Node("J");
		Node K = new Node("K");
		Node L = new Node("L");
		Node M = new Node("M");
		Node N = new Node("N");
		Node O = new Node("O");
		Node P = new Node("P");
		Node Q = new Node("Q");
		Node R = new Node("R");
		Node S = new Node("S");
		Node T = new Node("T");
		Node U = new Node("U", 4);
		Node V = new Node("V", 9);
		Node W = new Node("W", 2);
		Node X = new Node("X", 1);
		Node Y = new Node("Y", 10);
		Node Z = new Node("Z", 0);
		Node ZA = new Node("ZA", 7);
		Node ZB = new Node("ZB", 4);
		Node ZC = new Node("ZC", 2);
		Node ZD = new Node("ZD", 1);
		Node ZE = new Node("ZE", 8);
		Node ZF = new Node("ZF", 3);
		Node ZG = new Node("ZG", 7);
		Node ZH = new Node("ZH", 4);
		Node ZI = new Node("ZI", 3);
		Node ZJ = new Node("ZJ", 1);

		A.addChild(B, C, D);
		B.addChild(E, F);
		C.addChild(G, H);
		D.addChild(I, J);
		E.addChild(K, L);
		F.addChild(M, N);
		G.addChild(O);
		H.addChild(P, Q);
		I.addChild(R, S);
		J.addChild(T);
		K.addChild(U, V);
		L.addChild(W);
		M.addChild(X, Y);
		N.addChild(Z, ZA);
		O.addChild(ZB);
		P.addChild(ZC);
		Q.addChild(ZD, ZE);
		R.addChild(ZF);
		S.addChild(ZG, ZH);
		T.addChild(ZI, ZJ);

		ISearchAlgo alphaBeta = new AlphaBetaSearchAlgo();
		alphaBeta.execute(A, false);
		System.out.println("Best next move for A: " + A.getBestNextMove());
		System.out.println("—————————————————————");
	
		System.out.println("Right To Left");
		alphaBeta.execute(A, true);
		System.out.println("Best next move for A: " + A.getBestNextMove());

	}

}
