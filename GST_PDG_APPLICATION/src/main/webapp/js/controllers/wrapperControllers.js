'use strict';

controllers.controller('wrapperCtrl', function($scope, $modal, modalService, $log,
		wrapMenu) {

	$scope.items = [ 'item1', 'item2', 'item3' ];

	$scope.titleSelected = "";

	wrapMenu.getData().then(function(data) {
		$scope.menuTitles = data;
	});

	$scope.setActive = function(title) {
		$scope.titleSelected = title;
	};
	
	// Afficher une fenêtre modal pour la saisie d'absences
	$scope.afficherModalAbsence = function() {
		var modalAbsences = $modal
		.open({
			templateUrl : 'partials/absence/formulaireAbsence.html',
			controller : absencesCtrl
		});
	};

});