package test5;
import base5.Greedy;
import base5.Node;
import base5.Puzzle;
import base5.PuzzleUtils;

public class TestGreedy {

	public static void main(String[] args) {
		Puzzle p = new Puzzle(true);
		p.readInput("./txt/PuzzleMap.txt", "./txt/PuzzleGoalState.txt");
		Greedy greedy = new Greedy();

		System.out.println("Processing with h1...");
		Node re = greedy.execute(p);
		PuzzleUtils.printPath(re);
		StringBuilder stb = new StringBuilder("\nUsed h1: G = " + re.getG() +
				", WHILE_LOOP = " + Greedy.WHILE_LOOP);

		p.setUseH1(false);
		Greedy.WHILE_LOOP = 0;
		System.out.println("\nProcessing with h2...");
		re = greedy.execute(p);
		PuzzleUtils.printPath(re);

		System.out.println(stb.append("\nUsed h2: G = " + re.getG() +
				", WHILE_LOOP = " + Greedy.WHILE_LOOP).toString());
	}
}
