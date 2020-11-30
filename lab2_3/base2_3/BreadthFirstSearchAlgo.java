package base2_3;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchAlgo implements ISearchAlgo {
	// Tree seach approach
	@Override
	public Node execute(Node tree, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.offer(tree);

		while (!frontier.isEmpty()) {
			Node curr = frontier.poll();
			if (curr.getLabel().equals(goal))
				return curr;

			List<Edge> childrens = curr.getChildren();
			Collections.sort(childrens);

			for (Edge e : childrens) {
				Node child = e.getEnd().clone();
				child.setParent(curr);
				child.setPathCost(curr.getPathCost() + e.getWeight());
				frontier.offer(child);
			}
		}
		return null;
	}

	@Override
	public Node execute(Node tree, String start, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		boolean started = false;
		frontier.offer(tree);

		while (!frontier.isEmpty()) {
			Node curr = frontier.poll();
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
					frontier.offer(child);
					break;
				}
				child.setParent(curr);
				child.setPathCost(curr.getPathCost() + e.getWeight());
				frontier.offer(child);
			}
		}
		return null;
	}
}
