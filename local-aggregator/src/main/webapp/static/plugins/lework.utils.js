/**
 * Global Utils Function
 **/


    window.lework = (function (lework) {
        lework.hasConsole =  window.console && window.console.log ;
        /**
         * 简单封装浏览器console.log方法
         * @param info Object 日志信息
         */
        lework.log = function (info) {
            lework.hasConsole  && window.console.log(info);
        };
        return lework;
    })(window.lework || {}); // closure
