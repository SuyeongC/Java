package com.company;

//import static com.company.MasterMap.*;
import static com.company.ProjConstants.*;


public class Main {

    public static void main(String[] args) {

    	int mapSize;
    	int mappingCredit;
    	int miningCredit;
    	
        MasterMap mapMaster = new MasterMap(TEST_XY);
        //mastermap class object --> mapMaster

        mapMaster.SetDefaultMap();
        //run the method SetDefaultMap from mapMaster
        //setting the SetDefaultMap feature on console through mapMaster
        mapSize = mapMaster.getMapSize();
        //just like int, long etc, defining the size of data being imported

        // show the setting map first
        mapMaster.displayMap();
        
        // initialize mapped array(where the mapcount in stored) in MasterMap 
        mapMaster.getAltitude(0,0);
        // initialize mined array in MasterMap
        mapMaster.minePosition(0, 0);
        
        /////////////////////////////////////////////////
        // Create Map Robot to explore map
        /////////////////////////////////////////////////
        //MapRobot mapRobot = new MapRobot(TEST_XY);
        MapRobot mapBot = new MapRobot(mapSize);
        
        // set starting position 
        int x = 7;
        int y = 6;
        int altitude;
        
        mapBot.setStartPosition(x,y); // 3,2
       //int j = 0;
        
        // explore the Map by mapBot
        while(!mapBot.mappingDone()) {
        	altitude = mapMaster.getAltitude(x, y); /* visit */// these x y are the original position given and the x y used after this is the new one (nextXY)
        	System.out.printf("(%d,%d)=%d\n", x, y, altitude);
        	mapBot.setAltitude(altitude);
        	x = mapBot.nextX();
        	y = mapBot.nextY();
        	// first get the original position first - before mapping
        	//if (j++ == 350) break;
        	
        }
        //while mappingDone isn't finished - run
        
     
        // display map in mapBot
        mapBot.DisplayAltitueMap();
        
        // display mapping progress in master
        mapMaster.displayMappingProgress(); 			// progress 
        //showing the mapped
        mappingCredit = mapMaster.getMappingCredits(); 	// credit
        //showing the copy of the mastermap
        System.out.println("\n\tMapping Credit is "+mappingCredit);
        
        //display peak info
        int numOfPeak = 0;
        int mineStartX = 0;
        int mineStartY = 0;
        
        numOfPeak = mapBot.getnumberOfPeaks();
        System.out.printf("#peak=%d\n", numOfPeak);
        //%d displays ,1,2,3, in order - comma
        for(int i = 0; i < numOfPeak; i++) {
        	System.out.printf("%d. (%d,%d)\n", i+1, mapBot.getPeakX(i), mapBot.getPeakY(i));
        	if (i==0) mineStartX = mapBot.getPeakX(i);
        	if (i==0) mineStartY = mapBot.getPeakX(i);
        }
        
        /////////////////////////////////////////////////
        // Create Mine Robot to mine the altitude
        /////////////////////////////////////////////////  \
        
		MineRobot mineBot = new MineRobot(mapSize);
		
		// copy map to mineBot's map
		mineBot.setMiningMap(mapBot.AltitudeMap());
		
		// display the copied map
		System.out.println();
		//mineBot.DisplayAltitueMap();
		
//        // set start position
		mineBot.setStartPosition(mineStartX,mineStartY);
//		
		x = mineStartX;
		y = mineStartY;
		System.out.printf("x=%d y=%d\n",  x, y);


		// j = 0;
		
		do {
			mapMaster.minePosition(x,y);
			System.out.printf("(%d,%d)\n", x, y);
			mineBot.doMine();
			x = mineBot.nextX();
			y = mineBot.nextY();
			//if (j++ == 500) break;
			
		} while(!mineBot.miningDone());
        
        
        // display mining progress
        mapMaster.displayMiningProgress();
        
        mineBot.displayMinedMap();
        //mapMaster.getMiningCredits();
        //mapMaster.getMinedData();
//		
//        // display mapping progress in master
//        mapMaster.displayMiningProgress(); 			// progress 
//        miningCredit = mapMaster.getMiningCredits(); 	// credit
//        System.out.println("\n\tMining Credit is "+miningCredit);
    }
}