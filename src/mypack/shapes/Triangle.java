package mypack.shapes;

import java.awt.Color;

import java.awt.Graphics;

public class Triangle extends Shape {
	Graphics g;

	public Triangle(Graphics g, int x, int y, int size) {
		super(x, y, size);
		this.g = g;
	}

	@Override
	public void draw() {
		g.setColor(getColor(Shape.Type.Triangle));

		int[] arrX = { x, x + size / 2, x + size };
		int[] arrY = { y + size, y, y + size };

		if (active) {
			g.fillPolygon(arrX, arrY, 3);
		} else {
			g.drawPolygon(arrX, arrY, 3);
		}
	}

	@Override
	public void draw(Color color) {
		g.setColor(color);

		int[] arrX = { x, x + size / 2, x + size };
		int[] arrY = { y + size, y, y + size };

		if (active) {
			g.fillPolygon(arrX, arrY, 3);
		} else {
			g.drawPolygon(arrX, arrY, 3);
		}
	}

	public void clearRect() {
		g.clearRect(x, y, size, size);
	}

	@Override
	public Shape copy() {
		return new Triangle(g, x, y, size);
	}

}
