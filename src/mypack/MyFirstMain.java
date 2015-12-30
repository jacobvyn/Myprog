package mypack;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mypack.save.Save;
//import mypack.shapes.Shape.Type;
import mypack.shapes.Shape;
import mypack.shapes.Shape.Direction;

public class MyFirstMain extends Frame implements KeyListener {

	OnScreen onScreen = new OnScreen();
	Graphics g1;
	Canvas canvas;

	public MyFirstMain() {
		setSize(new Dimension(800, 800));
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
		setFont(font);
		setTitle("My first programm with GUI");

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				if (me.isControlDown()) {
					onScreen.uniteOrClone(me.getX(), me.getY(), true);
				}
				if (me.isShiftDown()) {
					onScreen.uniteOrClone(me.getX(), me.getY(), false);
				}
			}

		});
		addKeyListener(this);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	public static void main(String[] args) {
		MyFirstMain m = new MyFirstMain();
		m.setVisible(true);

		File file = new File("Save.txt");
		if (file.exists())
			m.loadScreen();

	}

	public void paint(Graphics g) {
		g1 = g.create();
		g1.drawString("1- add circle, 2 - rectangle, 3- triangle, DEL -delete", 30, 50);
		g1.drawString("PgUP/PgDN - next shape, F1/F2 - increase/decrease, arrows- move", 30, 70);
		g1.drawString("Cntrl - make group, Shift - clone", 30, 90);
		g1.drawString("Ctrl+S - save, L - load", 30, 110);

		onScreen.drawAll();

	}

	public void loadScreen() {
		try {
			File f = new File("Save.txt");
			String jsonString = FileUtils.readFileToString(f, "windows-1251");
			Gson gson = new GsonBuilder().create();

			Save save = gson.fromJson(jsonString, Save.class);
			onScreen.loadSave(g1, save);

		} catch (IOException e) {
			System.out.println("Can't load.");
		}

	}

	public void saveScreen() {
		Gson gson = new Gson();
		String jsonString = gson.toJson(onScreen.makeSave());

		File f = new File("Save.txt");
		try {
			FileUtils.writeStringToFile(f, jsonString, "windows-1251", false);

		} catch (IOException e) {
			System.out.println("Can't save.");
		}
	}

	// -----------------------------KeyListener----------------------
	@Override
	public void keyPressed(KeyEvent ke) {
		int key = ke.getKeyCode();
		switch (key) {
		case KeyEvent.VK_1:
			onScreen.add(g1, Shape.Type.Circle);
			break;

		case KeyEvent.VK_2:
			onScreen.add(g1, Shape.Type.Rectangle);
			break;

		case KeyEvent.VK_3:
			onScreen.add(g1, Shape.Type.Triangle);
			break;

		case KeyEvent.VK_RIGHT:
			onScreen.move(Direction.RIGHT);
			break;

		case KeyEvent.VK_LEFT:
			onScreen.move(Direction.LEFT);
			break;

		case KeyEvent.VK_UP:
			onScreen.move(Direction.UP);
			break;

		case KeyEvent.VK_DOWN:
			onScreen.move(Direction.DOWN);
			break;

		case KeyEvent.VK_F1:
			onScreen.inc();
			break;

		case KeyEvent.VK_F2:
			onScreen.dec();
			break;

		case KeyEvent.VK_PAGE_UP:
			onScreen.nextActiveShape();
			break;

		case KeyEvent.VK_PAGE_DOWN:
			onScreen.previouseActiveShape();
			break;

		case KeyEvent.VK_DELETE:
			onScreen.remove();
			break;

		case KeyEvent.VK_S:
			if (ke.isControlDown())
				saveScreen();
			break;

		case KeyEvent.VK_L:
			loadScreen();
			break;
		}

		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
