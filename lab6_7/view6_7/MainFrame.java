package view6_7;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import base6_7.Node;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private BoardPanel pInit;
	private BoardPanel pOut;

	public MainFrame(String title, Node initialState, Node outputState) {
		super(title);

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0, 0, 0, 50);
		JPanel pLeft = new JPanel();
		Font font = new Font(pLeft.getFont().getName(), Font.BOLD, 24);
		pLeft.add(createLabel("Initial State — H =  " + initialState.getH(), font));
		pLeft.setOpaque(true);
		pLeft.setBackground(Color.YELLOW);

		gridbag.setConstraints(pLeft, c);
		contentPane.add(pLeft);

		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.EAST;
		JPanel pRight = new JPanel();
		pRight.add(createLabel("Output State — H =  " + outputState.getH(), font));
		pRight.setOpaque(true);
		pRight.setBackground(Color.GREEN);

		gridbag.setConstraints(pRight, c);
		contentPane.add(pRight);

		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0, 0, 0, 50);
		pInit = new BoardPanel(initialState);
		gridbag.setConstraints(pInit, c);
		contentPane.add(pInit);

		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 0);
		pOut = new BoardPanel(outputState);
		gridbag.setConstraints(pOut, c);
		contentPane.add(pOut);

		init();
	}

	public void init() {
		setSize(1300, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public JLabel createLabel(String text, Font font) {
		JLabel label = new JLabel(text);
		label.setFont(font);
		return label;
	}

}
