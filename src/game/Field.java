package game;

public class Field {
	
	private int xPos;
	private int yPos;
	
	private boolean mine;
	
	public Field() {
		this.xPos = 0;
		this.yPos = 0;
		this.mine = false;
	}
	
	public Field(int xPos, int yPos, boolean mine) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.mine = mine;
	}
	
	public boolean isMine() {
		return mine;
	}
	
	//getters and setters
	
	public void setXPos(int xPos) throws IndexOutOfBoundsException {
		if(xPos >= 0)
			this.xPos = xPos;
		else
			throw new IndexOutOfBoundsException();
	}
	
	public void setYPos(int yPos) throws IndexOutOfBoundsException {
		if(yPos >= 0)
			this.yPos = yPos;
		else
			throw new IndexOutOfBoundsException();
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}

}
