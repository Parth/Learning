import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
class HangmanFrame extends JFrame
{

	private HangmanPanel p;

	public HangmanFrame()
	{
		p = new HangmanPanel();
		add(p);
		p.setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(p);
		pack();
		setVisible(true);
	}

}
