/**
*	@author  pzq
*	@company Sunline
*
*	使用说明：
*	1.正式使用时此文件会被压缩。
*	2.在需要监控的页面加入以下代码即可。
*	
*	<script src="sunline_sdk.js"></script>
*	<script type="text/javascript">
*		SunSDK.setUser('userId').run(); //注意：需要主动注入用户ID
*	</script>
*/
var SunSDK = (function() {

	function Info() {
		this.location = '';
		this.paths = '';
		this.user = 'anonymous';
		this.timestamp = '';
	}

	Info.prototype.init = function(element) {
		this.paths = getPath(element);
		var p = document.location.pathname;
		if(document.location.search){
			p += document.location.search;
		}
		if(document.location.hash){
			p += document.location.hash;
		}
		this.location = p;
		this.timestamp = new Date().format("yyyy-MM-dd hh:mm:ss");
	};

	var request;
	if (window.XMLHttpRequest) {
		request = new XMLHttpRequest();
	} else {
		request = new ActiveXObject("Microsoft.XMLHTTP");
	}

	request.onload = function() {

	};

	request.onerror = function() {
		console.log('There was a connection error of some sort');
	};

	/**
	 *	获取元素路径
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
	 *	获取元素在兄弟节点中的位置
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

	Date.prototype.format = function(fmt) {
		var o = {
			"M+": this.getMonth() + 1,
			"d+": this.getDate(),
			"h+": this.getHours(),
			"m+": this.getMinutes(),
			"s+": this.getSeconds(),
			"q+": Math.floor((this.getMonth() + 3) / 3),
			"S": this.getMilliseconds()
		};
		if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for (var k in o)
			if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	};

	var hosts = "http://gzunline.f3322.net:8088";
	var serv = "/click?info=";
	var info = new Info();
	var enable = true;

	return {
		setHost: function(host) {
			hosts = host;
			return this;
		},
		setUser: function(user) {
			info.user = user;
			return this;
		},
        enable: function(flag) {
            enable = flag;
		},
		run: function() {
			document.addEventListener('click',
				function(e) {
                    if (enable) {
                        var element = e.target;
                        info.init(element);

                        // console.log('监测到点击： ' + JSON.stringify(info));
                        var url = hosts + serv + encodeURIComponent(JSON.stringify(info));
                        request.open("GET", url, true);
                        request.send();
                    }
				},
				true);
		}
	};
})();