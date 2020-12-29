package com.company;

public class ProjConstants {

// ---------*---------*---------*---------*---------*
    // Integer Constants

    public static final int INVALID = -1;
    public static final int MAX_XY = 1000;
    public static final int TEST_XY = 20;

    // Constants that will enable different log messages
    public static final boolean WARNING_MSG =  true;
    public static final boolean INFO_MSG    =  true;
    public static final boolean DBG_MINING  =  true;
    public static final boolean DBG_MAPPING =  true;

    // Default Maps provided for testing
    public static final int[][] RIDGE_LINE = {
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,2,3,4,5,4,3,2,1,1,1,1,1,1 }
    };

    public static final int[][] FLAT_MAP = {
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 },
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 }
    };

    public static final int[][] BOWL_MAP = {
            {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6 },
            {6,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,6 },
            {6,5,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,5,6 },
            {6,5,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,5,6 },
            {6,5,4,3,2,2,2,2,2,2,2,2,2,2,2,2,3,4,5,6 },
            {6,5,4,3,2,2,2,2,2,2,2,2,2,2,2,2,3,4,5,6 },
            {6,5,4,3,2,2,2,2,2,2,2,2,2,2,2,2,3,4,5,6 },
            {6,5,4,3,2,2,2,2,2,2,2,2,2,2,2,2,3,4,5,6 },
            {6,5,4,3,2,2,2,2,2,2,2,2,2,2,2,2,3,4,5,6 },
            {6,5,4,3,2,2,2,2,2,2,2,2,2,2,2,2,3,4,5,6 },
            {6,5,4,3,2,2,2,2,2,2,2,2,2,2,2,2,3,4,5,6 },
            {6,5,4,3,2,2,2,2,2,2,2,2,2,2,2,2,3,4,5,6 },
            {6,5,4,3,2,2,2,2,2,2,2,2,2,2,2,2,3,4,5,6 },
            {6,5,4,3,2,2,2,2,2,2,2,2,2,2,2,2,3,4,5,6 },
            {6,5,4,3,2,2,2,2,2,2,2,2,2,2,2,2,3,4,5,6 },
            {6,5,4,3,2,2,2,2,2,2,2,2,2,2,2,2,3,4,5,6 },
            {6,5,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,5,6 },
            {6,5,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,5,6 },
            {6,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,6 },
            {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6 }
    };

    public static final int[][] ONE_PEAK_MAP = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,2,3,3,3,3,3,2,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,2,3,4,4,4,3,2,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,2,3,4,5,4,3,2,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,2,3,4,4,4,3,2,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,2,3,3,3,3,3,2,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 }
    };

    public static final int[][] TWO_PEAK_MAP = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 },
            {1,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1 },
            {1,2,3,3,3,3,3,3,3,3,3,2,1,1,1,1,1,1,1,1 },
            {1,2,3,4,4,4,4,4,4,4,3,2,1,1,1,1,1,1,1,1 },
            {1,2,3,4,5,5,5,5,5,4,3,2,1,1,1,1,1,1,1,1 },
            {1,2,3,4,5,6,6,6,5,4,3,2,1,1,1,1,1,1,1,1 },
            {1,2,3,4,5,6,7,6,5,4,3,2,1,1,1,1,1,1,1,1 },
            {1,2,3,4,5,6,6,6,5,4,3,2,1,1,1,1,1,1,1,1 },
            {1,2,3,4,5,5,5,5,5,4,3,2,2,2,2,2,2,2,2,1 },
            {1,2,3,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,2,1 },
            {1,2,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,3,2,1 },
            {1,2,2,2,2,2,2,2,2,3,4,5,5,5,5,5,4,3,2,1 },
            {1,1,1,1,1,1,1,1,2,3,4,5,6,6,6,5,4,3,2,1 },
            {1,1,1,1,1,1,1,1,2,3,4,5,6,7,6,5,4,3,2,1 },
            {1,1,1,1,1,1,1,1,2,3,4,5,6,6,6,5,4,3,2,1 },
            {1,1,1,1,1,1,1,1,2,3,4,5,5,5,5,5,4,3,2,1 },
            {1,1,1,1,1,1,1,1,2,3,4,4,4,4,4,4,4,3,2,1 },
            {1,1,1,1,1,1,1,1,2,3,3,3,3,3,3,3,3,3,2,1 },
            {1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,1 },
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 }
    };


    // PRIVATE //

    // ---------*---------*---------*---------*---------*---------*---------*---------*
    // The caller references the constants using ProjConstants.MAXDATA,
    // and so on. So the caller should be prevented from constructing objects of
    // this class.
    // --> By declaring this private constructor for the class we accomplish this. <--
    //
    private ProjConstants(){
        //this prevents even the native class from calling this constructor as well
        throw new AssertionError();
    }

}