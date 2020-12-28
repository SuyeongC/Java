package com.company;

import static com.company.ProjConstants.*;

/**
 * Created by Robert Hubert on 22/05/2019.
 */
public class MapRobot {
	private int mapSize = INVALID;
	//to see which spots are not visited or set with a valid value 

	private int[][] map;
	private int[][] peakPosition;
	
	private int peakValue = 0;

	private int currentX = 0;
	private int currentY = 0;
	
	private int startX = 0;
	private int startY = 0;
//setting them all zero so that they don't start with an invalid value

	private boolean doneMapped = false;
	private boolean donePeaked = false;
	
	private enum MatrixDirection { TOPLEFT, LEFT, BOTTOMLEFT, BOTTOM, BOTTOMRIGHT, RIGHT, TOPRIGHT, TOP }
//order of the direction - left to right priority 
	
	public MapRobot() {
		 map = new int[MAX_XY][MAX_XY];
		 mapSize = MAX_XY;
	}

	
	
	public MapRobot(int arraySize) {
		
		 map = new int[arraySize][arraySize];
		 mapSize = arraySize;
	}
	
	
	public boolean mappingDone() {
		return doneMapped;
		
//		for (int r = 0; r < mapSize; r++) {
//			for (int c = 0; c < mapSize; c++) {
//				if (map[r][c] == 0) {
//					return false;
//				}
//			}
//		}
//		return true;
	}

	
// =======================================================================================================
// if this method returns true then this robot is done mapping, otherwise it returns false
//
	
	
	public void setStartPosition(int x, int y) {
		//main- sets where to start
		currentX = x;
		currentY = y;
		
		startX = x;
		startY = y;
	}
	
// =======================================================================================================
// this method sets the starting position on the MasterMap 
//
	
	
	public int nextX() {
		
		boolean rc;
		
		//rc = moveToCounterClockWise();
		//rc = moveToClockWise();
		//rc = moveZigzag2();
		rc = moveZigzag();
		
		if (!rc) {
			doneMapped = true; /* no way to move: done */
			System.out.print("Map Scan COMPLETED !!\n");
		}
		
		return currentX;
	}
// =======================================================================================================
//	this is the method that calculates the next “x” position to be interrogated
//
	
	
	
	public int nextY() {
		
		return currentY;
	}
	
	
// =======================================================================================================
//	this is the method that calculates the next “y” position to be interrogated
//
	
	
	
	
	
