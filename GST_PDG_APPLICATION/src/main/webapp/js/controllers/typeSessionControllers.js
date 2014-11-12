'use strict';

controllers
		.controller(
				'typeSessionsCtrl',
				function($scope, $rootScope, $http, $modal, $log, $timeout, toaster, TypeSessionsFactory, FichiersFactory) {
					$scope.pagingOptions = TypeSessionsFactory.pagingOptions;		
					$scope.sortOptions = TypeSessionsFactory.sortOptions;		
					$scope.filterOptions = TypeSessionsFactory.filterOptions;
					$scope.title = "Types de session de validation";
					$scope.canEdit=TypeSessionsFactory.canEdit;
					$scope.canView=TypeSessionsFactory.canView;
					$scope.gridOptions = {
						data : 'typesessions',
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

					$scope.editRow = function(typeSession) {
						$scope.editerTypeSession(typeSession.id);
					};

					$scope.viewRow = function(typeSession) {
						$scope.visualiserTypeSession(typeSession.id);
					};
					
					$scope.removeRow = function(typeSession) {
						$scope.supprimerTypeSession(typeSession.id);
					};
					
					$scope.afficherFenetreEdition = function(){
						$scope.ajouterTypeSession();
					};
					
					$scope.exporter = function(){
						FichiersFactory.exporter('/ng_gst_pdg/web/typesessions/csv', $scope.pagingOptions, $scope.filterOptions, $scope.sortOptions);
					};
					
					$scope.ajouterTypeSession = function(
							typeSessionId) {
						var modalAdd = $modal
								.open({
									templateUrl : 'partials/typeSession.html',
									controller : modalEditionTypeSessionCtrl,
									resolve : {
										title : function() {return "Ajout d'un type de session";},
										readonly : function() {return false;},
										affFichiers : function() {return false;},
										affTelech : function() {return false;},
										typeSession : function(){ return {}},
										fichiers : function() {
											return null;
										},
										schema : function(TypeSessionsFactory) {
											return TypeSessionsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){ 
											return TypeSessionsFactory.create.doAction(
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
							TypeSessionsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					$scope.visualiserTypeSession = function(
							typeSessionId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/typeSession.html',
									controller : modalEditionTypeSessionCtrl,
									resolve : {
										title : function() {return "Visualisation d'un type de session";},
										readonly : function() {return true;},
										affFichiers : function() {return true;},
										affTelech : function() {return false;},
										typeSession : function(TypeSessionsFactory) {
											return TypeSessionsFactory.detail.getData({id : typeSessionId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData({entite_type : "TypeSession", entite_id : typeSessionId}).$promise;
										},
										schema : function(TypeSessionsFactory) {
											return TypeSessionsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Fermer";},
										ok : function() { return function(item){ return item;}}
									}
								});

						modalEdit.result.then(function(selectedItem) {
							TypeSessionsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.editerTypeSession = function(
							typeSessionId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/typeSession.html',
									controller : modalEditionTypeSessionCtrl,
									resolve : {
										title : function() {return "Edition d'un type de session";},
										readonly : function() {return false;},
										affFichiers : function() {return true;},
										affTelech : function() {return true;},
										typeSession : function(TypeSessionsFactory) {
											return TypeSessionsFactory.detail.getData({id : typeSessionId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData({entite_type : "TypeSession", entite_id : typeSessionId}).$promise;
										},
										schema : function(TypeSessionsFactory) {
											return TypeSessionsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){ 
											return TypeSessionsFactory.modify.doAction(
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
							TypeSessionsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.supprimerTypeSession = function(
							typeSessionId) {
						var modalDelete = $modal
								.open({
									templateUrl : 'partials/templates/dialog.html',
									controller : modalConfirmationDeleteTypeSessionCtrl,
									resolve : {
										id : function() {return typeSessionId},
										title : function() {return "Suppression type de session";},
										message : function() {return "Etes-vous sur de vouloir supprimer ce type de session ?";},
										ok : function () { return function(id) {return TypeSessionsFactory.delete.doAction(
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
							TypeSessionsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.$watch('pagingOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
				        	TypeSessionsFactory.refreshData($scope);
				        }
				    }, true);

					$scope.$watch('filterOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	if ($scope.timer) {
			                    $timeout.cancel($scope.timer);
			                }
				        	$scope.timer = $timeout(function () {
			                    TypeSessionsFactory.refreshData($scope);
			                }, 500);
				        }
				    }, true);

				    $scope.$watch('sortOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	TypeSessionsFactory.refreshData($scope);
				        }
				    }, true);
					
					TypeSessionsFactory.refreshData($scope);
				});

var modalEditionTypeSessionCtrl = function($scope, $modalInstance, $modal, $filter, FileUploader, fichiers,
		TypeSessionsFactory, FichiersFactory, title, readonly, affFichiers, affTelech, typeSession, schema, ok, okTitle) {
	$scope.affFichiers=affFichiers;
	$scope.affTelech=affTelech;
	$scope.title = title;
	$scope.data = typeSession;
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
		if ($scope.form.typeSession.$valid) {
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
		FichiersFactory.telecharger('TypeSession', $scope.data.id, fichier.filename);
	};
	
	$scope.removeFile = function(fichier) {
		var modalDelete = $modal
		.open({
			templateUrl : 'partials/templates/dialog.html',
			controller : modalConfirmationDeleteTypeSessionCtrl,
			resolve : {
				id : function() {return fichier.filename;},
				title : function() {return "Suppression d'un fichier";},
				message : function() {return "Etes-vous sur de vouloir supprimer ce fichier ?";},
				ok : function () { return function(id) { return FichiersFactory.delete.doAction({entite_type: 'TypeSession', entite_id: $scope.data.id, filename : id });}}
			}
		});	
		modalDelete.result.then(function(selectedItem) {
			FichiersFactory.fichiers.getData({entite_type : "TypeSession", entite_id : $scope.data.id})
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
		item.formData.push({entite_type : "TypeSession"});
		item.formData.push({entite_id : $scope.data.id});
	};
	uploader.onCompleteAll = function() {
		FichiersFactory.fichiers.getData({entite_type : "TypeSession", entite_id : $scope.data.id})
		.$promise.then(function(data){
			$scope.fichiers = data;
			$scope.results = data;
		});
	};
};

var modalConfirmationDeleteTypeSessionCtrl = function($scope, $modalInstance, 
		TypeSessionsFactory, id, title, message, ok) {
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
