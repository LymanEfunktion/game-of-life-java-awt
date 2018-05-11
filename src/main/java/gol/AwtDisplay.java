package gol;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics;

class AwtDisplay extends Display  {

	private int deltaX = 0;
	private int deltaY = 0;

	private Canvas canvas = new Canvas();
	
	public AwtDisplay() {
		canvas.setIgnoreRepaint(true);
	}

	public void calibrate(Window window, Grid grid) {
		int x = grid.center().x;
		deltaX = window.getDimension().x / 2 - x * 100;
		int y = grid.center().y;
		deltaY = window.getDimension().y / 2 - y * 100;
	}

	public void paint(Grid grid) {
		Graphics g = canvas.getGraphics();
		g.clearRect(0, 0, 801, 801);

		for (Cell cell : grid) {
			paint(cell, 10);
		}
	}

	public void paint(Cell cell, int scale) {
		canvas.getGraphics().fillRect(deltaX + scale * cell.x, deltaY + cell.y * scale, scale, scale);
	}

	@Override
	GridCanvas gridCanvas() {
		return null;
	}

	@Override
	CellCanvas cellCanvas() {
		// TODO Auto-generated method stub
		return null;
	}

	public Component component() {
		return canvas;
	}
}