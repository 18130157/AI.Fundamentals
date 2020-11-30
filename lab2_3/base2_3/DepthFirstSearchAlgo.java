package base2_3;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo {
	// Tree seach approach
	@Override
	public Node execute(Node tree, String goal) {
		Stack<Node> frontier = new Stack<Node>();
		frontier.push(tree);

		while (!frontier.empty()) {
			Node curr = frontier.pop();
			if (curr.getLabel().equals(goal))
				return curr;

			List<Edge> childrens = curr.getChildren();
			Collections.sort(childrens);

			for (Edge e : childrens) {
				Node child = e.getEnd().clone();
				child.setParent(curr);
				child.setPathCost(curr.getPathCost() + e.getWeight());
				frontier.push(child);
			}
		}
		return null;
	}

	@Override
	public Node execute(Node tree, String start, String goal) {
		Stack<Node> frontier = new Stack<Node>();
		boolean started = false;
		frontier.push(tree);
		
		while (!frontier.empty()) {
			Node curr = frontier.pop();
			if (curr.getLabel().equals(goal))
				if (started)
					return curr;
				else
					return null;

			List<Edge> childrens = curr.getChildren();
			Collections.sort(childrens);

			for (Edge e : childrens) {
				Node child = e.getEnd().clone();

				if (child.getLabel().equals(start)) {
					started = true;
					frontier.clear();
					child.setParent(null);
					child.setPathCost(0);
					frontier.push(child);
					break;
				}
				child.setParent(curr);
				child.setPathCost(curr.getPathCost() + e.getWeight());
				frontier.push(child);
			}
		}
		return null;
	}
}
