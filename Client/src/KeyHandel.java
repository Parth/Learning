import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class KeyHandel implements MouseMotionListener, MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Screen.store.click(e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Screen.mse = new Point(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//Screen.mse = new Point((e.getX()) + (Frame.size.width - Screen.myWidth/2), (e.getY()) + ((Frame.size.height - (Screen.myHeight)) - (Frame.size.width - Screen.myWidth) / 2));
		Screen.mse = new Point(e.getX(), e.getY());
	}
}
