package ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import game.MineSweeperGame;
import game.Point;

public class MainGameWindow {

	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGTH = 400;

	private static ButtonField[][] fields;

	private static JFrame mainWindow;

	private static MineSweeperGame game;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				mainWindow = new JFrame();
				initMenuBar(mainWindow);

				game = new MineSweeperGame("Mine Sweeper", 10, 10, 10);
				game.startGame();

				initFrame(mainWindow, game);
				initFields(game);
				placeFields(mainWindow, game);

			}
		});
	}

	private static void initFields(MineSweeperGame game) {
		fields = new ButtonField[game.getRow()][game.getCol()];
		for (int i = 0; i < game.getRow(); ++i) {
			for (int j = 0; j < game.getCol(); ++j) {
				ButtonField newButton = new ButtonField(new Point(i, j));
				game.getField(newButton.getPoint()).setObserver(newButton);
				newButton.addActionListener(new FieldButtonAction(game, newButton));
				fields[i][j] = newButton;
			}
		}
	}

	private static void placeFields(JFrame frame, MineSweeperGame game) {
		for (int i = 0; i < game.getRow(); ++i) {
			for (int j = 0; j < game.getCol(); ++j) {
				frame.add(fields[i][j]);
			}
		}
	}

	private static void initFrame(JFrame frame, MineSweeperGame game) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setVisible(true);
		frame.setTitle(game.getName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGTH);
		frame.setLocation((int) (dim.width / 2 - frame.getSize().getWidth() / 2),
				(int) (dim.height / 2 - frame.getSize().getHeight() / 2));
		frame.setLayout(new GridLayout(game.getRow(), game.getCol()));
	}

	public static void initMenuBar(JFrame frame) {
		JMenuBar menuBar = new JMenuBar();

		JMenu gameMenu = new JMenu("Game");
		JMenuItem newGameMenuItem = new JMenuItem("New Game");
		gameMenu.add(newGameMenuItem);

		menuBar.add(gameMenu);

		frame.setJMenuBar(menuBar);
	}

	public static void disableButtons() {
		for (int i = 0; i < fields.length; ++i) {
			for (int j = 0; j < fields[i].length; ++j) {
				fields[i][j].setEnabled(false);
			}
		}
	}

}
