var Sphero = {
    connect: function (successCallback, errorCallback, args) {
        args = args || [];
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'SpheroPlugin', // mapped to our native Java class called
            'connect', // with this action name , in this case 'beep'
            args);// arguments, if needed
    },
    disconnect: function (successCallback, errorCallback, args) {
        args = args || [];
        cordova.exec(
            successCallback,
            errorCallback,
            'SpheroPlugin',
            'disconnect',
            args
        )
    }
};

module.exports = Sphero;