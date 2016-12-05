# cordova-plugin-sphero

This plugin allows you to control your Sphero robot. It includes some basic commands - rotate left, right, up and down, move, change color and reset to default state.

# Installation

-  Add `https://github.com/D0d0/cordova-plugin-sphero.git` into `cordovaPlugins` in your `package.json` file.

-  Include `Sphero.js` in your app

# Documentation

`Sphero.connect(successCallback, errorCallback, args)`
connects to your Sphero

`Sphero.disconnect(successCallback, errorCallback, args)`
disconnects from your Sphero

`Sphero.changeColor(successCallback, errorCallback, args)`
Change Sphero color

`Sphero.setRobotToDefaultState(successCallback, errorCallback, args)`
Set Sphero to default state

`Sphero.runMacro(successCallback, errorCallback, args)`
Run macro. Commands are sent in `args` as an `array`. Note: longer commands are ignored by Sphero.
Allowed commands in `args`
-  Change color to RBG. RGB values are [0-255] `{type: "color", r: r, g: g, b: b}`
-  Rotate and move robot `{type: "forward||backwards||left||right"}`
-  Blink with random colors `{type: "blink"}`
