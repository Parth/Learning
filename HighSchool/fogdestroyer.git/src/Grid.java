import org.jbox2d.dynamics.World;

public class Grid{

	World w;
	private Cell[][] cells;
	int columns;
	int rows;
	float width;
	float height;

	public Grid(World w, int col, int row, float wid, float hei){
		this.w = w;
		columns = col;
		rows = row;
		width = wid;
		height = hei;
		cells = new Cell[col][row];
		float cellWidth = width / columns;
		float cellHeight = height / rows;
		for(int c = 0; c < columns; c++)
			for(int r = 0; r < rows; r++)
				cells[c][r] = new Cell(cellWidth, cellHeight, c, r);
	}

	public Cell getCell(float x, float y){
		/*
		//CLEARER WAY TO DO THIS
		float cellWidth = width / columns;
		float cellHeight = height / rows;
		int c = (int)(x / cellWidth);
		int r = (int)(y / cellHeight);
		return cells[c][r];
		*/
		return cells[(int)(x * (float)columns / width)][(int)(y * (float)rows / height)];
	}

	public int getNumberOfBlocks(){
		int sum = 0;
		for(int c = 0; c < columns; c++)
			for(int r = 0; r < rows; r++)
				sum += cells[c][r].getNumberOfBlocks();
		return sum;

	}

	public void click(float x, float y){
		System.out.println("[GRID]:");
		System.out.println("x = " + x + ", y = " + y);
		System.out.println("Cell: " + (int)(x * (float)columns / width) + ", " + (int)(y * (float)rows / height));
		getCell(x, y).click();
		getCell(x, y).attachPhysics(w);
		System.out.println("Blocks: " + getNumberOfBlocks());
		System.out.println();
	}
	
	public void render(){
		for(int c = 0; c < columns; c++)
			for(int r = 0; r < rows; r++)
				cells[c][r].render();
	}
}
