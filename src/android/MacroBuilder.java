package com.plugin.sphero;

import com.orbotix.ConvenienceRobot;
import com.orbotix.macro.AbortMacroCommand;
import com.orbotix.macro.MacroObject;
import com.orbotix.macro.cmd.Delay;
import com.orbotix.macro.cmd.LoopEnd;
import com.orbotix.macro.cmd.LoopStart;
import com.orbotix.macro.cmd.RGB;
import com.orbotix.macro.cmd.RawMotor;
import com.orbotix.macro.cmd.Roll;
import com.orbotix.macro.cmd.Stabilization;

public class MacroBuilder {

    private final static float SPEED = 0.5f;
    private static int orientation = 0;

    public static void buildMacro(MacroObject macro, JSONArray args) throws JSONException {
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
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
            if ("color".equals(object.getString("type").toLowerCase())) {
                moveRight(macro, object.getInt("r"), object.getInt("g"), object.getInt("b"));
            }
        }
    }

    private static stopMacro(MacroObject macro) {
        macro.addCommand(new Roll(0.0f, orientation, 255));
    }

    private static setColor(MacroBuilder macro, int r, int g, int b) {
        macro.addCommand(new RGB(r, g, b, 255));
    }

    private static moveForward(MacroObject macro) {
        macro.addCommand(new Roll(SPEED, 0, 0));
        macro.addCommand(new Delay(700));
        orientation = 0;
    }

    private static moveBackwards(MacroObject macro) {
        macro.addCommand(new Roll(SPEED, 180, 0));
        macro.addCommand(new Delay(700));
        orientation = 180;
    }

    private static moveLeft(MacroObject macro) {
        macro.addCommand(new Roll(SPEED, 270, 0));
        macro.addCommand(new Delay(700));
        orientation = 270;
    }

    private static moveRight(MacroObject macro) {
        macro.addCommand(new Roll(SPEED, 90, 0));
        macro.addCommand(new Delay(700));
        orientation = 90;
    }
}