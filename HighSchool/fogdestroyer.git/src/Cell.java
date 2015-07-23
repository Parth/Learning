import org.jbox2d.dynamics.World;
import org.lwjgl.opengl.GL11;

public class Cell{
	static final int EMPTY  = 0;
	static final int BLOCKS = 1;
	static final int BRICKS = 2;
	static final int BOARDS = 3;

	Block[] clarks;
	int contentType;
	float width;
	float height;
	int col;
	int row;

	public Cell(float wid, float hei, int col, int row){
		this.col = col;
		this.row = row;
		width = wid;
		height = hei;
		contentType = 0;
	}

	public int getNumberOfBlocks(){
		if(clarks != null)
			return clarks.length;
		return 0;
	}
	
	public void click(){
		destroyAllBodies();
		clarks = null;
		contentType++;
		contentType %= 4;
		switch(contentType){
			case 1:
				setAsBlocks();
				break;
			case 2:
				setAsBoards();
				break;
			case 3:
				break;
		}
	}

	private void setAsBlocks(){
		float blockWidth = (width / 2);
		float blockHeight = (height / 2);
		float cellX = 2 * (width * col) + width / 4;
		float cellY = 2 * (height * row) + height / 4;

		clarks = new Block[4];
		clarks[0] = new Block(cellX + blockWidth / 2, cellY + blockHeight / 2, blockWidth, blockHeight);
		clarks[1] = new Block(cellX + 5 * blockWidth / 2, cellY + blockHeight / 2, blockWidth, blockHeight);
		clarks[2] = new Block(cellX + blockWidth / 2, cellY + 5 * blockHeight / 2, blockWidth, blockHeight);
		clarks[3] = new Block(cellX + 5 * blockWidth / 2, cellY + 5 * blockHeight / 2, blockWidth, blockHeight);
	}

	private void setAsBoards(){
		float blockWidth1 = (width);
		float blockWidth2 = (width / 2);
		float blockHeight = (height / 4);
		float cellX = 2 * (width * col);
		float cellY = 2 * (height * row);
		
		clarks = new Block[6];
		clarks[0] = new Block(cellX + width / 2, cellY + height / 4, blockWidth2, blockHeight);//bottom left
		clarks[1] = new Block(cellX + 3 * width / 2, cellY + height / 4, blockWidth2, blockHeight);//bottom right
		clarks[2] = new Block(cellX + width / 1, cellY +  3 * height / 4, blockWidth1, blockHeight);//bottom long
		clarks[3] = new Block(cellX + width / 2, cellY +  5 * height / 4, blockWidth2, blockHeight);//top left
		clarks[4] = new Block(cellX + 3 * width / 2, cellY +  5 * height / 4, blockWidth2, blockHeight);//top right
		clarks[5] = new Block(cellX + width / 1, cellY + 7 * height / 4, blockWidth1, blockHeight);//top long
			
	}

	public void destroyAllBodies(){
		if(clarks != null)
			for(int i = 0; i < clarks.length; i++)
				clarks[i].destroy();
	}

	public void attachPhysics(World w){
		if(clarks != null)
			for(int i = 0; i < clarks.length; i++)
				clarks[i].attachPhysics(w);
	}

	public void render(){
		if(clarks != null)
			for (GameObject clark : clarks) {
				GL11.glPushMatrix();
				clark.render();
				GL11.glPopMatrix();
			}	
	}
}

