package mypack;

import java.io.File;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import mypack.listeners.MyKeyAdapter;
import mypack.listeners.MyMouseListener;

public class MyMain extends JFrame {
	MyFirst screen;

	MyMain() {
		setTitle("My first programm with GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		screen = new MyFirst();
		add(screen);


		addMouseListener(new MyMouseListener(screen.getOnScreen()));
		addKeyListener(new MyKeyAdapter(screen));
		
		pack();
		setVisible(true);

	}

	public void load(File f) {
		screen.loadScreen(f);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MyMain main = new MyMain();
				/*
				File file = new File("Save.txt");
				if (file.exists()){
					main.load(file);
				}
				*/
			}
		});

	}

}
