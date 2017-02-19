import java.awt.Graphics;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Mob extends Rectangle {
	public int xC, yC;
	int mobSize = 52;
	int mobID = Value.mobAir;
	public boolean inGame = false;

	public int mobWalk = 0;
	public int direction = 2;
	public int upward = 0, downward = 1, right = 2, left = 3;

	public boolean hasUpward = false;
	public boolean hasDownward = false;
	public boolean hasRight = false;
	public boolean hasLeft = false;

	public Mob() {

	}

	public void spawnMob(int mobID) {
		for (int y = 0; y < Screen.room.block.length; y++) {
			if (Screen.room.block[y][0].groundID == Value.groundRoad) {
				setBounds(Screen.room.block[y][0].x, Screen.room.block[y][0].y,
						mobSize, mobSize);
				xC = 0;
				yC = y;

			}
		}

		this.mobID = mobID;
		inGame = true;
	}

	public int walkFrame = 0, walkSpeed = 40;

	public void physic() {
		if (walkFrame >= walkSpeed) {
			if (direction == right) {
				x++;
			} else if (direction == upward) {
				y--;
			} else if (direction == downward) {
				y++;
			} else if (direction == left) {
				x--;
			}
			mobWalk += 1;
			if (mobWalk == Screen.room.blockSize) {

				if (direction == right) {
					xC++;
					hasRight = true;
				} else if (direction == upward) {
					yC--;
					hasUpward = true;
				} else if (direction == downward) {
					yC++;
					hasDownward = true;
				} else if (direction == left) {
					xC--;
					hasLeft = true;
				}

				try {
					if (!hasUpward) {
						if (Screen.room.block[yC + 1][xC].groundID == Value.groundRoad) {
							direction = downward;
						}
					}
					if (!hasDownward) {
						if (Screen.room.block[yC - 1][xC].groundID == Value.groundRoad) {
							direction = upward;
						}
					}
					if (!hasLeft) {
						if (Screen.room.block[yC][xC + 1].groundID == Value.groundRoad) {
							direction = right;
						}
					}
					if (!hasRight) {
						if (Screen.room.block[yC][xC - 1].groundID == Value.groundRoad) {
							direction = left;
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				if (Screen.room.block[yC][xC].airID == Value.airCave) {
					deleteMob();
					Screen.health -= 1;
				}
				
				hasUpward = false;
				hasDownward = false;
				hasRight = false;
				hasLeft = false;
				mobWalk = 0;
			}

			walkFrame = 0;
		} else {
			walkFrame++;
		}
	}
	
	public void deleteMob() {
		inGame = false;
	}
	
	public void loseHealth() {
		Screen.health -= 1;
	}

	public void draw(Graphics g) {
		g.drawImage(Screen.tileset_mob[mobID], x, y, width, height, null);
	}
}
