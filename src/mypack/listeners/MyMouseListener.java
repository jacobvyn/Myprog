package mypack.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import mypack.OnScreen;

public class MyMouseListener extends MouseAdapter {
	private OnScreen onScreen;
	
	public MyMouseListener(OnScreen onScreen) {
		this.onScreen=onScreen;
		
	}
	
	@Override
	public void mousePressed(MouseEvent me) {

		if (me.isControlDown()) {
			onScreen.uniteOrClone(me.getX(), me.getY(), true);
		}
		if (me.isShiftDown()) {
			onScreen.uniteOrClone(me.getX(), me.getY(), false);
		}

	}

}