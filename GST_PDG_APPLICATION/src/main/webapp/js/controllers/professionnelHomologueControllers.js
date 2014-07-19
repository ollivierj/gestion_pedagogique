'use strict';

controllers
		.controller(
				'professionelHomologuesCtrl',
				function($scope, $modal, $log, $timeout, ProfessionnelHomologuesFactory, TitreProfessionnelsFactory) {
					$scope.pagingOptions = ProfessionnelHomologuesFactory.pagingOptions;		
					$scope.sortOptions = ProfessionnelHomologuesFactory.sortOptions;		
					$scope.filterOptions = ProfessionnelHomologuesFactory.filterOptions;
					$scope.title = "Professionnels homologués";
					$scope.gridOptions = {
						data : 'professionnelHomologues',
						multiSelect : false,
						columnDefs : [
								{
									field : 'nom',
									displayName : 'Nom'
								},
								{
									field : 'prenom',
									displayName : 'Prénom'
								},
								{
									field : 'civilite',
									displayName : 'Civilité'
								},
								{
									field : 'formatedDateNaissance',
									cellFilter: 'date:\'dd/MM/yyyy\'',
									displayName : 'Date de naissance'
								},
								{
									field : 'adresse',
									displayName : 'Adresse'
								},
								{
									field : 'codePostal',
									displayName : 'Code postal'
								},
								{
									field : 'ville',
									displayName : 'Ville'
								},
								{
									field : 'email',
									displayName : 'E-mail'
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

					$scope.editRow = function(professionnelHomologue) {
						$scope.editerProfessionnelHomologue(professionnelHomologue.id);
					};

					$scope.viewRow = function(professionnelHomologue) {
						$scope.visualiserProfessionnelHomologue(professionnelHomologue.id);
					};
					
					$scope.removeRow = function(professionnelHomologue) {
						$scope.supprimerProfessionnelHomologue(professionnelHomologue.id);
					};
					
					$scope.afficherFenetreEdition = function(){
						$scope.ajouterProfessionnelHomologue();
					}
					
					$scope.ajouterProfessionnelHomologue = function(
							professionnelHomologueId) {
						var modalAdd = $modal
								.open({
									templateUrl : 'partials/templates/form.html',
									controller : modalProfessionnelHomologueCtrl,
									resolve : {
										title : function() {return "Ajout d'un professionnel homologué";},
										readonly : function() {return false;},
										professionnelHomologue : function(){ return {}},
										titreProfessionnels : function(TitreProfessionnelsFactory){
											return TitreProfessionnelsFactory.titlemap.getData().$promise;
										},
										schema : function(ProfessionnelHomologuesFactory) {
											return ProfessionnelHomologuesFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){ return ProfessionnelHomologuesFactory.create.doAction(item);}}
									}
								});

						modalAdd.result.then(function(selectedItem) {
							ProfessionnelHomologuesFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					$scope.visualiserProfessionnelHomologue = function(
							professionnelHomologueId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/templates/form.html',
									controller : modalProfessionnelHomologueCtrl,
									resolve : {
										title : function() {return "Visualisation d'un professionnel homologué";},
										readonly : function() {return true;},
										professionnelHomologue : function(ProfessionnelHomologuesFactory) {
											return ProfessionnelHomologuesFactory.detail.getData({id : professionnelHomologueId}).$promise;
										},
										titreProfessionnels : function(TitreProfessionnelsFactory){
											return TitreProfessionnelsFactory.titlemap.getData().$promise;
										},
										schema : function(ProfessionnelHomologuesFactory) {
											return ProfessionnelHomologuesFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Fermer";},
										ok : function() { return function(item){ return item;}}
									}
								});

						modalEdit.result.then(function(selectedItem) {
							ProfessionnelHomologuesFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.editerProfessionnelHomologue = function(
							professionnelHomologueId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/templates/form.html',
									controller : modalProfessionnelHomologueCtrl,
									resolve : {
										title : function() {return "Edition d'un professionnel homologué";},
										readonly : function() {return false;},
										professionnelHomologue : function(ProfessionnelHomologuesFactory) {
											return ProfessionnelHomologuesFactory.detail.getData({id : professionnelHomologueId}).$promise;
										},
										titreProfessionnels : function(TitreProfessionnelsFactory){
											return TitreProfessionnelsFactory.titlemap.getData().$promise;
										},
										schema : function(ProfessionnelHomologuesFactory) {
											return ProfessionnelHomologuesFactory.jsonschema.getData().$promise;
										},
										okTitle : function() {return "Enregistrer";},
										ok : function() { return function(item){ return ProfessionnelHomologuesFactory.modify.doAction(item);}}
									}
								});

						modalEdit.result.then(function(selectedItem) {
							ProfessionnelHomologuesFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.supprimerProfessionnelHomologue = function(
							professionnelHomologueId) {
						var modalDelete = $modal
								.open({
									templateUrl : 'partials/templates/dialog.html',
									controller : modalConfirmationDeleteProfessionnelHomologueCtrl,
									resolve : {
										id : function() {return professionnelHomologueId},
										title : function() {return "Suppression professionnel homologué";},
										message : function() {return "Etes-vous sur de vouloir supprimer ce professionnel homologué ?";},
										ok : function () { return function(id) {return ProfessionnelHomologuesFactory.delete.doAction({id : id});};}
									}
								});
						modalDelete.result.then(function(selectedItem) {
							ProfessionnelHomologuesFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					$scope.$watch('pagingOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
				        	ProfessionnelHomologuesFactory.refreshData($scope);
				        }
				    }, true);

					$scope.$watch('filterOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	if ($scope.timer) {
			                    $timeout.cancel($scope.timer);
			                }
				        	$scope.timer = $timeout(function () {
			                    ProfessionnelHomologuesFactory.refreshData($scope);
			                }, 500);
				        }
				    }, true);

				    $scope.$watch('sortOptions', function (newVal, oldVal) {
				        if (newVal !== oldVal) {
				        	ProfessionnelHomologuesFactory.refreshData($scope);
				        }
				    }, true);
					
					ProfessionnelHomologuesFactory.refreshData($scope);
				});

var modalProfessionnelHomologueCtrl = function($scope, $modalInstance,
		ProfessionnelHomologuesFactory, onlyNumbersFilter, title, readonly, professionnelHomologue, titreProfessionnels, schema, ok, okTitle) {
	$scope.title = title;
	$scope.data = professionnelHomologue;
	$scope.data.readonly = readonly;
	$scope.titreProfessionnelsTitleMap = titreProfessionnels;
	$scope.titreProfessionnelsEnum = onlyNumbersFilter(Object.keys($scope.titreProfessionnelsTitleMap)),
	$scope.okTitle = okTitle;
	$scope.ok = ok;
	$scope.schema = schema;
	$scope.form = 
		[
		{
		    type: "tabs",
		    tabs: 
		    	[
			    {
			      title: "Etat civil",
			      items: 	[
			             	{
			             		key : "civilite",
			             		disabled : $scope.data.readonly
			             	},
			             	{
			             		key : "nom",
			             		disabled : $scope.data.readonly
			             	},
			             	{
			             		key : "prenom",
			             		disabled : $scope.data.readonly
			             	},
			             	{
			             		key : "formatedDateNaissance",
			             		disabled : $scope.data.readonly
			             	}
				            ]
			    },
			    {
				      title: "Adresse",
				      items: 	[
								{
									key : "adresse1",
									disabled : $scope.data.readonly
								},
								{
									key : "adresse2",
									disabled : $scope.data.readonly
								},
								{
									key : "adresse3",
									disabled : $scope.data.readonly
								},
								{
									key : "ville",
									disabled : $scope.data.readonly
								},
								{
									key : "codePostal",
									disabled : $scope.data.readonly
								},
								{
									key : "codeRegion",
									disabled : $scope.data.readonly
								}					            
								]
				    },
			    {
			        title: "Contact",
			        items: 	[
							{
								key : "email",
								disabled : $scope.data.readonly
							},
							{
								key : "telephoneFixe",
								disabled : $scope.data.readonly
							},
							{
								key : "telephonePortable",
								disabled : $scope.data.readonly
							}
			            	]
			    },
			    {
			    	title: "Homologations",
			    	items :
			    	[
			    	{
			    	  key: "homologations",
			    	  disabled : $scope.data.readonly,
		    	      add: "Ajouter une homologation",
		    	      items: [
						{
							title : "Titre professionnel",
							key: "homologations[].titreProfessionnel.id",
							type : "select",
							disabled : $scope.data.readonly,
							schema : { enum : $scope.titreProfessionnelsEnum},
							titleMap : $scope.titreProfessionnelsTitleMap
						},
						{
							key: "homologations[].formatedDateDebut",
							disabled : $scope.data.readonly
						},
						{
							key: "homologations[].formatedDateFin",
							disabled : $scope.data.readonly
						}
		    	      ]
		    	}
			    ]
			    },
			    {
			        title: "Autre",
			        items: 	[
							{
								key: "permis",
								disabled : $scope.data.readonly
							}			            	
							]
			    }
			    ]
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
};

var modalConfirmationDeleteProfessionnelHomologueCtrl = function($scope, $modalInstance, 
		ProfessionnelHomologuesFactory, id, title, message, ok) {
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
