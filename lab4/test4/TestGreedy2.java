package test4;

import base4.GreedyBestFirstSearchAlgo;
import base4.IInformedSearchAlgo;
import base4.Node;
import base4.NodeUtils;

public class TestGreedy2 {

	public static void main(String[] args) {
		Node s = new Node("S", 30);
		Node a = new Node("A", 22);
		Node b = new Node("B", 25);
		Node c = new Node("C", 20);
		Node d = new Node("D", 10);
		Node e = new Node("E", 6);
		Node f = new Node("F", 8);
		Node g1 = new Node("G1", 0);
		Node g2 = new Node("G2", 0);
		Node h = new Node("H", 16);
		Node k = new Node("K", 26);

		s.addEdge(b, 8);
		s.addEdge(c, 19);
		s.addEdge(k, 6);
		a.addEdge(b, 8);
		a.addEdge(d, 15);
		b.addEdge(a, 7);
		b.addEdge(c, 11);
		c.addEdge(e, 12);
		d.addEdge(g2, 9);
		e.addEdge(g1, 7);
		f.addEdge(g1, 10);
		h.addEdge(s, 9);
		h.addEdge(f, 7);
		h.addEdge(g1, 19);
		k.addEdge(a, 13);
		k.addEdge(h, 10);
	
		IInformedSearchAlgo greedy = new GreedyBestFirstSearchAlgo();
		Node res = greedy.execute(s, g1.getLabel());
		NodeUtils.print(res);

		res = greedy.execute(s, k.getLabel(), g1.getLabel());
		NodeUtils.print(res);
	}

}
