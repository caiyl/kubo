/**
 *    @author  pzq
 *    @company Sunline
 *    采集配置
 */
(function () {

    function Info() {
        this.location = '';
        this.paths = '';
        this.point = '';
        this.memo = '';
    }

    Info.prototype.init = function (element) {
        this.paths = getPath(element);
        var p = document.location.pathname;
        if (document.location.search) {
            p += document.location.search;
        }
        if (document.location.hash) {
            p += document.location.hash;
        }
        this.location = p;
    };

    var request;
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    } else {
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }

    request.onload = function () {
        closeConfigWindow();
    };

    request.onerror = function () {
        document.getElementById("tips").innerText = "网络错误！";
    };

    /**
     *    获取元素路径
     */
    function getPath(element) {
        var str = '';
        var paths = [];
        while (element) {
            var tagName = element.tagName;
            var id = element.id;
            var className = element.className;
            var ps = tagName;
            if (id) {
                ps += '#' + id;
            }
            if (className) {
                ps += '.' + className;
            }
            var index = getIndexAtSibling(element);
            if (index != 0) {
                ps += ':nth-child(' + index + ')';
            }
            paths.push(ps);
            element = element.parentElement;
        }
        for (var i = paths.length - 1; i >= 0; i--) {
            str += paths[i];
            if (i != 0) {
                str += '>';
            }
        }
        return str;
    }

    /**
     *    获取元素在兄弟节点中的位置
     */
    function getIndexAtSibling(element) {
        var index = 1;
        var have = false;
        var next = element;

        var tagName = element.tagName;
        while (element.previousElementSibling) {
            if (tagName == element.previousElementSibling.tagName) {
                index += 1;
                have = true;
            }
            element = element.previousElementSibling;
        }

        while (next.nextElementSibling) {
            if (tagName == next.nextElementSibling.tagName) {
                have = true;
                break;
            }
            next = next.nextElementSibling;
        }
        if (!have) {
            index = 0;
        }
        return index;
    }

    /*

     */
    function showToolbar() {
        document.body.innerHTML = document.body.innerHTML
            + '<div id="toolbar" draggable="true" style="border:1px solid green;padding:6px;position:fixed;left:0;top:0;width:120px;height:20px;background:#fff;z-index: 9999999;">' +
            '打开采集功能<input id="ck" type="checkbox">' +
            '</div>';
        moveable();
    }

    function moveable() {
        var ck = document.getElementById("ck");
        if(typeof SunSDK !== 'undefined'){
            ck.addEventListener("click",function (event) {
                if (ck.checked) {
                    SunSDK.enable(false);
                }else{
                    SunSDK.enable(true);
                }
            });
        }else{
            console.log('no SunSDK....');
        }


        var bar = document.getElementById("toolbar");
        var x = 0;var y = 0;var dragged;

        bar.addEventListener("dragstart", function( event ) {
            dragged = event.target;
            x = event.clientX;
            y = event.clientY;
        }, false);
        bar.addEventListener("dragend", function( event ) {
            var dx = event.clientX - x;
            var dy = event.clientY - y;
            var l = parseInt(dragged.style.left.replace("px","")) + dx;
            var t = parseInt(dragged.style.top.replace("px","")) + dy;
            event.target.style.left = l + "px";
            event.target.style.top = t + "px";
        }, false);
    }

    var showing = false;

    /**
     *    弹出配置窗口
     */
    function showConfigWindow(info) {
        showing = true;
        var bg = document.getElementById("bg");
        var win = document.getElementById("win");
        if(bg && win){
            bg.style.display="block";
            win.style.display="block";
        }else {
            document.body.innerHTML = document.body.innerHTML
                + '<div id="bg" style="background:#000;position:fixed;left:0;top:0;opacity:0.2;width:100%;height:100%;z-index:9999990;"></div>'
                +'<div id="win" style="width:400px;height:160px;position:fixed;left:50%;top:50%;margin:-150px -200px 0;border:1px #FF0 solid;background:#FFF;z-index:9999998;">' +
                    '<div style="text-align: right"><a href="javascript:;" id="ccw">关闭</a></div>' +
                    '<div style="margin: 8px;padding: 4px">' +
                        '<div id="tips" style="padding: 3px;color:red;text-align: center"></div>'+
                        '<div style="padding: 3px"><label style="width:80px">采集点：</label><span><input id="cpoint" type="text" style="width: 200px;"><i style="color: red">*</i></span></div>'+
                        '<div style="padding: 3px"><label style="width:80px">备&nbsp;注：</label><span><input id="cmemo" type="text" style="width: 200px;"></span></div>'+
                    '</div>' +
                    '<div style="text-align: center"><button id="btt">提交</button></div>'+
                '</div>';
            document.getElementById("ck").checked = true;
            document.getElementById("ccw").addEventListener('click',function (e) {
                closeConfigWindow();
            });
            document.getElementById("btt").addEventListener('click',function (e) {
                submit();
            });
            moveable();
        }
    }


    function closeConfigWindow() {
        document.getElementById("cpoint").value = '';
        document.getElementById("cmemo").value = '';
        showing = false;
        var bg = document.getElementById("bg");
        var win = document.getElementById("win");
        bg.style.display="none";
        win.style.display="none";
    }

    var hosts = "http://gzunline.f3322.net:8088";
    // var hosts = "http://192.168.1.22:8080";
    var serv = "/click?config=";
    var info = new Info();

    showToolbar();

    function submit() {
        var cp = document.getElementById("cpoint").value;
        var cm = document.getElementById("cmemo").value;
        if(cp){
            document.getElementById("tips").innerText = "";
            info.point = cp;
            info.memo = cm;
            console.log('send： ' + JSON.stringify(info));
            var url = hosts + serv + encodeURIComponent(JSON.stringify(info));
            request.open("GET", url, true);
            request.send();
        } else {
            document.getElementById("tips").innerText = "采集点名称不能为空！";
        }
    }

    document.addEventListener('click',
        function (e) {
            var element = e.target;

            if (document.getElementById("ck").checked && !showing && element.id != "ck" && element.id != "toolbar") {
                e.preventDefault();
                e.stopImmediatePropagation();
                info.init(element);
                showConfigWindow(info);
            }
        },
        true);

})();