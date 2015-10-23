package com.example.myplugin;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Context;
import android.os.Handler;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.Exception;
import java.lang.Integer;
import java.lang.String;

import com.orbotix.ConvenienceRobot;
import com.orbotix.DualStackDiscoveryAgent;
import com.orbotix.common.DiscoveryException;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener;
import com.orbotix.le.RobotLE;

public class CustomPlugin extends CordovaPlugin {

    Integer a = 1;
    private ConvenienceRobot mRobot;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("beep".equals(action)) {
            // print your log here...
            a++;
            String err = "";
            try {
                DualStackDiscoveryAgent.getInstance().addRobotStateListener(new RobotChangedStateListener() {
                    @Override
                    public void handleRobotChangedState(Robot robot, RobotChangedStateNotificationType type) {
                        switch (type) {
                            case Online:
                            /*if (robot instanceof RobotLE) {
                                ((RobotLE) robot).setDeveloperMode(true);
                            }*/

                                //Save the robot as a ConvenienceRobot for additional utility methods
                                mRobot = new ConvenienceRobot(robot);

                                mRobot.isConnected();

                                mRobot.setLed(0.0f, 0.0f, 1.0f);
                                mRobot.drive(90.0f, 5.0f);
                                alert(a.toString(), err, "tu", callbackContext);
                                callbackContext.success();
                            case Disconnected:
                                break;
                        }
                    }
                });
                DualStackDiscoveryAgent.getInstance().startDiscovery(cordova.getActivity().getApplicationContext());
            } catch (Exception e) {
                err = e.getMessage();
            }
            return true;
        }
        return false;  // Returning false results in a "MethodNotFound" error.
    }

    private Context getApplicationContext() {
        return this.cordova.getActivity().getApplicationContext();
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

}