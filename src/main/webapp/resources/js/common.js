/**
 * @desc 全局空间
 * @author sunzhen
 * @Date 2018-11-23 11:00:00
 */
var Globals = {
    contextPath: function () {
        var strPath = window.document.location.pathname;
        var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
        return postPath;
    }
}

Globals.yearMonthDay = "yyyyMMdd";
