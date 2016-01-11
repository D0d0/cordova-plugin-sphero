package com.plugin.sphero;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import com.orbotix.classic.DiscoveryAgentClassic;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener;

import android.content.Context;

import com.orbotix.ConvenienceRobot;

public class SpheroPlugin extends CordovaPlugin {

    private ConvenienceRobot mRobot;

    @Override
    public boolean execute(String action, JSONArray args,
                           CallbackContext callbackContext) throws JSONException {
        if ("connect".equals(action)) {
            connect(callbackContext);

            return true;
        }

        if ("disconnect".equals(action)) {
            disconnect();

            return true;
        }

        if ("changeColor".equals(action)) {
            changeColor(args);

            return true;
        }

        return false;
    }

    private void connect(CallbackContext callbackContext) {
        if (mRobot == null) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    DiscoveryAgentClassic.getInstance().addRobotStateListener(
                            new RobotChangedStateListener() {
                                @Override
                                public void handleRobotChangedState(Robot robot,
                                                                    RobotChangedStateNotificationType type) {
                                    switch (type) {
                                        case Online:

                                            // Save the robot as a ConvenienceRobot for
                                            // additional utility methods
                                            mRobot = new ConvenienceRobot(robot);

                                            mRobot.setLed(0.0f, 0.0f, 1.0f);
                                            if (DiscoveryAgentClassic.getInstance()
                                                    .isDiscovering()) {
                                                DiscoveryAgentClassic.getInstance()
                                                        .stopDiscovery();
                                            }
                                            callbackContext.success();
                                        case Disconnected:
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            });
                }
            });
        }
    }

    private void disconnect() {
        if (mRobot != null) {
            mRobot.disconnect();
            mRobot = null;
        }
    }

    private void changeColor(JSONArray args) throws JSONException {
        if (mRobot != null) {

            mRobot.setLed(args.getLong(0), args.getLong(1), args.getLong(2));
        }
    }

    public void setRobot(ConvenienceRobot r) {
        this.mRobot = r;
    }

    @Override
    public void onDestroy() {
        if (mRobot != null) {
            mRobot.disconnect();
            mRobot = null;
        }
        super.onDestroy();
    }

    private Context getApplicationContext() {
        return this.cordova.getActivity().getApplicationContext();
    }

}