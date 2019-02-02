package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import game.Field;
import game.MineSweeperGame;

public class FieldMouseAdapter extends MouseAdapter {

	private MainGameWindow window;
	private ButtonField button;

	public FieldMouseAdapter(MainGameWindow window, ButtonField button) {
		this.window = window;
		this.button = button;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) { // left click
			leftClick();
		} else if (e.getButton() == MouseEvent.BUTTON3) { // right click
			rightClick();
		}
	}

	private void rightClick() {
		MineSweeperGame game = window.getGame();
		Field field = game.getField(button.getPoint());
		if (!field.isStepped()) {
			game.putFlag(button.getPoint());
		}
	}

	private void leftClick() {
		MineSweeperGame game = window.getGame();
		Field field = game.getField(button.getPoint());
		if (!field.isStepped() && !field.isFlag()) {
			game.stepField(button.getPoint());
			if (game.isFinished() == MineSweeperGame.GAME_LOSE) {
				window.disableButtons();
				JOptionPane.showMessageDialog(null, "You stepped on a mine! Boom! You lose haha!");
			} else if (window.getGame().isFinished() == MineSweeperGame.GAME_WIN) {
				window.disableButtons();
				JOptionPane.showMessageDialog(null, "You swept all the mines! You win!");
			}
		}
		window.getMainWindow().repaint();
	}

}
