package ui;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import game.Field;
import game.Point;

public class ButtonField extends JButton {

	private static final long serialVersionUID = 1L;

	private static final int BUTTON_WIDTH = 20;
	private static final int BUTTON_HEIGTH = 20;

	/**
	 * Button images.
	 */
	public static BufferedImage[] images = null;

	/**
	 * Location of the button.
	 */
	private Point point;

	public ButtonField(Point point) {
		this.point = point;
		this.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGTH));
		this.setIcon(new ImageIcon(images[ButtonIcon.DEFAULT.ordinal()]));
	}

	/**
	 * Updates itself according to the changes in <b>{@code field}</b>
	 * 
	 * @param field
	 */
	public void update(Field field) {
		if (field.isStepped()) {
			// setEnabled(false);
			if (field.getMinesAround() >= 0) {
				this.setIcon(new ImageIcon(images[field.getMinesAround()]));
			} else if (field.getMinesAround() == -1) {
				this.setIcon(new ImageIcon(images[ButtonIcon.MINE.ordinal()]));
			}
		} else {
			if (field.isFlag()) {
				this.setIcon(new ImageIcon(images[ButtonIcon.FLAG.ordinal()]));
			} else {
				this.setIcon(new ImageIcon(images[ButtonIcon.DEFAULT.ordinal()]));
			}
		}
	}

	/**
	 * Reads the images for button icons and copies them into the array.
	 * 
	 * @return the image-array.
	 */
	public static BufferedImage[] getIcons() {
		BufferedImage[] icons = new BufferedImage[12];
		BufferedImage img = new BufferedImage(BUTTON_WIDTH, BUTTON_HEIGTH, BufferedImage.TYPE_INT_RGB);
		try {
			img = ImageIO.read(ButtonField.class.getResource("/flag.png"));
			img = ImageManipulation.resize(img, BUTTON_WIDTH, BUTTON_HEIGTH);
			icons[ButtonIcon.FLAG.ordinal()] = img;

			img = ImageIO.read(ButtonField.class.getResource("/mine.png"));
			img = ImageManipulation.resize(img, BUTTON_WIDTH, BUTTON_HEIGTH);
			icons[ButtonIcon.MINE.ordinal()] = img;

			img = ImageIO.read(ButtonField.class.getResource("/default.png"));
			img = ImageManipulation.resize(img, BUTTON_WIDTH, BUTTON_HEIGTH);
			icons[ButtonIcon.DEFAULT.ordinal()] = img;

			img = ImageIO.read(ButtonField.class.getResource("/0.png"));
			img = ImageManipulation.resize(img, BUTTON_WIDTH, BUTTON_HEIGTH);
			icons[ButtonIcon.ZERO.ordinal()] = img;

			img = ImageIO.read(ButtonField.class.getResource("/1.png"));
			img = ImageManipulation.resize(img, BUTTON_WIDTH, BUTTON_HEIGTH);
			icons[ButtonIcon.ONE.ordinal()] = img;

			img = ImageIO.read(ButtonField.class.getResource("/2.png"));
			img = ImageManipulation.resize(img, BUTTON_WIDTH, BUTTON_HEIGTH);
			icons[ButtonIcon.TWO.ordinal()] = img;

			img = ImageIO.read(ButtonField.class.getResource("/3.png"));
			img = ImageManipulation.resize(img, BUTTON_WIDTH, BUTTON_HEIGTH);
			icons[ButtonIcon.THREE.ordinal()] = img;

			img = ImageIO.read(ButtonField.class.getResource("/4.png"));
			img = ImageManipulation.resize(img, BUTTON_WIDTH, BUTTON_HEIGTH);
			icons[ButtonIcon.FOUR.ordinal()] = img;

			img = ImageIO.read(ButtonField.class.getResource("/5.png"));
			img = ImageManipulation.resize(img, BUTTON_WIDTH, BUTTON_HEIGTH);
			icons[ButtonIcon.FIVE.ordinal()] = img;

			img = ImageIO.read(ButtonField.class.getResource("/6.png"));
			img = ImageManipulation.resize(img, BUTTON_WIDTH, BUTTON_HEIGTH);
			icons[ButtonIcon.SIX.ordinal()] = img;

			img = ImageIO.read(ButtonField.class.getResource("/7.png"));
			img = ImageManipulation.resize(img, BUTTON_WIDTH, BUTTON_HEIGTH);
			icons[ButtonIcon.SEVEN.ordinal()] = img;

			img = ImageIO.read(ButtonField.class.getResource("/8.png"));
			img = ImageManipulation.resize(img, BUTTON_WIDTH, BUTTON_HEIGTH);
			icons[ButtonIcon.EIGHT.ordinal()] = img;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return icons;
	}

	// getters and setters

	public Point getPoint() {
		return point;
	}

}
