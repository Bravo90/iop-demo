/**
 * @desc 全局空间
 * @author sunzhen
 * @Date 2018-11-23 11:00:00
 */
var Globals = {
    /*contextPath获取*/
    contextPath: function () {
        var strPath = window.document.location.pathname;
        var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
        return postPath;
    },
    validate: {
        validateNull: function () {
            var argLength = arguments.length;
            for (var i = 0; i < argLength; i++) {
                if (arguments[i] == undefined || arguments[i] == "") {
                    return false;
                }
            }
            return true;
        }
    }
}
Globals.yearMonthDay = "yyyyMMdd";

