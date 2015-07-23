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

public class Block implements GameObject {
	public float width, height;
	public float x, y;
	private World wo;
	private Body body;

	public Block(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	public Body getBody() {
		return body;
	}

	public void render() {
		Vec2 bodyPosition = getBody().getPosition().mul(30);
		GL11.glTranslatef(bodyPosition.x, bodyPosition.y, 0);
		GL11.glRotatef((float) Math.toDegrees(getBody().getAngle()), 0f, 0f, 1f);
		GL11.glRectf(-width * 30, -height * 30, width * 30, height * 30);
	}

	public void attachPhysics(World w) {
		BodyDef bodyDef = new BodyDef();
		//bodyDef.position.set(320/30/1, 240/30/1);
		bodyDef.position.set(x, y);
		bodyDef.type = BodyType.DYNAMIC;
		PolygonShape boxShape = new PolygonShape();

		boxShape.setAsBox(width, height);
		body = w.createBody(bodyDef);
		FixtureDef boxFixture = new FixtureDef();
		boxFixture.density = 1;
		boxFixture.shape = boxShape;
		body.createFixture(boxFixture);
	}

	public void attachPhysics(World w, Vec2 v) {
		BodyDef bodyDef = new BodyDef();
		//bodyDef.position.set(320/30/1, 240/30/1);
		bodyDef.position.set(x, y);
		bodyDef.type = BodyType.DYNAMIC;
		
		PolygonShape boxShape = new PolygonShape();

		boxShape.setAsBox(width, height);
		body = w.createBody(bodyDef);
		FixtureDef boxFixture = new FixtureDef();
		boxFixture.density = 1;
		boxFixture.shape = boxShape;
		body.createFixture(boxFixture);
	}
	//Destroys this block's body so that it is no longer part of the world
	public void destroy(){
		body.getWorld().destroyBody(body);
	}
}
