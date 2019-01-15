package game;

import java.util.Random;

public class MineSweeperGame {

	private static final int MAX_ROW = 50;
	private static final int MAX_COL = 50;

	private static final int MIN_ROW = 5;
	private static final int MIN_COL = 5;
	
	private static final int MIN_MINES = 5;

	private int row;
	private int col;

	private Field[][] area;
	
	private int numOfMines;

	public MineSweeperGame(int row, int col, int numOfMines) throws Exception {
		setRow(row);
		setCol(col);
		setNumOfMines(numOfMines);
		setArea();
		placeMines();
	}
	
	public synchronized void step(int row, int col) {
		Field steppedField = area[row][col];
		if(!steppedField.isStepped()) {
			
			//find the number of mines around
			int minesAround = findMinesAround(steppedField);
			
			steppedField.setMinesAround(minesAround);
			
			//field is stepped
			steppedField.setStepped(true);
			
			//notify the button about changes
			notifyButton(steppedField);
			
			//if there is no mines around, then step the adjacent fields.
			if(minesAround == 0) {
				stepAround(steppedField);
			}
		}
	}
	
	private void notifyButton(Field field) {
		// TODO
	}
	
	private void stepAround(Field field) {
		// TODO
	}
	
	private int findMinesAround(Field field) {
		// TODO
		return 0;
	}

	/**
	 * Creates the area matrix and fields.
	 */
	private void setArea() {
		area = new Field[row][col];
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < col; ++j) {
				area[i][j] = new Field(i, j, false);
			}
		}
	}

	/**
	 * Places the mines to the area.
	 */
	private void placeMines() {
		Random rndm = new Random();
		int placed = 0;
		while (placed < numOfMines) {
			int x;
			int y;
			do {
				x = rndm.nextInt(row);
				y = rndm.nextInt(col);
			} while (area[x][y].isMine());
			area[x][y].setMine(true);
			++placed;
		}

	}

	/**
	 * Checks if {@code row} is in valid range.
	 * 
	 * @param row value that is going to be checked.
	 * @return {@code true} if {@code row} is in valid range, {@code false}
	 *         otherwise.
	 */
	private boolean isValidRow(int row) {
		if (row < MIN_ROW || row > MAX_ROW)
			return false;
		return true;
	}

	/**
	 * Checks if {@code col} is in valid range.
	 * 
	 * @param col value that is going to be checked.
	 * @return {@code true} if {@code col} is in valid range, {@code false}
	 *         otherwise.
	 */
	private boolean isValidCol(int col) {
		if (col < MIN_COL || col > MAX_COL)
			return false;
		return true;
	}

	/**
	 * Checks if {@code numOfMines} is in valid range.
	 * @param numOfMines value that is going to be checked.
	 * @return {@code true} if {@code numOfMines} is in valid range, {@code false}
	 *         otherwise.
	 */
	private boolean isValidNumOfMines(int numOfMines) {
		if (numOfMines <= (row * col) / 2 && numOfMines >= MIN_MINES)
			return true;
		return false;
	}

	public void setRow(int row) throws Exception {
		if (isValidRow(row))
			throw new Exception();
		else
			this.row = row;
	}

	public void setCol(int col) throws Exception {
		if (isValidCol(col))
			throw new Exception();
		else
			this.col = col;
	}

	public void setNumOfMines(int numOfMines) throws Exception {
		if (isValidNumOfMines(numOfMines))
			this.numOfMines = numOfMines;
		else
			throw new Exception();
	}

}
