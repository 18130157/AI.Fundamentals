package task2.model;

public class Coordinate {
	private byte row;
	private byte col;

	public Coordinate(byte row, byte col) {
		this.row = row;
		this.col = col;
	}

	public Coordinate(int row, int col) {
		this.row = (byte) row;
		this.col = (byte) col;
	}

	@Override
	public String toString() {
		return String.format("[%d, %d]", row, col);
	}

	@Override
	public boolean equals(Object arg0) {
		try {
			Coordinate c = (Coordinate) arg0;
			return this.row == c.row && this.col == c.col;
		} catch (Exception e) {
			return false;
		}
	}

	public byte getRow() {
		return row;
	}

	public byte getCol() {
		return col;
	}

	public boolean isAbove(Coordinate c) {
		return this.row < c.row && this.col == c.col;
	}

	public boolean isUnder(Coordinate c) {
		return this.row > c.row && this.col == c.col;
	}

	public boolean isOnLeft(Coordinate c) {
		return this.row == c.row && this.col < c.col;
	}

	public boolean isOnRight(Coordinate c) {
		return this.row == c.row && this.col > c.col;
	}

}
