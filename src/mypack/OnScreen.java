package mypack;

//import java.awt.Color;

import java.awt.Graphics;

import java.util.ArrayList;
import java.util.List;

import mypack.save.Save;
import mypack.save.SaveShape;
import mypack.shapes.Group;
import mypack.shapes.Shape;

public class OnScreen {
	ArrayList<Shape> screenList = new ArrayList<Shape>();
	int activeShapeIndex;
	boolean isAnyCollision = false;
	int index = -1;

	public OnScreen() {
	}

	public void add(Graphics g, Shape.Type shapeType) {
		screenList.add(ShapeMaker.crateShape(g, shapeType));
		activeShapeIndex = screenList.size() - 1;

	}

	public Shape getActiveShape(int activeShapeIndex) {
		return screenList.get(activeShapeIndex);
	}

	public void inc() {
		getActiveShape(activeShapeIndex).inc();

	}

	public void dec() {
		getActiveShape(activeShapeIndex).dec();

	}

	public void nextActiveShape() {
		activeShapeIndex++;
		if (activeShapeIndex > screenList.size() - 1)
			activeShapeIndex = 0;

	}

	public void previouseActiveShape() {
		activeShapeIndex--;
		if (activeShapeIndex < 0)
			activeShapeIndex = screenList.size() - 1;

	}

	public void setActiveShapeInList() {

		for (int i = 0; i < screenList.size(); i++) {
			if (i == activeShapeIndex) {
				screenList.get(i).setActiveShape(true);
			} else {
				screenList.get(i).setActiveShape(false);
			}
		}
	}

	public void remove() {
		if (screenList.size() > 1) {
			screenList.remove(activeShapeIndex);
		}
		activeShapeIndex = screenList.size() - 1;

	}

	public void move(Shape.Direction direction) {
		getActiveShape(activeShapeIndex).move(direction);
		checkCollision();
	}

	private void changeColor() {
		getActiveShape(activeShapeIndex).draw(Shape.generateColor());
		getActiveShape(index).draw(Shape.generateColor());

		for (int i = 0; i < screenList.size(); i++) {
			if (i != activeShapeIndex && i != index) {
				screenList.get(i).draw();
			}
		}

	}

	public void drawAll() {
		if (isAnyCollision) {
			changeColor();
		} else {
			for (Shape shape : screenList) {
				setActiveShapeInList();
				shape.draw();
			}
		}
	}

	private void checkCollision() {
		for (int i = 0; i < screenList.size(); i++) {
			if (i != activeShapeIndex) {
				if (isCollision(screenList.get(activeShapeIndex), screenList.get(i))) {
					isAnyCollision = true;
					index = i;
					break;
				} else {
					isAnyCollision = false;
					index = -1;
				}
			}
		}

	}

	public static boolean isCollision(Shape activeShape, Shape shape) {
		if (activeShape instanceof Group && !(shape instanceof Group)) {
			Group gr = (Group) activeShape;
			for (Shape sp : gr.getList()) {
				if (Shape.isCollision(sp, shape))
					return true;
			}
		}
		if (shape instanceof Group && !(activeShape instanceof Group)) {
			Group gr = (Group) shape;
			for (Shape sp : gr.getList()) {
				if (Shape.isCollision(activeShape, sp))
					return true;
			}
		}
		if (activeShape instanceof Group && shape instanceof Group) {
			Group actGroup = (Group) activeShape;
			Group group = (Group) shape;
			for (Shape actSp : actGroup.getList()) {
				for (Shape sp : group.getList()) {
					if (Shape.isCollision(actSp, sp))
						return true;
				}
			}
		}

		else if (Shape.isCollision(activeShape, shape))
			return true;
		return false;

	}

	public ArrayList<Shape> getList() {
		return screenList;
	}

	public void uniteOrClone(int mouseX, int mouseY, boolean unite) {

		for (int i = 0; i < screenList.size(); i++) {
			Shape shape = screenList.get(i);

			if (shape.isPointIn(mouseX, mouseY)) {

				Group group = new Group();

				group.addShape(screenList.get(activeShapeIndex).copy());
				group.addShape(shape.copy());
			//	group.cleanList();

				if (unite) {
					screenList.remove(screenList.get(activeShapeIndex));
					screenList.remove(shape);
				}

				screenList.add(group);
				activeShapeIndex = screenList.size() - 1;
				drawAll();
				break;

			}
		}
	}

	public Save makeSave() {
		List<SaveShape> savesList = new ArrayList<SaveShape>();

		for (Shape shape : screenList) {
			SaveShape save = SaveShape.createSaveShape(shape);
			savesList.add(save);
		}
		return new Save(savesList, activeShapeIndex);
	}

	public void loadSave(Graphics g, Save save) {
		screenList.clear();
		List<SaveShape> savesList = save.getSavesList();
		activeShapeIndex = save.getActiveShapeIndex();

		for (SaveShape saveShape : savesList) {
			Shape shape = SaveShape.createShape(g, saveShape);
			screenList.add(shape);
		}
		drawAll();

	}

}
