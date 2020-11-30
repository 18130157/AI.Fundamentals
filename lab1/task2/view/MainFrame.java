package task2.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.EventObject;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import task2.model.Coordinate;
import task2.model.MyAction;
import task2.model.MyAgent;
import task2.model.MyAgentProgram;
import task2.model.MyEnvironment;
import task2.model.MyEnvironmentState;
import task2.model.MyLocationState;

@SuppressWarnings(value = { "serial", "unused", "rawtypes", "unchecked" })
public class MainFrame extends JFrame {
	private static MyEnvironment env = new MyEnvironment((byte) 10, (byte) 10);

	private static MyCanvas canvas;
	private MyEventQueue events;
	private static JPanel panel;
	private JButton btnRandom;
	private JButton btnRefresh;
	private JButton btnAbout;
	private JLabel lblColorAgent;
	private JLabel lblColorWall;
	private JLabel lblColorDirt;
	private JComboBox<String> cbbColorWall;
	private JComboBox<String> cbbColorDirt;
	private JComboBox<String> cbbColorAgent;
	private JComboBox<String> cbbGridSize;
	private static JTextField txtPointText;
	private JSlider slider;
	private static int nSuck = 0, nUp = 0, nDown = 0, nLeft = 0, nRight = 0;
	private static int nSteps = 0, points = 0;
	private static JLabel lblSuckValue, lblUpValue, lblDownValue, lblLeftValue, lblRightValue;

	private static byte thicknessX; 
	private static byte thicknessO;
	private static byte marginGridCell;
	private static byte marginGrid;
	private static short gridSize;
	private static short lengthCell;
	private int widthCanvas;
	private short widthPanel;
	private int height;
	private short widthButton;
	private short heightButton;

	private Color defaultColorAgent;
	private Color defaultColorWall;
	private Color defaultColorDirt;
	private Color defaultColorGrid;
	private static Color colorAgent;
	private static Color colorWall;
	private static Color colorDirt;
	private static Color colorGrid;

	private String[] colorSelectionData = { "Default", "Black", "Blue", "Cyan", "Dark Gray", "Gray", "Green",
			"Light Gray", "Magenta", "Orange", "Pink", "Red", "White", "Yellow" };
	private String[] gridSizeData = { "Default", "3 x 3", "5 x 5", "10 x 10", "15 x 15", "20 x 20", "25 x 25",
			"30 x 30" };

	public MainFrame() {
		super("Vaccum Cleaner");
		thicknessX = 4;
		thicknessO = 4;
		marginGridCell = 2;
		colorAgent = defaultColorAgent = Color.GREEN;
		colorWall = defaultColorWall = Color.RED;
		colorDirt = defaultColorDirt = Color.BLACK;
		colorGrid = defaultColorGrid = Color.GRAY;
		initGUI();
		initEventListener();
		init();
	}

