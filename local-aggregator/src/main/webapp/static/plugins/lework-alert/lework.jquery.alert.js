/**
 * lework alert 组件
 * google alert style @see http://www.bootcss.com/p/google-bootstrap/components.html#alerts
 * User: Gongle
 * Date: 13-11-23
 * <p>
 *     api:
 *     method:
 * /p>
 */
var lework = (function (lework) {
    function Alert(){};
    lework.alert = function (options) {
      /*  "use strict";*/

        var $container, $alert;
        var noop = function () {
        };
        var defaults = {
            onHide: noop,
            content: '', /**html or text **/
            timer: 4500, /** intger **/
            id: null,
            width: '300px',
            type: '', /**info,success,danger,error**/
            parentEl: 'body' /**父容器,默认为body**/
        };
        // helper - merge two objects together, without using $.extend
        var merge = function (obj1, obj2) {
            var obj3 = {};
            for (var attrOne in obj1) {
                obj3[attrOne] = obj1[attrOne];
            }
            for (var attrTwo in obj2) {
                obj3[attrTwo] = obj2[attrTwo];
            }
            return obj3;
        };

        // helper - does it support CSS3 transitions/animation
        var doesTransitions = (function () {
            var b = document.body || document.documentElement;
            var s = b.style;
            var p = 'transition';
            if (typeof s[p] === 'string') {
                return true;
            }

            // Tests for vendor specific prop
            var v = ['Moz', 'Webkit', 'Khtml', 'O', 'ms'];
            p = p.charAt(0).toUpperCase() + p.substr(1);
            for (var i = 0; i < v.length; i++) {
                if (typeof s[v[i] + p] === 'string') {
                    return true;
                }
            }
            return false;
        }());

        // setup overlay settings
        var settings = merge(defaults, options);
        var init = function () {
            $container =  $('#lework-PopupPanel')
            if ($container.length){
                return false;
            }

            //创建容器

           $(settings.parentEl).append('<div class="lework-PopupPanel" id="lework-PopupPanel" ></div>')
           $container =  $('#lework-PopupPanel')
        };
        var create = function () {
            var callbackRet;
            //create alert body
            $alert = $('<div />')
                .css('width', settings.width)
                .addClass('lework-alert' + (settings.type ? ' lework-alert-' + settings.type : ''))
                .prop({'id': (settings.id ? settings.id : (new Date()).getTime()) })
                .on('onHide.alert',function () {
                    hide();
                    settings.onHide.apply(this);
                    destroy();
                }).html(settings.content)
            $alert.append(' <button type="button" class="close" >×</button> ')
            //关闭按钮
            $alert.on('click', '.close', function () {
                $alert.trigger('onHide.alert');
            })

            $alert.appendTo($container);
            //自动关闭
            if (settings.timer) {
                setTimeout(function () {
                    if($alert)
                         $alert.trigger('onHide.alert');
                }, settings.timer)
            }
        };
        var hide = function () {
            $alert.hide();
        };
        var destroy = function () {
            $alert.off('.alert').remove();
            $alert = null;
        };
        var update = function (content) {
            $alert.html(settings.content);
        }

        init();

        create();

        return {
            hide: hide,
            destroy: destroy,
            update: update
        };
    }
    return lework;
})(lework || {})
