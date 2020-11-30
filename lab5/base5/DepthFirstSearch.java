package base5;

import java.util.Stack;

public class DepthFirstSearch implements IPuzzleAlgo {
	public static int WHILE_LOOP = 0;

	@Override
	public Node execute(Puzzle model) {
		Stack<Node> frontier = new Stack<Node>();
//		Set<Node> explored = new HashSet<Node>();
		frontier.push(model.getInitialState());

		while (!frontier.empty()) {
			Node curr = frontier.pop();
//			explored.add(curr);

			if (curr.getH() == 0)
				return curr;

			for (Node child : model.getSuccessors(curr)) {
//				if (!explored.contains(child) && !frontier.contains(child)) {
					child.setParent(curr);
					child.setG(curr.getG() + 1);
					frontier.push(child);
//				}
			}
			WHILE_LOOP++;
		}
		return null;
	}
}
