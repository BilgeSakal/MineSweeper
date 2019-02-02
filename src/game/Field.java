package game;

import ui.ButtonField;

public class Field {

	/**
	 * Button to be updated when a change is occurred.
	 */
	private ButtonField observer;

	/**
	 * Location of the field.
	 */
	private Point point;

	/**
	 * If <b>{@code true}</b> then there is a mine on the field,
	 * <b>{@code false}</b> otherwise.
	 */
	private boolean mine;

	/**
	 * If <b>{@code true}</b> then the field was stepped before,
	 * <b>{@code false}</b> otherwise.
	 */
	private boolean stepped;

	/**
	 * If <b>{@code true}</b> the there is a flag on the field, <b>{@code false}</b>
	 * otherwise.
	 */
	private boolean flag;

	/**
	 * Number of mines around the field.
	 */
	private int minesAround;

	public Field(Point point, boolean mine) {
		this.point = point;
		this.mine = mine;
		this.minesAround = 0;
		this.flag = false;
		this.stepped = false;
	}

	/**
	 * If the field was not stepped before or there is not a flag on the field,
	 * steps on the field. Notifies the button about the changes.
	 * 
	 * @param minesAround is to set the number of mines around the field.
	 */
	public void step(int minesAround) {
		if (!isFlag() || !isStepped()) {
			setStepped(true);
			setMinesAround(minesAround);
			notifyObserver();
		}
	}

	/**
	 * 
	 * @return <b>{@code true}</b> if there is a mine on the field,
	 *         <b>{@code false}</b> otherwise.
	 */
	public boolean isMine() {
		return mine;
	}

	/**
	 * 
	 * @return <b>{@code true}</b> if there is a flag on the field,
	 *         <b>{@code false}</b> otherwise.
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * 
	 * @return <b>{@code true}</b> if the field was stepped before,
	 *         <b>{@code false}</b> otherwise.
	 */
	public boolean isStepped() {
		return stepped;
	}

	/**
	 * Notifies the button.
	 */
	private void notifyObserver() {
		observer.update(this);
	}

	// getters and setters

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
		notifyObserver();
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
