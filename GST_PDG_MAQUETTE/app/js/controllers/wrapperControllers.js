'use strict';

controllers.controller('wrapperCtrl', function($scope, modalService, $log,
		wrapMenu) {

	$scope.items = [ 'item1', 'item2', 'item3' ];

	$scope.titleSelected = "";

	wrapMenu.getData().then(function(data) {
		$scope.menuTitles = data;
	});

	$scope.setActive = function(title) {
		$scope.titleSelected = title;
	};
	
	var modalDefaults = {
		backdrop : true,
		keyboard : true,
		size : 'lg',
		modalFade : true,
		templateUrl : 'partials/absence/formulaireAbsence.html',
		controller : formulaireAbsenceCtrl,
		resolve : {
			items : function() {
				return $scope.items;
			}, 
			retardataires : function (RetardatairesFactory) {
	            return RetardatairesFactory.query().$promise;
	        },
		}
	};
	$scope.afficherModalAbsence = function() {
		modalService.showModal(modalDefaults, {}).then(function(result) {
			$scope.selected = result;
		});
	}

});