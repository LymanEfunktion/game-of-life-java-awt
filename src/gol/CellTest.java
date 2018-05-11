package gol;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CellTest {

	@Test
	public void shouldTellThatCellTopIsNeighbor() throws Exception {
		Cell cell = new Cell(0,0);
		
		assertThat(cell.isNeighbor(new Cell(1,0)), is(true));
	}
	
	@Test
	public void shouldTellThatCellRightIsNeighbor() throws Exception {
		Cell cell = new Cell(0,0);
		
		assertThat(cell.isNeighbor(new Cell(0,1)), is(true));
	}
	
	@Test
	public void shouldTellThatRemoteCellOnXIsNoNeighbor() throws Exception {
		Cell cell = new Cell(0,0);
		
		assertThat(cell.isNeighbor(new Cell(2,0)), is(false));
	}
	
	@Test
	public void shouldTellThatRemoteCellOnYIsNoNeighbor() throws Exception {
		Cell cell = new Cell(0,0);
		
		assertThat(cell.isNeighbor(new Cell(0,2)), is(false));
	}
	
	@Test
	public void shouldTellThatSameCellIsNoNeighbor() throws Exception {
		Cell cell = new Cell(0,0);
		
		assertThat(cell.isNeighbor(new Cell(0,0)), is(false));
	}
}