	public void initGUI() {
		gridSize = 600;
		marginGrid = 10;
		widthCanvas = gridSize + 2 * marginGrid;
		height = widthCanvas;
		canvas = new MyCanvas();
		canvas.setBounds(0, 0, widthCanvas, height);
		add(canvas);

		widthPanel = 400;
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(widthCanvas, 0, widthPanel, height);
		add(panel);

		widthButton = 120;
		heightButton = 30;
		int marginButton = 50;
		short scoreTextWidth = 250;
		int textPositionX = widthCanvas + (widthPanel - scoreTextWidth) / 2;

		JLabel lblTutor = new JLabel("Press mouse on grid to add an agent");
		panel.add(lblTutor);
		lblTutor.setFont(new Font(lblTutor.getFont().getName(), Font.BOLD, 13));
		lblTutor.setHorizontalAlignment(JLabel.CENTER);
		lblTutor.setBounds(textPositionX, marginButton - 40, scoreTextWidth, heightButton);

		panel.add(txtPointText = new JTextField("0 Points —/— 0 Steps"));
		txtPointText.setEditable(false);
		txtPointText.setFocusable(false);
		txtPointText.setHorizontalAlignment(JTextField.CENTER);
		txtPointText.setBounds(textPositionX, marginButton, scoreTextWidth, heightButton);

		int buttonPositionX = widthCanvas + (widthPanel - widthButton) / 2;
		int labelPositionX = widthCanvas + marginButton;
		panel.add(btnRandom = new JButton("Random"));
		btnRandom.setFocusPainted(false);
		btnRandom.setBounds(labelPositionX, 2 * marginButton, widthButton, heightButton);

		panel.add(btnRefresh = new JButton("Refresh"));
		btnRefresh.setFocusPainted(false);
		btnRefresh.setBounds(labelPositionX + widthButton + 35, 2 * marginButton, widthButton, heightButton);

		JLabel lblSuck = new JLabel("SUCK:");
		panel.add(lblSuck);
		lblSuck.setBounds(labelPositionX, 3 * marginButton, widthButton, heightButton);

		lblSuckValue = new JLabel(nSuck + "");
		panel.add(lblSuckValue);
		lblSuckValue.setBounds(labelPositionX + widthButton, 3 * marginButton, widthButton, heightButton);

		JLabel lblUp = new JLabel("UP:");
		panel.add(lblUp);
		lblUp.setBounds(labelPositionX, 4 * marginButton - 20, widthButton, heightButton);

		lblUpValue = new JLabel(nUp + "");
		panel.add(lblUpValue);
		lblUpValue.setBounds(labelPositionX + widthButton, 4 * marginButton - 20, widthButton, heightButton);

		JLabel lblDown = new JLabel("DOWN:");
		panel.add(lblDown);
		lblDown.setBounds(labelPositionX, 5 * marginButton - 40, widthButton, heightButton);

		lblDownValue = new JLabel(nDown + "");
		panel.add(lblDownValue);
		lblDownValue.setBounds(labelPositionX + widthButton, 5 * marginButton - 40, widthButton, heightButton);

		JLabel lblLeft = new JLabel("LEFT:");
		panel.add(lblLeft);
		lblLeft.setBounds(labelPositionX, 6 * marginButton - 60, widthButton, heightButton);

		lblLeftValue = new JLabel(nLeft + "");
		panel.add(lblLeftValue);
		lblLeftValue.setBounds(labelPositionX + widthButton, 6 * marginButton - 60, widthButton, heightButton);

		JLabel lblRight = new JLabel("RIGHT:");
		panel.add(lblRight);
		lblRight.setBounds(labelPositionX, 7 * marginButton - 80, widthButton, heightButton);

		lblRightValue = new JLabel(nRight + "");
		panel.add(lblRightValue);
		lblRightValue.setBounds(labelPositionX + widthButton, 7 * marginButton - 80, widthButton, heightButton);

		JLabel lblGridSize = new JLabel("Grid size:");
		panel.add(lblGridSize);
		lblGridSize.setBounds(labelPositionX, 8 * marginButton - 80, widthButton, heightButton);

		short widthCombobox = 160;
		panel.add(cbbGridSize = new JComboBox<>(gridSizeData));
		cbbGridSize.setBounds(labelPositionX + widthButton, 8 * marginButton - 80, widthCombobox, heightButton);

		JLabel lblDelay = new JLabel("Step delay (ms):");
		panel.add(lblDelay);
		lblDelay.setBounds(labelPositionX, 9 * marginButton - 70, widthButton, heightButton);

		panel.add(slider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 200));
		slider.setBounds(labelPositionX + widthButton, 9 * marginButton - 70, widthCombobox + 40, heightButton + 30);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(200);
		slider.setMinorTickSpacing(50);
		slider.setPaintLabels(true);

		lblColorAgent = new JLabel("Color of Agent:");
		panel.add(lblColorAgent);
		lblColorAgent.setBounds(labelPositionX, 11 * marginButton - 80, widthButton, heightButton);
		lblColorAgent.setOpaque(true);
		lblColorAgent.setBackground(colorAgent);
		lblColorAgent.setForeground(Color.BLACK);

		panel.add(cbbColorAgent = new JComboBox<>(colorSelectionData));
		cbbColorAgent.setBounds(labelPositionX + widthButton, 11 * marginButton - 80, widthCombobox, heightButton);
		cbbColorAgent.setRenderer(new MyRendererCombobox(cbbColorAgent.getRenderer()));

		lblColorWall = new JLabel("Color of Wall:");
		panel.add(lblColorWall);
		lblColorWall.setBounds(labelPositionX, 12 * marginButton - 80, widthButton, heightButton);
		lblColorWall.setOpaque(true);
		lblColorWall.setBackground(colorWall);
		lblColorWall.setForeground(Color.BLACK);

