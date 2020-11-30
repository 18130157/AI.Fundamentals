package test6_7;

import base6_7.HillClimbingSearch;
import base6_7.Node;
import view6_7.MainFrame;

public class TestHCS {

	public static void main(String[] args) {
		Node initialState = new Node();
		initialState.displayBoard();
		System.out.println("H = " + initialState.getH());
		HillClimbingSearch hcs = new HillClimbingSearch();
		System.out.println("————————————————————");

		Node re = hcs.execute(initialState);
		System.out.println("H = " + re.getH());
		hcs.printStepClimbed();
		re.displayBoard();
		new MainFrame("Hill Climbing Search", initialState, re);
		System.out.println("————————————————————");
		
		System.out.println("Processing execute with random restart...");
		re = hcs.executeWithRandomRestart(initialState);
		System.out.println("H = " + re.getH());
		re.displayBoard();
		hcs.printStepClimbed();
		new MainFrame("Hill Climbing Search execute with random restart", initialState, re);
		
	}

}
