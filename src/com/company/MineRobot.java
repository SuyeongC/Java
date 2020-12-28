package com.company;

import static com.company.ProjConstants.*;

//import com.company.MapRobot.MatrixDirection;

/**
 * Created by Robert Hubert on 22/05/2019.
 */
public class MineRobot {
	
	private int mapSize = INVALID;

	private int[][] map;
	//private int[][] visited;
	private int peakValue; 
	
	private int currentX = 0;
	private int currentY = 0;
	
	private int startX = 0;
	private int startY = 0;

	private boolean doneMined = false;
	
	private enum MatrixDirection { TOPLEFT, LEFT, BOTTOMLEFT, BOTTOM, BOTTOMRIGHT, RIGHT, TOPRIGHT, TOP }
	
    // =======================================================================================================
    // this is the constructor that will create required structures with default values
    //
	public MineRobot() {
		 map = new int[MAX_XY][MAX_XY];
		 mapSize = MAX_XY;
	}
	
    // =======================================================================================================
    // this is the constructor that will create required structures to a user define size
    //
	public MineRobot(int arraySize) {
		
		 map = new int[arraySize][arraySize];
		 mapSize = arraySize;
	}
	
    // =======================================================================================================
    // this is the constructor that will create required structures to a user define size
    //
	public boolean miningDone() {
		return doneMined;
		
//		for (int r = 0; r < mapSize; r++) {
//			for (int c = 0; c < mapSize; c++) {
//				if (map[r][c] > 1) {
//					return false;
//				}
//			}
//		}
//		return true;
	}

    // =======================================================================================================
    // sets the starting position on its the copy Altitude Map created
    //
	public void setStartPosition(int x, int y) {
		//main- sets where to start
		currentX = x;
		currentY = y;
		
		startX = x;
		startY = y;
	}

    // =======================================================================================================
    // calculates the next "x" position to be mined
    //
	public int nextX() {		
		boolean rc;
		
		//rc = moveToCounterClockWise();
		//rc = moveToClockWise();
		rc = moveZigzag();
		
		if (!rc) {
			doneMined = true; /* no way to move: done */
			System.out.print("Map Scan COMPLETED !!\n");
		}
		
		return currentX;
	}
	
    // =======================================================================================================
    // calculates the next "x" position to be mined
    //
	public int nextY() {
		return currentY;
	}
	
	// =======================================================================================================
    // makes a copy of an entire map
    //
	public void setMiningMap(int [][]map) {	
		this.map = map;
		
		// update peakValue//
		peakValue = map[startX][startY]; //XXX
	}
	
	// =======================================================================================================
    // return a copy of the mining progress on its map
    //
	public int[][] shareMinedMap() {
		
		return map;
	}
	
	public void displayMinedMap() {
		
		for (int r = 0; r < mapSize; r++) {
			for (int c = 0; c < mapSize; c++) {
				if (map[r][c] != 0) {
					System.out.print(" " + map[r][c]);
				} else {
					System.out.print(" X");
				}
			}
			System.out.println();
		}
	}
	
	public boolean doMine() {
		int x = currentX;
		int y = currentY;
		
		if (x >= mapSize || y >= mapSize ) 
			return false;
		
		// save altitude
		map[x][y]--;
		
		return true;
	}
	
	//////////////////////////////////////////////////////////////////////////
	// Private method
	// ////////////////////////////////////////////////////////////////////////
	private boolean moveZigzag() {
		
		MatrixDirection[] direction = new MatrixDirection[4];
		
		direction[0] = MatrixDirection.RIGHT;
		direction[1] = MatrixDirection.LEFT;
		if ((startX % 2)==0) { /* even */
			direction[2] = MatrixDirection.BOTTOM;
			direction[3] = MatrixDirection.TOP;			
		} else { /* odd */
			direction[2] = MatrixDirection.TOP;
			direction[3] = MatrixDirection.BOTTOM;		
		}
		
		return moveOneStep(direction);
	}
	
	
	private boolean moveToCounterClockWise() {
		
		MatrixDirection[] direction = new MatrixDirection[8];
		
		direction[0] = MatrixDirection.TOPLEFT;
		direction[1] = MatrixDirection.LEFT;
		direction[2] = MatrixDirection.BOTTOMLEFT;
		direction[3] = MatrixDirection.BOTTOM;
		direction[4] = MatrixDirection.BOTTOMRIGHT;
		direction[5] = MatrixDirection.RIGHT;
		direction[6] = MatrixDirection.TOPRIGHT;
		direction[7] = MatrixDirection.TOP;
		
		return moveOneStep(direction);
		
	}
	
	private boolean moveToClockWise() {
		
		MatrixDirection[] direction = new MatrixDirection[8];
		
		direction[0] = MatrixDirection.TOPRIGHT;
		direction[1] = MatrixDirection.RIGHT;
		direction[2] = MatrixDirection.BOTTOMRIGHT;
		direction[3] = MatrixDirection.BOTTOM;
		direction[4] = MatrixDirection.BOTTOMLEFT;
		direction[5] = MatrixDirection.LEFT;
		direction[6] = MatrixDirection.TOPLEFT;
		direction[7] = MatrixDirection.TOP;
		
		return moveOneStep(direction);
		
	}
	
