package game;

public abstract class Game {
	
	public static final int NOT_FINISHED = -9;
	
	protected String name;
	
	public Game(String name) {
		this.name = name;
	}

	/**
	 * Starts the game.
	 */
	public abstract void startGame();
	
	/**
	 * Checks if the game is finished or not.
	 * @return {@code NOT_FINISHED} if the game is not finished.
	 */
	public abstract int isFinished();
	
	//getters and setters
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
