package com.plugin.sphero;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

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
        try {
            new SpheroConnect(callbackContext, getApplicationContext(),
                    this).execute();
        } catch (Exception e) {

        }
    }

    private void disconnect() {
        if (mRobot != null) {
            mRobot.disconnect();
            mRobot = null;
        }
    }

    private void changeColor(JSONArray args) {
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