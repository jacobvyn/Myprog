package mypack;

import java.awt.Graphics;

import mypack.shapes.Circle;
import mypack.shapes.Group;
import mypack.shapes.Rectangle;
import mypack.shapes.Shape;
import mypack.shapes.Triangle;

public class ShapeMaker {
	Graphics g;

	public ShapeMaker(Graphics g) {
		this.g = g;

	}

	public void cleanScreen(int width, int height) {
		g.clearRect(0, 0, width, height);

	}

	public static Shape crateShape(Graphics g, Shape.Type shapeType) {
		switch (shapeType) {
		case Circle:
			return new Circle(g, 50, 120, Shape.DEFAULT_SIZE);

		case Rectangle:
			return new Rectangle(g, 50, 120, Shape.DEFAULT_SIZE);

		case Triangle:
			return new Triangle(g, 50, 120, Shape.DEFAULT_SIZE);

		case GROUP:
			return new Group();

		default:
			return null;
		}
	}

	public static Shape crateShape(Graphics g, Shape.Type saveType, int x, int y, int size) {

		switch (saveType) {
		case Circle:
			return new Circle(g, x, y, size);

		case Rectangle:
			return new Rectangle(g, x, y, size);

		case Triangle:
			return new Triangle(g, x, y, size);

		case GROUP:
			return new Group();

		default:
			return null;
		}
	}
	/*
	public static Shape crateShape(Graphics g, Shape.Type saveType, int x, int y, int size) {

		switch (saveType) {
		case Circle:
			return new Circle(g, x, y, size);

		case Rectangle:
			return new Rectangle(g, x, y, size);

		case Triangle:
			return new Triangle(g, x, y, size);

		case GROUP:
			return new Group();

		default:
			return null;
		}
	}
*/
}
