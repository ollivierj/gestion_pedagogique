'use strict';

controllers
		.controller(
				'sujetEvaluationsCtrl',
				function($scope, $rootScope, $http, $modal, $log, $timeout, toaster, SujetEvaluationsFactory, ModulesFactory, FichiersFactory) {
					if (!$rootScope.utilisateurConnecte && !$rootScope.authtoken){
						$http.defaults.headers.common.Authorization =  'Basic ' + $rootScope.authtoken;
					}
					$scope.pagingOptions = SujetEvaluationsFactory.pagingOptions;		
					$scope.sortOptions = SujetEvaluationsFactory.sortOptions;		
					$scope.filterOptions = SujetEvaluationsFactory.filterOptions;
					$scope.title = "Sujets d'évaluation";
					$scope.canEdit=SujetEvaluationsFactory.canEdit;
					$scope.canView=SujetEvaluationsFactory.canView;
					$scope.gridOptions = {
						data : 'sujetEvaluations',
						multiSelect : false,
						columnDefs : 	[
										{
											field : 'module.libelle',
											displayName : 'Module'
										},
										{
											field : 'version',
											displayName : 'Version'
										},
										{
											displayName : 'Actions',
											cellTemplate : 'partials/templates/ng-grid_actions.html'
										}
										],
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

					$scope.editRow = function(sujetEvaluation) {
						$scope.editerSujetEvaluation(sujetEvaluation.id);
					};

					$scope.viewRow = function(sujetEvaluation) {
						$scope.visualiserSujetEvaluation(sujetEvaluation.id);
					};
					
					$scope.removeRow = function(sujetEvaluation) {
						$scope.supprimerSujetEvaluation(sujetEvaluation.id);
					};
					
					$scope.afficherFenetreEdition = function(){
						$scope.ajouterSujetEvaluation();
					};
					
					$scope.exporter = function(){
						FichiersFactory.exporter('/ng_gst_pdg/web/sujetEvaluations/csv', $scope.pagingOptions, $scope.filterOptions, $scope.sortOptions);
					};
					
					$scope.ajouterSujetEvaluation = function(
							sujetEvaluationId) {
						var modalAdd = $modal
								.open({
									templateUrl : 'partials/sujetEvaluation.html',
									controller : 'modalEditionSujetEvaluationCtrl',
									resolve : {
										title : function() {return "Ajout d'un sujet d'évaluation";},
										readonly : function() {return false;},
										affFichiers : function() {return false;},
										affTelech : function() {return false;},
										sujetEvaluation : function(){ return {}},
										modules : function(ModulesFactory){
											return ModulesFactory.titlemap.getData().$promise;
										},
										fichiers : function() {
											return null;
										},
										schema : function(SujetEvaluationsFactory) {
											return SujetEvaluationsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){
											return SujetEvaluationsFactory.create.doAction(
												item,
												function(success) {
													toaster.pop('success', null, "Sujet d'évaluation enregistré");
												},
												function(error) {
													toaster.pop('error', null, error.message);
												}		
											);};}
									}
								});

						modalAdd.result.then(function(selectedItem) {
							SujetEvaluationsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					$scope.visualiserSujetEvaluation = function(
							sujetEvaluationId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/sujetEvaluation.html',
									controller : 'modalEditionSujetEvaluationCtrl',
									resolve : {
										title : function() {return "Visualisation d'un sujet d'évaluation";},
										readonly : function() {return true;},
										affFichiers : function() {return true;},
										affTelech : function() {return false;},
										sujetEvaluation : function(SujetEvaluationsFactory) {
											return SujetEvaluationsFactory.detail.getData({id : sujetEvaluationId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData({entite_type : "SujetEvaluation", entite_id : sujetEvaluationId}).$promise;
										},
										modules : function(ModulesFactory){
											return ModulesFactory.titlemap.getData().$promise;
										},
										schema : function(SujetEvaluationsFactory) {
											return SujetEvaluationsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Fermer";},
										ok : function() { return function(item){ return item;}}
									}
								});

						modalEdit.result.then(function(selectedItem) {
							SujetEvaluationsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.editerSujetEvaluation = function(
							sujetEvaluationId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/sujetEvaluation.html',
									controller : 'modalEditionSujetEvaluationCtrl',
									resolve : {
										title : function() {return "Edition d'un sujet d'évaluation";},
										readonly : function() {return false;},
										affFichiers : function() {return true;},
										affTelech : function() {return true;},
										sujetEvaluation : function(SujetEvaluationsFactory) {
											return SujetEvaluationsFactory.detail.getData({id : sujetEvaluationId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData({entite_type : "SujetEvaluation", entite_id : sujetEvaluationId}).$promise;
										},
										modules : function(ModulesFactory){
											return ModulesFactory.titlemap.getData().$promise;
										},
										schema : function(SujetEvaluationsFactory) {
											return SujetEvaluationsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){ 
											return SujetEvaluationsFactory.modify.doAction(
												item,
												function(success) {
													toaster.pop('success', null, "Sujet d'évaluation enregistré");
												},
												function(error) {
													toaster.pop('error', null, error.message);
												}	
											);};}
									}
								});

						modalEdit.result.then(function(selectedItem) {
							SujetEvaluationsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.supprimerSujetEvaluation = function(
							sujetEvaluationId) {
						var modalDelete = $modal
								.open({
									templateUrl : 'partials/templates/dialog.html',
									controller : modalConfirmationDeleteSujetEvaluationCtrl,
									resolve : {
										id : function() {return sujetEvaluationId},
										title : function() {return "Suppression sujet d'évaluation";},
										message : function() {return "Etes-vous sur de vouloir supprimer ce sujet d'évaluation ?";},
										ok : function () { return function(id) {
											return SujetEvaluationsFactory.delete.doAction(
												{id : id},
												function(success) {
													toaster.pop('warning', null, "Sujet d'évaluation supprimé");
												},
												function(error) {
													toaster.pop('error', null, error.message);
												}	
											);};}
									}
								});
						modalDelete.result.then(function(selectedItem) {
							SujetEvaluationsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.$watch('pagingOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
				        	SujetEvaluationsFactory.refreshData($scope);
				        }
				    }, true);

					$scope.$watch('filterOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	if ($scope.timer) {
			                    $timeout.cancel($scope.timer);
			                }
				        	$scope.timer = $timeout(function () {
			                    SujetEvaluationsFactory.refreshData($scope);
			                }, 500);
				        }
				    }, true);

				    $scope.$watch('sortOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	SujetEvaluationsFactory.refreshData($scope);
				        }
				    }, true);
					
					SujetEvaluationsFactory.refreshData($scope);
				}).controller('modalEditionSujetEvaluationCtrl', function($scope, $modalInstance, $filter, $modal, FileUploader, fichiers,
		SujetEvaluationsFactory, FichiersFactory, onlyNumbersFilter, title, readonly, affFichiers, affTelech, sujetEvaluation, modules, schema, ok, okTitle) {
	$scope.affFichiers=affFichiers;
	$scope.affTelech=affTelech;
	$scope.title = title;
	$scope.data = sujetEvaluation;
	$scope.data.readonly = readonly;
	$scope.modulesTitleMap = modules;
	$scope.modulesEnum = onlyNumbersFilter(Object.keys($scope.modulesTitleMap)),
	$scope.okTitle = okTitle;
	$scope.ok = ok;
	$scope.schema = schema;
	$scope.form = 
		[
		{
			title : "Module",
			key: "module.id",
			type : "select",
			required : true,
			disabled : $scope.data.readonly,
			schema : { enum : $scope.modulesEnum},
			titleMap : $scope.modulesTitleMap
		},
		{
			key : "version",
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
		if ($scope.form.sujetEvaluation.$valid) {
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
		var downloadPath = '/ng_gst_pdg/web/fichiers/telecharger/SujetEvaluation/'+$scope.data.id+'/'+fichier.filename;
		window.open(downloadPath,'_blank');  
	};
	
	$scope.removeFile = function(fichier) {
		var modalDelete = $modal
		.open({
			templateUrl : 'partials/templates/dialog.html',
			controller : modalConfirmationDeleteSujetEvaluationCtrl,
			resolve : {
				id : function() {return fichier.filename;},
				title : function() {return "Suppression d'un fichier";},
				message : function() {return "Etes-vous sur de vouloir supprimer ce fichier ?";},
				ok : function () { return function(id) { return FichiersFactory.delete.doAction({entite_type: 'SujetEvaluation', entite_id: $scope.data.id, filename : id });}}
			}
		});	
		modalDelete.result.then(function(selectedItem) {
			FichiersFactory.fichiers.getData({entite_type : "SujetEvaluation", entite_id : $scope.data.id})
				.$promise.then(function(data){
					$scope.fichiers = data;
					$scope.results = data;
				}
			);
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
	}
		
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
		item.formData.push({entite_type : "SujetEvaluation"});
		item.formData.push({entite_id : $scope.data.id});
	};
	uploader.onCompleteAll = function() {
		FichiersFactory.fichiers.getData({entite_type : "SujetEvaluation", entite_id : $scope.data.id})
		.$promise.then(function(data){
			$scope.fichiers = data;
			$scope.results = data;
		});
	};
});



var modalConfirmationDeleteSujetEvaluationCtrl = function($scope, $modalInstance, 
		SujetEvaluationsFactory, id, title, message, ok) {
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
