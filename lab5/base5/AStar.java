package base5;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class AStar implements IPuzzleAlgo {
	public static int WHILE_LOOP = 0;

	@Override
	public Node execute(Puzzle model) {
		Queue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByF);
		Set<Node> explored = new HashSet<Node>();
		frontier.offer(model.getInitialState());

		while (!frontier.isEmpty()) {
			Node curr = frontier.poll();
			explored.add(curr);

			if (curr.getH() == 0)
				return curr;

			for (Node child : model.getSuccessors(curr)) {
				if (!explored.contains(child))
					if (!frontier.contains(child)) {
						child.setParent(curr);
						child.setG(curr.getG() + 1);
						frontier.offer(child);
					} else {
						int newG = curr.getG() + 1;
						if (newG < child.getG()) {
							child.setParent(curr);
							child.setG(newG);
						}
					}
			}
			WHILE_LOOP++;
		}
		return null;
	}

}
