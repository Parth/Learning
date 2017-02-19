import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {

	public Frame() {
		this.setSize(400, 400);
		this.setTitle("Steve");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new Panel());
	}

	public static void main(String args[]) {
		JFrame frame = new Frame();
		
	}
	
	private class Panel extends JPanel {
		public Panel() {
			
		}
		
		public void paint(Graphics g) {
			g.setColor(Color.BLUE);
			g.fillRect(20, 20, 300, 300);
		}
	}
}
