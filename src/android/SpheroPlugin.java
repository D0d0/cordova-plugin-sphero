package com.plugin.sphero;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;

import com.orbotix.ConvenienceRobot;

public class SpheroPlugin extends CordovaPlugin {

    private ConvenienceRobot mRobot;
    private SpheroMacro spheroMacro;

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

        if ("setRobotToDefaultState".equals(action)) {
            setRobotToDefaultState();

            return true;
        }

        if ("runMacro".equals(action)) {
            runMacro(args);

            return true;
        }

        return false;
    }

    private void connect(CallbackContext callbackContext) {
        if (mRobot == null) {
            try {
                new SpheroConnect(callbackContext, getApplicationContext(),
                        this).execute();
            } catch (Exception e) {

            }
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

    private void setRobotToDefaultState() {
        if (mRobot != null) {
            spheroMacro.setRobotToDefaultState();
        }
    }

    public void runMacro(JSONArray args) throws JSONException{
        if (mRobot != null) {
            spheroMacro.runMacro(args);
        }
    }

    public void setRobot(ConvenienceRobot r) {
        this.mRobot = r;
        spheroMacro = new SpheroMacro(mRobot);
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