//Cankat Tuzun
import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.*;
import java.lang.Math;
import java.lang.*;

public class Hangman extends JPanel// implements ActionListener,KeyListener
{
	String secret;
	String entered;
	int sequence2;
	int sequence;
	int b , m;
	private Font font = new Font("Calibri",1,42);		//Font
	private Font font2 = new Font("Calibri",1,36);

	public Hangman() 
	{
		//b = (int)(Math.random()*5);
		String[] range = {"apples"};
		secret = range[0]; 
		m = secret.length();
	}
	public void setSequence(int seq, int seq2)
	{
		sequence = seq;
		sequence2 = seq2;
	}
	public void setString(String s)
	{
		entered = s;
	}
	public String getSecret()
	{
		return secret;
	}
	String entered;
	public void paintComponent(Graphics g)
	{	
	
		switch(sequence2)
		{
			case 1:
				g.drawString(entered, 350, 275);
			break;
			case 2:
				g.drawString(entered, 375, 275);
			break;
			case 3:
				g.drawString(entered, 400, 275);
			break;
			case 4:
				g.drawString(entered, 425, 275);
			break;
			case 5:
				g.drawString(entered, 450, 275);
			break;
			case 6:
				g.drawString(entered, 475, 275);
			break;
			case 7:
				g.drawString(entered, 500, 275);
			break;
			case 8:
				g.drawString(entered, 525, 275);
			break;
			case 9:
				g.drawString(entered, 550, 275);
			break;
			case 10:
				g.drawString(entered, 575, 275);
			break;
			case 11:
				g.drawString(entered, 600, 275);
			break;
			default:
			/*case m:
				g.drawString(entered, 500, 275);
				g.drawString("You Win!", 250, 150);
			break;
			default:
			*/

		}
		switch(sequence)
		{
			case 1:
				g.fillOval(50,100,50,50);
			break;
			case 2:
				g.drawLine(75,150,75,220);
			break;
			case 3:
				g.drawLine(75,220,90,250);
			break;
			case 4:
				g.drawLine(75,220,60,250);
			break;
			case 5:
				g.drawLine(75,175,90,195);
			break;
			case 6:	
				g.drawLine(75,175,60,195);
				g.drawString("You Lose!", 250, 150);
			default:
					
		}
	}
}
