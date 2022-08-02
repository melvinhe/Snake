import java.awt.EventQueue;

import javax.swing.JFrame;

public class Player extends JFrame {
	//main method
	public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
    		@Override
        	public void run() {
        		JFrame frame = new Player();
            	frame.setVisible(true);
        	}
    	});
	}
	//creates player and adds board
	public Player() {
		add(new Board());
		setResizable(false);
		pack();
		setLocationRelativeTo(null);

		setTitle("Melvin's Snake Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}
