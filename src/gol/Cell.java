package gol;

public class Cell {

	int x;
	int y;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isNeighbor(Cell cell) {
		if (cell.x == x && cell.y == y) {
			return false;
		}
		if (cell.x < x - 1 || cell.x > x + 1) {
			return false;
		}
		if (cell.y < y - 1 || cell.y > y + 1) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("(%d:%d)", x, y);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
