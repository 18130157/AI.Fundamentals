package base4;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


public class AStarSearchAlgo implements IInformedSearchAlgo {
	
	@Override
	public Node execute(Node tree, String goal) {
		Queue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByFn());
		Set<Node> explored = new HashSet<Node>();
		frontier.offer(tree);

		while (!frontier.isEmpty()) {
			Node curr = frontier.poll();
			explored.add(curr);
			if (curr.getLabel().equals(goal))
				return curr;

			List<Edge> childrens = curr.getChildren();
			for (Edge e : childrens) {
				Node child = e.getEnd();
				if (!explored.contains(child))
					if (!frontier.contains(child)) {
						child.setParent(curr);
						child.setG(curr.getG() + e.getWeight());
						frontier.offer(child);
					} else {
						double newG = curr.getG() + e.getWeight();
						if (newG < child.getG()) {
							child.setParent(curr);
							child.setG(newG);
						}
					}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node tree, String start, String goal) {
		Queue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByFn());
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

			for (Edge e : curr.getChildren()) {
				Node child = e.getEnd();
				if (!explored.contains(child))
					if (!frontier.contains(child)) {
						if (child.getLabel().equals(start)) {
							started = true;
							child.setParent(null);
							child.setG(0);
							frontier.clear();
							frontier.offer(child);
							break;
						}
						child.setParent(curr);
						child.setG(curr.getG() + e.getWeight());
						frontier.offer(child);
					} else {
						double newG = curr.getG() + e.getWeight();
						if (newG < child.getG()) {
							child.setParent(curr);
							child.setG(newG);
						}
					}
			}
		}
		return null;
	}

	private Queue<Node> frontier = new LinkedList<Node>();
	private Set<Node> explored = new HashSet<Node>();
	private boolean result = true;

	public boolean isAdmissibleH(Node tree, String goal) {
		frontier.offer(tree);

		while (!frontier.isEmpty()) {
			Node curr = frontier.poll();
			explored.add(curr);
			Node nodeRe = execute(curr, goal);
			// if nodeRe == null => kh có đường đi => tự quy ước true => tiếp tục đệ quy
			if (nodeRe != null) {
				if (curr.getH() > nodeRe.getG())
					return false;

				for (Node child : curr.getChildrenNodes())
					if (!explored.contains(child) && !frontier.contains(child)) {
						child.setParent(null);
						child.setG(0);
						result = result && isAdmissibleH(child, goal);
						if (!result)
							return result;
					}
			}
		}
		return true;
	}
	
}

class NodeComparatorByFn implements Comparator<Node> {
	@Override
	public int compare(Node node1, Node node2) {
		return (Double.compare(node1.getF(), node2.getF()) != 0) ? Double.compare(node1.getF(), node2.getF())
				: node1.getLabel().compareTo(node2.getLabel());
	}
}
