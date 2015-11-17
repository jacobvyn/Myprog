package mypack.save;

import java.util.ArrayList;
import java.util.List;

public class Save {
	private List<SaveShape> savesList = new ArrayList<SaveShape>();
	private int activeShapeIndex;

	public Save(List<SaveShape> list, int activeShapeIndex) {
		this.savesList = list;
		this.activeShapeIndex = activeShapeIndex;

	}

	public int getActiveShapeIndex() {
		return activeShapeIndex;
	}

	public List<SaveShape> getSavesList() {
		return savesList;
	}

}
