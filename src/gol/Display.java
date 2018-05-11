package gol;

public abstract class Display {

	public void paint(Grid grid) {

	}

	public void paint(Cell cell) {

	}

	abstract GridCanvas gridCanvas();

	abstract CellCanvas cellCanvas();
}
