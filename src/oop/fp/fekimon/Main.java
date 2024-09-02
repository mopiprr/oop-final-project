package oop.fp.fekimon;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame window = new JFrame();
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window.setResizable(false);
				window.setTitle("Fekimon");
				
				GamePanel gamePanel = new GamePanel();
				window.add(gamePanel);
				gamePanel.setupGame();
				gamePanel.startGameThread();
				
				window.pack();
				window.setLocationRelativeTo(null);
				window.setVisible(true);
				
				
			}
		});

	}

}