package gol;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GridTest {

	@Test
	public void shouldReturnCenterForSingleCell() throws Exception {
		Cell cell = new Cell(0,0);
		
		Grid grid = new Grid(asList(cell));
		
		assertThat(grid.center(), is(cell));
	}
}
