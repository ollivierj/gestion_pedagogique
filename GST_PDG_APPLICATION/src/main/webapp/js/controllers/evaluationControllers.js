'use strict';

controllers
		.controller(
				'evaluationsCtrl',
				function($scope, $modal, $log, $timeout, $http, toaster, EvaluationsFactory, SujetEvaluationsFactory, UtilisateursFactory, FichiersFactory) {
					$scope.pagingOptions = EvaluationsFactory.pagingOptions;		
					$scope.sortOptions = EvaluationsFactory.sortOptions;		
					$scope.filterOptions = EvaluationsFactory.filterOptions;
					$scope.title = "Evaluations";
					$scope.gridOptions = {
						data : 'evaluations',
						multiSelect : false,
						columnDefs : 	[
										{
											field : 'sujetEvaluation.module.libelle',
											displayName : 'Module'
										},
										{
											field : 'sujetEvaluation.version',
											displayName : 'Version'
										},
										{
											field : 'formatedDateHeureDebutPassage',
											cellFilter: 'date:\'dd/MM/yyyy HH:mm\'',
											displayName : 'Date et heure de début'
										},
										{
											field : 'formatedDateHeureFinPassage',
											cellFilter: 'date:\'dd/MM/yyyy HH:mm\'',
											displayName : 'Date et heure de fin'
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

					$scope.editRow = function(evaluation) {
						$scope.editerEvaluation(evaluation.id);
					};

					$scope.viewRow = function(evaluation) {
						$scope.visualiserEvaluation(evaluation.id);
					};
					
					$scope.removeRow = function(evaluation) {
						$scope.supprimerEvaluation(evaluation.id);
					};
					
					$scope.afficherFenetreEdition = function(){
						$scope.ajouterEvaluation();
					}
					
					
					$scope.exporter = function(){
						FichiersFactory.exporter('/ng_gst_pdg/web/evaluations/csv', $scope.pagingOptions, $scope.filterOptions, $scope.sortOptions);
					};
					
					$scope.ajouterEvaluation = function(
							evaluationId) {
						var modalAdd = $modal
								.open({
									templateUrl : 'partials/evaluation.html',
									controller : modalEditionEvaluationCtrl,
									resolve : {
										title : function() {return "Ajout d'une évaluation";},
										readonly : function() {return false;},
										affFichiers : function() {return false;},
										affTelech : function() {return false;},
										evaluation : function(){ return {}},
										fichiers : function() {
											return null;
										},
										sujetEvaluations : function(SujetEvaluationsFactory){
											return SujetEvaluationsFactory.titlemap.getData().$promise;
										},
										utilisateurs : function(UtilisateursFactory){
											return UtilisateursFactory.titlemap.getData().$promise;
										},
										schema : function(EvaluationsFactory) {
											return EvaluationsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { 
											return function(item){ 
												return EvaluationsFactory.create.doAction(
													item,
													function(success) {
														toaster.pop('success', null, "Evaluation enregistrée");
													},
													function(error) {
														toaster.pop('error', null, error.message);
													}
												);
											};
										}
									}
								});

						modalAdd.result.then(function(selectedItem) {
							EvaluationsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					$scope.visualiserEvaluation = function(
							evaluationId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/evaluation.html',
									controller : modalEditionEvaluationCtrl,
									resolve : {
										title : function() {return "Visualisation d'une évaluation";},
										readonly : function() {return true;},
										affFichiers : function() {return true;},
										affTelech : function() {return false;},
										evaluation : function(EvaluationsFactory) {
											return EvaluationsFactory.detail.getData({id : evaluationId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData({entite_type : "Evaluation", entite_id : evaluationId}).$promise;
										},
										sujetEvaluations : function(SujetEvaluationsFactory){
											return SujetEvaluationsFactory.titlemap.getData().$promise;
										},
										utilisateurs : function(UtilisateursFactory){
											return UtilisateursFactory.titlemap.getData().$promise;
										},
										schema : function(EvaluationsFactory) {
											return EvaluationsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Fermer";},
										ok : function() { return function(item){ return item;}}
									}
								});

						modalEdit.result.then(function(selectedItem) {
							EvaluationsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.editerEvaluation = function(
							evaluationId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/evaluation.html',
									controller : modalEditionEvaluationCtrl,
									resolve : {
										title : function() {return "Edition d'une évaluation";},
										readonly : function() {return false;},
										affFichiers : function() {return true;},
										affTelech : function() {return true;},
										evaluation : function(EvaluationsFactory) {
											return EvaluationsFactory.detail.getData({id : evaluationId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData({entite_type : "Evaluation", entite_id : evaluationId}).$promise;
										},
										sujetEvaluations : function(SujetEvaluationsFactory){
											return SujetEvaluationsFactory.titlemap.getData().$promise;
										},
										utilisateurs : function(UtilisateursFactory){
											return UtilisateursFactory.titlemap.getData().$promise;
										},
										schema : function(EvaluationsFactory) {
											return EvaluationsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){return EvaluationsFactory.modify.doAction(
											item,
											function(success) {
									    		toaster.pop('success', null, "Evaluation enregistrée");
											},
											function(error) {
												toaster.pop('error', null, error.message);
											});};}
									}
								});
						
						modalEdit.result.then(function(selectedItem) {
							EvaluationsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.supprimerEvaluation = function(
							evaluationId) {
						var modalDelete = $modal
								.open({
									templateUrl : 'partials/templates/dialog.html',
									controller : modalConfirmationDeleteEvaluationCtrl,
									resolve : {
										id : function() {return evaluationId},
										title : function() {return "Suppression d'une évaluation";},
										message : function() {return "Etes-vous sur de vouloir supprimer cette évaluation ?";},
										ok : function () { 
											return function(id) {
												return EvaluationsFactory.delete.doAction(
													{id : id},
													function(success) {
											    		toaster.pop('warning', null, "Evaluation enregistrée");
													},
													function(error) {
														toaster.pop('error', null, error.message);
													}
												);
											};
										}
									}
								});
						modalDelete.result.then(function(selectedItem) {
							EvaluationsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.$watch('pagingOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
				        	EvaluationsFactory.refreshData($scope);
				        }
				    }, true);

					$scope.$watch('filterOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	if ($scope.timer) {
			                    $timeout.cancel($scope.timer);
			                }
				        	$scope.timer = $timeout(function () {
			                    EvaluationsFactory.refreshData($scope);
			                }, 500);
				        }
				    }, true);

				    $scope.$watch('sortOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	EvaluationsFactory.refreshData($scope);
				        }
				    }, true);
					
					EvaluationsFactory.refreshData($scope);
				});

var modalEditionEvaluationCtrl = function($scope, $modalInstance, $filter, $modal, FileUploader ,
		EvaluationsFactory, StagiaireFactory, onlyNumbersFilter, title, FichiersFactory, fichiers, readonly, affFichiers, affTelech, evaluation, sujetEvaluations, utilisateurs, schema, ok, okTitle) {
	$scope.affFichiers=affFichiers;
	$scope.affTelech=affTelech;
	$scope.title = title;
	$scope.data = evaluation;
	$scope.data.evaluationStagiaires=($scope.data.evaluationStagiaires)?$scope.data.evaluationStagiaires:[];
	$scope.data.readonly = readonly;
	$scope.sujetEvaluationsTitleMap = sujetEvaluations;
	$scope.sujetEvaluationsEnum = onlyNumbersFilter(Object.keys($scope.sujetEvaluationsTitleMap)),
	$scope.utilisateursTitleMap = utilisateurs;
	$scope.utilisateursEnum = onlyNumbersFilter(Object.keys($scope.utilisateursTitleMap)),
	$scope.okTitle = okTitle;
	$scope.ok = ok;
	$scope.schema = schema;
	$scope.form = 
		[
		{
			title : "Sujet d'évaluation",
			key: "sujetEvaluation.id",
			type : "select",
			required : true,
			disabled : $scope.data.readonly,
			schema : { enum : $scope.sujetEvaluationsEnum},
			titleMap : $scope.sujetEvaluationsTitleMap
		}, 
		{
			title : "Correcteur",
			key: "correcteur.id",
			type : "select",
			disabled : $scope.data.readonly,
			schema : { enum : $scope.utilisateursEnum},
			titleMap : $scope.utilisateursTitleMap
		},
		{
			key : "formatedDateHeureDebutPassage",
			maxdate : "formatedDateHeureFinPassage",
			disabled : $scope.data.readonly
			
		},
		{
			key : "formatedDateHeureFinPassage",
			mindate : "formatedDateHeureDebutPassage",
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
	var columnDefs =  		
		[
		{field:'stagiaire.nom', displayName:'Nom'},
		{field:'stagiaire.prenom', displayName:'Prénom'},
		{field:'stagiaire.codePromotion', displayName:'Promotion', cellTemplate: 'partials/templates/ng-grid_detailsPromotion.html'}
		];
	
	if (!$scope.data.readonly){
		columnDefs.push(
		{										
			displayName : 'Actions',
			cellTemplate : 'partials/templates/ng-grid_remove_action.html'
		}
		);
	}
	$scope.stagiairesFilterOptions = {
			filterText: ''
		};
	 $scope.stagiairesGridOptions = {
		        data: 'data.evaluationStagiaires',
		        selectedItems: $scope.stagiaireSelected,
		        columnDefs : columnDefs,
		        enablePaging: false,
		        showFooter: false,
		        multiSelect: false,
		        filterOptions : $scope.stagiairesFilterOptions
		    };
	$scope.removeRow = function(evaluationStagiaire) {
		var index = $scope.data.evaluationStagiaires.indexOf(evaluationStagiaire);
		 $scope.data.evaluationStagiaires.splice(index, 1);     
	};
	$scope.chargerStagiairesOrPromotions = function(search) {
		return StagiaireFactory.stagiaireOrPromotionAutocomplete.getData({search: search}).$promise.then(function(data) {
			var stagiairesOrPromotions = [];
			angular.forEach(data, function(item) {
				stagiairesOrPromotions.push(item);
			});
			return stagiairesOrPromotions;
		});
	};
	$scope.addItem = function(item) {
		StagiaireFactory.stagiaireOrPromotion.getData({type: item.type, id : item.id}).$promise.then(function(data) {
			angular.forEach(data, function(stagiaire) {
				var evaluation = {id : $scope.data.id};
				var test = $filter('filter')($scope.data.evaluationStagiaires, {stagiaire : {id:stagiaire.id}});
				if (0==test.length){
					$scope.data.evaluationStagiaires.push({evaluation : evaluation, stagiaire : stagiaire});
				}
			});
		});
	};
	$scope.decorator = 'bootstrap-decorator';
	$scope.submit =function(){
		 $scope.$broadcast('schemaFormValidate');
		if ($scope.form.evaluation.$valid) {
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
		var downloadPath = '/ng_gst_pdg/web/fichiers/telecharger/Evaluation/'+$scope.data.id+'/'+fichier.filename;
		window.open(downloadPath,'_blank');  
	};
	
	$scope.removeFile = function(fichier) {
		var modalDelete = $modal
		.open({
			templateUrl : 'partials/templates/dialog.html',
			controller : modalConfirmationDeleteEvaluationCtrl,
			resolve : {
				id : function() {return fichier.filename;},
				title : function() {return "Suppression d'un fichier";},
				message : function() {return "Etes-vous sur de vouloir supprimer ce fichier ?";},
				ok : function () { return function(id) { return FichiersFactory.delete.doAction({entite_type: 'Evaluation', entite_id: $scope.data.id, filename : id });}}
			}
		});	
		modalDelete.result.then(function(selectedItem) {
			FichiersFactory.fichiers.getData({entite_type : "Evaluation", entite_id : $scope.data.id})
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
		item.formData.push({entite_type : "Evaluation"});
		item.formData.push({entite_id : $scope.data.id});
	};
	uploader.onCompleteAll = function() {
		FichiersFactory.fichiers.getData({entite_type : "Evaluation", entite_id : $scope.data.id})
		.$promise.then(function(data){
			$scope.fichiers = data;
			$scope.results = data;
		});
	};
};


var modalConfirmationDeleteEvaluationCtrl = function($scope, $modalInstance, 
		EvaluationsFactory, id, title, message, ok) {
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
