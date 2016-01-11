var Sphero = {
    _errorFunction: function (error) {
        console.error(error);
    },
    _successFunction: function (response) {
        console.debug(response)
    },
    connect: function (successCallback, errorCallback, args) {
        args = args || [];
        cordova.exec(
            successCallback || this._successFunction,
            errorCallback || this._errorFunction,
            'SpheroPlugin',
            'connect',
            args);
    },
    disconnect: function (successCallback, errorCallback, args) {
        args = args || [];
        cordova.exec(
            successCallback || this._successFunction,
            errorCallback || this._errorFunction,
            'SpheroPlugin',
            'disconnect',
            args
        )
    },
    changeColor: function (successCallback, errorCallback, args) {
        args = args || [];
        cordova.exec(
            successCallback || this._successFunction,
            errorCallback || this._errorFunction,
            'SpheroPlugin',
            'changeColor',
            args
        )
    }
};

module.exports = Sphero;