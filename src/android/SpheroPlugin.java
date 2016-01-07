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
            try {
                new SpheroConnect(callbackContext, getApplicationContext(),
                        this).execute();
            } catch (Exception e) {

            }

            return true;
        }

        if ("disconnect".equals(action)) {
            if (mRobot != null) {
                mRobot.disconnect();
                mRobot = null;
            }

            return true;
        }

        return false; // Returning false results in a "MethodNotFound" error.
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