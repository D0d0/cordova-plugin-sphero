var MyCustomPlugin = {
  PrintLog: function (successCallback, errorCallback, action) {
    cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'CustomPlugin', // mapped to our native Java class called
            action, // with this action name , in this case 'beep'
            []  )// arguments, if needed
  }
}
module.exports = MyCustomPlugin;