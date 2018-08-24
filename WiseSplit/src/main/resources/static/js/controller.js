var app = angular.module('app', [ 'ngRoute' ]).config(function($routeProvider) {
	$routeProvider.when("/login", {

		templateUrl : "templates/login.html",
		controller : 'loginController'
	}).when("/createUser", {

		templateUrl : "templates/user.html",
		controller : 'postcontroller'
	}).when("/addMoney", {

		templateUrl : "templates/addMoney.html",
		controller : 'addAmountController'
	}).when("/calculation" , {
		
		templateUrl : "templates/calculation.html",
		controller : 'resultcontroller'
		
	}).when("/transaction" , {
		
		templateUrl : "templates/transaction.html",
		controller : 'transactionController'
		
	})
	/*
	 * .otherwise({ templateUrl:"templates/login.html", controller :
	 * 'loginController' })
	 */
}).controller('transactionController', function($scope, $http, $location) {

	$scope.getUsers = function() {

		var url = "http://localhost:8080/v1/getAllUsers";

		
		$http.get(url).then(function(response) {
			$scope.userRecords = response.data;
		}, function error(response) {
			$scope.userRecords = "Error with status: " +response.data;
		});


	};
	
	$scope.addAccountMoney = function(userId,amount,comment) {

		var url = "http://localhost:8080/v1/addMoney";

		
		var data = {
				comment : comment,
				amount : amount,
				userId : userId

			};

			$http.post(url, data).then(function(response) {
				$scope.postResultMessages = response.data.status;
			}, function error(response) {
				$scope.postResultMessages = "Error with status: " +response.data.status;
			});

			$scope.comment = "";
			$scope.amount = "";


	};

}).controller('resultcontroller', function($scope, $http, $location) {

	$scope.getResult = function() {

		var url = "http://localhost:8080/v1/calculationAccount";

		
		$http.get(url).then(function(response) {
			$scope.records = response.data;
		}, function error(response) {
			$scope.records = "Error with status: " +response.data;
		});


	};

	$scope.getExpenditure = function() {

		var url = "http://localhost:8080/v1/moneyTransfer";
		
		$http.get(url).then(function(response) {
			$scope.transactionmoney = response.data;
		}, function error(response) {
			$scope.transactionmoney = "Error with status: " +response.data;
		});


	};
	
	$scope.getToAndFrom = function() {

		var url = "http://localhost:8080/v1/fromAndTo";
		
		$http.get(url).then(function(response) {
			$scope.toandfrom = response.data;
		}, function error(response) {
			$scope.toandfrom = "Error with status: " +response.data;
		});


	};

}).controller('addAmountController', function($scope, $http, $location) {

	$scope.AddAmount = function(userId,amount,comment) {

		var url = "http://localhost:8080/v1/addMoney";

		var data = {
			comment : comment,
			amount : amount,
			userId : userId

		};

		$http.post(url, data).then(function(response) {
			$scope.postResultMessage = response.data.status;
		}, function error(response) {
			$scope.postResultMessage = "Error with status: " +response.data.status;
		});

		$scope.comment = "";
		$scope.amount = "";

	};

}).controller(
		'postcontroller',
		function($scope, $http, $location) {

			$scope.submitForm = function() {

				var url = "http://localhost:8080/v1/createUser";

				var data = {
					firstname : $scope.firstname,
					lastname : $scope.lastname,
					emailId : $scope.emailID

				};

				data = angular.toJson(data);

				$http.post(url, data).then(
						function(response) {
							$scope.postResultMessage = response;
						},
						function error(response) {
							$scope.postResultMessage = "Error with status: "
									+response.emailId;
						});

				$scope.firstname = "";
				$scope.lastname = "";
				$scope.emailid = "";

			}

		}).controller('loginController', function($scope) {

	$scope.message = "loginLogin";
	$scope.submit = function($scope, $password) {
		var uname = $scope.username;
		var pass = $scope.password;

		if ($scope.username == "Admin" && $scope.password == "Admin") {
			$location.path('/dashboard');
		}

	}

});

/*
 * app.config(['$routeProvider', function($routeProvider){
 * 
 * $routeProvider .when('/login',{ templateUrl : 'login.html', controller
 * :'loginController' })
 * 
 * .when('/dashboard',{ templateUrl : 'user.html', controller : 'postcontroller' })
 * .otherwise({ templateUrl : '/' });
 * 
 * }]);
 */

