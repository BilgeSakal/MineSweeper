package game.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import game.Field;

class TestField {

	@Test
	void testCase0() {
		Field f = new Field();
		int xPos = 5;
		f.setXPos(xPos);
		int expected = xPos;
		int actual = f.getXPos();
		assertEquals(expected, actual);
	}
	
	@Test
	void testCase1() {
		Field f = new Field();
		int yPos = 3;
		f.setYPos(yPos);
		int expected = yPos;
		int actual = f.getYPos();
		assertEquals(expected, actual);
	}
	
	@Test
	void testCase2() {
		Field f = new Field(0, 0, true);
		boolean expected = true;
		boolean actual = f.isMine();
		assertEquals(expected, actual);
	}
	
	@Test
	void testCase3() {
		Field f = new Field();
		int xPos = -5;
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> f.setXPos(xPos));
	}
	
	@Test
	void testCase4() {
		Field f = new Field();
		int yPos = -3;
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> f.setYPos(yPos));
	}

}
