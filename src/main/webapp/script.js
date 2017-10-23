var module=angular.module("app",[]);

/**
 * Controlador del modulo app.
 */
module.controller("PruebaController", 
		["$scope","$http",function($scope, $http){

	$scope.customer = 0;
	var urlBase = 'http://localhost:13511/Products/api';
	var date = new Date();
	var initDateToSend = date.getFullYear() + "-" + date.getMonth() + "-" + date.getDate();
	var finalDateToSend = date.getFullYear() + "-" + (date.getMonth() +1) + "-" + date.getDate();
	console.log(initDateToSend + ' - ' + finalDateToSend);
	$scope.error = 1;
	
	/**
	 * Metodo que consume el servicio Web
	 */
	$scope.getOrders = function(){
		$http({
		    method: 'GET',
	  		url: urlBase + '/getOrders/idCustomer/' + $scope.customer + '/initDate/' + initDateToSend + '/finalDate/' + finalDateToSend
	  	}).then(function successCallback(response) {
	      	$scope.orders = response.data;
	      	angular.forEach($scope.orders, function(item){// calcula el precio total de cada orden
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