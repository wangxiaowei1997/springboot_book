Vue.prototype.setCookie = function (name, value,day) {
    /*
    * 使用
    * this.setCookie("XXXX",res.token);
    */
    var Days = day || 365;    //这里设置天数
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";domain=127.0.0.1"+ ";expires=" + exp.toGMTString();
    // document.cookie = name + "="+ escape (value) + ";domain=(这里可以放入指定使用的域名);expires=" + exp.toGMTString();
};


// 获取cookie
Vue.prototype.getCookie = function (name) {
    /*
    * 使用
    * console.log(this.getCookie("XXX"))
    */
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) {
        return unescape(arr[2]);
    } else {
        return null;
    }
};


// 删除cookie
Vue.prototype.delCookie = function (name) {
    /*
    * 使用
    * this.delCookie("XXX")
    */
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = this.getCookie(name);
    if (cval != null) {
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
    }
};