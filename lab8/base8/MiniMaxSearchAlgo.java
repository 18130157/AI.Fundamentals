package base8;

import java.util.Collections;
import java.util.List;

public class MiniMaxSearchAlgo implements ISearchAlgo {

	// function MINIMAX-DECISION(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node, boolean rightToLeft) {
		int v = maxValue(node, rightToLeft);
		System.out.println(v);
		// action
	}

	// function MAX-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MIN_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s))
	// return v
	public int maxValue(Node node, boolean rightToLeft) {
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MIN_VALUE;
		int newV = Integer.MIN_VALUE;

		List<Node> childrens = node.getChildren();
		Collections.sort(childrens, Node.LabelComparator);
		if (rightToLeft)
			Collections.reverse(childrens);

		for (Node child : childrens) {
			newV = minValue(child, rightToLeft);
			if (newV > v) {
				node.setBestNextMove(child);
				v = newV;
			}
		}
		return v;
	}

	// function MIN-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s))
	// return v
	public int minValue(Node node, boolean rightToLeft) {
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MAX_VALUE;
		int newV = Integer.MAX_VALUE;

		List<Node> childrens = node.getChildren();
		Collections.sort(childrens, Node.LabelComparator);
		if (rightToLeft)
			Collections.reverse(childrens);

		for (Node child : childrens) {
			newV = maxValue(child, rightToLeft);
			if (newV < v) {
				node.setBestNextMove(child);
				v = newV;
			}
		}
		return v;
	}
}
