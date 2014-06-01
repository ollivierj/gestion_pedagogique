'use strict';

controllers.controller('corrigerECFCtrl', function($scope, $modal, $log, cECF) {

	$scope.items = [ 'item1', 'item2', 'item3' ];

	
	$scope.correctionECFSelected = [];

	cECF.getData().then(function(data) {
		$scope.correctionECF = data;
	});

	var linkCellTemplate = '<a href="" ng-click="afficherModalCorrectionECF()">{{row.getProperty(col.field)}}</a>';

	$scope.gridCorrectionECF = {
		data : 'correctionECF',
		selectedItems : $scope.correctionECFSelected,
		multiSelect : false,
		columnDefs : [ {
			field : 'module',
			displayName : 'Module',
			cellTemplate : linkCellTemplate
		}, {
			field : 'formation',
			displayName : 'Formation'

		}, {
			field : 'date',
			displayName : 'Date'

		}, {
			field : 'corrige',
			displayName : 'Corrigé'
		} ]
	};
	
	
	$scope.afficherModalCorrectionECF = function() {

		var modalEdit = $modal.open({
			templateUrl : 'partials/gestionEvaluations/editCorrection.html',
			controller : ModalCorrectionECFCtrl,
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

var ModalCorrectionECFCtrl = function($scope, $modalInstance, items) {

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