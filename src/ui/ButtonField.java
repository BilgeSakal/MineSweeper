package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import game.Field;
import game.Point;

public class ButtonField extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point point;

	public ButtonField(Point point) {
		this.point = point;
		setFont(new Font("Arial", Font.BOLD, 20));
	}

	public Point getPoint() {
		return point;
	}

	public void update(Field field) {
		if (field.isStepped()) {
			this.setEnabled(false);
			if (field.getMinesAround() > 0) {
				this.setBackground(Color.LIGHT_GRAY);
				this.setOpaque(true);
				this.setText(field.getMinesAround() + "");
			} else if (field.getMinesAround() == -1) {
				this.setBackground(Color.RED);
				this.setOpaque(true);
				this.setText("*");
			} else if (field.getMinesAround() == 0) {
				this.setBackground(Color.LIGHT_GRAY);
				this.setOpaque(true);
			}
		}
	}

}
