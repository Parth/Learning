import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: parthmehrotra
 * Date: 5/16/14
 * Time: 9:04 AM
 */
public class CreateBoxesTrial {
    private List<Box> boxes;
    private boolean selected = false;
    private boolean mouseWasDown = false;
    private int x, y;
    private boolean creating = false;

    public static void main(String args[]) throws LWJGLException {
        new CreateBoxesTrial();
    }

    public CreateBoxesTrial() throws LWJGLException {
        boxes = new ArrayList<Box>();
//        Display.setFullscreen(true);
        Display.setDisplayMode(new DisplayMode(800, 600));
        Display.create();

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        boxes.add(new Box(Display.getWidth() / 2 - 20, Display.getHeight() / 2 - 20, 40, 40));

        while (!Display.isCloseRequested()) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            x = Mouse.getX();
            y = Display.getHeight() - Mouse.getY();
            int selectedX = -1, selectedY = -1;

            if (!mouseWasDown && Mouse.isButtonDown(0)) {
                mouseWasDown = true;
            }

            if (!Mouse.isButtonDown(0) && mouseWasDown) {
                mouseWasDown = false;
                selected = !selected;
                selectedX = x;
                selectedY = y;
            }

            for (int i = 0; i < boxes.size(); i++) {
                boxes.get(i).draw();
                if (selected && boxes.get(i).intersect(selectedX, selectedY)) {
                    boxes.get(i).setSelected(!boxes.get(i).getSelected());
		    creating = false;
                } else if (selected && !creating && !anyBoxWasSelected(selectedX, selectedY)) {
                    boxes.add(new Box(selectedX, selectedY, 0, 0));
                    creating = true;
                }

		if (creating && !selected) {
			creating = false;
		}

                if (creating && selected) {
                    Box b = boxes.get(boxes.size() - 1);
                    b.setDimentions(x - b.getX(), y - b.getY());
                }

                if (boxes.get(i).getSelected()) {
                    boxes.get(i).update(Mouse.getDX(), -Mouse.getDY());
                }
            }

            if (!selected) {
                for (Box b : boxes) {
                    b.setSelected(false);
		}
		creating = false;
            }

	    if (anyBoxWasSelected(selectedX, selectedY)) 
	    System.out.println("AHHH");

            Display.sync(60);
            Display.update();
        }
    }

        private boolean anyBoxWasSelected(int x, int y) {
	    for (int i = 0; i < boxes.size(); i++) {
		    if (boxes.get(i).intersect(x, y)) {
			    return true;
		    }
	    }
	    return false;
    }
}
