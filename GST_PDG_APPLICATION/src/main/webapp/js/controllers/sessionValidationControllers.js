'use strict';

controllers
		.controller(
				'sessionValidationsCtrl',

				function($scope, $modal, $log, $timeout, SessionValidationsFactory, TitreProfessionnelsFactory) {
					$scope.pagingOptions = SessionValidationsFactory.pagingOptions;		
					$scope.sortOptions = SessionValidationsFactory.sortOptions;		
					$scope.filterOptions = SessionValidationsFactory.filterOptions;
					$scope.title = "SessionValidations";
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


					
					$scope.ajouterSessionValidation = function(
							sessionValidationId) {

						var modalAdd = $modal
								.open({
									templateUrl : 'partials/templates/inscription-form.html',
									controller : modalEditionSessionValidationCtrl,

									resolve : {
										title : function() {return "Ajout d'une session de validation";},
										readonly : function() {return false;},
										sessionValidation : function(){ return {}},
										titreProfessionnels : function(TitreProfessionnelsFactory){

											return TitreProfessionnelsFactory.titlemap.getData().$promise;
										},
										schema : function(SessionValidationsFactory) {

											return SessionValidationsFactory.jsonschema.getData().$promise;
										},



										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){ return SessionValidationsFactory.create.doAction(item);}}
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
									templateUrl : 'partials/templates/inscription-form.html',
									controller : modalEditionSessionValidationCtrl,

									resolve : {
										title : function() {return "Visualisation d'une session de validation";},
										readonly : function() {return true;},
										sessionValidation : function(SessionValidationsFactory) {

											return SessionValidationsFactory.detail.getData({id : sessionValidationId}).$promise;
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
									templateUrl : 'partials/templates/inscription-form.html',
									controller : modalEditionSessionValidationCtrl,

									resolve : {
										title : function() {return "Edition d'une session de validation";},
										readonly : function() {return false;},
										sessionValidation : function(SessionValidationsFactory) {

											return SessionValidationsFactory.detail.getData({id : sessionValidationId}).$promise;
										},
										titreProfessionnels : function(TitreProfessionnelsFactory){

											return TitreProfessionnelsFactory.titlemap.getData().$promise;
										},
										schema : function(SessionValidationsFactory) {




											return SessionValidationsFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){ return SessionValidationsFactory.modify.doAction(item);}}
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
										ok : function () { return function(id) {return SessionValidationsFactory.delete.doAction({id : id});};}
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

var modalEditionSessionValidationCtrl = function($scope, $modalInstance,
		SessionValidationsFactory, StagiaireFactory, onlyNumbersFilter, title, readonly, sessionValidation, titreProfessionnels, schema, ok, okTitle) {
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
			disabled : $scope.data.readonly,
			schema : { enum : $scope.titreProfessionnelsEnum},
			titleMap : $scope.titreProfessionnelsTitleMap

		}, 
		{
			key : "formatedDateDebut",
			disabled : $scope.data.readonly






		},
		{
			key : "formatedDateFin",

			disabled : $scope.data.readonly
		},
		{
			key : "lienModelesPublipostage",
		 	disabled : $scope.data.readonly


		},
		{
			key : "lienDocsGeneres",

		 	disabled : $scope.data.readonly
		},
		{
			key : "lienDocsCollectes",

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
	$scope.gridOptions = {
        data: 'data.sessionValidationStagiaires',

        selectedItems: $scope.stagiaireSelected,
        columnDefs : [
                {field:'stagiaire.nom', displayName:'Nom'},
                {field:'stagiaire.prenom', displayName:'Prénom'},
                {field:'stagiaire.codePromotion', displayName:'Promotion', cellTemplate: 'partials/templates/ng-grid_detailsPromotion.html'},
                {										

					displayName : 'Actions',
					cellTemplate : 'partials/templates/ng-grid_remove_action.html'
				}
                ],


        enablePaging: false,
        showFooter: false,
        multiSelect: false
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
				$scope.data.sessionValidationStagiaires.push({sessionValidation : sessionValidation, stagiaire : stagiaire});
			});
		});
	};
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
