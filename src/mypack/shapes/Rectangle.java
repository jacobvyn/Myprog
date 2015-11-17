package mypack.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {
	Graphics g;

	public Rectangle(Graphics g, int x, int y, int size) {
		super(x, y, size);
		this.g = g;

	}

	@Override
	public void draw() {
		g.setColor(getColor(Shape.Type.Rectangle));

		if (active) {
			g.fillRect(x, y, size, size);
		} else
			g.drawRect(x, y, size, size);
	}

	@Override
	public void draw(Color color) {
		g.setColor(color);

		if (active) {
			g.fillRect(x, y, size, size);
		} else
			g.drawRect(x, y, size, size);
	}

	@Override
	public Shape copy() {
		return new Rectangle(g, x, y, size);
	}

	public void clearRect() {
		g.clearRect(x, y, size, size);
	}

}
