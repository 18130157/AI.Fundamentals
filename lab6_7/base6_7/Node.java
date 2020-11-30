package base6_7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class Node implements Comparable<Node> {
	public static final int N = 8;
	private Queen[] state;

	public Node() {
		state = new Queen[N];
		generateBoard();
	}

	public Node(Queen[] state) {
		this.state = new Queen[N];
		for (int i = 0; i < state.length; i++)
			this.state[i] = new Queen(state[i].getRow(), state[i].getColumn());
	}

	public Node(Node n) {
		this.state = new Queen[N];
		for (int i = 0; i < N; i++) {
			Queen qi = n.state[i];
			this.state[i] = new Queen(qi.getRow(), qi.getColumn());
		}
	}

	public void generateBoard() {
		Random random = new Random();
		for (int i = 0; i < N; i++)
			state[i] = new Queen(random.nextInt(N), i);
	}

	public int getH() {
		int heuristic = 0;
		for (int i = 0; i < state.length - 1; i++)
			for (int j = i + 1; j < state.length; j++)
				heuristic = (state[i].isConflict(state[j])) ? ++heuristic : heuristic;
		return heuristic;
	}

	public List<Node> generateAllCandidates() {
		List<Node> result = new ArrayList<Node>();
		for (int i = 0; i < state.length; i++) {
			Node copy = new Node(this);
			for (int j = 0; j < state.length - 1; j++) {
				copy.state[i].move();
				result.add(copy);
				copy = new Node(copy);
			}
		}
		return result;
	}

	public Node getBestCandidate() {
		SortedSet<Node> set = new TreeSet<>(generateAllCandidates());
		return set.first();
	}

	public Node selectNextRandomCandidate() {
		Random rd = new Random();
		Node re = new Node(state);
		Queen qi = re.state[rd.nextInt(N)];
		int rowRd = rd.nextInt(N);
		while (rowRd == qi.getRow())
			rowRd = rd.nextInt(N);
		qi.setRow(rowRd);
		return re;
	}

	// get the row of a queen at index i
	public int getRow(int i) {
		return state[i].getRow();
	}

	// set the row of a queen at index i
	public void setRow(int i, int row) {
		state[i].setRow(row);
	}

	public Queen[] getState() {
		return state;
	}

	public void displayBoard() {
		int[][] board = new int[N][N];
		// set queen position on the board
		for (int i = 0; i < N; i++) {
			board[state[i].getRow()][state[i].getColumn()] = 1;
		}
		// print board
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					System.out.print("Q" + " ");
				} else {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}
	}

	@Override
	public int compareTo(Node that) {
		return Integer.compare(this.getH(), that.getH());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		else if (obj == null || obj.getClass() != this.getClass())
			return false;
		else {
			Node that = (Node) obj;
			if (this.state.length != that.state.length)
				return false;

			for (int i = 0; i < state.length; i++)
				if (!this.state[i].equals(that.state[i]))
					return false;

			return true;
		}
	}
}
