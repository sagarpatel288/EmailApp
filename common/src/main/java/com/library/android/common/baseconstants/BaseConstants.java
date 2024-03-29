package com.library.android.common.baseconstants;

public class BaseConstants {
    // Note: 11/27/2018 by sagar  Default null value to be compared
    public static final int NULL = (int) -1L;

    // Note: 11/27/2018 by sagar  App tag to be used in logs
    public static final String TAG = " :commonLog: ";

    // Note: 11/27/2018 by sagar  Error message to print or show while trying to use reflection for typeface utility
    public static final String STR_MSG_ERROR_TYPEFACE_REFLECTION
            = "Use getInstance() method to get single instance of this class";

    // Note: 11/27/2018 by sagar  Delay in millisecond to perform continuous touch event for quantity modification
    public static final long DELAY = 100;

    public static final class ItemViewType {
        public static final int ITEM_VIEW_TYPE = 1;
        public static final int PROGRESS_BAR_CIRCULAR_LOADING = 2;
    }
}
