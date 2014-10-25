'use strict';

controllers
		.controller(
				'sessionValidationsCtrl',

				function($scope, $modal, $log, $timeout, $rootScope, $http, toaster, SessionValidationsFactory, TitreProfessionnelsFactory, FichiersFactory) {
					if (!$rootScope.utilisateurConnecte && !$rootScope.authtoken){
						$http.defaults.headers.common.Authorization =  'Basic ' + $rootScope.authtoken;
					}
					$scope.pagingOptions = SessionValidationsFactory.pagingOptions;		
					$scope.sortOptions = SessionValidationsFactory.sortOptions;		
					$scope.filterOptions = SessionValidationsFactory.filterOptions;
					$scope.title = "Sessions de validation";
					$scope.canEdit=SessionValidationsFactory.canEdit;
					$scope.canView=SessionValidationsFactory.canView;
					$scope.gridOptions = {
						data : 'sessionValidations',

						multiSelect : false,
						columnDefs : 	[
										{
											field : 'titreProfessionnel.code',

											displayName : 'Module'
										},
										{
											field : 'formatedDateDebut',
											cellFilter: 'date:\'dd/MM/yyyy\'',

											displayName : 'Date de début'
										},
										{
											field : 'formatedDateFin',

											cellFilter: 'date:\'dd/MM/yyyy\'',
											displayName : 'Date de fin'
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

					$scope.editRow = function(sessionValidation) {
						$scope.editerSessionValidation(sessionValidation.id);
					};

					$scope.viewRow = function(sessionValidation) {
						$scope.visualiserSessionValidation(sessionValidation.id);
					};
					
					$scope.removeRow = function(sessionValidation) {
						$scope.supprimerSessionValidation(sessionValidation.id);
					};
					
					$scope.afficherFenetreEdition = function(){
						$scope.ajouterSessionValidation();
					};
					
					$scope.exporter = function(){
						FichiersFactory.exporter('/ng_gst_pdg/web/sessionValidations/csv', $scope.pagingOptions, $scope.filterOptions, $scope.sortOptions);
					};
					
					$scope.ajouterSessionValidation = function(
							sessionValidationId) {

						var modalAdd = $modal
								.open({
									templateUrl : 'partials/sessionValidation.html',
									controller : modalEditionSessionValidationCtrl,
									resolve : {
										title : function() {return "Ajout d'une session de validation";},
										readonly : function() {return false;},
										affFichiers : function() {return false;},
										affTelech : function() {return false;},
										sessionValidation : function(){ return {}},
										fichiers : function() {
											return null;
										},
										titreProfessionnels : function(TitreProfessionnelsFactory){

											return TitreProfessionnelsFactory.titlemap.getData().$promise;
										},
										schema : function(SessionValidationsFactory) {

											return SessionValidationsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){ 
											return SessionValidationsFactory.create.doAction(
												item,
												function(success) {
													toaster.pop('success', null, "Session de validation enregistrée");
												},
												function(error) {
													toaster.pop('error', null, error.message);
												}		
											);};}
									}
								});

						modalAdd.result.then(function(selectedItem) {
							SessionValidationsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					$scope.visualiserSessionValidation = function(
							sessionValidationId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/sessionValidation.html',
									controller : modalEditionSessionValidationCtrl,
									resolve : {
										title : function() {return "Visualisation d'une session de validation";},
										readonly : function() {return true;},
										affFichiers : function() {return true;},
										affTelech : function() {return false;},
										sessionValidation : function(SessionValidationsFactory) {
											return SessionValidationsFactory.detail.getData({id : sessionValidationId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData({entite_type : "SessionValidation", entite_id : sessionValidationId}).$promise;
										},
										titreProfessionnels : function(TitreProfessionnelsFactory){

											return TitreProfessionnelsFactory.titlemap.getData().$promise;
										},
										schema : function(SessionValidationsFactory) {
											return SessionValidationsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Fermer";},
										ok : function() { return function(item){ return item;}}
									}
								});

						modalEdit.result.then(function(selectedItem) {
							SessionValidationsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.editerSessionValidation = function(
							sessionValidationId) {

						var modalEdit = $modal
								.open({
									templateUrl : 'partials/sessionValidation.html',
									controller : modalEditionSessionValidationCtrl,
									resolve : {
										title : function() {return "Edition d'une session de validation";},
										readonly : function() {return false;},
										affFichiers : function() {return true;},
										affTelech : function() {return true;},
										sessionValidation : function(SessionValidationsFactory) {
											return SessionValidationsFactory.detail.getData({id : sessionValidationId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData({entite_type : "SessionValidation", entite_id : sessionValidationId}).$promise;
										},
										titreProfessionnels : function(TitreProfessionnelsFactory){

											return TitreProfessionnelsFactory.titlemap.getData().$promise;
										},
										schema : function(SessionValidationsFactory) {
											return SessionValidationsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){ 
											return SessionValidationsFactory.modify.doAction(
												item,
												function(success) {
													toaster.pop('success', null, "Session de validation enregistrée");
												},
												function(error) {
													toaster.pop('error', null, error.message);
												}			
											);};}
									}
								});

						modalEdit.result.then(function(selectedItem) {
							SessionValidationsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.supprimerSessionValidation = function(
							sessionValidationId) {

						var modalDelete = $modal
								.open({
									templateUrl : 'partials/templates/dialog.html',
									controller : modalConfirmationDeleteSessionValidationCtrl,
									resolve : {
										id : function() {return sessionValidationId},
										title : function() {return "Suppression d'une session de validation";},
										message : function() {return "Etes-vous sur de vouloir supprimer cette session de validation ?";},
										ok : function () { return function(id) {
											return SessionValidationsFactory.delete.doAction(
												{id : id},
												function(success) {
													toaster.pop('success', null, "Session de validation supprimée");
												},
												function(error) {
													toaster.pop('error', null, error.message);
												}			
											
											);};}
									}
								});
						modalDelete.result.then(function(selectedItem) {
							SessionValidationsFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.$watch('pagingOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
				        	SessionValidationsFactory.refreshData($scope);
				        }
				    }, true);

					$scope.$watch('filterOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	if ($scope.timer) {
			                    $timeout.cancel($scope.timer);
			                }
				        	$scope.timer = $timeout(function () {
			                    SessionValidationsFactory.refreshData($scope);

			                }, 500);
				        }
				    }, true);

				    $scope.$watch('sortOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	SessionValidationsFactory.refreshData($scope);
				        }
				    }, true);
					
					SessionValidationsFactory.refreshData($scope);
				});

var modalEditionSessionValidationCtrl = function($scope, $modalInstance, $filter, $modal, FileUploader,
		SessionValidationsFactory, StagiaireFactory, onlyNumbersFilter, title, FichiersFactory, fichiers, readonly, affFichiers, affTelech, sessionValidation, titreProfessionnels, schema, ok, okTitle) {
	$scope.affFichiers=affFichiers;
	$scope.affTelech=affTelech;
	$scope.title = title;
	$scope.data = sessionValidation;
	$scope.data.sessionValidationStagiaires=($scope.data.sessionValidationStagiaires)?$scope.data.sessionValidationStagiaires:[];
	$scope.data.readonly = readonly;
	$scope.titreProfessionnelsTitleMap = titreProfessionnels;
	$scope.titreProfessionnelsEnum = onlyNumbersFilter(Object.keys($scope.titreProfessionnelsTitleMap)),
	$scope.promotions=[];
	$scope.stagiaire={};
	$scope.okTitle = okTitle;
	$scope.ok = ok;
	$scope.schema = schema;
	$scope.form = 
		[
		{
			title : "Titre professionnel",
			key: "titreProfessionnel.id",
			type : "select",
			required : true,
			disabled : $scope.data.readonly,
			schema : { enum : $scope.titreProfessionnelsEnum},
			titleMap : $scope.titreProfessionnelsTitleMap
		}, 
		{
			key : "formatedDateDebut",
			maxdate : "formatedDateFin",
			disabled : $scope.data.readonly
		},
		{
			key : "formatedDateFin",
			mindate : "formatedDateDebut",
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
        data: 'data.sessionValidationStagiaires',
        selectedItems: $scope.stagiaireSelected,
        columnDefs : columnDefs,
        enablePaging: false,
        showFooter: false,
        multiSelect: false,
        filterOptions : $scope.stagiairesFilterOptions
    };

	$scope.removeRow = function(stagiaire) {
		var index = $scope.sessionValidationStagiaires.indexOf(sessionValidationStagiaire);
		 $scope.sessionValidationStagiaires.splice(index, 1);     
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
				var sessionValidation = {id : $scope.data.id};
				var test = $filter('filter')($scope.data.sessionValidationStagiaires, {stagiaire : {id:stagiaire.id}});
				if (0==test.length){
					$scope.data.sessionValidationStagiaires.push({sessionValidation : sessionValidation, stagiaire : stagiaire});
				}
			});
		});
	};
	$scope.decorator = 'bootstrap-decorator';
	$scope.submit =function(){
		 $scope.$broadcast('schemaFormValidate');
		if ($scope.form.sessionValidation.$valid) {
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
		var downloadPath = '/ng_gst_pdg/web/fichiers/telecharger/SessionValidation/'+$scope.data.id+'/'+fichier.filename;
		window.open(downloadPath,'_blank');  
	};
	
	$scope.removeFile = function(fichier) {
		var modalDelete = $modal
		.open({
			templateUrl : 'partials/templates/dialog.html',
			controller : modalConfirmationDeleteSessionValidationCtrl,
			resolve : {
				id : function() {return fichier.filename;},
				title : function() {return "Suppression d'un fichier";},
				message : function() {return "Etes-vous sur de vouloir supprimer ce fichier ?";},
				ok : function () { return function(id) { return FichiersFactory.delete.doAction({entite_type: 'SessionValidation', entite_id: $scope.data.id, filename : id });}}
			}
		});	
		modalDelete.result.then(function(selectedItem) {
			FichiersFactory.fichiers.getData({entite_type : "SessionValidation", entite_id : $scope.data.id})
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
		item.formData.push({entite_type : "SessionValidation"});
		item.formData.push({entite_id : $scope.data.id});
	};
	uploader.onCompleteAll = function() {
		FichiersFactory.fichiers.getData({entite_type : "SessionValidation", entite_id : $scope.data.id})
		.$promise.then(function(data){
			$scope.fichiers = data;
			$scope.results = data;
		});
	};
	
	
};


var modalConfirmationDeleteSessionValidationCtrl = function($scope, $modalInstance, 
		SessionValidationsFactory, id, title, message, ok) {
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
