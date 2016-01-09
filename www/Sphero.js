var Sphero = {
    connect: function (successCallback, errorCallback, args) {
        args = args || [];
        cordova.exec(
            successCallback || function () {
            }, // success callback function
            errorCallback || function () {
            }, // error callback function
            'SpheroPlugin', // mapped to our native Java class called
            'connect', // with this action name , in this case 'beep'
            args);// arguments, if needed
    },
    disconnect: function (successCallback, errorCallback, args) {
        args = args || [];
        cordova.exec(
            successCallback || function () {
            },
            errorCallback || function () {
            },
            'SpheroPlugin',
            'disconnect',
            args
        )
    },
    changeColor: function (successCallback, errorCallback, args) {
        args = args || [];
        cordova.exec(
            successCallback || function () {
            },
            errorCallback || function () {
            },
            'SpheroPlugin',
            'changeColor',
            args
        )
    }
};

module.exports = Sphero;