import java.util.Random;

public class Missile {
	int startX, startY;
	int targetX, targetY;
	int currX, currY;
	int screenSide;
	int xUpdate, yUpdate;
	public boolean complete = false;
	public boolean deleted = false;
	int decayCount = 0;
	
	Random rand = new Random();
	
	public Missile(int playerX, int playerY) {
		int randOffsetX = rand.nextInt(3);
		int randOffsetY = rand.nextInt(3);
		targetX = playerX + randOffsetX;
		targetY = playerY + randOffsetY;
		if (targetX <= 0) {
			targetX = 1;
		}
		if (targetX >= Grid.width - 1) {
			targetX = Grid.width - 2;
		}
		if (targetY <= 0) {
			targetY = 1;
		}
		if (targetY >= Grid.height - 1) {
			targetY = Grid.height - 2;
		}
		screenSide = rand.nextInt(4);
		
		if (screenSide == 0) {
			startX = 1;
			startY = targetY;
			xUpdate = 1;
			yUpdate = 0;
			
		}
		else if (screenSide == 1) {
			startX = targetX;
			startY = Grid.height - 2; //need to clarify height/width to x/y correspondence
			xUpdate = 0;
			yUpdate = -1;
		}
		else if (screenSide == 2) {
			startX = Grid.width - 2;
			startY = targetY;
			xUpdate = -1;
			yUpdate = 0;
		}
		else { //screenSide == 3
			startX = targetX;
			startY = 1;
			xUpdate = 0;
			yUpdate = 1;
		}	
		currX = startX;
		currY = startY;
	}
	
	public void update() {
		if (currX == targetX && currY == targetY) { 
			if (Grid.board[currX][currY] != 2) { //
				Grid.board[currX][currY] = 1;
			}
			complete = true;
		}
		
		if (complete) {
			return;
		}
		
		if (Grid.board[currX][currY] != 2) {
			Grid.board[currX][currY] = 1;
		}
		
		currX = currX + xUpdate;
		currY = currY + yUpdate;
	}
		
	public void decay() { //will need to add check such that we don't clear red spaces that are part of the level layout
		decayCount++;
		if (decayCount == 5) {
			delete();
		}
	}
	
	public void delete() {
		for (int x = startX; x != targetX; x = x + xUpdate) {
			if (Grid.board[x][startY] == 1) {
				Grid.board[x][startY] = 0;
			}	
		}
		for (int y = startY; y != targetY; y = y + yUpdate) {
			if (Grid.board[startX][y] == 1) {
				Grid.board[startX][y] = 0;
			}
		}
		deleted = true;
	}

}
