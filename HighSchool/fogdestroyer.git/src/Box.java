import org.lwjgl.opengl.GL11;

/**
 * Author: parthmehrotra
 * Date: 5/16/14
 * Time: 9:20 AM
 */
public class Box {
    private int x, y, width, height;
    private boolean selected;

    public Box(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        selected = false;
    }

    public boolean intersect(int mX, int mY) {
        return (mX > x && mX < (x+width) && mY > y && mY < y+height);
    }

    public void update(int dX, int dY) {
            x+=dX;
            y+=dY;
    }

    public void draw() {
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2i(x, y);
        GL11.glVertex2i(x + width, y);
        GL11.glVertex2i(x + width, y + height);
        GL11.glVertex2i(x, y+height);
        GL11.glEnd();
    }

    public boolean getSelected() {
        return selected;
    }

    public void setDimentions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setSelected(boolean selected) {

        this.selected = selected;
    }

    public String toString() {
        return "X: " + x + ", Y: " + y;
    }
}
