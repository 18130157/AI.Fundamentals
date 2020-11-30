package base5;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch implements IPuzzleAlgo {
	public static int WHILE_LOOP = 0;

	@Override
	public Node execute(Puzzle model) {
		Queue<Node> frontier = new LinkedList<Node>();
//		Set<Node> explored = new HashSet<Node>();
		frontier.offer(model.getInitialState());

		while (!frontier.isEmpty()) {
			Node curr = frontier.poll();
//			explored.add(curr);

			if (curr.getH() == 0)
				return curr;

			for (Node child : model.getSuccessors(curr)) {
//				if (!explored.contains(child) && !frontier.contains(child)) {
					child.setParent(curr);
					child.setG(curr.getG() + 1);
					frontier.offer(child);
//				}
			}
			WHILE_LOOP++;
		}
		return null;
	}
}
