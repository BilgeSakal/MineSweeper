
package game;

import java.util.ArrayList;
import java.util.Random;

public class MineSweeperGame extends Game {

	public static final int GAME_LOSE = -1;
	public static final int GAME_WIN = 1;
	public static final int GAME_NOT_FINISHED = -9;

	public static final int SMALL = 10;
	public static final int MEDIUM = 18;
	public static final int LARGE = 25;

	private int prevRow;
	private int prevCol;
	private int prevMines;

	/**
	 * Number of rows.
	 */
	private int row;

	/**
	 * Number of columns.
	 */
	private int col;

	/**
	 * Number of mines in the game.
	 */
	private int numOfMines;

	/**
	 * If <b>{@code true}</b>, the player stepped on a mine.
	 */
	private boolean steppedMine;

	/**
	 * Total number of fields that the player has stepped.
	 */
	private int totalSteppedFields;

	private int placedFlags;

	private Field[][] mineField;

	private Timer timer;

	private ArrayList<Point> mineLocations;

	public MineSweeperGame(String name, int row, int col, int numOfMines) {
		super(name);
		steppedMine = false;
		mineLocations = new ArrayList<Point>(numOfMines);
		totalSteppedFields = 0;
		placedFlags = 0;
		setRow(row);
		setCol(col);
		setNumOfMines(numOfMines);
		this.prevRow = row;
		this.prevCol = col;
		this.prevMines = numOfMines;
		this.timer = new Timer();
		timer.start();
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
			return GAME_NOT_FINISHED;
		}
	}

	/**
	 * Puts a flag on the field that on location <b>{@code p}</b>. If there is a
	 * flag on the field already then removes the flag from the field.
	 * 
	 * @param p is the location of the field.
	 */
	public void putFlag(Point p) {
		Field field = getField(p);
		if (field.isFlag()) {
			field.setFlag(false);
			--placedFlags;
		} else {
			if (placedFlags < numOfMines) {
				field.setFlag(true);
				++placedFlags;
			}
		}
	}

	/**
	 * Checks if the game is won.
	 * 
	 * @return <b>{@code true}</b> if the player revealed all the fields without
	 *         stepping on a mine. <b>{@code false}</b> otherwise.
	 */
	public boolean isWin() {
		int totalFields = row * col;
		return totalFields - numOfMines == totalSteppedFields;
	}

	/**
	 * Steps the field in the position <b>{@code p}</b>. If there is no mine around
	 * the stepped field, the fields around the stepped field will be stepped as
	 * well.
	 * 
	 * @param p is the location of the field.
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
	 * @param field that adjacent fields to it will be checked if there is mine on
	 *              them.
	 * @return Total number of mines around the <b>{@code field}</b>.
	 */
	private int findMinesAround(Field field) {
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

	/**
	 * Returns the field on location <b>{@code p}</b>.
	 * 
	 * @param p is the location of the field.
	 * @return The field on location <b>{@code p}</b>.
	 */
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
				mineField[i][j] = new Field(new Point(i, j), false);
			}
		}
	}

	/**
	 * Places the mines on the area.
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
			mineLocations.add(new Point(x, y));
			++placed;
		}

	}

	public static int defaultNumOfMines(int row, int col) {
		return row * col / 8;
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

	public int getPrevRow() {
		return prevRow;
	}

	public int getPrevCol() {
		return prevCol;
	}

	public int getPrevMines() {
		return prevMines;
	}

	public int getPlacedFlags() {
		return placedFlags;
	}

	public Timer getTimer() {
		return timer;
	}

	public ArrayList<Point> getMineLocations() {
		return mineLocations;
	}

}
