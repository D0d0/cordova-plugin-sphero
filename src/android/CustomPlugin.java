package com.example.myplugin;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("beep".equals(action)) {
            // print your log here...
            callbackContext.sendPluginResult(new PluginResult(Status.OK, new JSONArray("test")));
            callbackContext.success();
            return true;
        }
        return false;  // Returning false results in a "MethodNotFound" error.
    }
}