package game;

public class Field {
	
	private int xPos;
	private int yPos;
	
	private boolean mine;
	
	public Field(int xPos, int yPos, boolean mine) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.mine = mine;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public boolean isMine() {
		return mine;
	}

}
