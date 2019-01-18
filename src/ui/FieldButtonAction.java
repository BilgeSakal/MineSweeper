package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import game.MineSweeperGame;

public class FieldButtonAction implements ActionListener {

	private MineSweeperGame game;
	private ButtonField button;

	public FieldButtonAction(MineSweeperGame game, ButtonField button) {
		this.game = game;
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		game.stepField(button.getPoint());
		if (game.isFinished() == MineSweeperGame.GAME_LOSE) {
			MainGameWindow.disableButtons();
			JOptionPane.showMessageDialog(null, "You stepped on a mine! Boom! You lose haha!");
		} else if (game.isFinished() == MineSweeperGame.GAME_WIN) {
			MainGameWindow.disableButtons();
			JOptionPane.showMessageDialog(null, "You swept all the mines! You win!");
		}
	}

}
