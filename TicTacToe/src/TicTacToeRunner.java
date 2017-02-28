import javax.swing.JFrame;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class TicTacToeRunner extends JFrame {
	
	public static int WIDTH = 900, HEIGHT = 900;
	public static TicTacToePanel panel;
	public static int Padding = 30;

	public TicTacToeRunner() {
		panel = new TicTacToePanel();
		setSize(WIDTH, HEIGHT+Padding);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Tic Tac Toe");
		addMouseListener(new Events());
		add(panel);
		setVisible(true);
	}	

	public static void main(String args[]) {
		JFrame frame = new TicTacToeRunner();
	}

	private class Events implements MouseListener { // TODO MOUSEMOTIONLISTENER 

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {
			sendButton(e);
		}

		private int sendButton(MouseEvent e) {
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y < 3; y++) {
					if ((e.getX() > (x*WIDTH) / 3 && e.getY() > (y*HEIGHT) / 3) && (e.getX() < ((x+1) * WIDTH) / 3 && e.getY() < ((y+1) * HEIGHT) / 3)) {
						panel.setTouched(x, y);
					}
				}
			}
			
			return -1;//something went wrong
		}
	}
}
