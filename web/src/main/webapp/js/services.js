angular.module('app.services',[])

    // .constant('ServerUrl', 'http://127.0.0.1:8080/RMIMobileServlet')

    .filter('paging', function () {
        return function (items, index, pageSize) {
            if (!items)
                return [];
            var offset = (index - 1) * pageSize;
            return items.slice(offset, offset + pageSize);
        }
    })

    .filter('size', function () {
        return function (items) {
            if (!items)
                return 0;
            return items.length || 0;
        }
    })

    .factory('TokenInterceptor', ['$q', '$window', function ($q, $window) {
        return {
            request: function (config) {
                // config.headers = config.headers || {};
                // if (window.sessionStorage.token && config.url.search('RMIMobileServlet') !== -1) {
                //     config.headers.Authorization = window.sessionStorage.token;
                // }
                // return config;
            },
            response: function (response) {
                // if(response.data.retCode==-4){
                //     window.sessionStorage.token="";
                //     window.location = "./index.html#/sessionExpired";
                // }
                // var newtoken = response.config.headers.Authorization;
                // if(newtoken && response.config.url.search('RMIMobileServlet') !== -1){
                //     var oldtoken = window.sessionStorage.token;
                //     if(newtoken !== oldtoken){
                //         window.sessionStorage.token = newtoken;
                //     }
                // }
                // return response || $q.when(response);
            }
        };
    }])

    .factory('RemoteService', ['$http',  function ($http) {
        return {
            get: function (url,queryParams) {
                return $http.get(url, {params: queryParams,timeout:60000});
            },
            post: function (queryParams) {
                return $http({
                    method: 'POST',
                    url: url,
                    data: {params: queryParams},
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                })
            }
        };
    }])

    .service('egfhttp', function($rootScope,$http) {
    this.sendHttp = function(reqUrl,method,param,headers,callback){

        //加载数据等待效果
        var loadingScreen = $('<div style="position:fixed;top:0;left:0;right:0;bottom:0;z-index:10000;background-color:gray;background-color:rgba(70,70,70,0.2);"><img style="position:absolute;top:50%;left:50%;" width="54px" height="54px" alt="请稍等" src="/img/loading.gif" /></div>')
            .appendTo($('body')).hide();
        loadingScreen.show();//显示
        
        $http({
            url : reqUrl,
            method : method,
            data : param,
            headers : headers
        }).success(function(data){
            loadingScreen.hide();//隐藏
            loadingScreen.remove();//删除加载数据等待效果元素
            if(105==data.code){
                alert(data.title+data.message);
                $rootScope.$state.go('login');
            }
            callback(data);
        }).error(function(e){
            loadingScreen.hide();//隐藏
            loadingScreen.remove();//删除加载数据等待效果元素
            alert("出错："+e);
        });
    }
});
