import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

/**
 * Author: parthmehrotra
 * Date: 5/15/14
 * Time: 4:28 PM
 */
public class InputDemo {

    private List<Box> shapes = new ArrayList<Box>(16);
    private boolean somethingIsSelecte= false;

    public InputDemo() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Hello, LWJGL");
            Display.create();
        } catch (LWJGLException e)  {

        }

        shapes.add(new Box(15, 15));
        shapes.add(new Box(115, 115));
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 640, 480, 0, 1, -1);
        GL11.glMatrixMode(GL_MODELVIEW);

        while(!Display.isCloseRequested()) {

            glClear(GL_COLOR_BUFFER_BIT);

            while(Keyboard.next()) {
//                if (Keyboard.getEventKey() == Keyboard.KEY_C)
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                Display.destroy();
                System.exit(0);
            }

            for (Box box : shapes) {
                if (Mouse.isButtonDown(0) && box.inBounds(Mouse.getX(), 480 - Mouse.getY()) && !somethingIsSelecte) {
                    somethingIsSelecte = true;
                    box.selected = true;
                    System.out.println("You clicked me!");
                }
                if (Mouse.isButtonDown(1)){
                    box.selected = false;
                    somethingIsSelecte = false;
                }
                if (box.selected) {
                    box.update(Mouse.getDX(), -Mouse.getDY());
                }
                box.draw();
            }

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }

    public static void main(String args[]) {
        new InputDemo();
    }

    private static class Box {
        public int x, y;
        private float colorRed, colorBlue, colorGreen;
        public boolean selected = false;

        Box (int x, int y) {
            this.x = x;
            this.y = y;

            Random randomGenerator = new Random();
            colorRed = randomGenerator.nextFloat();
            colorBlue = randomGenerator.nextFloat();
            colorGreen = randomGenerator.nextFloat();
        }

        boolean inBounds(int mX, int mY) {
            if (mX > x && mX < x + 50 && mY > y && mY < y+50) {
                 return true;
            }
            return false;
        }

        void randomizeColors() {

            Random randomGenerator = new Random();
            colorRed = randomGenerator.nextFloat();
            colorBlue = randomGenerator.nextFloat();
            colorGreen = randomGenerator.nextFloat();
        }

        void update(int dx, int dy) {
            x += dx;
            y += dy;
        }

        void draw() {
            glColor3f(colorRed, colorGreen, colorBlue);

            glBegin(GL_QUADS);
            glVertex2i(x, y);
            glVertex2i(x + 50, y);
            glVertex2i(x + 50, y + 50);
            glVertex2i(x, y + 50);
            glEnd();
        }
    }
}
