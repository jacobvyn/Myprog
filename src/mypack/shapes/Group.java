package mypack.shapes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Group extends Shape {
	ArrayList<Shape> groupList = new ArrayList<Shape>();

	public Group() {
	}

	public Group(ArrayList<Shape> list) {
		groupList.addAll(list);
	}

	public void cleanList() {
		for (int i = 0; i < groupList.size(); i++) {
			if (equals(groupList.get(i), groupList.get(i + 1))) {
				groupList.remove(i + 1);
			}

		}

	}

	public void addShape(Shape shape) {
		if (shape instanceof Group) {
			Group gr = (Group) shape;
			groupList.addAll(gr.getList());

		} else
			groupList.add(shape);
	}

	public void inc() {
		for (Shape shape : groupList) {
			shape.inc();
		}
	}

	public void dec() {
		for (Shape shape : groupList) {
			shape.dec();
		}
	}

	@Override
	public void move(Direction dir) {
		for (Shape shape : groupList) {
			shape.move(dir);
		}
	}

	@Override
	public void draw() {
		for (Shape shape : groupList) {
			shape.draw(Color.CYAN);
		}
	}

	public ArrayList<Shape> getList() {
		return groupList;

	}

	@Override
	public void draw(Color color) {
		for (Shape shape : groupList) {
			shape.draw(color);
		}
	}

	@Override
	public void clearRect() {
		for (Shape shape : groupList) {
			shape.clearRect();
		}
	}

	@Override
	public Shape copy() {
		ArrayList<Shape> copyList = new ArrayList<Shape>();
		for (Shape shape : groupList) {
			copyList.add(shape.copy());
		}
		return new Group(copyList);
	}

	public void setActiveShape(boolean active) {
		for (Shape shape : groupList) {
			shape.setActiveShape(active);
		}
	}

	@Override
	public boolean isPointIn(int mouseX, int mouseY) {
		for (Shape shape : groupList) {
			if (shape.isPointIn(mouseX, mouseY)) {
				return true;
			}
		}
		return false;

	}

	@Override
	public void setColor(Color color) {
		for (Shape shape : groupList) {
			shape.setColor(color);
		}
	}

	public void setList(List<Shape> list) {
		groupList.clear();

		for (Shape shape : list) {
			addShape(shape);
		}

	}

}
