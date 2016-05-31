package mypack.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import mypack.MyFirst;
import mypack.shapes.Shape;
import mypack.shapes.Shape.Direction;

public class MyKeyAdapter extends KeyAdapter {

	private MyFirst myFirst;

	public MyKeyAdapter(MyFirst myFirst) {
		this.myFirst = myFirst;

	}

	@Override
	public void keyPressed(KeyEvent ke) {

		int key = ke.getKeyCode();
		switch (key) {
		case KeyEvent.VK_1:
			myFirst.getOnScreen().add(myFirst.getG1(), Shape.Type.Circle);
			break;

		case KeyEvent.VK_2:
			myFirst.getOnScreen().add(myFirst.getG1(), Shape.Type.Rectangle);
			break;

		case KeyEvent.VK_3:
			myFirst.getOnScreen().add(myFirst.getG1(), Shape.Type.Triangle);
			break;

		case KeyEvent.VK_RIGHT:
			myFirst.getOnScreen().move(Direction.RIGHT);
			break;

		case KeyEvent.VK_LEFT:
			myFirst.getOnScreen().move(Direction.LEFT);
			break;

		case KeyEvent.VK_UP:
			myFirst.getOnScreen().move(Direction.UP);
			break;

		case KeyEvent.VK_DOWN:
			myFirst.getOnScreen().move(Direction.DOWN);
			break;

		case KeyEvent.VK_F1:
			myFirst.getOnScreen().inc();
			break;

		case KeyEvent.VK_F2:
			myFirst.getOnScreen().dec();
			break;

		case KeyEvent.VK_PAGE_UP:
			myFirst.getOnScreen().nextActiveShape();
			break;

		case KeyEvent.VK_PAGE_DOWN:
			myFirst.getOnScreen().previouseActiveShape();
			break;

		case KeyEvent.VK_DELETE:
			myFirst.getOnScreen().remove();
			break;

		case KeyEvent.VK_S:
			if (ke.isControlDown())
				myFirst.saveScreen();
			break;

		case KeyEvent.VK_L:
			myFirst.loadScreen(new File("Save.txt"));
			break;
		}

		myFirst.repaintAll();;

	}
}
