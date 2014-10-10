'use strict';

controllers
		.controller(
				'titreProfessionnelsCtrl',
				function($scope, $modal, $log, $timeout, TitreProfessionnelsFactory) {
					$scope.pagingOptions = TitreProfessionnelsFactory.pagingOptions;		
					$scope.sortOptions = TitreProfessionnelsFactory.sortOptions;		
					$scope.filterOptions = TitreProfessionnelsFactory.filterOptions;
					$scope.title = "Titres professionnels";
					$scope.gridOptions = {
						data : 'titreProfessionnels',
						multiSelect : false,
						columnDefs : [
								{
									field : 'code',
									displayName : 'Code'
								}/*,
								{
									field : 'lienDocReferences',
									displayName : 'Documents de référence'
								}*/,
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
						$scope.visualiserTitreProfessionnel(titreProfessionnel.id);
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
										readonly : function() {return false;},
										titreProfessionnel : function(){ return {}},
										fichiers : function() {
											return null;
										},
										schema : function(TitreProfessionnelsFactory) {
											return TitreProfessionnelsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){ return TitreProfessionnelsFactory.create.doAction(item);}}
									}
								});

						modalAdd.result.then(function(selectedItem) {
							TitreProfessionnelsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					$scope.visualiserTitreProfessionnel = function(
							titreProfessionnelId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/templates/form.html',
									controller : modalEditionTitreProfessionnelCtrl,
									resolve : {
										title : function() {return "Visualisation d'un titre professionnel";},
										readonly : function() {return true;},
										titreProfessionnel : function(TitreProfessionnelsFactory) {
											return TitreProfessionnelsFactory.detail.getData({id : titreProfessionnelId}).$promise;
										},
										schema : function(TitreProfessionnelsFactory) {
											return TitreProfessionnelsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Fermer";},
										ok : function() { return function(item){ return item;}}
									}
								});

						modalEdit.result.then(function(selectedItem) {
							TitreProfessionnelsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.editerTitreProfessionnel = function(
							titreProfessionnelId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/titreProfessionnel.html',
									controller : modalEditionTitreProfessionnelCtrl,
									resolve : {
										title : function() {return "Edition d'un titre professionnel";},
										readonly : function() {return false;},
										titreProfessionnel : function(TitreProfessionnelsFactory) {
											return TitreProfessionnelsFactory.detail.getData({id : titreProfessionnelId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData().$promise;
										},
										schema : function(TitreProfessionnelsFactory) {
											return TitreProfessionnelsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
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

var modalEditionTitreProfessionnelCtrl = function($scope, $modalInstance, $filter, FileUploader, fichiers,
		TitreProfessionnelsFactory, FichiersFactory, title, readonly, titreProfessionnel, schema, ok, okTitle) {
	$scope.title = title;
	$scope.data = titreProfessionnel;
	$scope.data.readonly = readonly;
	$scope.okTitle = okTitle;
	$scope.ok = ok;
	$scope.schema = schema;
	$scope.form = 
		[
			{
				key : "code",
				disabled : $scope.data.readonly
			}/*,
			{
	    		key : "lienDocReferences",
	    		disabled : $scope.data.readonly
			},
			{
		    	type: "conditional",
	            condition: "!data.readonly", 
	            items: 
	           	 [
	           	 {
	           	 type: "actions",
	           	 items:	
	           		 [
			         {
			         type: 'submit', 
			         title: $scope.okTitle 
			         },
			         { 
					 type: 'button', 
					 title: 'Annuler', 
					 onClick: "cancel()" 
					 }
			         ]
	           	 }
	           	 ]	
			},
			{
		    	type: "conditional",
	            condition: "data.readonly", 
	            items: 
	           	 [
	           	 {
	           	 type: "actions",
	           	 items:	
	           		 [
			         {
			         type: 'submit', 
			         title: $scope.okTitle 
			         }
			         ]
	           	 }
	           	 ]	
			}*/
	    ];
	$scope.form2 =
		[
		{
		type: "conditional",
		condition: "!data.readonly", 
		items: 
		 [
		 {
		 type: "actions",
		 items:	
			 [
		     {
		     type: 'submit', 
		     title: $scope.okTitle 
		     },
		     { 
			 type: 'button', 
			 title: 'Annuler', 
			 onClick: "cancel()" 
				 }
		         ]
		   	 }
		   	 ]	
		},
		{
		type: "conditional",
		condition: "data.readonly", 
		items: 
		 [
		 {
		 type: "actions",
		 items:	
			 [
		     {
		     type: 'submit', 
		         title: $scope.okTitle 
		         }
		         ]
		   	 }
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
	$scope.fichiers = fichiers;
	$scope.results = fichiers;
	$scope.filterOptions = FichiersFactory.filterOptions;
	$scope.gridOptions = {
		data : 'results',
		multiSelect : false,
		columnDefs : 	[
						{
							field : 'filename',
							displayName : 'Nom'
						},
						{
							field : 'size',
							displayName : 'Taille'
						},
						{
							field : 'url',
							displayName : 'Chemin'
						}
						],
		enablePaging : false,
		showFooter : false,
		keepLastSelected: true,
		enableColumnResize: true,
		enableColumnReordering : true,
		filterOptions : $scope.filterOptions,
		useExternalSorting : true,
		showColumnMenu : true,
		i18n : 'fr'
	};
		
	$scope.$watch('filterOptions', function (newVal, oldVal) {
		if (newVal.filterText){
	        $scope.results=$filter('filter')($scope.fichiers, {filename:newVal.filterText}, false);
		}else{
			$scope.results=$scope.fichiers;
		}
    }, true);
	
	var uploader = $scope.uploader = new FileUploader({
		url : '/ng_gst_pdg/web/fichiers/deposer'
	});

	// FILTERS

	uploader.filters.push({
		name : 'customFilter',
		fn : function(item /* {File|FileLikeObject} */, options) {
			return this.queue.length < 10;
		}
	});

	// CALLBACKS

	uploader.onWhenAddingFileFailed = function(item /* {File|FileLikeObject} */,
			filter, options) {
		console.info('onWhenAddingFileFailed', item, filter, options);
	};
	uploader.onAfterAddingFile = function(fileItem) {
		console.info('onAfterAddingFile', fileItem);
	};
	uploader.onAfterAddingAll = function(addedFileItems) {
		console.info('onAfterAddingAll', addedFileItems);
	};
	uploader.onBeforeUploadItem = function(item) {
		console.info('onBeforeUploadItem', item);
	};
	uploader.onProgressItem = function(fileItem, progress) {
		console.info('onProgressItem', fileItem, progress);
	};
	uploader.onProgressAll = function(progress) {
		console.info('onProgressAll', progress);
	};
	uploader.onSuccessItem = function(fileItem, response, status, headers) {
		console.info('onSuccessItem', fileItem, response, status, headers);
	};
	uploader.onErrorItem = function(fileItem, response, status, headers) {
		console.info('onErrorItem', fileItem, response, status, headers);
	};
	uploader.onCancelItem = function(fileItem, response, status, headers) {
		console.info('onCancelItem', fileItem, response, status, headers);
	};
	uploader.onCompleteItem = function(fileItem, response, status, headers) {
		console.info('onCompleteItem', fileItem, response, status, headers);
	};
	uploader.onCompleteAll = function() {
		console.info('onCompleteAll');
	};

	console.info('uploader', uploader);
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
