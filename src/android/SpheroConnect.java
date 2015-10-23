package com.example.myplugin;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.orbotix.ConvenienceRobot;
import com.orbotix.DualStackDiscoveryAgent;
import com.orbotix.common.DiscoveryException;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener;
import com.orbotix.le.RobotLE;

public class SpheroConnect implements RobotChangedStateListener {

    private ConvenienceRobot mRobot;

    public SpheroConnect(Context c) {

        DualStackDiscoveryAgent.getInstance().addRobotStateListener(this);

        if (!DualStackDiscoveryAgent.getInstance().isDiscovering()) {
            try {
                DualStackDiscoveryAgent.getInstance().startDiscovery(c);
            } catch (DiscoveryException e) {

            }
        }
    }

    @Override
    public void handleRobotChangedState(Robot robot, RobotChangedStateNotificationType type) {
        switch (type) {
            case Online: {

                //If robot uses Bluetooth LE, Developer Mode can be turned on.
                //This turns off DOS protection. This generally isn't required.
                if (robot instanceof RobotLE) {
                    ((RobotLE) robot).setDeveloperMode(true);
                }

                //Save the robot as a ConvenienceRobot for additional utility methods
                mRobot = new ConvenienceRobot(robot);

                //Start blinking the robot's LED
                blink(false);
                break;
            }
        }
    }

    private void blink(final boolean lit) {
        if (mRobot == null)
            return;

        if (lit) {
            mRobot.setLed(0.0f, 0.0f, 0.0f);
        } else {
            mRobot.setLed(0.0f, 0.0f, 1.0f);
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                blink(!lit);
            }
        }, 2000);
    }
}