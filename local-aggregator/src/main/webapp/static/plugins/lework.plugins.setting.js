/**
 * jquery 插件全局设置
 **/

$(function () {
    window.lework = (function (lework) {
        /**=================================
         * jquery DataTables Plugins 全局设置
         ===================================**/
        if ($.fn.dataTable) {

            //适配Spring data jpa page 参数
            lework.springDataJpaPageableAdapter = function (sSource, aoData, fnCallback, oSettings) {


                //extract name/value pairs into a simpler map for use later
                var paramMap = {};
                for (var i = 0; i < aoData.length; i++) {
                    paramMap[aoData[i].name] = aoData[i].value;
                }


                //page calculations
                var pageSize = paramMap.iDisplayLength;
                var start = paramMap.iDisplayStart;
                var pageNum = (start == 0) ? 1 : (start / pageSize) + 1; // pageNum is 1 based

                // extract sort information
                var sortCol, sortDir, sortName;
                for (var i = 0; i < parseInt(paramMap['iColumns']); i++) {
                    if (paramMap['bSortable_' + i] == true) {
                        sortName = paramMap['mDataProp_' + i];
                        sortDir = paramMap['sSortDir_' + i];
                        sortCol = paramMap['bSortable_' + i];
                        break;
                    }
                }

                //create new json structure for parameters for REST request
                // var restParams = [] ;
                var restParams = aoData;
                restParams.push({'name': 'page.size', 'value': pageSize});
                restParams.push({'name': 'page.page', 'value': pageNum });
                restParams.push({'name': 'page.sort', 'value': sortName });
                restParams.push({'name': 'page.sort.dir', 'value': sortDir ? sortDir : 'asc' });

                //finally, make the request
                oSettings.jqXHR = $.ajax({
                    'dataType': 'json',
                    'type': 'post',
                    'url': sSource,
                    'data': restParams,
                    'success': function (data) {
                        data.sEcho = paramMap.sEcho;
                        fnCallback(data);
                    }
                });
            };
            var _opt = {
                sPaginationType: "full_numbers",
                oLanguage: {
                    sSearch: "<span>搜索:</span> ",
                    sInfo: "当前显示 <span>_START_</span> 到 <span>_END_</span> 条,共 <span>_TOTAL_</span> 条记录",
                    sInfoEmpty: "当前显示 <span>0</span> 到 <span>0</span> 条,共 <span>0</span> 条记录",
                    sLengthMenu: "每页显示 _MENU_ 条记录",
                    sProcessing: "加载中...",
                    sEmptyTable: "无可用数据",
                    sZeroRecords: "无记录数据",
                    oPaginate: {  "sFirst": "首页", "sPrevious": " 上一页 ", "sNext": " 下一页 ", "sLast": " 尾页 " }
                }
            };
            $.extend(true, $.fn.dataTable.defaults, _opt);

        }

        /**==================================
         * jquery pnotify 全局方法封装
         * ==================================**/
        if ($.pnotify) {
            $.pnotify.defaults.history = false;
            var stack_bottomright = {"dir1": "up", "dir2": "left", "firstpos1": 25, "firstpos2": 25};

            lework.notify = function (title, message, type) {
                var opts = {
                    title: title || '',
                    text: message || '',
                    shadow: false,
                    addclass: "stack-bottomright",
                    labels: {redisplay: "重新显示", all: "所有", last: "最后", close: "关闭", stick: "播放/停止"},
                    animate_speed: 'fast',
                    stack: stack_bottomright
                };
                switch (type) {
                    case 'error':
                        opts.type = "error";
                        break;
                    case 'info':
                        opts.type = "info";
                        break;
                    case 'success':
                        opts.type = "success";
                        break;
                    //default  type: "notice"
                }
                $.pnotify(opts);
            };
        }
        /**====================
         * jquery easyui
         * ====================**/
        if (easyloader) {
            easyloader.locale = "zh_CN"; // 本地化设置
            easyloader.theme = "bootstrap"; // 设置主题
            easyloader.css = false  ;

        }
        return lework;
    })(window.lework || {}); // closure

}) //dom ready