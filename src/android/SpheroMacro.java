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

public class SpheroMacro {

    private ConvenienceRobot mRobot;

    public SpheroMacro(ConvenienceRobot mRobot) {
        this.mRobot = mRobot;
    }

    public void setRobotToDefaultState() {
        if (mRobot == null) {
            return;
        }

        mRobot.sendCommand(new AbortMacroCommand());
        mRobot.setLed(1.0f, 1.0f, 1.0f);
        mRobot.enableStabilization(true);
        mRobot.setBackLedBrightness(0.0f);
        mRobot.stop();
    }

    public void runMacro(JSONArray args) throws JSONException {
        if (mRobot == null) {
            return;
        }

        setRobotToDefaultState();

        MacroObject macro = new MacroObject();
/*
        //Stabilization must be turned off before you can issue motor commands
        //macro.addCommand(new Stabilization(false, 0));

        macro.addCommand(new LoopStart(5));

        macro.addCommand(new RGB(255, 0, 0, 0));
        macro.addCommand(new Roll(0.5f, 0, 400));
        macro.addCommand(new Delay(400));

        macro.addCommand(new RGB(0, 255, 0, 0));
        macro.addCommand(new Roll(0.5f, 90, 400));
        macro.addCommand(new Delay(400));


        macro.addCommand(new RGB(0, 0, 255, 0));
        macro.addCommand(new Roll(0.5f, 180, 400));
        macro.addCommand(new Delay(400));

        macro.addCommand(new RGB(0, 0, 255, 0));
        macro.addCommand(new Roll(0.5f, 270, 400));
        macro.addCommand(new Delay(400));

*/
        /*//Change the LED to red
        macro.addCommand(new RGB(255, 0, 0, 0));
        //Run the robot's motors backwards
        macro.addCommand(new RawMotor(RawMotor.DriveMode.REVERSE, 255, RawMotor.DriveMode.REVERSE, 255, 100));
        macro.addCommand(new Delay(100));
        //Change the LED to green
        macro.addCommand(new RGB(0, 255, 0, 0));
        //Run the robot's motors forward
        macro.addCommand(new RawMotor(RawMotor.DriveMode.FORWARD, 255, RawMotor.DriveMode.FORWARD, 255, 100));
        macro.addCommand(new Delay(100));*/
        //macro.addCommand(new LoopEnd());

        //Turn stabilization back on
        //macro.addCommand(new Stabilization(true, 0));

        //Send the macro to the robot and play
        MacroBuilder.buildMacro(macro, args)
        macro.setMode(MacroObject.MacroObjectMode.Normal);
        macro.setRobot(mRobot.getRobot());
        macro.playMacro();
    }
}