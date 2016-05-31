package mypack;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import mypack.listeners.MyKeyAdapter;
import mypack.listeners.MyMouseListener;

public class MyMain extends JFrame {
	private MyFirst myFirst;

	
	MyMain() {
		setTitle("My first programm with GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		myFirst = new MyFirst();
		add(myFirst);

		addMouseListener(new MyMouseListener(myFirst.getOnScreen()));
		addKeyListener(new MyKeyAdapter(myFirst));

		pack();
		setVisible(true);

	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MyMain main = new MyMain();
				
				File file = new File("Save.txt");
				if (file.exists()){
					main.getMyFirst().setSave(true);
					//main.load(file);
				}
				
			}
		});

	}

	public MyFirst getMyFirst() {
		return myFirst;
	}


}
