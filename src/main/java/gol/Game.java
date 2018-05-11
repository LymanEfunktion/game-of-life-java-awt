package gol;

public class Game {

	public static void main(String[] args) throws InterruptedException {
		AwtWindow display = new AwtWindow();
		int round = 0;
		World world = new World();
		world.add(oktagon());
		display.initialize(world.asGrid());
		while (world.hasCells()) {
			round++;
			display.draw(world.asGrid());
			World tmp = world.nextGeneration();
			Thread.sleep(400);
			if (tmp.equals(world)) {
				System.out.println(String.format("no more growth after %d rounds.", round));
				break;
			}
			world = tmp;
		}
	}

	private static Cell[] blinker() {
		return new Cell[] { new Cell(3, 3), new Cell(4, 3), new Cell(5, 3) };
	}

	private static Cell[] pulsator() {
		return new Cell[] { new Cell(0, 1), new Cell(1, 1), new Cell(2, 0), new Cell(2, 2), new Cell(3, 1),
				new Cell(4, 1), new Cell(5, 1), new Cell(6, 1), new Cell(7, 0), new Cell(7, 2), new Cell(8, 1),
				new Cell(9, 1) };
	}

	private static Cell[] oktagon() {
		return new Cell[] { new Cell(3, 0), new Cell(4, 0), new Cell(1, 2), new Cell(2, 1), new Cell(0, 3),
				new Cell(0, 4), new Cell(1, 5), new Cell(2, 6), new Cell(5, 1), new Cell(6, 2), new Cell(7, 3),
				new Cell(7, 4), new Cell(5, 6), new Cell(6, 5), new Cell(3, 7), new Cell(4, 7) };
	}
}
