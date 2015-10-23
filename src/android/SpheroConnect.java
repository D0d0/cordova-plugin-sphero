package com.example.myplugin;

import org.apache.cordova.CallbackContext;
import android.os.AsyncTask;
import android.content.Context;


import com.orbotix.ConvenienceRobot;
import com.orbotix.DualStackDiscoveryAgent;
import com.orbotix.common.DiscoveryException;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener;
import com.orbotix.le.RobotLE;

public class SpheroConnect extends AsyncTask<String, String, String> {

    private ConvenienceRobot mRobot;

    private CallbackContext callbackContext;

    public SpheroConnect(CallbackContext callbackContext, Context c) {
        this.callbackContext = callbackContext;
        DualStackDiscoveryAgent.getInstance().startDiscovery(c);
    }

    @Override
    public String doInBackground(String... arg0) {
        DualStackDiscoveryAgent.getInstance().addRobotStateListener(new RobotChangedStateListener() {
            @Override
            public void handleRobotChangedState(Robot robot, RobotChangedStateNotificationType type) {
                switch (type) {
                    case Online:
                        if (robot instanceof RobotLE) {
                            ((RobotLE) robot).setDeveloperMode(true);
                        }

                        //Save the robot as a ConvenienceRobot for additional utility methods
                        mRobot = new ConvenienceRobot(robot);

                        mRobot.isConnected();

                        mRobot.setLed(0.0f, 0.0f, 1.0f);
                        mRobot.drive(90.0f, 5.0f);
                        this.callbackContext.success();
                    case Disconnected:
                        break;
                }
            }
        });
        return null;
    }
}