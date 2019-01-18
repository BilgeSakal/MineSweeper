package game;

import ui.ButtonField;

public class Field {

	private ButtonField observer;

	private Point point;

	private boolean mine;

	private boolean stepped;

	private int minesAround;

	public Field(int xPos, int yPos, boolean mine) {
		point = new Point(xPos, yPos);
		this.mine = mine;
		this.minesAround = 0;
		this.stepped = false;
	}

	public void step(int minesAround) {
		setStepped(true);
		setMinesAround(minesAround);
		notifyObserver();
	}

	public boolean isMine() {
		return mine;
	}

	public boolean isStepped() {
		return stepped;
	}

	private void notifyObserver() {
		observer.update(this);
	}

	// getters and setters

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public void setStepped(boolean stepped) {
		this.stepped = stepped;
	}

	public void setMinesAround(int minesAround) {
		this.minesAround = minesAround;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setObserver(ButtonField observer) {
		this.observer = observer;
	}

	public Point getPoint() {
		return point;
	}

	public int getMinesAround() {
		return minesAround;
	}

}
