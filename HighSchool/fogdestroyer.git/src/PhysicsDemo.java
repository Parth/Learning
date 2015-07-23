import org.jbox2d.dynamics.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.World;
import java.util.*;
import org.jbox2d.dynamics.Body;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import java.util.Set;

public class PhysicsDemo {

	private static int screenHeight;
	private static int screenWidth;
	
	private static boolean mouseDownLastFrame = false;
	private static boolean xDownLastFrame = false;
	
	private static Grid grid;

	private static World world = new World(new Vec2(0, -9.8f), false);
	private static Set<GameObject> clarks = new HashSet<GameObject>();

	private static void setupDisplays() {
		try {
			Display.setFullscreen(true);
			Display.create();
		} catch (LWJGLException e) {
		}
		screenHeight = Display.getHeight();
		screenWidth = Display.getWidth();
	}

	private static void setupObjects() {
		grid = new Grid(world, 10, 8, (float)(screenWidth / 2)/30, (float)(screenHeight / 2)/30);
		GameObject ground = new Wall(.1f);
		ground.attachPhysics(world);;
	}

	private static void setupMatrices() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glOrtho(0, screenWidth, 0, screenHeight , 1 , -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	private static void enterGameLoop() {
		while (!Display.isCloseRequested()) {
			render();
			logic();
			update();

		}
	}

	private static void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		for (GameObject clark : clarks) {
			GL11.glPushMatrix();
			clark.render();
			GL11.glPopMatrix();
		}
		grid.render();
	}

	private static void logic() {
		if (!Keyboard.isKeyDown(Keyboard.KEY_SPACE)) 
			world.step(1/60f, 8, 3);
		if(!Mouse.isButtonDown(0) && mouseDownLastFrame){
			mouseDownLastFrame = false;
			click();
		}
		if(Mouse.isButtonDown(0)){
			mouseDownLastFrame = true;
		}
		if(!Keyboard.isKeyDown(Keyboard.KEY_X) && xDownLastFrame){
			xDownLastFrame = false;
			launch();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_X)){
			xDownLastFrame = true;
		}
	}

	private static void update() {
		Display.update();
		Display.sync(60);
	}

	private static void click(){
		grid.click(Mouse.getX() * ((float)(screenWidth / 2)/(30 * screenWidth)), (Mouse.getY() * ((float)(screenHeight / 2)/(30 * screenHeight))));
	}

	private static void launch(){
		GameObject projectile = new Block(Mouse.getX() / 30f, Mouse.getY() / 30f, 0.2f, 0.2f);
		projectile.attachPhysics(world);
		clarks.add(projectile);
	}

	public static void main(String[] args) {
		setupDisplays();
		setupObjects();
		setupMatrices();
		enterGameLoop();
	}
}
