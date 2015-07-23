import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.*;
import java.lang.Math;

public class HangmanPanel extends JPanel implements KeyListener {

	private final String SECRET_WORD = "apples";
	private ArrayList<String> secretWord;
	private ArrayList<String> secretWordLeft;
	private ArrayList<String> guesses;
	private String correct, incorrect;

	public HangmanPanel() {
		this.setPreferredSize(new Dimension(800, 600));
		this.addKeyListener(this); 
		secretWord = new ArrayList<String>();
		secretWordLeft = new ArrayList<String>();
		guesses = new ArrayList<String>();
		correct = "";
		incorrect = "";

		for (int i = 0; i < SECRET_WORD.length(); i++) {
			secretWord.add(SECRET_WORD.charAt(i)+"");
			secretWordLeft.add(SECRET_WORD.charAt(i)+"");
		}

	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(75,100,75,25);		
		g.drawLine(75,25,200,25);
		g.drawLine(200,25,200,250);
		g.drawLine(200,250,100,250);
		g.drawLine(200,250,300,250);
		Font font = new Font("Calibri",1,42);		//Font
		Font font2 = new Font("Calibri",1,36);
		g.setFont(font2);
		g.setColor(Color.ORANGE);
		g.drawString("Correct below & incorrect above", 300, 200);
		g.setFont(font);
		g.setColor(Color.BLUE);
		g.drawString("Hangman", 300, 50);
		g.drawString(correct, 200, 300);
		g.drawString(incorrect, 200, 50);
	}

	public void keyPressed(KeyEvent e)  {
	}
	public void keyReleased(KeyEvent e) {
		guesses.add(e.getKeyChar() + "");
		if (valid) {
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	private boolean validGuess() {
		boolean valid = false;
		for (int g = 0; g < secretWordLeft.size(); g++) {
			if (guesses.get(guess.size()-1).equalsIgnoreCase(secretWordLeft.get(g))) {
			secretWordLeft.remove(g);
			g--;
			valid = true;
			}  
		}
		return valid;
	}
}
