package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import game.MineSweeperGame;

public class FieldButtonAction implements ActionListener {

	private MainGameWindow window;
	private ButtonField button;

	public FieldButtonAction(MainGameWindow window, ButtonField button) {
		this.window = window;
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		window.getGame().stepField(button.getPoint());
		if (window.getGame().isFinished() == MineSweeperGame.GAME_LOSE) {
			window.disableButtons();
			JOptionPane.showMessageDialog(null, "You stepped on a mine! Boom! You lose haha!");
		} else if (window.getGame().isFinished() == MineSweeperGame.GAME_WIN) {
			window.disableButtons();
			JOptionPane.showMessageDialog(null, "You swept all the mines! You win!");
		}
	}

}
