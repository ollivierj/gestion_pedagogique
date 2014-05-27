'use strict';

controllers.controller('gestionDroitCtrl', function($scope, $modal, $log, droitAdmin, droitFormateur) {

	$scope.items = [ 'item1', 'item2', 'item3' ];

	$scope.droitAdminSelected = [];
	$scope.droitFormateurSelected = [];
	
	droitAdmin.getData().then(function(data) {
        $scope.droitAdmin = data;
    });
	
	droitFormateur.getData().then(function(data) {
        $scope.droitFormateur = data;
    });
	
    var linkCellTemplate = '<a href="" ng-click="afficherModalModifProfil()">{{row.getProperty(col.field)}}</a>';
    
	$scope.gridDroitAdmin = {
		data : 'droitAdmin',
		selectedItems : $scope.droitAdminSelected,
		multiSelect : false,
		columnDefs : [ {
			field : 'nom',
			displayName : 'Nom'
		}, {
			field : 'prenom',
			displayName : 'Prenom'
		}, {
			field : 'profil',
			displayName : 'Profil',
			cellTemplate: linkCellTemplate
		}, {
			field : 'formation',
			displayName : 'Formation',
			cellTemplate: linkCellTemplate
		} ]
	};
	
	$scope.gridDroitFormateur = {
			data : 'droitFormateur',
			selectedItems : $scope.droitFormateurSelected,
			multiSelect : false,
			columnDefs : [ {
				field : 'nom',
				displayName : 'Nom'
			}, {
				field : 'prenom',
				displayName : 'Prenom'
			}, {
				field : 'profil',
				displayName : 'Profil',
				cellTemplate: linkCellTemplate
			}, {
				field : 'formation',
				displayName : 'Formation',
				cellTemplate: linkCellTemplate
			} ]
		};
	
	

	$scope.afficherModalModifProfil = function() {

		var modalEdit = $modal.open({
			templateUrl : 'partials/droit/modifProfil.html',
			controller : ModalEditProfilCtrl,
			resolve : {
				items : function() {
					return $scope.items;
				}
			}
		});

		modalEdit.result.then(function(selectedItem) {
			$scope.selected = selectedItem;
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};
	
	$scope.afficherModalGestionDroit = function() {
		var modalEdit = $modal.open({
			templateUrl : 'partials/droit/modifDroit.html',
			controller : ModalModifDroitCtrl,
			resolve : {
				items : function() {
					return $scope.items;
				}
			}
		});

		modalEdit.result.then(function(selectedItem) {
			$scope.selected = selectedItem;
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

});

var ModalEditProfilCtrl = function($scope, $modalInstance, items) {

	$scope.items = items;
	$scope.selected = {
		item : $scope.items[0]
	};

	// Disable weekend selection
	$scope.disabled = function(date, mode) {
		return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
	};

	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();

		$scope.opened = true;
	};

	$scope.ok = function() {
		$modalInstance.close($scope.selected.item);
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};


var ModalModifDroitCtrl = function($scope, $modalInstance, items) {

	$scope.items = items;
	$scope.selected = {
		item : $scope.items[0]
	};

	// Disable weekend selection
	$scope.disabled = function(date, mode) {
		return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
	};

	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();

		$scope.opened = true;
	};

	$scope.ok = function() {
		$modalInstance.close($scope.selected.item);
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};