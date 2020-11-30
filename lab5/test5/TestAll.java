package test5;

import base5.AStar;
import base5.BreadthFirstSearch;
import base5.DepthFirstSearch;
import base5.Greedy;
import base5.Node;
import base5.Puzzle;

@SuppressWarnings("unused")
public class TestAll {
	private static StringBuilder stb = new StringBuilder();

	public static void main(String[] args) {
		Puzzle p = new Puzzle(true);
		p.readInput("./txt/MapTestAll.txt", "./txt/PuzzleGoalState.txt");
		Greedy greedy = new Greedy();
		AStar aStar = new AStar();
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		DepthFirstSearch dfs = new DepthFirstSearch();
		Node re;

		System.out.println("Greedy");
		re = greedy.execute(p);
		print(re, "greedy", 1);
		p.setUseH1(false);
		re = greedy.execute(p);
		print(re, "greedy", 2);

		System.out.println("AStar");
		p.setUseH1(true);
		re = aStar.execute(p);
		print(re, "astar", 1);
		p.setUseH1(false);
		re = aStar.execute(p);
		print(re, "astar", 2);

		System.out.println("BFS tree search approach");
		p.setUseH1(true);
		re = bfs.execute(p);
		print(re, "bfs", 1);
		p.setUseH1(false);
		re = bfs.execute(p);
		print(re, "bfs", 2);

		// DFS quá lâu
		System.out.println("DFS tree search approach");
//		p.setUseH1(true);
//		re = dfs.execute(p);
//		print(re, "dfs", 1);
//		p.setUseH1(false);
//		re = dfs.execute(p);
//		print(re, "dfs", 2);
	}

	public static void print(Node re, String algo, int n) {
		int loop = 0;
		switch (algo) {
		case "greedy":
			loop = Greedy.WHILE_LOOP;
			break;
		case "astar":
			loop = AStar.WHILE_LOOP;
			break;
		case "bfs":
			loop = BreadthFirstSearch.WHILE_LOOP;
			break;
		case "dfs":
			loop = DepthFirstSearch.WHILE_LOOP;
			break;
		}
		stb.append("\nUsed h" + n + ": G = " + re.getG() + ", WHILE_LOOP = " + loop);
		if (n == 2) {
			System.out.println(stb.toString());
			System.out.println("\n———————————————————————————————————");
			stb.setLength(0);
		}

		switch (algo) {
		case "greedy":
			Greedy.WHILE_LOOP = 0;
			break;
		case "astar":
			AStar.WHILE_LOOP = 0;
			break;
		case "bfs":
			BreadthFirstSearch.WHILE_LOOP = 0;
			break;
		case "dfs":
			DepthFirstSearch.WHILE_LOOP = 0;
			break;
		}
	}

}
