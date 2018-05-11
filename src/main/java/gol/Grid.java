package gol;

import java.util.Iterator;
import java.util.List;

public class Grid implements Iterable<Cell> {

	private List<Cell> cells;

	public Grid(List<Cell> cells) {
		this.cells = cells;
	}

	public Cell center() {
		return new Cell(0, 0);
	}

	@Override
	public Iterator<Cell> iterator() {
		return cells.iterator();
	}
}
