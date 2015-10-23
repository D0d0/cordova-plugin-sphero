package com.example.myplugin;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;


import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import java.lang.Exception;
import java.lang.Integer;
import java.lang.String;

import com.orbotix.ConvenienceRobot;
import com.orbotix.DualStackDiscoveryAgent;
import com.orbotix.common.DiscoveryException;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener;
import com.orbotix.le.RobotLE;
import com.orbotix.classic.view.SpheroConnectionView;

public class CustomPlugin extends CordovaPlugin implements RobotChangedStateListener {

    Integer a = 1;
    private ConvenienceRobot mRobot;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        DualStackDiscoveryAgent.getInstance().addRobotStateListener(this);

        if ("beep".equals(action)) {
            // print your log here...
            String result = "";
            try {
                if (!DualStackDiscoveryAgent.getInstance().isDiscovering()) {
                    DualStackDiscoveryAgent.getInstance().startDiscovery(getApplicationContext());
                }
            } catch (Exception e) {
                result = e.getMessage();
            }
            a++;
            alert(a.toString(), result, "tu", callbackContext);
            callbackContext.success();
            return true;
        }
        return false;  // Returning false results in a "MethodNotFound" error.
    }

    private Context getApplicationContext() {
        return cordova.getActivity().getApplicationContext();
    }

    private synchronized void alert(final String title,
                                    final String message,
                                    final String buttonLabel,
                                    final CallbackContext callbackContext) {
        new AlertDialog.Builder(cordova.getActivity())
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNeutralButton(buttonLabel, new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
                    }
                })
                .create()
                .show();
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

}