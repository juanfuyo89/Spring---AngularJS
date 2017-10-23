var module=angular.module("app",[]);

module.controller("PruebaController", 
		["$scope","$http",function($scope, $http){

	$scope.customer = 0;
	var urlBase = 'http://localhost:13511/Products/api';
	var date = new Date();
	var initDateToSend = date.getFullYear() + "-" + date.getMonth() + "-" + date.getDate();
	var finalDateToSend = date.getFullYear() + "-" + (date.getMonth() +1) + "-" + date.getDate();
	console.log(initDateToSend + ' - ' + finalDateToSend);
	$scope.error = 1;
	$scope.getOrders = function(){
		$http({
		    method: 'GET',
	  		url: urlBase + '/getOrders/idCustomer/' + $scope.customer + '/initDate/' + initDateToSend + '/finalDate/' + finalDateToSend
	  	}).then(function successCallback(response) {
	      	$scope.orders = response.data;
	      	angular.forEach($scope.orders, function(item){
	      		var total = 0;
                angular.forEach(item.orderDetails, function(item){
	            	total += item.amount * item.price;
	            });
	            console.log(total);
	            item.total = total;
            });
            $scope.error = 0;
	  	},function errorCallback(response) {
	      	$scope.error = 1;
	      	$scope.errorMsg =  response.data.message;
	  	});
	}
	
}]);

/**
var module = angular.module("app", []);

function RemoteResource($http,baseUrl) {
    this.get=function(fnOK,fnError) {
        $http({
		    method: 'GET',
	  		url: baseUrl+ '/getOrders/idCustomer/3/initDate/2017-09-21/finalDate/2017-10-22'
	  	}).then(function successCallback(response) {
	      	fnOK(response.data);
	  	},function errorCallback(response) {
	      	fnError(response.data,response.status);
	  	});
    }
}

function RemoteResourceProvider() {
  var _baseUrl;
  this.setBaseUrl=function(baseUrl) {
    _baseUrl=baseUrl;
  }
  this.$get=['$http',function($http) {
    return new RemoteResource($http,_baseUrl);
  }];
}
 
module.constant("baseUrl", "http://localhost:13511/Products/api");
module.config(['baseUrl', 'remoteResourceProvider',function(baseUrl, remoteResourceProvider) {
    remoteResourceProvider.setBaseUrl(baseUrl);
}]);
  
module.provider("remoteResource",RemoteResourceProvider);
 
module.controller("PruebaController",['$scope', 'remoteResource',function($scope, remoteResource) {
	idUser = $scope.idUser;
  	remoteResource.get(function(orders) {
      	$scope.orders = orders;
	    angular.forEach($scope.orders, function(item){
	      	var total = 0;
            angular.forEach(item.orderDetails, function(item){
	            total += item.amount * item.price;
	        });
	        console.log(total);
	        item.total = total;
        });
    }, function(data, status) {
      	alert("Ha fallado la petición. Estado HTTP:" + status);
    });
   
}]);

*/