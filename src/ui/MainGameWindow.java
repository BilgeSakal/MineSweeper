package ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import game.MineSweeperGame;
import game.Point;

public class MainGameWindow {

	/**
	 * Buttons
	 */
	private ButtonField[][] fields;

	private JFrame mainWindow;

	/**
	 * Panel that holds buttons.
	 */
	private JPanel gamePanel;

	/**
	 * Panel that holds information about the game.
	 */
	private JPanel upperPanel;

	private JMenuBar menuBar;

	private MineSweeperGame game;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainGameWindow mgw = new MainGameWindow();
				mgw.initGame(10, 10, 10);
			}
		});
	}

	public MainGameWindow() {
		ButtonField.images = ButtonField.getIcons();
	}

	/**
	 * Initiates the game.
	 * 
	 * @param row        is the number of rows.
	 * @param col        is the number of columns.
	 * @param numOfMines is the number of mines in the game.
	 */
	private void initGame(int row, int col, int numOfMines) {
		game = new MineSweeperGame("Mine Sweeper v1.1", row, col, numOfMines);
		game.startGame();
		initFrame();
		initUpperPanel();
		initGamePanel();
		initMenuBar();
		initFields();
		mainWindow.pack();
		setFrameLocation();
		mainWindow.setVisible(true);
	}

	/**
	 * Initiates game panel.
	 */
	private void initGamePanel() {
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(game.getRow(), game.getCol()));
		mainWindow.add(gamePanel);
	}

	/**
	 * Initiates upper panel.
	 */
	private void initUpperPanel() {
		upperPanel = new JPanel();
		upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.LINE_AXIS));
		JButton bida = new JButton("Bida");
		bida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.dispose();
				initGame(10, 10, 10);
			}
		});
		upperPanel.add(bida);
		mainWindow.add(upperPanel);
	}

	/**
	 * Initiates main window.
	 */
	private void initFrame() {
		mainWindow = new JFrame();
		mainWindow.setResizable(false);
		mainWindow.setTitle(game.getName());
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setLayout(new BoxLayout(mainWindow.getContentPane(), BoxLayout.PAGE_AXIS));
	}

	/**
	 * Place the main window in the middle of the screen.
	 */
	private void setFrameLocation() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainWindow.setLocation((int) (dim.width / 2 - mainWindow.getSize().getWidth() / 2),
				(int) (dim.height / 2 - mainWindow.getSize().getHeight() / 2));
	}

	/**
	 * Creates the fields with their listeners and adds them to the
	 * {@code gamePanel}.
	 */
	private void initFields() {
		fields = new ButtonField[game.getRow()][game.getCol()];
		for (int i = 0; i < game.getRow(); ++i) {
			for (int j = 0; j < game.getCol(); ++j) {
				ButtonField newButton = new ButtonField(new Point(i, j));
				game.getField(newButton.getPoint()).setObserver(newButton);
				newButton.addMouseListener(new FieldMouseAdapter(this, newButton));
				fields[i][j] = newButton;
				gamePanel.add(newButton);
			}
		}
	}

	/**
	 * Creates the menu bar and adds it to the {@code mainWindow}.
	 */
	private void initMenuBar() {
		menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);
		mainWindow.setJMenuBar(menuBar);
	}

	/**
	 * Disable all buttons.
	 */
	public void disableButtons() {
		for (int i = 0; i < fields.length; ++i) {
			for (int j = 0; j < fields[i].length; ++j) {
				game.getField(new Point(i, j)).setStepped(true);
			}
		}
	}

	// getters and setters

	public MineSweeperGame getGame() {
		return game;
	}

	public JFrame getMainWindow() {
		return mainWindow;
	}

}
