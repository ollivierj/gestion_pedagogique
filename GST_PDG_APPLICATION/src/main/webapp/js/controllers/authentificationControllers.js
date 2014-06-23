'use strict';

var ModalAuthentificationCtrl = function($scope, $modalInstance, items) {
	var loginBase = 'kjamin', passwordBase = '333';

	$scope.connexionTest = function() {
		if ($scope.login == loginBase && $scope.password == passwordBase) {
			console.log('connexion ok');
		} else {
			console.log('connexion nok');
		}
	};

	//Bouton d'action de la fenêtre modal
	$scope.ok = function () {
		$modalInstance.close($scope.selected.item);
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
};