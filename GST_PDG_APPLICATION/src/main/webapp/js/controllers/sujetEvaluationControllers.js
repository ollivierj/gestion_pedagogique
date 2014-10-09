'use strict';

controllers
		.controller(
				'sujetEvaluationsCtrl',
				function($scope, $modal, $log, $timeout, SujetEvaluationsFactory, ModulesFactory) {
					$scope.pagingOptions = SujetEvaluationsFactory.pagingOptions;		
					$scope.sortOptions = SujetEvaluationsFactory.sortOptions;		
					$scope.filterOptions = SujetEvaluationsFactory.filterOptions;
					$scope.title = "Sujets evaluations";
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
										}/*,
										{
											field : 'lienSujet',
											displayName : 'Lien vers les sujets'
										},
										{
											field : 'lienModeleCorrection',
											displayName : 'Lien vers les modèles de correction'
										},
										{
											field : 'lienGrilleCorrection',
											displayName : 'Lien vers la grille de correction'
										}*/,
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
					}
					
					$scope.ajouterSujetEvaluation = function(
							sujetEvaluationId) {
						var modalAdd = $modal
								.open({
									templateUrl : 'partials/templates/form.html',
									controller : modalEditionSujetEvaluationCtrl,
									resolve : {
										title : function() {return "Ajout d'un sujet d'évaluation";},
										readonly : function() {return false;},
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
										ok : function() { return function(item){ return SujetEvaluationsFactory.create.doAction(item);}}
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
									templateUrl : 'partials/templates/form.html',
									controller : modalEditionSujetEvaluationCtrl,
									resolve : {
										title : function() {return "Visualisation d'un sujet d'évaluation";},
										readonly : function() {return true;},
										sujetEvaluation : function(SujetEvaluationsFactory) {
											return SujetEvaluationsFactory.detail.getData({id : sujetEvaluationId}).$promise;
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
									controller : modalEditionSujetEvaluationCtrl,
									resolve : {
										title : function() {return "Edition d'un sujet d'évaluation";},
										readonly : function() {return false;},
										sujetEvaluation : function(SujetEvaluationsFactory) {
											return SujetEvaluationsFactory.detail.getData({id : sujetEvaluationId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData().$promise;
										},
										modules : function(ModulesFactory){
											return ModulesFactory.titlemap.getData().$promise;
										},
										schema : function(SujetEvaluationsFactory) {
											return SujetEvaluationsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){ return SujetEvaluationsFactory.modify.doAction(item);}}
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
										ok : function () { return function(id) {return SujetEvaluationsFactory.delete.doAction({id : id});};}
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
				});

var modalEditionSujetEvaluationCtrl = function($scope, $modalInstance, $filter, FileUploader, fichiers,
		SujetEvaluationsFactory, FichiersFactory, onlyNumbersFilter, title, readonly, sujetEvaluation, modules, schema, ok, okTitle) {
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
			disabled : $scope.data.readonly,
			schema : { enum : $scope.modulesEnum},
			titleMap : $scope.modulesTitleMap
		},
		{
			key : "version",
		 	disabled : $scope.data.readonly
		}/*,
		{
			key : "lienSujet",
		 	disabled : $scope.data.readonly
		},
		{
			key : "lienModeleCorrection",
		 	disabled : $scope.data.readonly
		},
		{
			key : "lienModeleGrilleCorrection",
		 	disabled : $scope.data.readonly
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
