angular.module('app',['ngRoute','app.services'])
    .controller('processCtrl',['$rootScope','$scope','$route','$location','egfhttp',function ($rootScope,$scope,$route,$location,egfhttp) {
        $scope.$route = $route;
        $scope.isShow = false;
        var headers={ 'Content-Type': 'application/json;charset=utf-8' };
        //初始化流程列表
        var initList=function () {
            egfhttp.sendHttp("/web/workflow/process-list.do", "POST", {}, headers, function (dat) {
                if (dat) {
                    console.log(dat)
                    $scope.processList = dat.result;
                } else {
                    alert("保存失败");
                }
            });
        }
        //页面初始化
        var init=function () {
            initList();//初始化列表
        }
        init();

        //部署控件显示切换
        $scope.showDeploy = function () {
            $scope.isShow= !$scope.isShow;

        };

        //部署流程
        $scope.deploySave = function () {

            var fd = new FormData();
            var file = document.querySelector('input[type=file]').files[0];
            fd.append('file', file);

            var headerfile={ 'Content-Type':undefined};
            egfhttp.sendHttp("/web/workflow/deploy.do","POST",fd,headerfile,function(dat){

                if(dat){
                    alert("部署成功");
                }else{
                    alert("部署失败");
                }

                initList();//初始化列表

            });
        };

        //删除流程
        $scope.delete = function (deploymentId) {
            console.log(deploymentId)
            egfhttp.sendHttp("/web/workflow/delete.do?deploymentId="+deploymentId,"GET",{},headers,function(dat){

                initList();//初始化列表
            });
        };

    }])
    .controller('leaveCtrl', function ($scope, $route) {
        $scope.$route = $route;
        $('.datetimepicker').datetimepicker(
        );
    })
    .controller('loginCtrl',['$rootScope','$scope','$route','$location','egfhttp', function ($rootScope,$scope,$route,$location,egfhttp) {
        $scope.$route = $route;
        var headers={ 'Content-Type': 'application/json;charset=utf-8' };
        $scope.login = function () {
            var params = {"userName": $scope.userName,"password":$scope.password};
            egfhttp.sendHttp("/web/login/loginIn.do", "POST", params, headers, function (dat) {
                if(dat.success){
                    $rootScope.userNameCn=dat.data.userNameCn;
                    alert(dat.data.userNameCn+":登录成功");
                    $location.path('/');
                }else{
                    alert("登录失败");
                    $location.path('/views/login');
                }

            });

        }
    }])
    .config(['$routeProvider', function($routeProvider){
        $routeProvider
            .when('/',{template:'首页'})
            .when('/views/workflow/process-list',{templateUrl:'views/workflow/process-list.html',controller:'processCtrl'})
            .when('/views/leave/leaveApply',{templateUrl:'views/leave/leaveApply.html',controller:'leaveCtrl'})
            .when('/views/login',{templateUrl:'views/login.html',controller:'loginCtrl'})
            .otherwise({redirectTo:'/'});
    }]);