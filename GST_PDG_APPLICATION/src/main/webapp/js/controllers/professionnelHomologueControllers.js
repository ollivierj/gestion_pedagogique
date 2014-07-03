'use strict';

controllers
		.controller(
				'professionelHomologueesCtrl',
				function($scope, $modal, $log, ProfessionnelHomologuesFactory,
						homologationData) {
					$scope
							.$watch(
									function() {
										return ProfessionnelHomologuesFactory.data;
									},
									function(data) {
										$scope.professionnelHomologues = data.professionnelHomologues;
										$scope.pagingOptions = data.pagingOptions;
										$scope.totalItems = $scope.pagingOptions.totalItems;
									}, true);

					$scope.$watch('pagingOptions', function(newVal, oldVal) {
						if (newVal !== oldVal && newVal.page !== oldVal.page) {
							ProfessionnelHomologuesFactory.refreshData();
						}
					}, true);

					$scope.gridOptionsPersonnesHomologuees = {
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
						totalServerItems : 'totalItems',
						pagingOptions : $scope.pagingOptions
					};

					$scope.editRow = function(professionnelHomologue) {
						$scope
								.editerProfessionnelHomologue(professionnelHomologue.id);
					};

					$scope.viewRow = function(professionnelHomologue) {
						$scope
								.editerProfessionnelHomologue(professionnelHomologue.id);
					};
					
					$scope.removeRow = function(professionnelHomologue) {
						$scope.supprimerProfessionnelHomologue(professionnelHomologue.id);
					};

					$scope.editerProfessionnelHomologue = function(
							professionnelHomologueId) {
						var modalEdit = $modal
								.open({
									templateUrl : 'partials/professionnelHomologues/professionnelHomologuesFormulaire.html',
									controller : modalEditionProfessionnelHomologueCtrl,
									resolve : {
										professionnelHomologue : function(
												ProfessionnelHomologuesFactory) {
											return ProfessionnelHomologuesFactory.detail
													.getData({
														id : professionnelHomologueId
													}).$promise;
										},
										homologations : function(
												homologationData) {
											return homologationData.getAll()
													.query().$promise;
										}
									}
								});

						modalEdit.result.then(function(selectedItem) {
							ProfessionnelHomologuesFactory.refreshData();
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
										title : function() {return "Suppression professionnel homologué"},
										message : function() {return "Etes-vous sur de vouloir supprimer ce professionnel homologué ?"}
										
									}
								});
						modalDelete.result.then(function(selectedItem) {
							ProfessionnelHomologuesFactory.refreshData();
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};


					ProfessionnelHomologuesFactory.refreshData();
				});

var modalEditionProfessionnelHomologueCtrl = function($scope, $modalInstance,
		ProfessionnelHomologuesFactory, professionnelHomologue, homologations) {
	$scope.professionnelHomologue = professionnelHomologue;
	$scope.homologations = homologations;

	$scope.ok = function() {
		ProfessionnelHomologuesFactory.modify
				.doAction($scope.professionnelHomologue).$promise.then(
				function(response) {
					$modalInstance.close($scope.professionnelHomologue);
				}, function(reason) {
					alert('Failed: ' + reason);
				});
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};

var modalConfirmationDeleteProfessionnelHomologueCtrl = function($scope,
		$modalInstance, ProfessionnelHomologuesFactory, id, title, message) {
	$scope.id = id;
	$scope.title = title;
	$scope.message = message;
	
	$scope.ok = function() {
		ProfessionnelHomologuesFactory.delete
		.doAction({
			id : $scope.id
		}).$promise.then(
		function(response) {
			$modalInstance.close($scope.professionnelHomologue);
		}, function(reason) {
			alert('Failed: ' + reason);
		});
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};
