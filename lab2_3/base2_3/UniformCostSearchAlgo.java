package base2_3;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class UniformCostSearchAlgo implements ISearchAlgo {
	// Graph seach approach
	@Override
	public Node execute(Node tree, String goal) {
		Queue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
		Set<Node> explored = new HashSet<Node>();
		frontier.offer(tree);

		while (!frontier.isEmpty()) {
			Node curr = frontier.poll();
			explored.add(curr);

			if (curr.getLabel().equals(goal))
				return curr;

			List<Edge> childrens = curr.getChildren();
			Collections.sort(childrens);

			for (Edge e : childrens) {
				Node child = e.getEnd();

				if (!explored.contains(child))
					if (!frontier.contains(child)) {
						child.setParent(curr);
						child.setPathCost(curr.getPathCost() + e.getWeight());
						frontier.offer(child);
					} else {
						double newPathCost = curr.getPathCost() + e.getWeight();
						if (newPathCost < child.getPathCost()) {
							child.setParent(curr);
							child.setPathCost(newPathCost);
						}
					}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node tree, String start, String goal) {
		Queue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
		Set<Node> explored = new HashSet<Node>();
		boolean started = false;
		frontier.offer(tree);

		while (!frontier.isEmpty()) {
			Node curr = frontier.poll();
			explored.add(curr);

			if (curr.getLabel().equals(goal)) 
				if (started)
					return curr;
				else
					return null;
			
			List<Edge> childrens = curr.getChildren();
			Collections.sort(childrens);
			for (Edge e : childrens) {
				Node child = e.getEnd();
			
				if (!explored.contains(child))
					if (!frontier.contains(child)) {
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
					} else {
						double newPathCost = curr.getPathCost() + e.getWeight();
						if (newPathCost < child.getPathCost()) {
							child.setParent(curr);
							child.setPathCost(newPathCost);
						}
					}
			}
		}
		return null;
	}

	class NodeComparator implements Comparator<Node> {
		@Override
		public int compare(Node node1, Node node2) {
			return Double.compare(node1.getPathCost(), node2.getPathCost());
		}
	}
}
