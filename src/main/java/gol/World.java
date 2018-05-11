package gol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class World {

	Set<Cell> cells = new HashSet<>();

	private Integer centerX = Integer.MIN_VALUE;
	private Integer centerY = Integer.MIN_VALUE;

	public World() {
	}

	private World(Set<Cell> cells) {
		this.cells = cells;
	}

	public void add(Cell... cells) {
		for (Cell cell : cells) {
			this.cells.add(cell);
		}
	}

	boolean has(Cell cell) {
		return cells.contains(cell);
	}

	public World nextGeneration() {
		Set<Cell> cellsOfNextGeneration = new HashSet<>();
		for (Cell cell : cells) {
			int neighbours = neighbours(cell);
			if (neighbours > 1 && neighbours < 4) {
				cellsOfNextGeneration.add(cell);
			}
			cellsOfNextGeneration.addAll(spawningCellsNextTo(cell));
		}
		return new World(cellsOfNextGeneration);
	}

	public int neighbours(Cell anotherCell) {
		int count = 0;
		for (Cell cell : cells) {
			if (cell.isNeighbor(anotherCell)) {
				count++;
			}
		}
		return count;
	}

	private List<Cell> grid(int x, int y) {
		List<Cell> cells = new ArrayList<>();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				cells.add(new Cell(i, j));
			}
		}
		return cells;
	}

	public List<Cell> spawningCellsNextTo(Cell cell) {
		List<Cell> spawningCellsNextTo = new ArrayList<>();
		for (Cell potentialCell : grid(cell.x, cell.y)) {
			if (!has(potentialCell) && neighbours(potentialCell) == 3) {
				spawningCellsNextTo.add(potentialCell);
			}
		}
		return spawningCellsNextTo;
	}

	public boolean hasCells() {
		return !cells.isEmpty();
	}

	public Cell center() {
		for (Cell cell : cells) {
			centerX = centerX < cell.x ? cell.x : centerX;
			centerY = centerY < cell.y ? cell.y : centerY;
		}
		return new Cell(centerX / 2, centerY / 2);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cells == null) ? 0 : cells.hashCode());
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
		World other = (World) obj;
		if (cells == null) {
			if (other.cells != null)
				return false;
		} else if (!cells.equals(other.cells))
			return false;
		return true;
	}

	public Grid asGrid() {
		return new Grid(new ArrayList<>(cells));
	}
}
