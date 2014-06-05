'use strict';

controllers
		.controller(
				'listeECFCtrl',
				function($scope, $modal, $log, lECF) {

					$scope.items = [ 'item1', 'item2', 'item3' ];

					$scope.listeECFSelected = [];

					lECF.getData().then(function(data) {
						$scope.listeECF = data;
					});

					var linkCellTemplate = '<a href="" ng-click="afficherModalModifierECF()">{{row.getProperty(col.field)}}</a>';
					var suppression = '<a href="" ><i class="fa fa-times-circle"></i></a>'
					
					$scope.gridListeECF = {
						data : 'listeECF',
						selectedItems : $scope.listeECFSelected,
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
							field : 'planifie',
							displayName : 'Planifié'
						}, {
							field:'supp',
							displayName:'Actions', 
							cellTemplate: 'partials/templates/ng-grid_actions.html'
						}]
					};

					$scope.afficherModalModifierECF = function() {

						var modalEdit = $modal
								.open({
									templateUrl : 'partials/gestionEvaluations/editECF.html',
									controller : ModalEditECFCtrl,
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

var ModalEditECFCtrl = function($scope, $modalInstance, items) {

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