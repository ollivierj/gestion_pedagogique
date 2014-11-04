'use strict';

controllers
		.controller(
				'avisCtrl',
				function($scope, $modal, $log, $timeout, $http, $rootScope, toaster, AvisFactory, InstanceCoursFactory, UtilisateursFactory) {
					if (!$rootScope.utilisateurConnecte && !$rootScope.authtoken){
						$http.defaults.headers.common.Authorization =  'Basic ' + $rootScope.authtoken;
					}
					$scope.pagingOptions = AvisFactory.pagingOptions;		
					$scope.sortOptions = AvisFactory.sortOptions;		
					$scope.filterOptions = AvisFactory.filterOptions;
					$scope.title = "Avis";
					$scope.canEdit=AvisFactory.canEdit;
					$scope.canView=AvisFactory.canView;
					$scope.gridOptions = {
						data : 'avis',
						multiSelect : false,
						columnDefs : 	[
										{
											field : 'cours.libelleCours',
											displayName : 'Libellé'
										},
										{
											field : 'cours.debut',
											displayName : 'Date de début',
											cellFilter: 'date:\'dd/MM/yyyym\'',
										},
										{
											field : 'cours.fin',
											cellFilter: 'date:\'dd/MM/yyyy HH:mm\'',
											displayName : 'Date de fin'
										},
										{
											field : 'cours.promotion.libelle',
											displayName : 'Libellé'
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

					$scope.editRow = function(avis) {
						$scope.editerAvis(avis.id);
					};

					$scope.viewRow = function(avis) {
						$scope.visualiserAvis(avis.id);
					};
					
					$scope.removeRow = function(avis) {
						$scope.supprimerAvis(avis.id);
					};
					
					$scope.afficherFenetreEdition = function(){
						$scope.ajouterAvis();
					}
					
					
					$scope.exporter = function(){
						FichiersFactory.exporter('/ng_gst_pdg/web/avis/csv', $scope.pagingOptions, $scope.filterOptions, $scope.sortOptions);
					};
					
					$scope.ajouterAvis = function(
							avisId) {
						var modalAdd = $modal
								.open({
									templateUrl : 'partials/avis.html',
									controller : modalEditionAvisCtrl,
									resolve : {
										title : function() {return "Ajout d'une évaluation";},
										readonly : function() {return false;},
										affFichiers : function() {return false;},
										affTelech : function() {return false;},
										avis : function(){ return {}},
										fichiers : function() {
											return null;
										},
										sujetAvis : function(SujetAvisFactory){
											return SujetAvisFactory.titlemap.getData().$promise;
										},
										utilisateurs : function(UtilisateursFactory){
											return UtilisateursFactory.titlemap.getData().$promise;
										},
										schema : function(AvisFactory) {
											return AvisFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { 
											return function(item){ 
												return AvisFactory.create.doAction(
													item,
													function(success) {
														toaster.pop('success', null, "Avis enregistrée");
													},
													function(error) {
														toaster.pop('error', null, error.data.message);
													}
												);
											};
										}
									}
								});

						modalAdd.result.then(function(selectedItem) {
							AvisFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					$scope.visualiserAvis = function(
							avisId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/avis.html',
									controller : modalEditionAvisCtrl,
									resolve : {
										title : function() {return "Visualisation d'une évaluation";},
										readonly : function() {return true;},
										affFichiers : function() {return true;},
										affTelech : function() {return false;},
										avis : function(AvisFactory) {
											return AvisFactory.detail.getData({id : avisId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData({entite_type : "Avis", entite_id : avisId}).$promise;
										},
										sujetAvis : function(SujetAvisFactory){
											return SujetAvisFactory.titlemap.getData().$promise;
										},
										utilisateurs : function(UtilisateursFactory){
											return UtilisateursFactory.titlemap.getData().$promise;
										},
										schema : function(AvisFactory) {
											return AvisFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Fermer";},
										ok : function() { return function(item){ return item;}}
									}
								});

						modalEdit.result.then(function(selectedItem) {
							AvisFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.editerAvis = function(
							avisId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/avis.html',
									controller : modalEditionAvisCtrl,
									resolve : {
										title : function() {return "Edition d'une évaluation";},
										readonly : function() {return false;},
										affFichiers : function() {return true;},
										affTelech : function() {return true;},
										avis : function(AvisFactory) {
											return AvisFactory.detail.getData({id : avisId}).$promise;
										},
										fichiers : function(FichiersFactory) {
											return FichiersFactory.fichiers.getData({entite_type : "Avis", entite_id : avisId}).$promise;
										},
										sujetAvis : function(SujetAvisFactory){
											return SujetAvisFactory.titlemap.getData().$promise;
										},
										utilisateurs : function(UtilisateursFactory){
											return UtilisateursFactory.titlemap.getData().$promise;
										},
										schema : function(AvisFactory) {
											return AvisFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){return AvisFactory.modify.doAction(
											item,
											function(success) {
									    		toaster.pop('success', null, "Avis enregistrée");
											},
											function(error) {
												toaster.pop('error', null, error.data.message);
											});};}
									}
								});
						
						modalEdit.result.then(function(selectedItem) {
							AvisFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.supprimerAvis = function(
							avisId) {
						var modalDelete = $modal
								.open({
									templateUrl : 'partials/templates/dialog.html',
									controller : modalConfirmationDeleteAvisCtrl,
									resolve : {
										id : function() {return avisId},
										title : function() {return "Suppression d'une évaluation";},
										message : function() {return "Etes-vous sur de vouloir supprimer cette évaluation ?";},
										ok : function () { 
											return function(id) {
												return AvisFactory.delete.doAction(
													{id : id},
													function(success) {
											    		toaster.pop('warning', null, "Avis enregistrée");
													},
													function(error) {
														toaster.pop('error', null, error.data.message);
													}
												);
											};
										}
									}
								});
						modalDelete.result.then(function(selectedItem) {
							AvisFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.$watch('pagingOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
				        	AvisFactory.refreshData($scope);
				        }
				    }, true);

					$scope.$watch('filterOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	if ($scope.timer) {
			                    $timeout.cancel($scope.timer);
			                }
				        	$scope.timer = $timeout(function () {
			                    AvisFactory.refreshData($scope);
			                }, 500);
				        }
				    }, true);

				    $scope.$watch('sortOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	AvisFactory.refreshData($scope);
				        }
				    }, true);
					
				    InstanceCoursFactory.refreshData($scope);
				});

var modalEditionAvisCtrl = function($scope, $modalInstance, $filter, $modal, FileUploader ,
		AvisFactory, StagiaireFactory, onlyNumbersFilter, title, FichiersFactory, fichiers, readonly, affFichiers, affTelech, avis, sujetAvis, utilisateurs, schema, ok, okTitle) {
	$scope.affFichiers=affFichiers;
	$scope.affTelech=affTelech;
	$scope.title = title;
	$scope.data = avis;
	$scope.data.avisStagiaires=($scope.data.avisStagiaires)?$scope.data.avisStagiaires:[];
	$scope.data.readonly = readonly;
	$scope.sujetAvisTitleMap = sujetAvis;
	$scope.sujetAvisEnum = onlyNumbersFilter(Object.keys($scope.sujetAvisTitleMap)),
	$scope.utilisateursTitleMap = utilisateurs;
	$scope.utilisateursEnum = onlyNumbersFilter(Object.keys($scope.utilisateursTitleMap)),
	$scope.okTitle = okTitle;
	$scope.ok = ok;
	$scope.schema = schema;
	$scope.form = 
		[
		{
			title : "Sujet d'évaluation",
			key: "sujetAvis.id",
			type : "select",
			required : true,
			disabled : $scope.data.readonly,
			schema : { enum : $scope.sujetAvisEnum},
			titleMap : $scope.sujetAvisTitleMap
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
		        data: 'data.avisStagiaires',
		        selectedItems: $scope.stagiaireSelected,
		        columnDefs : columnDefs,
		        enablePaging: false,
		        showFooter: false,
		        multiSelect: false,
		        filterOptions : $scope.stagiairesFilterOptions
		    };
	$scope.removeRow = function(avisStagiaire) {
		var index = $scope.data.avisStagiaires.indexOf(avisStagiaire);
		 $scope.data.avisStagiaires.splice(index, 1);     
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
				var avis = {id : $scope.data.id};
				var test = $filter('filter')($scope.data.avisStagiaires, {stagiaire : {id:stagiaire.id}});
				if (0==test.length){
					$scope.data.avisStagiaires.push({avis : avis, stagiaire : stagiaire});
				}
			});
		});
	};
	$scope.decorator = 'bootstrap-decorator';
	$scope.submit =function(){
		 $scope.$broadcast('schemaFormValidate');
		if ($scope.form.avis.$valid) {
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
		var downloadPath = '/ng_gst_pdg/web/fichiers/telecharger/Avis/'+$scope.data.id+'/'+fichier.filename;
		window.open(downloadPath,'_blank');  
	};
	
	$scope.removeFile = function(fichier) {
		var modalDelete = $modal
		.open({
			templateUrl : 'partials/templates/dialog.html',
			controller : modalConfirmationDeleteAvisCtrl,
			resolve : {
				id : function() {return fichier.filename;},
				title : function() {return "Suppression d'un fichier";},
				message : function() {return "Etes-vous sur de vouloir supprimer ce fichier ?";},
				ok : function () { return function(id) { return FichiersFactory.delete.doAction({entite_type: 'Avis', entite_id: $scope.data.id, filename : id });}}
			}
		});	
		modalDelete.result.then(function(selectedItem) {
			FichiersFactory.fichiers.getData({entite_type : "Avis", entite_id : $scope.data.id})
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
		item.formData.push({entite_type : "Avis"});
		item.formData.push({entite_id : $scope.data.id});
	};
	uploader.onCompleteAll = function() {
		FichiersFactory.fichiers.getData({entite_type : "Avis", entite_id : $scope.data.id})
		.$promise.then(function(data){
			$scope.fichiers = data;
			$scope.results = data;
		});
	};
};


var modalConfirmationDeleteAvisCtrl = function($scope, $modalInstance, 
		AvisFactory, id, title, message, ok) {
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
