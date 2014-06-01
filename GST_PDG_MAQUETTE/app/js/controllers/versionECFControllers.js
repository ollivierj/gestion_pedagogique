'use strict';

controllers.controller('versionECFCtrl', function($scope, $modal, $log, vECF) {

	$scope.items = [ 'item1', 'item2', 'item3' ];

	$scope.versionECFSelected = [];

    vECF.getData().then(function(data) {
        $scope.versionECF = data;
    });
	
    var linkCellTemplate = '<a href="" ng-click="afficherModalVersionECF()">{{row.getProperty(col.field)}}</a>';
    var suppression = '<a href="" ><i class="fa fa-times-circle"></i></a>'
	$scope.gridVersionECF = {
		data : 'versionECF',
		selectedItems : $scope.versionECFSelected,
		multiSelect : false,
		columnDefs : [ {
			field : 'version',
			displayName : 'Version'
		}, {
			field : 'nom',
			displayName : 'Nom',
			cellTemplate: linkCellTemplate
		}, {
			field : 'ennonce',
			displayName : 'Ennoncé'
		}, {
			field : 'correction',
			displayName : 'Correction'
		}, {
			field : 'formation',
			displayName : 'Formation'
		}, {
			field : 'module',
			displayName : 'Module'
		}, {
			field : 'del',
			displayName : 'Suppression',
			cellTemplate: suppression
		}  ]
	};
	
	

	$scope.afficherModalVersionECF = function() {

		var modalEdit = $modal.open({
			templateUrl : 'partials/gestionEvaluations/editVersion.html',
			controller : ModalEditVersionCtrl,
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

var ModalEditVersionCtrl = function($scope, $modalInstance, items) {

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
