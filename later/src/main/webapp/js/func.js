$(function () {
    // 自定义规则
    $.extend($.fn.validatebox.defaults.rules, {
        isLength: {
            // 验证规则函数,返回true-通过
            // 参数1：代表 当前输入的内容
            // 参数2：代表 参数数组
            validator: function (value, params) {
                return value.length == params[0];
            },
            message: "长度必须是  {0} 位！" //{下标} 代表获取 参数中指定下标的值
        },
        isNum: {
            validator: function (val, args) {
                //console.log(isNaN(val)); // isNaN-判断n是否是数字，true-不是数字，false-是数字
                return !isNaN(val);
            },
            message: "必须是数字！"
        },
        mustEquals: {
            validator: function (val, params) {
                return val == $(params[0]).val();
            },
            message: "两次输入必须 相同！"
        }
    });

    // 日期格式转换
    $.fn.datebox.defaults.formatter = function (date) {
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        var d = date.getDate();
        return y + "/" + m + "/" + d;
    }
    $.fn.datebox.defaults.parser = function (s) {
        var t = Date.parse(s);
        if (!isNaN(t)) {
            return new Date(t);
        } else {
            return new Date();
        }
    }

});