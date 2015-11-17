package mypack.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {
	Graphics g;

	public Circle(Graphics g, int x, int y, int size) {
		super(x, y, size);
		this.g = g;

	}

	@Override
	public void draw() {
		g.setColor(getColor(Shape.Type.Circle));

		if (active) {
			g.fillOval(x, y, size, size);
		} else
			g.drawOval(x, y, size, size);

	}

	@Override
	public void draw(Color color) {
		g.setColor(color);

		if (active) {
			g.fillOval(x, y, size, size);
		} else
			g.drawOval(x, y, size, size);

	}

	@Override
	public void clearRect() {
		g.clearRect(x, y, size, size);

	}

	@Override
	public Shape copy() {
		return new Circle(g, x, y, size);

	}

}
