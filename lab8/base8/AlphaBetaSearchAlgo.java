package base8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlphaBetaSearchAlgo implements ISearchAlgo {

	// function ALPHA-BETA-SEARCH(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state, Integer.MIN_VALUE , Integer.MAX_VALUE)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node, boolean rightToLeft) {
		int v = maxValue(node, Integer.MIN_VALUE, Integer.MAX_VALUE, rightToLeft);
		System.out.println(v);
		// action
	}

	// function MAX-VALUE(state, alpha, beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- MIN_VALUE;
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s, alpha, beta))
	// if v >= beta then return v
	// alpha <- MAX(alpha, v)
	// return v
	public int maxValue(Node node, int alpha, int beta, boolean rightToLeft) {
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MIN_VALUE;
		int newV = Integer.MIN_VALUE;

		List<Node> childrens = node.getChildren();
		Collections.sort(childrens, Node.LabelComparator);
		if (rightToLeft)
			Collections.reverse(childrens);
		List<Node> unexplored = new ArrayList<>(childrens);

		for (Node child : childrens) {
			unexplored.remove(child);
			newV = minValue(child, alpha, beta, rightToLeft);
			if (newV > v) {
				node.setBestNextMove(child);
				v = newV;
			}
			if (v >= beta) {
				if (!unexplored.isEmpty())
					System.out.println("Pruned node: " + unexplored);
				return v;
			}
			alpha = Math.max(alpha, v);
		}
		return v;
	}

	// function MIN-VALUE(state, alpha , beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s, alpha , beta))
	// if v <= alpha then return v
	// beta <- MIN(beta ,v)
	// return v
	public int minValue(Node node, int alpha, int beta, boolean rightToLeft) {
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MAX_VALUE;
		int newV = Integer.MAX_VALUE;

		List<Node> childrens = node.getChildren();
		Collections.sort(childrens, Node.LabelComparator);
		if (rightToLeft)
			Collections.reverse(childrens);
		List<Node> unexplored = new ArrayList<>(childrens);

		for (Node child : childrens) {
			unexplored.remove(child);
			newV = maxValue(child, alpha, beta, rightToLeft);
			if (newV < v) {
				node.setBestNextMove(child);
				v = newV;
			}
			if (v <= alpha) {
				if (!unexplored.isEmpty())
					System.out.println("Pruned node: " + unexplored);
				return v;
			}
			beta = Math.min(beta, v);
		}
		return v;
	}
}
