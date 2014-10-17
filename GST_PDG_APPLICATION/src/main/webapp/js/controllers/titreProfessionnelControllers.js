'use strict';

controllers
		.controller(
				'titreProfessionnelsCtrl',
				function($scope, $modal, $log, $timeout, toaster, TitreProfessionnelsFactory) {
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
									templateUrl : 'partials/titreProfessionnel.html',
									controller : modalEditionTitreProfessionnelCtrl,
									resolve : {
										title : function() {return "Ajout d'un titre professionnel";},
										readonly : function() {return false;},
										affFichiers : function() {return false;},
										affTelech : function() {return false;},
										titreProfessionnel : function(){ return {}},
										fichiers : function() {
											return null;
										},
										schema : function(TitreProfessionnelsFactory) {
											return TitreProfessionnelsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){ 
											return TitreProfessionnelsFactory.create.doAction(
												item,
												function(success) {
													toaster.pop('success', null, "Enregistrement d'un titre prfessionnel effectué");
												},
												function(error) {
													toaster.pop('error', null, error.message);
												}		
											);}}
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
									templateUrl : 'partials/titreProfessionnel.html',
									controller : modalEditionTitreProfessionnelCtrl,
									resolve : {
										title : function() {return "Visualisation d'un titre professionnel";},
										readonly : function() {return true;},
										affFichiers : function() {return true;},
										affTelech : function() {return false;},
										titreProfessionnel : function(TitreProfessionnelsFactory) {
											return TitreProfessionnelsFactory.detail.getData({id : titreProfessionnelId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData({entite_type : "TitreProfessionnel", entite_id : titreProfessionnelId}).$promise;
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
										affFichiers : function() {return true;},
										affTelech : function() {return true;},
										titreProfessionnel : function(TitreProfessionnelsFactory) {
											return TitreProfessionnelsFactory.detail.getData({id : titreProfessionnelId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData({entite_type : "TitreProfessionnel", entite_id : titreProfessionnelId}).$promise;
										},
										schema : function(TitreProfessionnelsFactory) {
											return TitreProfessionnelsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){ 
											return TitreProfessionnelsFactory.modify.doAction(
												item,
												function(success) {
													toaster.pop('success', null, "Enregistrement d'un titre prfessionnel effectué");
												},
												function(error) {
													toaster.pop('error', null, error.message);
												}	
											);};}
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
										ok : function () { return function(id) {return TitreProfessionnelsFactory.delete.doAction(
											{id : id},
											function(success) {
												toaster.pop('warning', null, "Suppression d'un titre prfessionnel effectuée");
											},
											function(error) {
												toaster.pop('error', null, error.message);
											}	
										);};}
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

var modalEditionTitreProfessionnelCtrl = function($scope, $modalInstance, $modal, $filter, FileUploader, fichiers,
		TitreProfessionnelsFactory, FichiersFactory, title, readonly, affFichiers, affTelech, titreProfessionnel, schema, ok, okTitle) {
	$scope.affFichiers=affFichiers;
	$scope.affTelech=affTelech;
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
			}
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
		 $scope.$broadcast('schemaFormValidate');
		if ($scope.form.titreProfessionnel.$valid) {
			$scope.ok($scope.data).$promise.then(
				function(response) {
					$modalInstance.close($scope.data);
				}, 
				function(reason) {
					alert('Echec: ' + reason);
				});
		}else{
			$('.ng-invalid')[1].focus();
		}
	};
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
	$scope.fichiers = fichiers;
	$scope.results = fichiers;
	$scope.fichiersFilterOptions = {
		filterText: ''
	};
	$scope.fichiersGridOptions = {
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
							displayName : 'Actions',
							cellTemplate : 'partials/templates/ng-grid_view_remove_action.html'
						}
						],
		enablePaging : false,
		showFooter : false,
		keepLastSelected: true,
		enableColumnResize: true,
		enableColumnReordering : true,
		filterOptions : $scope.fichiersFilterOptions,
		useExternalSorting : true,
		showColumnMenu : true,
		i18n : 'fr'
	};
	
	$scope.downloadFile = function(fichier) {
		var downloadPath = '/ng_gst_pdg/web/fichiers/telecharger/TitreProfessionnel/'+$scope.data.id+'/'+fichier.filename;
		window.open(downloadPath,'_blank');  
	};
	
	$scope.removeFile = function(fichier) {
		var modalDelete = $modal
		.open({
			templateUrl : 'partials/templates/dialog.html',
			controller : modalConfirmationDeleteTitreProfessionnelCtrl,
			resolve : {
				id : function() {return fichier.filename;},
				title : function() {return "Suppression d'un fichier";},
				message : function() {return "Etes-vous sur de vouloir supprimer ce fichier ?";},
				ok : function () { return function(id) { return FichiersFactory.delete.doAction({entite_type: 'TitreProfessionnel', entite_id: $scope.data.id, filename : id });}}
			}
		});	
		modalDelete.result.then(function(selectedItem) {
			FichiersFactory.fichiers.getData({entite_type : "TitreProfessionnel", entite_id : $scope.data.id})
				.$promise.then(function(data){
					$scope.fichiers = data;
					$scope.results = data;
				}
			);
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};
	
	var uploader = $scope.uploader = new FileUploader({
		url : '/ng_gst_pdg/web/fichiers/deposer'
	});

	uploader.filters.push({
		name : 'customFilter',
		fn : function(item, options) {
			return this.queue.length < 10;
		}
	});

	uploader.onBeforeUploadItem = function(item) {
		item.formData.push({entite_type : "TitreProfessionnel"});
		item.formData.push({entite_id : $scope.data.id});
	};
	uploader.onCompleteAll = function() {
		FichiersFactory.fichiers.getData({entite_type : "TitreProfessionnel", entite_id : $scope.data.id})
		.$promise.then(function(data){
			$scope.fichiers = data;
			$scope.results = data;
		});
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
