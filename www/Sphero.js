var Sphero = {
    connect: function (successCallback, errorCallback, args) {
        args = args || [];
        cordova.exec(
            successCallback || function () {

            },
            errorCallback || function () {

            },
            'SpheroPlugin',
            'connect',
            args);
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
    },
    setRobotToDefaultState: function (successCallback, errorCallback, args) {
        args = args || [];
        cordova.exec(
            successCallback || function () {

            },
            errorCallback || function () {

            },
            'SpheroPlugin',
            'setRobotToDefaultState',
            args
        )
    },
    runMacro: function (successCallback, errorCallback, args) {
        args = args || [];
        cordova.exec(
            successCallback || function () {

            },
            errorCallback || function () {

            },
            'SpheroPlugin',
            'runMacro',
            args
        )
    }
};

module.exports = Sphero;