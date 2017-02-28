import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Font;

public class TicTacToePanel extends JPanel {

	private Tile[][] tiles;

	private boolean gameBoardDrawn = false;

	private int turn = 0;
	private boolean gameOver = false;

	public TicTacToePanel() {
		tiles = new Tile[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				tiles[x][y] = new Tile((x*TicTacToeRunner.WIDTH) / 3, (y*TicTacToeRunner.HEIGHT) / 3);
			}
		}
	}

	@Override
	public void paintComponent(Graphics gP) {
		Graphics2D g = (Graphics2D) gP;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		drawGameBoard(g);
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				tiles[x][y].draw(g);
		
			}
		}
		if (gameOver) {
			drawGameOverDialog(g);
		}
	}

	public void setTouched(int x, int y) {
		if (!gameOver) {
			boolean shouldWeProceed = false;
			if (turn % 2 == 0) {
				shouldWeProceed = tiles[x][y].setState(Tile.xSelected);
			} else {
				shouldWeProceed = tiles[x][y].setState(Tile.oSelected);
			}
		
			if (shouldWeProceed) {
				turn++;
			}
			if (turn > 8) {
				gameOver = true;
			}

			checkForWinners();
			repaint();
		}
	}

	private void drawGameOverDialog(Graphics g) {
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 50);
		g.setFont(font);
		int width = 350;
		int height = 100;
		int wP = 35;
		int hP = 30;
		g.setColor(Color.BLUE);
		g.fillRect((TicTacToeRunner.WIDTH - width) / 2, (TicTacToeRunner.HEIGHT - height) / 2, width, height);
		g.setColor(Color.WHITE);
		g.drawString("Game Over!", ((TicTacToeRunner.WIDTH - width) / 2) + wP, ((TicTacToeRunner.HEIGHT + height) / 2) - hP);
	}

	private void drawGameBoard(Graphics g) {
		for (int x = 0; x < 2; x++) {
			g.drawLine(((x+1) * TicTacToeRunner.WIDTH)/3, 0, (((x+1) * TicTacToeRunner.WIDTH) / 3), TicTacToeRunner.HEIGHT);
		}
		for (int y = 0; y < 2; y++) {
			g.drawLine(0, ((y+1) * TicTacToeRunner.HEIGHT)/3, TicTacToeRunner.WIDTH, ((y+1) * TicTacToeRunner.HEIGHT) / 3);
		}
	}

	@Override
	public String toString() {
		String s = "";
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				s += tiles[x][y].toString();
			}
			s+="\n";
		}
		return s;
	}

	public void checkForWinners() {
		//Check the horizontals 
		for (int y = 0; y < 3; y++) {
			if (tiles[0][y].equals(tiles[1][y]) && tiles[0][y].equals(tiles[2][y])) {
				winnerHorizontal(y);
				return;
			}
		}
		
		//Check the verticals
		for (int x = 0; x < 3; x++) {
			if (tiles[x][0].equals(tiles[x][1]) && tiles[x][0].equals(tiles[x][2])) {
				winnerVertical(x);
				return;
			}
		}

		//Check the diagonals
		if (tiles[0][0].equals(tiles[1][1]) && tiles[0][0].equals(tiles[2][2])) {
			winnerDiagonalDown();
			return;
		}

		if (tiles[2][0].equals(tiles[1][1]) && tiles[2][0].equals(tiles[0][2])) {
			winnerDiagonalUp();
			return;
		}
	}

	private void winnerHorizontal(int y) {
		for (int i = 0; i < 3; i++) {
			tiles[i][y].victory();
		} 
		repaint();
		gameOver = true;
	}

	private void winnerVertical(int x) {
		for (int i = 0; i < 3; i++) {
			tiles[x][i].victory();
		}
		repaint();
		gameOver = true;
	}

	private void winnerDiagonalDown() {
		for (int i = 0; i < 3; i++) {
			tiles[i][i].victory();
		}
		repaint();
		gameOver = true;
	}

	private void winnerDiagonalUp() {
		for (int i = 2; i <= 0; i++) {
			tiles[i][2-i].victory();
		}
		repaint();
		gameOver = true;
	}

}
