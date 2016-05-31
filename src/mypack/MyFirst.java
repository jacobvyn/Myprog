package mypack;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mypack.save.Save;

public class MyFirst extends JPanel {

	private OnScreen onScreen;
	private Graphics g1;

	public MyFirst() {
		onScreen = new OnScreen();
		
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
		setFont(font);

//--------------------
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 800);
	}
	


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g1 = g.create();
		g1.drawString("1- add circle, 2 - rectangle, 3- triangle, DEL -delete", 30, 50);
		g1.drawString("PgUP/PgDN - next shape, F1/F2 - increase/decrease, arrows- move", 30, 70);
		g1.drawString("Cntrl - make group, Shift - clone", 30, 90);
		g1.drawString("Ctrl+S - save, L - load", 30, 110);

		onScreen.drawAll();

	}

	public void loadScreen(File f) {
		try {
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

	
	public void repaintAll() {
		this.repaint();
	}

	public Graphics getG1() {
		return g1;
	}

	public OnScreen getOnScreen() {
		return onScreen;
	}

}
