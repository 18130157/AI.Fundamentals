package test5;

import base5.AStar;
import base5.Node;
import base5.Puzzle;
import base5.PuzzleUtils;

public class TestAStar {

	public static void main(String[] args) {
		Puzzle p = new Puzzle(true);
		p.readInput("./txt/PuzzleMap.txt", "./txt/PuzzleGoalState.txt");
		AStar aStar = new AStar();

		System.out.println("Processing with h1...");
		Node re = aStar.execute(p);
		PuzzleUtils.printPath(re);
		StringBuilder stb = new StringBuilder("\nUsed h1: G = " + re.getG() +
				", WHILE_LOOP = " + AStar.WHILE_LOOP);

		p.setUseH1(false);
		AStar.WHILE_LOOP = 0;
		System.out.println("\nProcessing with h2...");
		re = aStar.execute(p);
		PuzzleUtils.printPath(re);

		System.out.println(stb.append("\nUsed h2: G = " + re.getG() +
				", WHILE_LOOP = " + AStar.WHILE_LOOP).toString());
	}

}
