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
            successCallback(response) || this._successFunction(response),
            errorCallback(error) || this._errorFunction(error),
            'SpheroPlugin',
            'connect',
            args);
    },
    disconnect: function (successCallback, errorCallback, args) {
        args = args || [];
        cordova.exec(
            successCallback(response) || this._successFunction(response),
            errorCallback(error) || this._errorFunction(error),
            'SpheroPlugin',
            'disconnect',
            args
        )
    },
    changeColor: function (successCallback, errorCallback, args) {
        args = args || [];
        cordova.exec(
            successCallback(response) || this._successFunction(response),
            errorCallback(error) || this._errorFunction(error),
            'SpheroPlugin',
            'changeColor',
            args
        )
    }
};

module.exports = Sphero;