var timeUtil = {
        /**
         * 日期格式化
         */
        formatDate: function (val, cFormat) {
            if (val == null) {
                return null;
            }
            let format = cFormat || 'YYYY-MM-DD';
            var o = {
                "M+": val.getMonth() + 1,   //month
                "d+": val.getDate(),      //day
                "h+": val.getHours(),     //hour
                "m+": val.getMinutes(),   //minute
                "s+": val.getSeconds(),   //second
                "q+": Math.floor((val.getMonth() + 3) / 3), //quarter
                "S": val.getMilliseconds() //millisecond
            };
            if (/(y+)/.test(format)) {
                format = format.replace(RegExp.$1, (val.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for (var k in o) {
                if (new RegExp("(" + k + ")").test(format)) {
                    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
                }
            }
            return format;
        },
        parseTime: function (time, cFormat) {
            if (arguments.length === 0) {
                return null
            }
            const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
            let date
            if (typeof time === 'object') {
                date = time
            } else {
                if (('' + time).length === 10) time = parseInt(time) * 1000
                date = new Date(time)
            }
            const formatObj = {
                y: date.getFullYear(),
                m: date.getMonth() + 1,
                d: date.getDate(),
                h: date.getHours(),
                i: date.getMinutes(),
                s: date.getSeconds(),
                a: date.getDay()
            }
            const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
                let value = formatObj[key]
                if (key === 'a') return ['一', '二', '三', '四', '五', '六', '日'][value - 1]
                if (result.length > 0 && value < 10) {
                    value = '0' + value
                }
                return value || 0
            })
            return time_str
        },
        formatTime: function (time, option) {
            time = +time * 1000
            const d = new Date(time)
            const now = Date.now()

            const diff = (now - d) / 1000

            if (diff < 30) {
                return '刚刚'
            } else if (diff < 3600) { // less 1 hour
                return Math.ceil(diff / 60) + '分钟前'
            } else if (diff < 3600 * 24) {
                return Math.ceil(diff / 3600) + '小时前'
            } else if (diff < 3600 * 24 * 2) {
                return '1天前'
            }
            if (option) {
                return parseTime(time, option)
            } else {
                return d.getMonth() + 1 + '月' + d.getDate() + '日' + d.getHours() + '时' + d.getMinutes() + '分'
            }
        }
    }


    // Cookie 工具
    ! function (e) {
        var n = !1;
        if ("function" == typeof define && define.amd && (define(e), n = !0), "object" == typeof exports && (module.exports = e(), n = !0), !n) {
            var o = window.Cookies,
                t = window.Cookies = e();
            t.noConflict = function () {
                return window.Cookies = o, t
            }
        }
    }(function () {
        function e() {
            for (var e = 0, n = {}; e < arguments.length; e++) {
                var o = arguments[e];
                for (var t in o) n[t] = o[t]
            }
            return n
        }

        function n(o) {
            function t(n, r, i) {
                var c;
                if ("undefined" != typeof document) {
                    if (arguments.length > 1) {
                        if ("number" == typeof (i = e({
                                path: "/"
                            }, t.defaults, i)).expires) {
                            var a = new Date;
                            a.setMilliseconds(a.getMilliseconds() + 864e5 * i.expires), i.expires = a
                        }
                        i.expires = i.expires ? i.expires.toUTCString() : "";
                        try {
                            c = JSON.stringify(r), /^[\{\[]/.test(c) && (r = c)
                        } catch (e) {}
                        r = o.write ? o.write(r, n) : encodeURIComponent(String(r)).replace(/%(23|24|26|2B|3A|3C|3E|3D|2F|3F|40|5B|5D|5E|60|7B|7D|7C)/g, decodeURIComponent), n = (n = (n = encodeURIComponent(String(n))).replace(/%(23|24|26|2B|5E|60|7C)/g, decodeURIComponent)).replace(/[\(\)]/g, escape);
                        var s = "";
                        for (var f in i) i[f] && (s += "; " + f, !0 !== i[f] && (s += "=" + i[f]));
                        return document.cookie = n + "=" + r + s
                    }
                    n || (c = {});
                    for (var p = document.cookie ? document.cookie.split("; ") : [], d = /(%[0-9A-Z]{2})+/g,
                            u = 0; u < p.length; u++) {
                        var l = p[u].split("="),
                            C = l.slice(1).join("=");
                        this.json || '"' !== C.charAt(0) || (C = C.slice(1, -1));
                        try {
                            var g = l[0].replace(d, decodeURIComponent);
                            if (C = o.read ? o.read(C, g) : o(C, g) || C.replace(d, decodeURIComponent), this.json) try {
                                C = JSON.parse(C)
                            } catch (e) {}
                            if (n === g) {
                                c = C;
                                break
                            }
                            n || (c[g] = C)
                        } catch (e) {}
                    }
                    return c
                }
            }

            return t.set = t, t.get = function (e) {
                return t.call(t, e)
            }, t.getJSON = function () {
                return t.apply({
                    json: !0
                }, [].slice.call(arguments))
            }, t.defaults = {}, t.remove = function (n, o) {
                t(n, "", e(o, {
                    expires: -1
                }))
            }, t.withConverter = n, t
        }

        return n(function () {})
    });

const myVueObj = new Vue();

// axios 拦截器
var instance = axios.create({
    baseURL: "", // api的base_url
    // baseURL: "http://localhost:8080", // api的base_url
    timeout: 60000 // 请求超时时间
})
// Cookies.set("Admin-Token", "123456", 30);
// request拦截器
instance.interceptors.request.use(
    config => {
        config.headers['Token'] = Cookies.get("token"); // 让每个请求携带自定义token 请根据实际情况自行修改
        return config
    },
    error => {
        console.log(error) // for debug
        Promise.reject(error)
    })

// respone拦截器
instance.interceptors.response.use(
    response => {
        const res = response.data
        if (res.status !== 0) {

            myVueObj.$message({
                message: res.msg,
                type: 'error',
                duration: 5 * 1000
            })
            return Promise.reject('error')
        } else {
            return response.data
        }
    },
    error => {
        console.log('err' + error) // for debug

        if (error.response.status == 404) {
            window.location.reload()
        } else {
            myVueObj.$message({
                message: '系统异常，请联系管理员！',
                type: 'error',
                duration: 5 * 1000
            })
        }

        return Promise.reject(error)
    }
)


var currentVule = {
    currentSceneId: ''
}


var urlPaser = function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

Date.prototype.format = function(format){
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(), //day
        "h+" : this.getHours(), //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3), //quarter
        "S" : this.getMilliseconds() //millisecond
    }

    if(/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }

    for(var k in o) {
        if(new RegExp("("+ k +")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        }
    }
    return format;
}

var locationHostname = location.hostname;
var mainHost = locationHostname.match(/(\w+.){2}$/)[0];
document.domain = mainHost;


