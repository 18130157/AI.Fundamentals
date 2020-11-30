package task2.model;

import java.util.Random;

import task2.view.MainFrame;

public class MyEnvironmentState {
	public static final float DIRT_RATE = 0.2f;
	public static final float WALL_RATE = 0.1f;
	private Random generator = new Random();
	public static MyLocationState[][] matrix = new MyLocationState[MyEnvironment.MAX_N][MyEnvironment.MAX_N];
	private MyLocationState[][] backup = new MyLocationState[MyEnvironment.MAX_N][MyEnvironment.MAX_N];
	private short dirtCell;
	private short wallCell;
	private byte rowRandom;
	private byte colRandom;
	public static Coordinate agentLocation;

	public MyEnvironmentState(byte m, byte n) {
		random(m, n);
	}

	public void random(byte m, byte n) {
		agentLocation = null;
		for (byte row = 0; row < m; row++)
			for (byte col = 0; col < n; col++) {
				matrix[row][col] = MyLocationState.CLEAN;
				backup[row][col] = MyLocationState.CLEAN;
			}
		dirtCell = (short) (m * n * DIRT_RATE);
		wallCell = (short) (m * n * WALL_RATE);

		rowRandom = 0;
		colRandom = 0;

		while (dirtCell > 0 || wallCell > 0) {
			rowRandom = randomByte(m);
			colRandom = randomByte(n);
			if (dirtCell > 0 && matrix[rowRandom][colRandom] == MyLocationState.CLEAN) {
				matrix[rowRandom][colRandom] = MyLocationState.DIRTY;
				backup[rowRandom][colRandom] = MyLocationState.DIRTY;
				dirtCell--;
			}
			rowRandom = randomByte(m);
			colRandom = randomByte(n);
			if (wallCell > 0 && matrix[rowRandom][colRandom] == MyLocationState.CLEAN) {
				matrix[rowRandom][colRandom] = MyLocationState.WALL;
				backup[rowRandom][colRandom] = MyLocationState.WALL;
				wallCell--;
			}
		}
	}

	public byte randomByte(byte b) {
		return (byte) generator.nextInt(b);
	}

	public static void setLocationState(byte row, byte col, MyLocationState state) {
		matrix[row][col] = state;
	}

	public static void setLocationState(Coordinate coor, MyLocationState state) {
		setLocationState(coor.getRow(), coor.getCol(), state);
	}

	public MyLocationState getLocationState(byte row, byte col) {
		return matrix[row][col];
	}

	public MyLocationState getLocationState(Coordinate coor) {
		return getLocationState(coor.getRow(), coor.getCol());
	}

	public Coordinate getAgentLocation() {
		return MyEnvironmentState.agentLocation;
	}

	public void setAgentLocation(Coordinate agentLocation) {
		MyEnvironmentState.agentLocation = agentLocation;
	}

	public void setAgentLocationAfter(MyAction action) {
		byte row = agentLocation.getRow();
		byte col = agentLocation.getCol();
		MainFrame.oldRowAgent = row;
		MainFrame.oldColAgent = col;

		if (action == MyAction.MOVE_UP)
			agentLocation = new Coordinate((byte) (row - 1), col);
		else if (action == MyAction.MOVE_DOWN)
			agentLocation = new Coordinate((byte) (row + 1), col);
		else if (action == MyAction.MOVE_LEFT)
			agentLocation = new Coordinate(row, (byte) (col - 1));
		else if (action == MyAction.MOVE_RIGHT)
			agentLocation = new Coordinate(row, (byte) (col + 1));
	}

	public static boolean canMove(String direction) {
		byte row = agentLocation.getRow();
		byte col = agentLocation.getCol();
		switch (direction) {
		case "UP":
			if (row > 0)
				return matrix[row - 1][col] == MyLocationState.CLEAN || matrix[row - 1][col] == MyLocationState.DIRTY;
			return false;

		case "DOWN":
			if (row < MyEnvironment.getM() - 1)
				return matrix[row + 1][col] == MyLocationState.CLEAN || matrix[row + 1][col] == MyLocationState.DIRTY;
			return false;

		case "LEFT":
			if (col > 0)
				return matrix[row][col - 1] == MyLocationState.CLEAN || matrix[row][col - 1] == MyLocationState.DIRTY;
			return false;

		case "RIGHT":
			if (col < MyEnvironment.getN() - 1)
				return matrix[row][col + 1] == MyLocationState.CLEAN || matrix[row][col + 1] == MyLocationState.DIRTY;
			return false;
		}
		return false;
	}

	public boolean isClean() {
		for (byte row = 0; row < MyEnvironment.getM(); row++)
			for (byte col = 0; col < MyEnvironment.getN(); col++)
				if (matrix[row][col] == MyLocationState.DIRTY)
					return false;
		return true;
	}

	public void restore() {
		for(byte row = 0; row < MyEnvironment.getM(); row++)
			for(byte col = 0; col < MyEnvironment.getN(); col++) {
				matrix[row][col] = backup[row][col];
			}
	}

}