	public int[][] AltitudeMap() {
		
		return map;
	}
// =======================================================================================================
// this is the method that returns the entire Altitude Map stored in the mapRobot
//
	
	
	
	
	public void DisplayAltitueMap() {
		
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
	
// =======================================================================================================
// this is the method that prints the entire map to the screen 
//
	
	
	
	
	
	
	
	public int getnumberOfPeaks() {
		
        if (!doneMapped && WARNING_MSG) {
            //System.out.println("\n\tWARNING: The peak info does not exist.");
            return -1;
        }
        
        if (!donePeaked) {
        	calculatePeak();
        }
        return peakPosition.length;
        
	}
// =======================================================================================================
// this is the method that returns the number of “peaks” on the map/grid. 
//	
	
	
	public int getPeakX(int peakID) {
		
        if (!doneMapped && WARNING_MSG) {
            System.out.println("\n\tWARNING: The peak info does not exist.");
            return -1;
        }
        if (!donePeaked) {
        	calculatePeak();
        }
        
        return peakPosition[peakID][0];
	}
// =======================================================================================================	
// this is the method that returns the X value for one of the peaks (peakID #)
//
	
	
	
	
	
	public int getPeakY(int peakID) {
		
        if (!doneMapped && WARNING_MSG) {
            System.out.println("\n\tWARNING: The peak info does not exist.");
            return -1;
        } 
        if (!donePeaked) {
        	calculatePeak();
        }
        
        return peakPosition[peakID][1];
	}
// =======================================================================================================
// this is the method that returns the Y value for one of the peaks  (peakID #)
//
	
	
	
	

	public int[][] getAllPeakPositions(){
        if (!doneMapped && WARNING_MSG) {
            System.out.println("\n\tWARNING: The peak info does not exist.");
            return null;
        } 
        if (!donePeaked) {
        	calculatePeak();
        }
		
		return peakPosition;	
	}
	
// =======================================================================================================
// this is the method that returns an array containing all of the peaks positions on the map
//
	
	public boolean setAltitude(int altitude) {
		int x = currentX;
		int y = currentY;
		
		if (x >= mapSize || y >= mapSize ) 
			return false;
		
		if (altitude <= 0) 
			return false;
		
		if (map[x][y] != 0) {
			// it should be 0 if it's not visited
			System.out.println("Already ocupied\n");
			if (map[x][y] != altitude) {
				System.out.printf("mapped[%d][%d] = %d, al=%d\n", x, y, map[x][y], altitude);	
			}
        //error message - just checking
		}
		
		//copying the value of the altitude onto the (my) map
		map[x][y] = altitude;
		
		//comparing the current peak value with the new value copied above
		//if it is greater than the original peakValue, it is set as the new peak value
		if (altitude > peakValue) {
			peakValue = altitude;
		}
		
		return true;
	}
	
	//////////////////////////////////////////////////////////////////////////
	// Private method
	// ////////////////////////////////////////////////////////////////////////
	
	private boolean moveZigzag() {
		
		
//		//array - runs from 0 to so on
//		MatrixDirection[] direction = new MatrixDirection[4];
//		
//		
//		direction[0] = MatrixDirection.RIGHT;
//		direction[1] = MatrixDirection.LEFT;
//		if ((startX %2) ==0){
//			direction[2] = MatrixDirection.BOTTOM;
//			direction[3] = MatrixDirection.TOP;
//		}else {
//			direction[2] = MatrixDirection.TOP;
//			direction[3] = MatrixDirection.BOTTOM;
//		}
//		
//		
//		return moveOne(direction);
		
		boolean result;
		result = towardRight();
		
		if (!result) {
			result = towardLeft();
		}
		
		if (!result) {
			if (startX%2 == 0) { 
				//two scenerio - it goes to the direction where the number of rows from the start position is an even number 
				//so it can make a turn 
				result = towardBottom();
				if (!result) {
					result = towardTop();
				}
			} else {
				result = towardTop();
				if (!result) {
					result = towardBottom();
					
			//if the count is not divisible by 2, it should go the opposite direction in which the count is divisible by 2
				}
			}
		}
		return result;
	}
	

	/*
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
		
		return moveOne(direction);
		
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
	
	private boolean moveOne(MatrixDirection[] direction)
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
	*/
	
	private boolean isValidXY(int x, int y) {
		// || means "or"
		if (x < 0 ||  y < 0 || x >= mapSize || y >= mapSize) {
			//System.out.printf("Invalid range (%d,%d) MAX=%d\n", x, y, mapSize);
			return false;
		}
		//checking for the real boundaries
		
		if (x != 0 && x != mapSize -1) {
			// x - row, y - column
			if (currentY >= startY) {
				//checking which side of the virtual wall the robot is located in
				//the startY - the virtual wall
				if (y < startY) {
				//when coming toward the virtual wall, it the new value y should not be smaller 
				//than the virtual wall meaning it has gone past the wall
				//thus it returns false
					//talking about to diff.matter x and y, so it's not in the same (&&) condition but below
					return false;
				}
			} else {
				
				if (y >= startY ) {
				//on the other side of the wall(from where it initially started, it cannot go over the 
				//virtual wall meaning that the y value cannot be bigger than the virtual wall
				//in this case it returns false
					
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
		//if you have visited the spot, the value is not going to be zero 
		}
		return false;
	}
	
	private boolean checkOpenSpot(int x, int y) {
		if (!isValidXY(x,y)) 
			return false;
		//seeing if the new x, y value are invalid values -- looking for a virtual wall and boundaries
		
		if (isOccupiedMap(x, y))
			return false;
		//if the cell already got a value(above) it will return false - already visited
		
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
		
		return checkOpenSpot(x,y);
		//checking all the conditions of chekcOpenway + boolean isValidXY
	}
	
	private boolean towardLeft() {
		// (x, y) -> (x, y-1)
		int x = currentX;
		int y = currentY - 1;
		System.out.printf("l: (%d,%d)->(%d,%d)\n", currentX, currentY, x, y);
		
		return checkOpenSpot(x,y);
	}
	
	private boolean towardBottomLeft() {
		// (x, y) -> (x+1, y-1)
		
		int x = currentX + 1;
		int y = currentY - 1;
		//System.out.printf("bl: (%d,%d)->(%d,%d)\n", currentX, currentY, x, y);
		

		return checkOpenSpot(x,y);
	}
	
	private boolean towardBottom() {
		// (x, y) -> (x+1, y)
		int x = currentX + 1;
		int y = currentY;
		//System.out.printf("b: (%d,%d)->(%d,%d)\n", currentX, currentY, x, y);
		

		return checkOpenSpot(x,y);
	}
	
	private boolean towardBottomRight() {
		// (x, y) -> (x+1, y+1)
		int x = currentX + 1;
		int y = currentY + 1;
		//System.out.printf("br: (%d,%d)->(%d,%d)\n", currentX, currentY, x, y);
		
		return checkOpenSpot(x,y);
	}
	
	
	private boolean towardRight() {
		// (x, y) -> (x, y+1)
		int x = currentX;
		int y = currentY + 1;
		//System.out.printf("r: (%d,%d)->(%d,%d)\n", currentX, currentY, x, y);
		

		return checkOpenSpot(x,y);
	}

	private boolean towardTopRight() {
		// (x, y) -> (x-1, y+1)
		int x = currentX - 1;
		int y = currentY + 1;
		//System.out.printf("tr: (%d,%d)->(%d,%d)\n", currentX, currentY, x, y);
		
		return checkOpenSpot(x,y);
	}


	private boolean towardTop() {
		int x = currentX - 1;
		int y = currentY;
		//System.out.printf("t: (%d,%d)->(%d,%d)\n", currentX, currentY, x, y);
		
		return checkOpenSpot(x,y);
	}
	
	private void calculatePeak() {
		int count = 0;
		
		// get peak counter
		for (int r = 0; r < mapSize; r++) {
			for (int c = 0; c < mapSize; c++) {
				if (map[r][c] == peakValue) {
					count++;
					//counting how many values are equal to the peak value
				}
			}
		}
		
		peakPosition = new int[count][2]; //XY
		
		// build peak array
		int index = 0;
		for (int r = 0; r < mapSize; r++) {
			for (int c = 0; c < mapSize; c++) {
				if (map[r][c] == peakValue) {
					peakPosition[index][0] = r; // X
					peakPosition[index][1] = c; // Y
					index++;
				}
			}
		}
		donePeaked = true;
	}
}