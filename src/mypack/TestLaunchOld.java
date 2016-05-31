
package mypack;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mypack.listeners.MyMouseListener;
import mypack.save.Save;

public class TestLaunchOld extends Frame {

	private OnScreen onScreen;
	private Graphics2D g1;



	public TestLaunchOld() {
		onScreen = new OnScreen();
		setSize(new Dimension(800, 800));
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
		setFont(font);
		setTitle("My first programm with GUI");

		addMouseListener(new MyMouseListener(onScreen));
		//addKeyListener(new MyKeyAdapter(this));
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	public static void main(String[] args) {

		TestLaunchOld m = new TestLaunchOld();
		m.setVisible(true);

		File file = new File("Save.txt");
		if (file.exists())
			m.loadScreen(file);

	}

	public void paint(Graphics g) {

		g1 = (Graphics2D) g.create();
		g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g1.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g1.drawString("1- add circle, 2 - rectangle, 3- triangle, DEL -delete", 30, 50);
		g1.drawString("PgUP/PgDN - next shape, F1/F2 - increase/decrease, arrows- move", 30, 70);
		g1.drawString("Cntrl - make group, Shift - clone", 30, 90);
		g1.drawString("Ctrl+S - save, L - load", 30, 110);

		onScreen.drawAll();

	}

	public void loadScreen(File f) {
		try {
			//File f = new File("Save.txt");
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
	
	public OnScreen getOnScreen() {
		return onScreen;
	}

	public Graphics getG1() {
		return g1;
	}

	public void repaintAll() {
		this.repaint();
		
	}

}
