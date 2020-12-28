package com.company;

import java.util.Scanner;

import static com.company.ProjConstants.*;

/**
 * Created by Robert Hubert on 22/05/2019.
 */
public class MasterMap {

    private int[][] mapped;
    private int[][] mined;
    private int[][] masterMap;

    private int mappingCredits = INVALID;
    private int miningCredits = INVALID;
    private int mapSize = INVALID;

    private boolean initMap = false;
    private boolean initMine = false;

    // =======================================================================================================
    // this is the constructor that will create required structures with default values
    //
    
    public MasterMap() {

        mapped = new int[MAX_XY][MAX_XY];
        mined = new int[MAX_XY][MAX_XY];
        masterMap = new int[MAX_XY][MAX_XY];

        mapSize = MAX_XY;

    }

    // =======================================================================================================
    // this is the constructor that will create required structures to a user define size
    //
    
    public MasterMap(int arraySize) {

        mapped = new int[arraySize][arraySize];
        mined = new int[arraySize][arraySize];
        masterMap = new int[arraySize][arraySize];

        mapSize = arraySize;

    }
    
    // =======================================================================================================
    // this is the constructor that will create required structures to a user define size
    //

    public void SetDefaultMap() {

        int mapOption = 0;

        Scanner scanSystemIn = new Scanner(System.in, "UTF-8");

        if (mapSize != TEST_XY) {
            System.out.println("\n\tERROR: Improper Value given to Constuctor during for Object creation. (Map sizing does not match TEST_XY = " + TEST_XY + ").");
        } else {

            System.out.println("================================================================");
            System.out.println(" 0 -> FLAT        ALL VALUES = 2");
            System.out.println(" 1 -> 1  peak,    MAX = 5, MIN = 1");
            System.out.println(" 2 -> 2  peaks,   MAX = 7, MIN = 1");
            System.out.println(" 3 -> RIDGE LINE, MAX = 5, MIN = 1");
            System.out.println(" 4 -> BOWL,       MAX = 6, MIN = 2");
            System.out.print("Select which test map you would like to use: ");

            if (scanSystemIn.hasNextInt()) {
                mapOption = scanSystemIn.nextInt();

                switch (mapOption) {

                    // 0 -> FLAT; ALL VALUES = 2
                    case 0: {
                        masterMap = FLAT_MAP;
                        break;
                    }

                    // 1 -> 1  peak; MAX = 5, MIN = 1
                    case 1: {
                        masterMap = ONE_PEAK_MAP;
                        break;
                    }

                    //  2 -> 2  peaks; MAX = 7, MIN = 1
                    case 2: {
                        masterMap = TWO_PEAK_MAP;
                        break;
                    }

                    //3 -> RIDGE LINE; MAX = 5, MIN = 1
                    case 3: {
                        masterMap = RIDGE_LINE;
                        break;
                    }

                    // 4 -> BOWL; AX = 6, MIN = 2
                    case 4: {
                        masterMap = BOWL_MAP;
                        break;
                    }

                    // Default uses FLAT Map; ALL VALUES = 2
                    default: {
                    	
//                        if (WARNING_MSG) {
//                            System.out.println("\n\tWARNING: Input Error - Flat Map - being used.");
//                        }
//                        masterMap = FLAT_MAP;
                    	mapSize = mapOption;  // Choi
                    	GenerateRandomMap();
                        break;
                    } // end default
                } // end switch
            }
            // Input error uses FLAT Map; ALL VALUES = 2
            else {
                if (WARNING_MSG) {

                    System.out.println("\n\tWARNING: Improper Input Type (integer expected).");
                    System.out.println("\n\tWARNING: Input Error - Flat Map - being used.");

                    masterMap = FLAT_MAP;
                }
            }
        }
        scanSystemIn.close();
        
    }
    



    // =======================================================================================================
    //this will return the value stored the the “x, y” position in the master map.
    //

    public int getAltitude(int x, int y) {

        if (!initMap) {

            if (DBG_MAPPING) {
                System.out.println("\n\tDBG MAPPING: initializing map data to 0");
            }

            for (int r = 0; r < mapSize; r++) {
                for (int c = 0; c < mapSize; c++) {
                    mapped[r][c] = 0;
                }
            }

            initMap = true;
        } else {
            // count the number of times a location is visited
            mapped[x][y]++;

            if (DBG_MAPPING) {
                System.out.println("\n\tDBG MAPPING: Altitude location mapped[" + x + "][" + y + "] = " + mapped[x][y] + " times.");
            }

        }

        return masterMap[x][y];
    }

    // =======================================================================================================
    // this will print the mapping progress to the screen
    //

    public int[][] getMapData() {

        if (!initMap && WARNING_MSG) {
            System.out.println("\n\tWARNING: Mapping has not been performed.");
        }

        return mapped;
    }

