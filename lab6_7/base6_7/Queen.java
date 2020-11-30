package base6_7;

public class Queen {
	private int row;
	private int column;
	private boolean conflict;

	public Queen(int row, int column) {
		this.row = row;
		this.column = column;
		this.conflict = false;
	}

	public void move() {
		row = (row == Node.N - 1) ? 0 : (row + 1);
	}

	// check whether this Queen can attack the given Queen (q)
	public boolean isConflict(Queen q) {
		boolean re = (row == q.row || (Math.abs(row - q.row) == Math.abs(column - q.column))) ? true : false;
		if (re) {
			this.conflict = true;
			q.conflict = true;
		}
		return re;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public boolean isConflict() {
		return conflict;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		else if (obj == null || obj.getClass() != this.getClass())
			return false;
		else {
			Queen that = (Queen) obj;
			return this.row == that.row && this.column == that.column && this.conflict == that.conflict;
		}
	}
}
