package gol;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WorldTest {

	private World world = new World();

	@Test
	public void shouldContainACell() throws Exception {
		Cell cell = new Cell(0, 0);
		world.add(cell);

		assertTrue(world.has(cell));
	}

	@Test
	public void shouldContainCells() throws Exception {
		world.add(new Cell(0, 0));

		assertTrue(world.hasCells());
	}

	@Test
	public void shouldNotContainCells() throws Exception {
		assertFalse(world.hasCells());
	}

	@Test
	public void shouldContainNoCell() throws Exception {
		assertFalse(world.has(new Cell(0, 0)));
	}

	@Test
	public void shouldNotContain() throws Exception {
		world.add(new Cell(0, 0));

		assertThat(world.has(new Cell(1, 1)), is(false));
	}

	@Test
	public void shouldMakeNextGenerationWithoutCells() throws Exception {
		Cell cell = new Cell(0, 0);
		world.add(cell);

		World newWorld = world.nextGeneration();

		assertThat(newWorld.has(cell), is(false));
	}

	@Test
	public void shouldMakeNextGenerationWithCells() throws Exception {
		world.add(cellsBuildingCorner());

		World newWorld = world.nextGeneration();

		assertThat(newWorld.has(new Cell(0, 0)), is(true));
		assertThat(newWorld.has(new Cell(1, 0)), is(true));
		assertThat(newWorld.has(new Cell(0, 1)), is(true));
	}

	@Test
	public void shouldTellNeighborCountForCell() throws Exception {
		world.add(cellsBuildingCorner());

		assertThat(world.neighbours(new Cell(1, 0)), is(2));
	}

	@Test
	public void shouldTellNeighborCountForCellAtTheEndOfLine() throws Exception {
		world.add(cellsBuildingLine());

		assertThat(world.neighbours(new Cell(2, 0)), is(1));
	}

	@Test
	public void shouldTellNeighborCountForCellAtTheStartOfLine() throws Exception {
		world.add(cellsBuildingLine());

		assertThat(world.neighbours(new Cell(0, 0)), is(1));
	}

	@Test
	public void shouldTellNeighborCountForRemoteCell() throws Exception {
		world.add(new Cell(0, 0), new Cell(1, 0), new Cell(0, 6));

		assertThat(world.neighbours(new Cell(0, 6)), is(0));
	}

	@Test
	public void shouldTellNeighborCountForNonExistingCell() throws Exception {
		world.add(new Cell(0, 0), new Cell(1, 0));

		assertThat(world.neighbours(new Cell(0, 3)), is(0));
	}

	@Test
	public void shouldMakeNextGenerationWithCellsOutEndOfLineDying() throws Exception {
		world.add(cellsBuildingLine());

		World newWorld = world.nextGeneration();

		assertThat(newWorld.has(new Cell(0, 0)), is(false));
		assertThat(newWorld.has(new Cell(1, 0)), is(true));
		assertThat(newWorld.has(new Cell(2, 0)), is(false));
	}

	@Test
	public void shouldSpawnCellsInNextGenerationForThreeNeighbours() throws Exception {
		world.add(cellsBuildingLine());

		World newWorld = world.nextGeneration();

		assertThat(newWorld.has(new Cell(1, 1)), is(true));
		assertThat(newWorld.has(new Cell(1, -1)), is(true));
	}

	@Test
	public void shouldReturnNoCellsNextToCellForEmptyWorld() throws Exception {
		assertThat(world.spawningCellsNextTo(new Cell(1, 0)), empty());
	}

	@Test
	public void shouldReturnSpawningCellsNextToSingleCell() throws Exception {
		Cell cell = new Cell(1, 1);
		world.add(cell);

		assertThat(world.spawningCellsNextTo(cell), empty());
	}

	@Test
	public void shouldReturnSpawningCellsNextToCellInLine() throws Exception {
		world.add(cellsBuildingLine());

		assertThat(world.spawningCellsNextTo(new Cell(1, 0)), containsInAnyOrder(new Cell(1, 1), new Cell(1, -1)));
	}

	@Test
	public void shouldReturnSpawningCellsNextToCellInCorner() throws Exception {
		world.add(cellsBuildingCorner());

		assertThat(world.spawningCellsNextTo(new Cell(1, 0)), containsInAnyOrder(new Cell(1, 1)));
	}

	@Test
	public void shouldMakeNextGenerationWithCellsInSqaureSurviving() throws Exception {
		world.add(cellsBuildingSquare());

		World newWorld = world.nextGeneration();

		assertThat(newWorld.has(new Cell(0, 0)), is(true));
		assertThat(newWorld.has(new Cell(1, 0)), is(true));
		assertThat(newWorld.has(new Cell(0, 1)), is(true));
		assertThat(newWorld.has(new Cell(1, 1)), is(true));
	}

	@Test
	public void shouldNotAddExisitingCells() throws Exception {
		world.add(cellsBuildingCorner());

		World newWorld = world.nextGeneration().nextGeneration();

		assertThat(newWorld.cells.size(), is(4));
	}

	@Test
	public void shouldMakeNextGenerationWithOvercroudedCellsDying() throws Exception {
		world.add(cellsBuildingSquarePlusOne());

		World newWorld = world.nextGeneration();

		assertThat(newWorld.has(new Cell(0, 0)), is(true));
		assertThat(newWorld.has(new Cell(1, 0)), is(false));
		assertThat(newWorld.has(new Cell(0, 1)), is(true));
		assertThat(newWorld.has(new Cell(1, 1)), is(false));
		assertThat(newWorld.has(new Cell(2, 1)), is(true));
	}

	@Test
	public void shouldCalculateCenterForSquarePlusOne() throws Exception {
		world.add(cellsBuildingSquarePlusOne());

		assertThat(world.center(), is(new Cell(1, 0)));
	}

	@Test
	public void shouldCalculateCenterToTheLeftForSquare() throws Exception {
		world.add(cellsBuildingSquare());

		assertThat(world.center(), is(new Cell(0, 0)));
	}

	@Test
	public void shouldCalculateTrueCenterForCross() throws Exception {
		world.add(cellsBuildingCross());

		assertThat(world.center(), is(new Cell(1, 1)));
	}

	@Test
	public void shouldCalculateCenterForLine() throws Exception {
		world.add(cellsBuildingLine());

		assertThat(world.center(), is(new Cell(1, 0)));
	}

	private static Cell[] cellsBuildingCorner() {
		return new Cell[] { new Cell(0, 0), new Cell(1, 0), new Cell(0, 1) };
	}

	private static Cell[] cellsBuildingSquare() {
		return new Cell[] { new Cell(0, 0), new Cell(1, 0), new Cell(0, 1), new Cell(1, 1) };
	}

	private static Cell[] cellsBuildingSquarePlusOne() {
		return new Cell[] { new Cell(0, 0), new Cell(1, 0), new Cell(0, 1), new Cell(1, 1), new Cell(2, 1) };
	}

	private static Cell[] cellsBuildingCross() {
		return new Cell[] { new Cell(0, 0), new Cell(2, 0), new Cell(0, 2), new Cell(1, 1), new Cell(2, 2) };
	}

	private static Cell[] cellsBuildingLine() {
		return new Cell[] { new Cell(0, 0), new Cell(1, 0), new Cell(2, 0) };
	}
}
