package ui;

import javax.swing.JLabel;

import game.Timer;

public class TimeLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	public void update(Timer timer) {
		setText("time: " + timer.getTime());
	}

}
