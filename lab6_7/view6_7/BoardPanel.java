package view6_7;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import base6_7.Node;
import base6_7.Queen;

public class BoardPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public BoardPanel(Node state) {
		setLayout(new GridLayout(8, 8));
		setPreferredSize(new Dimension(600, 600));
		paintChessBox(state);
	}

	public void paintChessBox(Node state) {
		int[][] matrix = new int[Node.N][Node.N];
		for (int i = 0; i < Node.N; i++) {
			Queen q = state.getState()[i];
			matrix[q.getRow()][q.getColumn()] = (q.isConflict()) ? 2 : 1;
		}

		for (int row = 0; row < 8; row++)
			for (int col = 0; col < 8; col++) {
				JPanel square = new JPanel();

				if (row % 2 != 0)
					if (col % 2 != 0)
						square.setBackground(Color.LIGHT_GRAY);
					else
						square.setBackground(Color.GRAY);
				else {
					if (col % 2 != 0)
						square.setBackground(Color.GRAY);
					else
						square.setBackground(Color.LIGHT_GRAY);
				}

				if (matrix[row][col] > 0) {
					square.add(new JLabel(new ImageIcon("images/Black_Queen.png")));
					if (matrix[row][col] == 2)
						square.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				}
				this.add(square);
			}
	}
}
