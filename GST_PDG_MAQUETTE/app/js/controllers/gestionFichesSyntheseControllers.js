'use strict';


controllers.controller('gestionFichesSyntheseCtrl', function($scope, $modal, $log, ficheSynth) {

	$scope.items = [ 'item1', 'item2', 'item3' ];

	$scope.ficheSynthSelected = [];

	ficheSynth.getData().then(function(data) {
        $scope.ficheSynth = data;
    });
	
    var linkCellTemplate = '<a href="" ng-click="afficherModalFicheSynthese()">{{row.getProperty(col.field)}}</a>';
    
	$scope.gridModeleFiche = {
		data : 'ficheSynth',
		selectedItems : $scope.ficheSynthSelected,
		multiSelect : false,
		columnDefs : [ {
			field : 'formation',
			displayName : 'Formation',
			cellTemplate: linkCellTemplate
		}, {
			field : 'lien',
			displayName : 'Lien',
			cellTemplate: linkCellTemplate
		}]
	};
	
	

	$scope.afficherModalFicheSynthese = function() {

		var modalEdit = $modal.open({
			templateUrl : 'partials/gestionFichesSynthese/editFicheSynth.html',
			controller : FicheSynthEditCtrl,
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

var FicheSynthEditCtrl = function($scope, $modalInstance, items) {

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