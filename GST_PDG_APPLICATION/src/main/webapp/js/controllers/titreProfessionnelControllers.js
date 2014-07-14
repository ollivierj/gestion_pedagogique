'use strict';

controllers
		.controller(
				'titreProfessionnelsCtrl',
				function($scope, $modal, $log, $timeout, TitreProfessionnelsFactory,
						homologationData) {
					$scope.pagingOptions = TitreProfessionnelsFactory.pagingOptions;		
					$scope.sortOptions = TitreProfessionnelsFactory.sortOptions;		
					$scope.filterOptions = TitreProfessionnelsFactory.filterOptions;
					
					$scope.titreProfessionnelsGridOptions = {
						data : 'titreProfessionnels',
						multiSelect : false,
						columnDefs : [
								{
									field : 'code',
									displayName : 'Code'
								},
								{
									field : 'lienDocReferences',
									displayName : 'Documents de référence'
								},
								{
									displayName : 'Actions',
									cellTemplate : 'partials/templates/ng-grid_actions.html'
								} ],
						enablePaging : true,
						showFooter : true,
						keepLastSelected: true,
						enableColumnResize: true,
						enableColumnReordering : true,
						useExternalSorting : true,
						showColumnMenu : true,
						i18n : 'fr',
						totalServerItems : 'totalServerItems',
						filterOptions : $scope.filterOptions,
						sortInfo : $scope.sortOptions,
						pagingOptions : $scope.pagingOptions
					};

					$scope.editRow = function(titreProfessionnel) {
						$scope.editerTitreProfessionnel(titreProfessionnel.id);
					};

					$scope.viewRow = function(titreProfessionnel) {
						$scope.editerTitreProfessionnel(titreProfessionnel.id);
					};
					
					$scope.removeRow = function(titreProfessionnel) {
						$scope.supprimerTitreProfessionnel(titreProfessionnel.id);
					};
					
					$scope.afficherFenetreEdition = function(){
						$scope.ajouterTitreProfessionnel();
					}
					
					$scope.ajouterTitreProfessionnel = function(
							titreProfessionnelId) {
						var modalAdd = $modal
								.open({
									templateUrl : 'partials/templates/form.html',
									controller : modalEditionTitreProfessionnelCtrl,
									resolve : {
										title : function() {return "Ajout d'un titre professionnel";},
										titreProfessionnel : function(){ return {}},
										schema : function(TitreProfessionnelsFactory) {
											return TitreProfessionnelsFactory.jsonschema.getData().$promise;
										},
										ok : function() { return function(item){ return TitreProfessionnelsFactory.create.doAction(item);}}
									}
								});

						modalAdd.result.then(function(selectedItem) {
							TitreProfessionnelsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					$scope.editerTitreProfessionnel = function(
							titreProfessionnelId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/templates/form.html',
									controller : modalEditionTitreProfessionnelCtrl,
									resolve : {
										title : function() {return "Edition d'un titre professionnel";},
										titreProfessionnel : function(TitreProfessionnelsFactory) {
											return TitreProfessionnelsFactory.detail.getData({id : titreProfessionnelId}).$promise;
										},
										schema : function(TitreProfessionnelsFactory) {
											return TitreProfessionnelsFactory.jsonschema.getData().$promise;
										},
										ok : function() { return function(item){ return TitreProfessionnelsFactory.modify.doAction(item);}}
									}
								});

						modalEdit.result.then(function(selectedItem) {
							TitreProfessionnelsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.supprimerTitreProfessionnel = function(
							titreProfessionnelId) {
						var modalDelete = $modal
								.open({
									templateUrl : 'partials/templates/dialog.html',
									controller : modalConfirmationDeleteTitreProfessionnelCtrl,
									resolve : {
										id : function() {return titreProfessionnelId},
										title : function() {return "Suppression titre professionnel";},
										message : function() {return "Etes-vous sur de vouloir supprimer ce titre professionnel ?";},
										ok : function () { return function(id) {return TitreProfessionnelsFactory.delete.doAction({id : id});};}
									}
								});
						modalDelete.result.then(function(selectedItem) {
							TitreProfessionnelsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.$watch('pagingOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
				        	TitreProfessionnelsFactory.refreshData($scope);
				        }
				    }, true);

					$scope.$watch('filterOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	if ($scope.timer) {
			                    $timeout.cancel($scope.timer);
			                }
				        	$scope.timer = $timeout(function () {
			                    TitreProfessionnelsFactory.refreshData($scope);
			                }, 500);
				        }
				    }, true);

				    $scope.$watch('sortOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	TitreProfessionnelsFactory.refreshData($scope);
				        }
				    }, true);
					
					TitreProfessionnelsFactory.refreshData($scope);
				});

var modalEditionTitreProfessionnelCtrl = function($scope, $modalInstance,
		TitreProfessionnelsFactory, title, titreProfessionnel, schema, ok) {
	$scope.title = title;
	$scope.data = titreProfessionnel;
	$scope.ok = ok;
	$scope.schema = schema;
	$scope.form = 
		[
		 	"code",
		 	"lienDocReferences",
		 	{
        	  type: "actions",
        	  items:	[
		        	    { type: 'submit', title: 'Enregistrer' },
		        	    { type: 'button', title: 'Annuler', onClick: "cancel()" }
		        	    ]
		 	}
	    ];
	$scope.decorator = 'bootstrap-decorator';
	$scope.submit =function(){
		$scope.ok($scope.data).$promise.then(
					function(response) {
						$modalInstance.close($scope.data);
					}, 
					function(reason) {
						alert('Echec: ' + reason);
					});
		

	};
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};

var modalConfirmationDeleteTitreProfessionnelCtrl = function($scope, $modalInstance, 
		TitreProfessionnelsFactory, id, title, message, ok) {
	$scope.title = title;
	$scope.message = message;
	$scope.ok =function(item){
		ok(id).$promise.then(
			function(response) {
				$modalInstance.close(id);
			}, 
			function(reason) {
				alert('Failed: ' + reason);
			});
	};
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};
