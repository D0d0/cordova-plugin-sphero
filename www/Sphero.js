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
    }
};

module.exports = Sphero;