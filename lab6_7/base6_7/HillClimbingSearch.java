package base6_7;

public class HillClimbingSearch {
	private int stepClimbed = 0;
	private int stepClimbedAfterRandomRestart = 0;
	private int randomRestarts = 0;

	public Node execute(Node initialState) {
		Node current = initialState;
		int currH = current.getH();
		if (randomRestarts > 1)
			stepClimbedAfterRandomRestart = 0;
		Node neighbor;
		while (true) {
			neighbor = current.getBestCandidate();
			if (neighbor.getH() >= currH)
				return neighbor;
			else {
				currH = neighbor.getH();
				current = neighbor;
				stepClimbed++;
				if (randomRestarts > 0)
					stepClimbedAfterRandomRestart++;
			}
		}
	}

	public Node executeWithRandomRestart(Node initialState) {
		Node state = execute(initialState);
		while (state.getH() != 0) {
			state.generateBoard();
			randomRestarts++;
			state = execute(state);
		}
		return state;
	}

	public void printStepClimbed() {
		System.out.println("stepClimbed = " + stepClimbed);
		System.out.println("stepClimbedAfterRandomRestart = " + stepClimbedAfterRandomRestart);
		System.out.println("randomRestarts = " + randomRestarts);
	}

	public int getStepClimbed() {
		return stepClimbed;
	}

	public int getStepClimbedAfterRandomRestart() {
		return stepClimbedAfterRandomRestart;
	}

	public int getRandomRestarts() {
		return randomRestarts;
	}

}
