'use strict';

controllers
		.controller(
				'sallesCtrl',
				function($scope, $rootScope, $http, $modal, $log, $timeout, toaster, SalleFactory, FichiersFactory) {
					$scope.pagingOptions = SalleFactory.pagingOptions;		
					$scope.sortOptions = SalleFactory.sortOptions;		
					$scope.filterOptions = SalleFactory.filterOptions;
					$scope.title = "Salles";
					$scope.canEdit=SalleFactory.canEdit;
					$scope.canView=SalleFactory.canView;
					$scope.gridOptions = {
						data : 'salles',
						multiSelect : false,
						columnDefs : [
								{
									field : 'libelle',
									displayName : 'Nom de la salle'
								},
								{
									field : 'nbPlaces',
									displayName : 'Nombre de places'
								},
								{
									field : 'lieu',
									displayName : 'Lieu'
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

					$scope.editRow = function(salle) {
						$scope.editerSalle(salle.id);
					};

					$scope.viewRow = function(salle) {
						$scope.visualiserSalle(salle.id);
					};
					
					$scope.removeRow = function(salle) {
						$scope.supprimerSalle(salle.id);
					};
					
					$scope.afficherFenetreEdition = function(){
						$scope.ajouterSalle();
					};
					
					$scope.exporter = function(){
						FichiersFactory.exporter('/ng_gst_pdg/web/salles/csv', $scope.pagingOptions, $scope.filterOptions, $scope.sortOptions);
					};
					
					$scope.ajouterSalle = function(
							salleId) {
						var modalAdd = $modal
								.open({
									templateUrl : 'partials/salle/salle.html',
									controller : modalEditionSalleCtrl,
									resolve : {
										title : function() {return "Ajout d'un type de session";},
										readonly : function() {return false;},
										affFichiers : function() {return false;},
										affTelech : function() {return false;},
										salle : function(){ return {}},
										fichiers : function() {
											return null;
										},
										schema : function(SalleFactory) {
											return SalleFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){ 
											return SalleFactory.create.doAction(
												item,
												function(success) {
													toaster.pop('success', null, "Type de session enregistré");
												},
												function(error) {
													toaster.pop('error', null, error.data.message);
												}		
											);}}
									}
								});

						modalAdd.result.then(function(selectedItem) {
							SalleFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					$scope.visualiserSalle = function(
							salleId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/salle/salle.html',
									controller : modalEditionSalleCtrl,
									resolve : {
										title : function() {return "Visualisation d'un type de session";},
										readonly : function() {return true;},
										affFichiers : function() {return true;},
										affTelech : function() {return false;},
										salle : function(SalleFactory) {
											return SalleFactory.detail.getData({id : salleId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData({entite_type : "Salle", entite_id : salleId}).$promise;
										},
										schema : function(SalleFactory) {
											return SalleFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Fermer";},
										ok : function() { return function(item){ return item;}}
									}
								});

						modalEdit.result.then(function(selectedItem) {
							SalleFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.editerSalle = function(
							salleId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/salle/salle.html',
									controller : modalEditionSalleCtrl,
									resolve : {
										title : function() {return "Edition d'un type de session";},
										readonly : function() {return false;},
										affFichiers : function() {return true;},
										affTelech : function() {return true;},
										salle : function(SalleFactory) {
											return SalleFactory.detail.getData({id : salleId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData({entite_type : "Salle", entite_id : salleId}).$promise;
										},
										schema : function(SalleFactory) {
											return SalleFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){ 
											return SalleFactory.modify.doAction(
												item,
												function(success) {
													toaster.pop('success', null, "Type de session enregistré");
												},
												function(error) {
													toaster.pop('error', null, error.data.message);
												}	
											);};}
									}
								});

						modalEdit.result.then(function(selectedItem) {
							SalleFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.supprimerSalle = function(
							salleId) {
						var modalDelete = $modal
								.open({
									templateUrl : 'partials/templates/dialog.html',
									controller : modalConfirmationDeleteSalleCtrl,
									resolve : {
										id : function() {return salleId},
										title : function() {return "Suppression type de session";},
										message : function() {return "Etes-vous sur de vouloir supprimer ce type de session ?";},
										ok : function () { return function(id) {return SalleFactory.delete.doAction(
											{id : id},
											function(success) {
												toaster.pop('warning', null, "Type de session supprimé");
											},
											function(error) {
												toaster.pop('error', null, error.data.message);
											}	
										);};}
									}
								});
						modalDelete.result.then(function(selectedItem) {
							SalleFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.$watch('pagingOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
				        	SalleFactory.refreshData($scope);
				        }
				    }, true);

					$scope.$watch('filterOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	if ($scope.timer) {
			                    $timeout.cancel($scope.timer);
			                }
				        	$scope.timer = $timeout(function () {
			                    SalleFactory.refreshData($scope);
			                }, 500);
				        }
				    }, true);

				    $scope.$watch('sortOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	SalleFactory.refreshData($scope);
				        }
				    }, true);
					
					SalleFactory.refreshData($scope);
				});

var modalEditionSalleCtrl = function($scope, $modalInstance, $modal, $filter, FileUploader, fichiers,
		SalleFactory, FichiersFactory, title, readonly, affFichiers, affTelech, salle, schema, ok, okTitle) {
	$scope.affFichiers=affFichiers;
	$scope.affTelech=affTelech;
	$scope.title = title;
	$scope.data = salle;
	$scope.data.readonly = readonly;
	$scope.okTitle = okTitle;
	$scope.ok = ok;
	$scope.schema = schema;
	$scope.form = 
		[
			{
				key : "libelle",
				disabled : $scope.data.readonly
			},
			{
				key : "nbPlaces",
				disabled : $scope.data.readonly
			},
			{
				key : "lieu",
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
		if ($scope.form.salle.$valid) {
			$scope.ok($scope.data).$promise.then(
				function(response) {
					$modalInstance.close($scope.data);
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
		FichiersFactory.telecharger('Salle', $scope.data.id, fichier.filename);
	};
	
	$scope.removeFile = function(fichier) {
		var modalDelete = $modal
		.open({
			templateUrl : 'partials/templates/dialog.html',
			controller : modalConfirmationDeleteSalleCtrl,
			resolve : {
				id : function() {return fichier.filename;},
				title : function() {return "Suppression d'un fichier";},
				message : function() {return "Etes-vous sur de vouloir supprimer ce fichier ?";},
				ok : function () { return function(id) { return FichiersFactory.delete.doAction({entite_type: 'Salle', entite_id: $scope.data.id, filename : id });}}
			}
		});	
		modalDelete.result.then(function(selectedItem) {
			FichiersFactory.fichiers.getData({entite_type : "Salle", entite_id : $scope.data.id})
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
		item.formData.push({entite_type : "Salle"});
		item.formData.push({entite_id : $scope.data.id});
	};
	uploader.onCompleteAll = function() {
		FichiersFactory.fichiers.getData({entite_type : "Salle", entite_id : $scope.data.id})
		.$promise.then(function(data){
			$scope.fichiers = data;
			$scope.results = data;
		});
	};
};

var modalConfirmationDeleteSalleCtrl = function($scope, $modalInstance, 
		SalleFactory, id, title, message, ok) {
	$scope.title = title;
	$scope.message = message;
	$scope.ok =function(item){
		ok(id).$promise.then(
			function(response) {
				$modalInstance.close(id);
			});
	};
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};
