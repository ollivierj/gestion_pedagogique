'use strict';
controllers
	.controller(
		'authentificationCtrl',
		function($scope, AuthentificationFactory) {
			
			$scope.ok = function() {
			
				AuthentificationFactory.connect.doAction({login : $scope.login, motPasse : $scope.password},
					function(sucess) {
					AuthentificationFactory.user = sucess;
					}, 
					
					function (errResponse){
						console.log("fail");
					}
				);

			};
		});