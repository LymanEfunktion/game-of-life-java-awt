package gol;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AwtWindow extends Frame implements Window {

	private static final long serialVersionUID = 4148252116968373679L;

	private AwtDisplay display = new AwtDisplay();

	public AwtWindow() {
		setTitle("Game of Life");
		setSize(801, 801);

		add(display.component(), BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent event) {
				event.getWindow().dispose();
				System.exit(0);
			}
		});
		setVisible(true);
	}

	public void initialize(Grid grid) {
		display.calibrate(this, grid);
	}

	public void draw(Grid grid) {
		display.paint(grid);
	}

	@Override
	public Dimension getDimension() {
		return new Dimension(801, 801);
	}
}
