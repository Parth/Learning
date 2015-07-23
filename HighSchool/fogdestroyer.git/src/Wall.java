import org.jbox2d.dynamics.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.jbox2d.dynamics.World;
import org.jbox2d.common.*;
import java.util.*;
import org.jbox2d.dynamics.Body;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import java.util.Set;

public class Wall implements GameObject {
	private BodyDef groundDef;
	private float restitution;

	public Wall() {
		this.restitution = 0.3f;
		groundDef = new BodyDef();
	}

	public Wall(float restitution) {
		this.restitution = restitution;
		groundDef = new BodyDef();
	}
	 
	public void render() {
	}

	public void attachPhysics(World world) {
		groundDef.position.set(0, 0);
		groundDef.type = BodyType.STATIC;
		PolygonShape groundShape = new PolygonShape();
		groundShape.setAsBox(1000, 0);
		Body ground = world.createBody(groundDef);
		FixtureDef groundFixture = new FixtureDef();
		groundFixture.density = 1;
		groundFixture.restitution = restitution;
		groundFixture.shape = groundShape;
		ground.createFixture(groundFixture);
	}
}
