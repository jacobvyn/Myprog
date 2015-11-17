package mypack.shapes;

import java.awt.Color;

public abstract class Shape {

	public static enum Type {
		Circle, Rectangle, Triangle, GROUP
	};

	public static enum Direction {
		UP, DOWN, LEFT, RIGHT
	};

	public static final int SPEED = 10;
	public static final int INC_STEP = 5;
	public static final int DEFAULT_SIZE = 40;
	public static final int MAX_SIZE = 150;
	public static final int MIN_SIZE = 10;

	protected int x;
	protected int y;
	protected int size;
	protected boolean active;
	protected Color color = generateColor();

	public Shape() {
	}

	public Shape(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;

	}

	public abstract void draw();

	public abstract void draw(Color color);

	public abstract Shape copy();

	public abstract void clearRect();

	public void move(Shape.Direction dir) {
		switch (dir) {
		case UP:
			y -= SPEED;
			break;

		case DOWN:
			y += SPEED;
			break;

		case RIGHT:
			x += SPEED;
			break;

		case LEFT:
			x -= SPEED;
			break;
		}
	}

	public void inc() {
		if (size < MAX_SIZE) {
			size += INC_STEP;
		} else {
			size = MAX_SIZE;
		}

	}

	public void dec() {
		if (size > MIN_SIZE) {
			size -= INC_STEP;
		} else {
			size = MIN_SIZE;
		}

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSize() {
		return size;
	}

	public void setActiveShape(boolean active) {
		this.active = active;
	}

	public static Color generateColor() {

		int color = (int) (Math.random() * 7);
		switch (color) {
		case 0:
			return new Color(0, 0, 128);

		case 1:
			return new Color(0, 191, 255);

		case 2:
			return new Color(0, 100, 0);

		case 3:
			return new Color(173, 255, 47);

		case 4:
			return new Color(178, 34, 34);

		case 5:
			return new Color(255, 165, 0);

		case 6:
			return new Color(255, 20, 147);

		default:
			return Color.black;
		}

	}

	public boolean isPointIn(int mouseX, int mouseY) {
		return between(mouseX, x, x + size) && between(mouseY, y, y + size);
	}

	public static boolean between(int check, int x1, int x2) {
		return check >= x1 && check <= x2;
	}

	public static boolean isCollision(Shape activeShape, Shape shape) {
		int activeX = activeShape.getX() + activeShape.getSize()/2;
		int activeY = activeShape.getY() + activeShape.getSize()/2;
		int x = shape.getX() + shape.getSize()/2;
		int y = shape.getY() + shape.getSize()/2;

		int distanceBetweenShapes = (activeShape.getSize()+shape.getSize())/2;
		
		if (Math.abs(activeX - x) < distanceBetweenShapes && Math.abs(activeY - y) < distanceBetweenShapes) {
			return true;
		}
		return false;

	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor(Shape.Type shapeType) {

		if (shapeType == Shape.Type.Circle) {
			return Color.RED;
		}
		if (shapeType == Shape.Type.Rectangle) {
			return Color.GREEN;
		} else
			return Color.BLUE;

	}

	public Color getColor() {
		return color;
	}
	
	
	public static boolean equals(Shape shape1, Shape shape2) {
		if (shape1.x == shape2.x   &&   shape1.y == shape2.y   &&   shape1.size == shape2.size  && shape1.getClass() == shape2.getClass()  ){
			return  true;
		}
		return false;
	
	}

}
