package ui;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainGameWindow {

	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGTH = 400;

	private static final int SMALL = 10;
	private static final int MEDIUM = 20;
	private static final int LARGE = 30;

	private static final int DEFAULT_ROW = MEDIUM;
	private static final int DEFAULT_COL = MEDIUM;
	
	private static final int DEFAULT_MINES = DEFAULT_COL * DEFAULT_ROW / 4;

	private int prevRow = DEFAULT_ROW;
	private int prevCol = DEFAULT_COL;
	
	
	
	private JButton[][] fields;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				JFrame mainWindow = new JFrame();
				
				initFrame(mainWindow);
				
				initMenuBar(mainWindow);
				
			}
		});
	}
	
	public static void initFrame(JFrame frame) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setVisible(true);
		frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGTH);
		frame.setLocation((int) (dim.width / 2 - frame.getSize().getWidth() / 2),
				(int) (dim.height / 2 - frame.getSize().getHeight() / 2));
	}

	public static void initMenuBar(JFrame frame) {
		JMenuBar menuBar = new JMenuBar();

		JMenu gameMenu = new JMenu("Game");
		JMenuItem newGameMenuItem = new JMenuItem("New Game");
		JMenuItem smallMenuItem = new JMenuItem("Small " + SMALL + "x" + SMALL);
		JMenuItem mediumMenuItem = new JMenuItem("Medium " + MEDIUM + "x" + MEDIUM);
		JMenuItem largeMenuItem = new JMenuItem("Large " + LARGE + "x" + LARGE);
		JMenuItem customMenuItem = new JMenuItem("Custom Size");
		gameMenu.add(newGameMenuItem);
		gameMenu.add(smallMenuItem);
		gameMenu.add(mediumMenuItem);
		gameMenu.add(largeMenuItem);
		gameMenu.add(customMenuItem);
		
		menuBar.add(gameMenu);
		
		frame.setJMenuBar(menuBar);
	}

}
