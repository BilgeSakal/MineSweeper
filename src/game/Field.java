package game;

public class Field {
	
	private int xPos;
	private int yPos;
	
	private boolean mine;
	
	private boolean stepped;
	
	private int minesAround;
	
	public Field() {
		this.xPos = 0;
		this.yPos = 0;
		this.minesAround = 0;
		this.mine = false;
		this.stepped = false;
	}
	
	public Field(int xPos, int yPos, boolean mine) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.mine = mine;
		this.minesAround = 0;
		this.stepped = false;
	}
	
	public boolean isMine() {
		return mine;
	}
	
	public boolean isStepped() {
		return stepped;
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
	
	public void setMine(boolean mine) {
		this.mine = mine;
	}
	
	public void setStepped(boolean stepped) {
		this.stepped = stepped;
	}
	
	public void setMinesAround(int minesAround) {
		this.minesAround = minesAround;
	}
	
	public int getMinesAround() {
		return minesAround;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}

}
