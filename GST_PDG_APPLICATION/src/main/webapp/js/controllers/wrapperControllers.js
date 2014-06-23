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
	// Afficher une fenêtre modal pour la saisie d'absences
	$scope.afficherModalAbsence = function() {
		modalService.showModal(modalDefaults, {}).then(function(result) {
			$scope.selected = result;
		});
	};

	// Afficher une fenêtre modal pour la connexion utilisateur
	$scope.afficherModalConnexion = function() {
		var modalConnexion = {
			backdrop : true,
			keyboard : true,
			modalFade : true,
			templateUrl : 'partials/authentification/authentification.html',
			controller : ModalAuthentificationCtrl,
			resolve : {
				items : function() {
					return $scope.items;
				}
			}
		};
		modalService.showModal(modalConnexion, {}).then(function(result) {
			$scope.selected = result;
		});
	};

});