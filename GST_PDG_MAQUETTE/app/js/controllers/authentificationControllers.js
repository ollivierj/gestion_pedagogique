'use strict';

controllers.controller('authentificationCtrl', function($scope) {
	var loginBase = 'kjamin', passwordBase = '333';

	$scope.connexionTest = function() {
		if ($scope.login == loginBase && $scope.password == passwordBase) {
			console.log('connexion ok');
		} else {
			console.log('connexion nok');
		}
	};
});