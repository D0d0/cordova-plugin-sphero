package com.example.myplugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class CustomPlugin extends CordovaPlugin {

   @Override
   public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if ("beep".equals(action)) {
        // print your log here... 
        callbackContext.success();
        return true;
    }
    return false;  // Returning false results in a "MethodNotFound" error.
    }
 }