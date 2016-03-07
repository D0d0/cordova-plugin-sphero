package com.plugin.sphero;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.orbotix.macro.MacroObject;
import com.orbotix.macro.cmd.Delay;
import com.orbotix.macro.cmd.RGB;
import com.orbotix.macro.cmd.Roll;

public class MacroBuilder {

    private final static float SPEED = 0.5f;
    private static int orientation = 0;

    public static void buildMacro(MacroObject macro, JSONArray args) throws JSONException {
        for (int i = 0; i < args.length(); i++) {
            JSONObject object = args.getJSONObject(i);
            if ("forward".equals(object.getString("type").toLowerCase())) {
                moveForward(macro);
                stopMacro(macro);
                continue;
            }
            if ("backwards".equals(object.getString("type").toLowerCase())) {
                moveBackwards(macro);
                stopMacro(macro);
                continue;
            }
            if ("left".equals(object.getString("type").toLowerCase())) {
                moveLeft(macro);
                stopMacro(macro);
                continue;
            }
            if ("right".equals(object.getString("type").toLowerCase())) {
                moveRight(macro);
                stopMacro(macro);
                continue;
            }
            if ("blink".equals(object.getString("type").toLowerCase())) {
                Random rand;
                for (int j = 0; j < 15; j++) {
                    macro.addCommand(new RGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256), 255));
                    stopMacro(macro);
                    macro.addCommand(new Delay(100));
                }
            }
            if ("color".equals(object.getString("type").toLowerCase())) {
                setColor(macro, object.getInt("r"), object.getInt("g"), object.getInt("b"));
                macro.addCommand(new Delay(2000));
            }
        }
    }

    private static void stopMacro(MacroObject macro) {
        macro.addCommand(new Roll(0.0f, orientation, 255));
    }

    private static void setColor(MacroObject macro, int r, int g, int b) {
        macro.addCommand(new RGB(r, g, b, 255));
    }

    private static void moveForward(MacroObject macro) {
        macro.addCommand(new Roll(SPEED, 0, 0));
        macro.addCommand(new Delay(2000));
        orientation = 0;
    }

    private static void moveBackwards(MacroObject macro) {
        macro.addCommand(new Roll(SPEED, 180, 0));
        macro.addCommand(new Delay(2000));
        orientation = 180;
    }

    private static void moveLeft(MacroObject macro) {
        macro.addCommand(new Roll(SPEED, 270, 0));
        macro.addCommand(new Delay(2000));
        orientation = 270;
    }

    private static void moveRight(MacroObject macro) {
        macro.addCommand(new Roll(SPEED, 90, 0));
        macro.addCommand(new Delay(2000));
        orientation = 90;
    }
}