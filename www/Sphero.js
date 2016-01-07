var PLUGIN_NAME = 'SpheroPlugin';

var Sphero = {
    connect: function (successCallback, errorCallback, args) {
        args |= [];
        var action = 'connect';
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            PLUGIN_NAME, // mapped to our native Java class called
            action, // with this action name , in this case 'beep'
            args);// arguments, if needed
    },
    disconnect: function (successCallback, errorCallback, args) {
        args |= [];
        var action = 'disconnect';
        cordova.exec(
            successCallback,
            errorCallback,
            PLUGIN_NAME,
            action,
            args
        )
    }
};

module.exports = Sphero;