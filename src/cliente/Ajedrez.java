package cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;

import tablero.Tablero;


public class Ajedrez {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tablero frame = new Tablero();
					frame.setAlwaysOnTop(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(500, 500);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
