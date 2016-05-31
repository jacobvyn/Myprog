package mypack.save;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import mypack.ShapeMaker;
import mypack.shapes.Circle;
import mypack.shapes.Group;
import mypack.shapes.Rectangle;
import mypack.shapes.Shape;
import mypack.shapes.Triangle;

public class SaveShape {
	private List<SaveShape> list;
	private int x;
	private int y;
	private int size;
	private Shape.Type saveType;

	public SaveShape() {
	}

	public static SaveShape createSaveShape(Shape shape) {
		SaveShape result = new SaveShape();
		result.x = shape.getX();
		result.y = shape.getY();
		result.size = shape.getSize();

		if (shape instanceof Circle) {
			result.saveType = Shape.Type.Circle;
		}
		if (shape instanceof Rectangle) {
			result.saveType = Shape.Type.Rectangle;
		}
		if (shape instanceof Triangle) {
			result.saveType = Shape.Type.Triangle;
		}
		if (shape instanceof Group) {
			result.saveType=Shape.Type.GROUP;
			result.list = new ArrayList<SaveShape>();
			Group group = (Group) shape;
			
			for (Shape tmpShape : group.getList()) {
				result.list.add(createSaveShape(tmpShape));
			}
		}

		return result;
	}

	public static Shape createShape(Graphics g, SaveShape saveShape) {

		Shape resultShape= ShapeMaker.crateShape(g, saveShape.getSaveType(), saveShape.getX(), saveShape.getY(),saveShape.getSize());
	
		if (saveShape.getSaveType() == Shape.Type.GROUP) {
			Group group = (Group) resultShape;
			List<Shape> groupList = new ArrayList<Shape>();
			for (SaveShape groupSavesMember : saveShape.getList()) {
				Shape groupsMember = createShape(g, groupSavesMember);
				groupList.add(groupsMember);
			}
			group.setList(groupList);
		}

		return resultShape;
	}
	
	
	

	public List<SaveShape> getList() {
		return list;
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

	public Shape.Type getSaveType() {
		return saveType;
	}

}
