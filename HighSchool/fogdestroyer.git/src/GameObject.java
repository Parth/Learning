import org.jbox2d.dynamics.World;

public interface GameObject {
	public void render();
	public void attachPhysics(World w);
}
