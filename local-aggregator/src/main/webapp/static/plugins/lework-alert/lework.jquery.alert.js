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

var lework = (function ($, lework) {
    var $container;
    var noop = function () {
    };
    var DEFAULTS = {
        onHide: noop,
        content: '', /**html or text **/
        timer: 6000, /** 6秒后关闭 **/
        id: null,
        width: '200px',
        type: '', /**info,success,danger,error**/
        parentEl: 'body' /**父容器,默认为body**/
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

    function Alert(opt) {
        this.settings = $.extend({}, DEFAULTS, opt);
        this.init();
        this.create();
        this.autoColse();
    };
    Alert.prototype.init = function () {
        if (!$container) {
            //创建容器
            $(this.settings.parentEl)
                .append('<div class="lework-PopupPanel" id="lework-PopupPanel" ></div>');
            $container = $('#lework-PopupPanel')
        }
    };
    Alert.prototype.create = function () {
        var that = this;
        //create alert body
        this.$alert = $('<div />')
            .css('width', this.settings.width)
            .addClass('lework-alert' + (this.settings.type ? ' lework-alert-' + this.settings.type : ''))
            .prop({'id': (this.settings.id ? this.settings.id : (new Date()).getTime()) })
            .on('onHide.alert',function () {
                that.hide();
                that.settings.onHide.apply(this);
                that.destroy();
            }).html(that.settings.content || '&nbsp;&nbsp;')
        this.$alert.append(' <button type="button" class="close" >×</button> ')
        //关闭按钮
        this.$alert.on('click', '.close', function (e) {
            e.preventDefault();
            $(this).parent().trigger('onHide.alert');
        })
        that.$alert.appendTo($container);
    };
    Alert.prototype.autoColse = function () {
        // debugger;
        //自动关闭
        var that = this;
        if (that.settings.timer) {
            setTimeout(function () {
                that.$alert.trigger('onHide.alert');
            }, that.settings.timer)
        }
    }
    Alert.prototype.hide = function () {
        this.$alert.hide();
    };
    Alert.prototype.destroy = function () {
        this.$alert.off('.alert').remove();
        //   this.$alert = null;
    };
    Alert.prototype.update = function (content) {
        this.$alert.html(this.settings.content);
    }


    lework.alert = function (options) {

        return new Alert(options);
    }
    return lework;
})(jQuery, lework || {})
