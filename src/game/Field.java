package game;

public class Field {
	
	private Point location;
	
	private boolean mine;
	
	private boolean stepped;
	
	private int minesAround;
	
	public Field() {
		location = new Point();
		this.minesAround = 0;
		this.mine = false;
		this.stepped = false;
	}
	
	public Field(int xPos, int yPos, boolean mine) {
		location = new Point(xPos, yPos);
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
	
	public void setMine(boolean mine) {
		this.mine = mine;
	}
	
	public void setStepped(boolean stepped) {
		this.stepped = stepped;
	}
	
	public void setMinesAround(int minesAround) {
		this.minesAround = minesAround;
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	
	public Point getLocation() {
		return location;
	}
	
	public int getMinesAround() {
		return minesAround;
	}

}
