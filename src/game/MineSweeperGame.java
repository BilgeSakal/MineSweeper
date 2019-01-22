package game;

import java.util.ArrayList;
import java.util.Random;

public class MineSweeperGame extends Game {

	public static final int GAME_LOSE = -1;
	public static final int GAME_WIN = 1;
	
	public static final int SMALL = 10;
	public static final int MEDIUM = 18;
	public static final int LARGE = 25;

	private int row;
	private int col;

	private int numOfMines;

	private boolean steppedMine;

	private int totalSteppedFields;

	private Field[][] mineField;

	public MineSweeperGame(String name, int row, int col, int numOfMines) {
		super(name);
		steppedMine = false;
		totalSteppedFields = 0;
		setRow(row);
		setCol(col);
		setNumOfMines(numOfMines);
	}

	@Override
	public void startGame() {
		initMineField();
		placeMines();
	}

	@Override
	public int isFinished() {
		if (steppedMine) {
			return GAME_LOSE;
		} else if (isWin()) {
			return GAME_WIN;
		} else {
			return Game.NOT_FINISHED;
		}
	}

	public boolean isWin() {
		int totalFields = row * col;
		return totalFields - numOfMines == totalSteppedFields;
	}

	/**
	 * Steps the field in the position <b>{@code p}</b>. If there is no mine around
	 * the stepped field, the fields around the stepped field will be stepped.
	 * 
	 * @param p indicates the location of the field.
	 */
	public void stepField(Point p) {
		Field steppedField = mineField[p.getX()][p.getY()];
		if (!steppedField.isStepped()) {
			if (steppedField.isMine()) {
				steppedMine = true;
			}
			// find the number of mines around
			int minesAround = findMinesAround(steppedField);

			// step on field
			steppedField.step(minesAround);

			// if there is no mines around, then step the adjacent fields.
			if (minesAround == 0) {
				stepAround(steppedField);
			}
			++totalSteppedFields;
		}
	}

	/**
	 * Steps the fields around the <b>{@code field}</b>.
	 * 
	 * @param field that adjacent fields to it will be stepped.
	 */
	private void stepAround(Field field) {
		// get the position of the adjacent fields.
		ArrayList<Point> adjacentLocations = getAdjacentFieldLocations(field);

		// step the adjacent fields.
		for (Point p : adjacentLocations) {
			stepField(p);
		}
	}

	/**
	 * Finds the total number of mines around the <b>{@code field}</b>.
	 * 
	 * @param field that adjacent fields to it will be checked if they contains
	 *              mine.
	 * @return Total number of mines around the <b>{@code field}</b>.
	 */
	public int findMinesAround(Field field) {
		if (field.isMine())
			return -1;

		Point p = field.getPoint();

		int mines = 0;

		int xPlus = p.getX() + 1;
		int xMinus = p.getX() - 1;
		int yPlus = p.getY() + 1;
		int yMinus = p.getY() - 1;

		// check down
		if (p.getX() < row - 1) {
			if (p.getY() < col - 1) {
				if (getField(new Point(xPlus, yPlus)).isMine())
					++mines;
			}
			if (p.getY() > 0) {
				if (getField(new Point(xPlus, yMinus)).isMine())
					++mines;
			}
			if (getField(new Point(xPlus, p.getY())).isMine())
				++mines;
		}
		// check up
		if (p.getX() > 0) {
			if (p.getY() < col - 1) {
				if (getField(new Point(xMinus, yPlus)).isMine())
					++mines;
			}
			if (p.getY() > 0) {
				if (getField(new Point(xMinus, yMinus)).isMine())
					++mines;
			}
			if (getField(new Point(xMinus, p.getY())).isMine())
				++mines;
		}
		// check left and right
		if (p.getY() > 0) {
			if (getField(new Point(p.getX(), yMinus)).isMine())
				++mines;
		}
		if (p.getY() < col - 1) {
			if (getField(new Point(p.getX(), yPlus)).isMine())
				++mines;
		}
		return mines;
	}

	/**
	 * Finds the locations of the fields that are adjacent to <b>{@code field}</b>.
	 * 
	 * @param field
	 * @return The locations of the fields that are adjacent to
	 *         <b>{@code field}</b>.
	 */
	private ArrayList<Point> getAdjacentFieldLocations(Field field) {
		ArrayList<Point> adjacentLocations = new ArrayList<Point>();

		Point p = field.getPoint();

		int xPlus = p.getX() + 1;
		int xMinus = p.getX() - 1;
		int yPlus = p.getY() + 1;
		int yMinus = p.getY() - 1;

		// check down
		if (p.getX() < row - 1) {
			if (p.getY() < col - 1) {
				adjacentLocations.add(new Point(xPlus, yPlus));
			}
			if (p.getY() > 0) {
				adjacentLocations.add(new Point(xPlus, yMinus));
			}
			adjacentLocations.add(new Point(xPlus, p.getY()));
		}
		// check up
		if (p.getX() > 0) {
			if (p.getY() < col - 1) {
				adjacentLocations.add(new Point(xMinus, yPlus));
			}
			if (p.getY() > 0) {
				adjacentLocations.add(new Point(xMinus, yMinus));
			}
			adjacentLocations.add(new Point(xMinus, p.getY()));
		}
		// check left and right
		if (p.getY() > 0) {
			adjacentLocations.add(new Point(p.getX(), yMinus));
		}
		if (p.getY() < col - 1) {
			adjacentLocations.add(new Point(p.getX(), yPlus));
		}

		return adjacentLocations;
	}

	public Field getField(Point p) {
		return mineField[p.getX()][p.getY()];
	}

	/**
	 * Creates the area matrix and fields.
	 */
	private void initMineField() {
		mineField = new Field[row][col];
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < col; ++j) {
				mineField[i][j] = new Field(i, j, false);
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
			} while (mineField[x][y].isMine());
			System.out.println(x + " " + y);
			mineField[x][y].setMine(true);
			++placed;
		}

	}

	// getters and setters

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setNumOfMines(int numOfMines) {
		this.numOfMines = numOfMines;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getNumOfMines() {
		return numOfMines;
	}

}