    // =======================================================================================================
    // Private utility routine to print map arrays
    // this is not used for mining display
    //

    private void displayMapInfo(int[][] theMap) {

        for (int r = 0; r < mapSize; r++) {
            for (int c = 0; c < mapSize; c++) {

                if (theMap[r][c] != 0) {
                    System.out.print(" " + theMap[r][c]);
                } else {
                    System.out.print(" X");
                }
            }
            System.out.println();
        }

    }

    // =======================================================================================================
    // this will print the Starting map data to the screen
    //

    public void displayMap() {

        for (int r = 0; r < mapSize; r++) {
            for (int c = 0; c < mapSize; c++) {

                if (masterMap[r][c] != 0) {
                    System.out.print(" " + masterMap[r][c]);
                } else {
                    System.out.print(" X");
                }
            }
            System.out.println();
        }

    }
    
    
    // =======================================================================================================
    // this will print the mapping progress to the screen
    //

    public void displayMappingProgress() {

        System.out.println("\n\n================================================================");
        System.out.println("This shows the amount of mapping performed.\n");

        if (!initMap && WARNING_MSG) {
            System.out.println("\n\tWARNING:The altitude map has not been completed.\n");
        }

        displayMapInfo(mapped);

        System.out.println("\n================================================================\n\n");

    }

    // =======================================================================================================
    //this will return the credit value stored in the Master
    //

    public int getMappingCredits() {
    	
    	int vCounter; /* visited Counter */
    	
    	mappingCredits = 0;
    	
        for (int r = 0; r < mapSize; r++) {
            for (int c = 0; c < mapSize; c++) {
            	
            	vCounter = mapped[r][c];
            	if (vCounter == 1) {
            		mappingCredits++;
            	} else if (vCounter > 1) {
            		mappingCredits -= (vCounter - 1) * 2;
            	}
            }
        }

//        if (INFO_MSG) {
//            System.out.println("\n\tINFO: Mapping Credit calculation is incomplete.\n");
//        }
        
        return mappingCredits;
    }

    // =======================================================================================================
    //this will mine a location/position
    //

    public void minePosition(int x, int y) {
        if (!initMine) {

            if (!initMap && WARNING_MSG) {
                System.out.println("\n\tWARNING: The Arial Mapping has not been performed.");
            }

            if (DBG_MAPPING) {
                System.out.println("\n\tDBG MINING: initializing mine data to 0");
            }

            for (int r = 0; r < mapSize; r++) {
                for (int c = 0; c < mapSize; c++) {
                    mined[r][c] = 0;
                }
            }

            initMine = true;

        } else {
            // count the number of times a location is visited
            mined[x][y]++;
            if (DBG_MINING) {
                System.out.println("\n\tDBG MINING: mined[" + x + "][" + y + "] = " + mined[x][y] + " times.");
            }
        }
    }

    // =======================================================================================================
    // this will print the mining progress to the screen
    //

    public int[][] getMinedData() {

        if (!initMine && WARNING_MSG) {
            System.out.println("\n\tWARNING: Mining has not been performed.");
        }
        return mined;
    }


    // =======================================================================================================
    // this will print the mining progress to the screen
    //

    public void displayMiningProgress() {

        System.out.println("\n\n================================================================");
        System.out.println("This shows the amount of mining performed.\n");

        if (!initMine && DBG_MINING) {
            System.out.println("\n\tDBG MINING: The mining data is not initialized and has not been completed.\n");
        }

        for (int r = 0; r < mapSize; r++) {
            for (int c = 0; c < mapSize; c++) {

                if (mined[r][c] != 0) {

                    // subtract the number of times the location has been mined and display this
                    System.out.print(" " + (masterMap[r][c] - mined[r][c]));
                } else {
                    System.out.print(" X");
                }
            }
            System.out.println();
        }
        System.out.println("\n\n================================================================");
    }

    // =======================================================================================================
    // this will return the credit value stored in the Master
    //

    public int getMiningCredits() {

        if (INFO_MSG) {
            System.out.println("\n\tINFO: Mining Credit calculation is incomplete.\n");
        }

        return miningCredits;
    }
 
    // =======================================================================================================
    // this will return the master map size stored in the Master : Choi
    //
    public int getMapSize() {
    	return mapSize;
    }
    
    // =======================================================================================================
    // Private utility routine to generate random map arrays : Choi
    //
    
    private void GenerateRandomMap() {
    	int randomNumber;
    	for (int r = 0; r < mapSize; r++) {
            for (int c = 0; c < mapSize; c++) {
            	randomNumber = (int )(Math.random()*9 + 1); // range 1 to 9
            	masterMap[r][c] = randomNumber;
            }
        }
    }

}
