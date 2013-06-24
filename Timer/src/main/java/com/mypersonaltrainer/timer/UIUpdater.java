package com.mypersonaltrainer.timer;

import android.os.Handler;

/**
 * Created by Pete on 24.6.2013.
 */
public class UIUpdater {

    private Handler mHandler = new Handler();

    private Runnable mStatusChecker;
    private int UPDATE_INTERVAL = 1000;


    /**
     * Creates an UIUpdater object, that can be used to
     * perform UIUpdates on a specified time interval.
     *
     * @param uiUpdater A runnable containing the update routine.
     */
    public UIUpdater (final Runnable uiUpdater){
        mStatusChecker = new Runnable(){
            @Override
            public void run(){
                //Run the passed runnable
                uiUpdater.run();
                //Re-run it after the update interval
                mHandler.postDelayed(this, UPDATE_INTERVAL);
            }
        };
    }

    /**
     * Starts the periodical update routine (mStatusChecker
     * adds the callback to the handler).
     */
    public void startUpdates(){
        mStatusChecker.run();
    }

    /**
     * Stops the periodical update routine from running,
     * by removing the callback.
     */
    public void stopUpdates(){
        mHandler.removeCallbacks(mStatusChecker);
    }

}
