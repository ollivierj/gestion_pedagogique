'use strict';

controllers
		.controller(
				'professionelHomologueesCtrl',
				function($scope, $modal, $log, $timeout, ProfessionnelHomologuesFactory,
						homologationData) {
					$scope.pagingOptions = ProfessionnelHomologuesFactory.pagingOptions;		
					$scope.sortOptions = ProfessionnelHomologuesFactory.sortOptions;		
					$scope.filterOptions = ProfessionnelHomologuesFactory.filterOptions;
					
					$scope.professionnelHomologuesGridOptions = {
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
									field : 'dateNaissance',
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
						$scope.editerProfessionnelHomologue(professionnelHomologue.id);
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
									controller : modalEditionProfessionnelHomologueCtrl,
									resolve : {
										title : function() {return "Ajout d'un professionnel homologué";},
										professionnelHomologue : function(){ return {}},
										schema : function(ProfessionnelHomologuesFactory) {
											return ProfessionnelHomologuesFactory.jsonschema.getData().$promise;
										},
										ok : function() { return function(item){ return ProfessionnelHomologuesFactory.create.doAction(item);}}
									}
								});

						modalAdd.result.then(function(selectedItem) {
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
									controller : modalEditionProfessionnelHomologueCtrl,
									resolve : {
										title : function() {return "Edition d'un professionnel homologué";},
										professionnelHomologue : function(ProfessionnelHomologuesFactory) {
											return ProfessionnelHomologuesFactory.detail.getData({id : professionnelHomologueId}).$promise;
										},
										schema : function(ProfessionnelHomologuesFactory) {
											return ProfessionnelHomologuesFactory.jsonschema.getData().$promise;
										},
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

var modalEditionProfessionnelHomologueCtrl = function($scope, $modalInstance,
		ProfessionnelHomologuesFactory, title, professionnelHomologue, schema, ok) {
	$scope.title = title;
	$scope.data = professionnelHomologue;
	$scope.schema = schema;
	$scope.form = [
	            "nom",
	            "prenom",
	            "civilite",
	            "adresse1",
	            "adresse2",
	            "adresse3",
	            "ville",
	            "codePostal",
	            "formatedDateNaissance",
	            "codeRegion",
	            "telephoneFixe",
	            "telephonePortable",
	            "permis",
	            {
	            	  type: "actions",
	            	  items: [
	            	    { type: 'submit', title: 'Enregistrer' },
	            	    { type: 'button', title: 'Annuler', onClick: "cancel()" }
	            	  ]
	            }
	            ];
	$scope.decorator = 'bootstrap-decorator';
	$scope.submit =function(){
		if (generic.$valid){
			ok($scope.data).$promise.then(
					function(response) {
						$modalInstance.close(data);
					}, 
					function(reason) {
						alert('Echec: ' + reason);
					});
		}else {
			alert("La saisie est incorrecte");
		}

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