		panel.add(cbbColorWall = new JComboBox<>(colorSelectionData));
		cbbColorWall.setBounds(labelPositionX + widthButton, 12 * marginButton - 80, widthCombobox, heightButton);
		cbbColorWall.setRenderer(new MyRendererCombobox(cbbColorWall.getRenderer()));

		lblColorDirt = new JLabel("Color of Dirt:");
		panel.add(lblColorDirt);
		lblColorDirt.setBounds(labelPositionX, 13 * marginButton - 80, widthButton, heightButton);
		lblColorDirt.setOpaque(true);
		lblColorDirt.setBackground(colorDirt);
		lblColorDirt.setForeground(Color.WHITE);

		panel.add(cbbColorDirt = new JComboBox<>(colorSelectionData));
		cbbColorDirt.setBounds(labelPositionX + widthButton, 13 * marginButton - 80, widthCombobox, heightButton);
		cbbColorDirt.setRenderer(new MyRendererCombobox(cbbColorDirt.getRenderer()));

		panel.add(btnAbout = new JButton("About"));
		btnAbout.setFocusPainted(false);
		btnAbout.setBounds(buttonPositionX, 14 * marginButton - 80, widthButton, heightButton);
	}

	public void initEventListener() {
		events = new MyEventQueue();
		events.listenTo(canvas, "canvas");
		events.listenTo(btnRandom, "Random");
		events.listenTo(btnRefresh, "Refresh");
		events.listenTo(btnAbout, "About");
		events.listenTo(cbbGridSize, "GridSize");
		events.listenTo(cbbColorAgent, "ColorA");
		events.listenTo(cbbColorWall, "ColorX");
		events.listenTo(cbbColorDirt, "ColorO");
		events.listenTo(slider, "StepDelay");
	}

	public void init() {
		setMinimumSize(new Dimension(widthCanvas + widthPanel, height + 100));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.out.println("setLookAndFeel => Failed");
			e.printStackTrace();
		}
		setVisible(true);
	}

	public void clearGrid() {
		canvas.setBackground(panel.getBackground());
	}

	public static short getLengthCell() {
		return lengthCell = (short) (gridSize / MyEnvironment.getN());
	}

	public static void drawGrid() {
		getLengthCell();
		byte x1 = marginGrid;
		int x2 = marginGrid + gridSize;
		byte y1 = marginGrid;
		int y2 = marginGrid + gridSize;
		canvas.setColor(colorGrid);
		for (byte i = 0; i <= MyEnvironment.getN(); i++) {
			canvas.drawLine(x1, y1 + i * lengthCell, x2, y1 + i * lengthCell);
			canvas.drawLine(x1 + i * lengthCell, y1, x1 + i * lengthCell, y2);
		}

		for (byte row = 0; row < MyEnvironment.getM(); row++) {
			for (byte col = 0; col < MyEnvironment.getN(); col++) {
				if (MyEnvironmentState.matrix[row][col] == MyLocationState.WALL)
					drawX(col, row);
				else if (MyEnvironmentState.matrix[row][col] == MyLocationState.DIRTY
						|| MyEnvironmentState.matrix[row][col] == MyLocationState.LOCK)
					drawO(col, row);
			}
		}
	}

	public static void fillRect(int boardX, int boardY) {
		int x1 = marginGrid + boardX * lengthCell;
		int y1 = marginGrid + boardY * lengthCell;
		canvas.setColor(colorAgent);
		canvas.fillRect(x1 + 1, y1 + 1, lengthCell - 2, lengthCell - 2);
	}

	public static void drawX(int boardX, int boardY) {
		int x1 = marginGrid + boardX * lengthCell;
		int y1 = marginGrid + boardY * lengthCell;
		int x2 = x1 + lengthCell;
		int y2 = y1 + lengthCell;
		x1 += marginGridCell;
		y1 += marginGridCell;
		x2 -= marginGridCell;
		y2 -= marginGridCell;
		canvas.setColor(colorWall);
		for (int i = 0; i <= thicknessX; i++) {
			canvas.drawLine(x1, y1 + i, x2 - i, y2);
			canvas.drawLine(x1 + i, y1, x2, y2 - i);
			canvas.drawLine(x1, y2 - i, x2 - i, y1);
			canvas.drawLine(x1 + i, y2, x2, y1 + i);
		}
	}

	public static void drawO(int boardX, int boardY) {
		int x = marginGrid + boardX * lengthCell + marginGridCell;
		int y = marginGrid + boardY * lengthCell + marginGridCell;
		int diameter = lengthCell - 2 * marginGridCell;
		canvas.setColor(colorDirt);
		for (byte i = 0; i <= thicknessO; i++)
			canvas.drawOval(x + i, y + i, diameter - 2 * i, diameter - 2 * i);
	}

	public static void clearCell(int boardX, int boardY) {
		int x1 = marginGrid + boardX * lengthCell;
		int y1 = marginGrid + boardY * lengthCell;
		canvas.setColor(panel.getBackground());
		canvas.fillRect(x1 + 1, y1 + 1, lengthCell - 2, lengthCell - 2);
	}

	public static void updateView(MyAction acion) {
		if (acion == MyAction.SUCK_DIRT) {
			nSteps++;
			nSuck++;
			points += 500;
		} else {
			nSteps++;
			points -= 10;
			if (acion == MyAction.MOVE_UP)
				nUp++;

			if (acion == MyAction.MOVE_DOWN)
				nDown++;

			if (acion == MyAction.MOVE_LEFT)
				nLeft++;

			if (acion == MyAction.MOVE_RIGHT)
				nRight++;

			clearCell(oldColAgent, oldRowAgent);
			fillRect(MyEnvironmentState.agentLocation.getCol(), MyEnvironmentState.agentLocation.getRow());
			setText();
		}
	}

	public static void setText() {
		txtPointText.setText(String.format("%d Points —/— %d Steps", points, nSteps));
		lblSuckValue.setText(nSuck + "");
		lblUpValue.setText(nUp + "");
		lblDownValue.setText(nDown + "");
		lblLeftValue.setText(nLeft + "");
		lblRightValue.setText(nRight + "");

		lblSuckValue.validate();
		lblSuckValue.repaint();
		lblUpValue.validate();
		lblUpValue.repaint();
		lblDownValue.validate();
		lblDownValue.repaint();
		lblLeftValue.validate();
		lblLeftValue.repaint();
		lblRightValue.validate();
		lblRightValue.repaint();
	}

	public static void clearData() {
		points = 0;
		nSteps = 0;
		nSuck = 0;
		nUp = 0;
		nDown = 0;
		nLeft = 0;
		nRight = 0;
	}

	public static int oldRowAgent, oldColAgent;
	private MyAgent agent = new MyAgent(new MyAgentProgram());

	public void play() {
		clearGrid();
		drawGrid();

		EventObject anEvent;
		while (true) {

			anEvent = events.waitEvent();
			if (MyEventQueue.isMouseEvent(anEvent))
				if (MyEventQueue.isMousePressed(anEvent)) {
					int mouseX = MyEventQueue.getMouseX(anEvent);
					int mouseY = MyEventQueue.getMouseY(anEvent);
					if ((mouseX > marginGrid) && (mouseX < marginGrid + gridSize))
						if ((mouseY > marginGrid) && (mouseY < marginGrid + gridSize)) {
							int boardX = (mouseX - marginGrid) / lengthCell;
							int boardY = (mouseY - marginGrid) / lengthCell;
							if (!MyEnvironment.isDone) {
								fillRect(boardX, boardY);
								env.addAgent(agent, new Coordinate(boardY, boardX));
								oldRowAgent = boardY;
								oldColAgent = boardX;
							}
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
							}
							env.stepUntilDone();
							continue;
						}
				}

			String name = MyEventQueue.getName(anEvent);

			if (name.equals("Random")) {
				env.random();
				clearGrid();
				drawGrid();
				clearData();
				setText();
				MyEnvironment.isDone = false;
				continue;
			}

			if (name.equals("Refresh")) {
				if (MyEnvironment.isDone) {
					env.restore();
					clearGrid();
					drawGrid();
					clearData();
					setText();
					MyEnvironment.isDone = false;
				}
				continue;
			}
			
			if(name.equals("StepDelay")) {
				MyEnvironment.stepDelay = slider.getValue();
				continue;
			}

			if (name.equals("About")) {
				JOptionPane.showMessageDialog(null, ABOUT, "About", JOptionPane.INFORMATION_MESSAGE);
				continue;
			}

			if (name.equals("ColorA")) {
				changeColor("A", cbbColorAgent.getSelectedIndex());
				continue;
			}

			if (name.equals("ColorX")) {
				changeColor("X", cbbColorWall.getSelectedIndex());
				continue;
			}

			if (name.equals("ColorO")) {
				changeColor("O", cbbColorDirt.getSelectedIndex());
				continue;
			}

			if (name.equals("GridSize")) {
				if (getGridSize(cbbGridSize.getSelectedIndex()) != MyEnvironment.getN()) {
					env.setN(getGridSize(cbbGridSize.getSelectedIndex()));
					clearGrid();
					drawGrid();
					clearData();
					setText();
					MyEnvironment.isDone = false;
				}
				continue;
			}
			unfocusAll();
		}
	}

	public static byte getGridSize(int index) {
		if (index == 0)
			return 10;
		if (index == 1)
			return 3;
		if (index == 2)
			return 5;
		if (index == 3)
			return 10;
		if (index == 4)
			return 15;
		if (index == 5)
			return 20;
		if (index == 6)
			return 25;
		return 30;
	}

	public static boolean isDarkGroup(Color color) {
		if (color == Color.BLACK || color == Color.BLUE || color == Color.DARK_GRAY)
			return true;
		return false;
	}

	public void changeColor(String type, int index) {
		switch (type) {
		case "A":
			if (colorSelectionData[index].equals("Default"))
				colorAgent = defaultColorAgent;
			else
				colorAgent = getColor(colorSelectionData[index]);
			lblColorAgent.setBackground(colorAgent);
			if (isDarkGroup(colorAgent))
				lblColorAgent.setForeground(Color.WHITE);
			else
				lblColorAgent.setForeground(Color.BLACK);
			break;

		case "X":
			if (colorSelectionData[index].equals("Default"))
				colorWall = defaultColorWall;
			else
				colorWall = getColor(colorSelectionData[index]);
			lblColorWall.setBackground(colorWall);
			if (isDarkGroup(colorWall))
				lblColorWall.setForeground(Color.WHITE);
			else
				lblColorWall.setForeground(Color.BLACK);
			drawGrid();
			break;

		case "O":
			if (colorSelectionData[index].equals("Default"))
				colorDirt = defaultColorDirt;
			else
				colorDirt = getColor(colorSelectionData[index]);
			lblColorDirt.setBackground(colorDirt);
			if (isDarkGroup(colorDirt))
				lblColorDirt.setForeground(Color.WHITE);
			else
				lblColorDirt.setForeground(Color.BLACK);
			drawGrid();
			break;
		}

	}

	public static Color getColor(String s) {
		if (s.equals("Black"))
			return Color.BLACK;
		if (s.equals("Blue"))
			return Color.BLUE;
		if (s.equals("Cyan"))
			return Color.CYAN;
		if (s.equals("Dark Gray"))
			return Color.DARK_GRAY;
		if (s.equals("Gray"))
			return Color.GRAY;
		if (s.equals("Green"))
			return Color.GREEN;
		if (s.equals("Light Gray"))
			return Color.LIGHT_GRAY;
		if (s.equals("Magenta"))
			return Color.MAGENTA;
		if (s.equals("Orange"))
			return Color.ORANGE;
		if (s.equals("Pink"))
			return Color.PINK;
		if (s.equals("Red"))
			return Color.RED;
		if (s.equals("White"))
			return Color.WHITE;
		if (s.equals("Yellow"))
			return Color.YELLOW;
		return Color.GRAY;
	}

	public void unfocusAll() {
		btnRandom.setFocusable(false);
		btnRefresh.setFocusable(false);
		btnAbout.setFocusable(false);
		cbbGridSize.setFocusable(false);
		cbbColorAgent.setFocusable(false);
		cbbColorWall.setFocusable(false);
		cbbColorDirt.setFocusable(false);
		slider.setFocusable(false);
	}

	public static final String ABOUT = "Software: Vaccum Cleaner — Lab 1\n"
			+ "Course: Artificial Itelligence Fundamentals\n" + "Lecturer: Van Du Nguyen, Ph.D\n"
			+ "Author: Le Viet Nha\n" + "Class: 2018 — 2022\n" + "Institution: Nong Lam University\n"
			+ "Copyright © 2020, Le Viet Nha. All rights reserved.\n\n";

	class MyRendererCombobox extends DefaultListCellRenderer {
		private ListCellRenderer defaultRenderer;
		private Component c;

		public MyRendererCombobox(ListCellRenderer defaultRenderer) {
			this.defaultRenderer = defaultRenderer;
		}

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			c = defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (c instanceof JLabel)
				if (isSelected)
					list.setSelectionBackground(getColor((String) value));
			return c;
		}
	}
}