	private boolean moveOneStep(MatrixDirection[] direction)
	{
		boolean found = false;
		
		for(int i = 0; i < direction.length; i++) {

			found = moveToward(direction[i]);
			if (found) {
				//System.out.printf("(%d,%d)->(%d,%d)\n", x, y, currentX, currentY);
				break;
			}
		}
		
		return found;
	}
	
	private boolean moveToward(MatrixDirection direction) {
		
		boolean result = false;
		
		switch (direction) {
		case TOPLEFT:
			result = towardTopLeft();
			break;
		case LEFT:
			result = towardLeft();
			break;
		case BOTTOMLEFT:
			result = towardBottomLeft();
			break;
		case BOTTOM:
			result = towardBottom();
			break;
		case BOTTOMRIGHT:
			result = towardBottomRight();
			break;
		case RIGHT:
			result = towardRight();
			break;
		case TOPRIGHT:
			result = towardTopRight();
		case TOP:
			result = towardTop();
		default:
			System.out.println("Invalid direction");
		}
		
		return result;
	}
	
	private boolean isValidXY(int x, int y) {
		if (x < 0 || y < 0 || x >= mapSize || y >= mapSize) {
			System.out.printf("Invalid range (%d,%d) MAX=%d\n", x, y, mapSize);
			return false;
		}
		
		if (x != 0 && x != mapSize-1) {
			if (currentY >= startY) {
				if (y < startY ) {
					//System.out.printf("2Invalid range (%d,%d) MAX=%d c=%d s=%d\n", x, y, mapSize, currentY, startY);
					return false;
				}
			} else {
				if (y >= startY) {
					//System.out.printf("3Invalid range (%d,%d) MAX=%d c=%d s=%d\n", x, y, mapSize, currentY, startY);
					return false;
				}
			}
		}
		
		return true;
	}
	
	private boolean isOccupiedMap(int x, int y) {	
		if (map[x][y] != 0) {
			//System.out.printf("occupied [%d][%d]=%d\n", x, y, map[x][y]);
			return true;
		}
		return false;
	}
	
	private boolean isLowerAltitude(int x, int y) {	
		if (map[x][y] < peakValue) {
			//System.out.printf("occupied [%d][%d]=%d\n", x, y, map[x][y]);
			return true;
		}
		return false;
	}
	
	
	
	private boolean checkOpenWay(int x, int y) {
		if (!isValidXY(x,y)) 
			return false;
		
		//if (isOccupiedMap(x, y))
		//	return false;
		
		if (isLowerAltitude(x,y))
			return false;
		
		
		// update current X, Y
		currentX = x;
		currentY = y;
		
		return true;
	}
	
	private boolean towardTopLeft() {	
		// (x, y) -> (x-1, y-1)
		int x = currentX - 1;
		int y = currentY - 1;
		System.out.printf("tl: (%d,%d)->(%d,%d)\n", currentX, currentY, x, y);
		
		boolean ret = checkOpenWay(x,y);
		return ret;
		
		//return checkOpenWay(x,y);
	}
	
	private boolean towardLeft() {
		// (x, y) -> (x, y-1)
		int x = currentX;
		int y = currentY - 1;
		System.out.printf("l: (%d,%d)->(%d,%d)\n", currentX, currentY, x, y);
		
		return checkOpenWay(x,y);
	}
	
	private boolean towardBottomLeft() {
		// (x, y) -> (x+1, y-1)
		
		int x = currentX + 1;
		int y = currentY - 1;
		System.out.printf("bl: (%d,%d)->(%d,%d)\n", currentX, currentY, x, y);
		

		return checkOpenWay(x,y);
	}
	
	private boolean towardBottom() {
		// (x, y) -> (x+1, y)
		int x = currentX + 1;
		int y = currentY;
		System.out.printf("b: (%d,%d)->(%d,%d)\n", currentX, currentY, x, y);
		

		return checkOpenWay(x,y);
	}
	
	private boolean towardBottomRight() {
		// (x, y) -> (x+1, y+1)
		int x = currentX + 1;
		int y = currentY + 1;
		System.out.printf("tb: (%d,%d)->(%d,%d)\n", currentX, currentY, x, y);
		
		return checkOpenWay(x,y);
	}
	
	
	private boolean towardRight() {
		// (x, y) -> (x, y+1)
		int x = currentX;
		int y = currentY + 1;
		System.out.printf("tr: (%d,%d)->(%d,%d)\n", currentX, currentY, x, y);
		

		return checkOpenWay(x,y);
	}

	private boolean towardTopRight() {
		// (x, y) -> (x-1, y+1)
		int x = currentX - 1;
		int y = currentY + 1;
		System.out.printf("r: (%d,%d)->(%d,%d)\n", currentX, currentY, x, y);
		
		return checkOpenWay(x,y);
	}


	private boolean towardTop() {
		int x = currentX - 1;
		int y = currentY;
		System.out.printf("t: (%d,%d)->(%d,%d)\n", currentX, currentY, x, y);
		
		return checkOpenWay(x,y);
	}
	
	///////////////////////////////////////////////////////////////////////

}