package test6_7;

import base6_7.GeneticAlgo;
import base6_7.Node;
import view6_7.MainFrame;

public class TestGA {

	public static void main(String[] args) {
		GeneticAlgo ga = new GeneticAlgo();

		Node re = ga.execute();
		System.out.println("H = " + re.getH());
		re.displayBoard();
		
		new MainFrame("Genetic Algo", re, re);
	}

}
