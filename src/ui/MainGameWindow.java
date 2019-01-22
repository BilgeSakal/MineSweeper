package ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import game.MineSweeperGame;
import game.Point;

public class MainGameWindow {

	private static final int BUTTON_WIDTH = 25;
	private static final int BUTTON_HEIGTH = 25;

	private ButtonField[][] fields;

	private JFrame mainWindow;
	private JPanel gamePanel;
	private JMenuBar menuBar;

	private int windowWidth;
	private int windowHeigth;

	private MineSweeperGame game;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainGameWindow mgw = new MainGameWindow();
			}
		});
	}

	public MainGameWindow() {
		game = new MineSweeperGame("Mine Sweeper v1.0", 10, 10, 10);
		game.startGame();
		initFrame();
		initPanel();
		initMenuBar();
		initFields();
		placeFields();
	}

	private void initGame(int row, int col, int numOfMines) {
		game = new MineSweeperGame("Mine Sweeper v1.0", row, col, numOfMines);
		game.startGame();
		initFrame();
		initPanel();
		initMenuBar();
		initFields();
		placeFields();
	}
	
	private void initPanel() {
		int margin = 50;
		gamePanel = new JPanel();
		gamePanel.setLocation(0, margin);
		gamePanel.setSize(mainWindow.getSize().width, mainWindow.getSize().height - 50 - margin);
		gamePanel.setLayout(new GridLayout(game.getRow(), game.getCol()));
		mainWindow.add(gamePanel);
	}

	private void initFrame() {
		calculateWindowHeight();
		calculateWindowWidth();
		mainWindow = new JFrame();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainWindow.setVisible(true);
		mainWindow.setTitle(game.getName());
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(windowWidth, windowHeigth);
		mainWindow.setLocation((int) (dim.width / 2 - mainWindow.getSize().getWidth() / 2),
				(int) (dim.height / 2 - mainWindow.getSize().getHeight() / 2));
		mainWindow.setLayout(null);
	}

	private void initFields() {
		fields = new ButtonField[game.getRow()][game.getCol()];
		for (int i = 0; i < game.getRow(); ++i) {
			for (int j = 0; j < game.getCol(); ++j) {
				ButtonField newButton = new ButtonField(new Point(i, j));
				game.getField(newButton.getPoint()).setObserver(newButton);
				newButton.addActionListener(new FieldButtonAction(this, newButton));
				fields[i][j] = newButton;
			}
		}
	}

	private void placeFields() {
		for (int i = 0; i < game.getRow(); ++i) {
			for (int j = 0; j < game.getCol(); ++j) {
				gamePanel.add(fields[i][j]);
			}
		}
	}

	public void calculateWindowWidth() {
		windowWidth = BUTTON_WIDTH * game.getCol();
	}

	public void calculateWindowHeight() {
		windowHeigth = BUTTON_HEIGTH * game.getRow() + 100;
	}

	public void initMenuBar() {
		menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		JMenuItem newGameMenuItem = new JMenuItem(new AbstractAction("New Game") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.dispose();
				initGame(10, 10, 10);
			}
		});
		gameMenu.add(newGameMenuItem);
		menuBar.add(gameMenu);
		mainWindow.setJMenuBar(menuBar);
	}

	public void disableButtons() {
		for (int i = 0; i < fields.length; ++i) {
			for (int j = 0; j < fields[i].length; ++j) {
				fields[i][j].setEnabled(false);
			}
		}
	}

	public MineSweeperGame getGame() {
		return game;
	}

}