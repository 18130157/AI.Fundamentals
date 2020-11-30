package base2_3;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DepthLimitedSearchAlgo {
	// Tree search appoarch
	public Node executeRecursively(Node tree, String goal, int limitedDepth) {
		if (tree.getLabel().equals(goal))
			return tree;
		if (limitedDepth == 0)
			return null;

		for (Edge e : tree.getChildren()) {
			Node child = e.getEnd().clone();
			child.setParent(tree);
			child.setPathCost(tree.getPathCost() + e.getWeight());

			Node result = executeRecursively(child, goal, limitedDepth - 1);
			if (result != null)
				return result;
		}
		return null;
	}

	// Graph search appoarch
	public Node execute(Node tree, String goal, int limitedDepth) {
		Stack<Node> frontier = new Stack<Node>();
		Set<Node> explored = new HashSet<Node>();
		frontier.push(tree);

		while (!frontier.empty()) {
			Node curr = frontier.pop();
			explored.add(curr);

			if (curr.getLabel().equals(goal))
				return curr;

			if (curr.getDepth() < limitedDepth) {
				List<Edge> childrens = curr.getChildren();
				Collections.sort(childrens);

				for (Edge e : childrens) {
					Node child = e.getEnd();
					if (!explored.contains(child) && !frontier.contains(child)) {
						child.setParent(curr);
						child.setDepth(curr.getDepth() + 1);
						child.setPathCost(curr.getPathCost() + e.getWeight());
						frontier.push(child);
					}
				}
			}
		}
		return null;
	}
}
