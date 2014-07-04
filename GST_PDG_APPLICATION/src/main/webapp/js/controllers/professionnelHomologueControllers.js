'use strict';

controllers
		.controller(
				'professionelHomologueesCtrl',
				function($scope, $modal, $log, ProfessionnelHomologuesFactory,
						homologationData) {
					$scope.pagingOptions = ProfessionnelHomologuesFactory.pagingOptions;		
					
					$scope.$watch(function() {
						return $scope.pagingOptions;
					},
					function(data){
						ProfessionnelHomologuesFactory.refreshData($scope);
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
									templateUrl : 'partials/professionnelHomologues/professionnelHomologuesFormulaire.html',
									controller : modalEditionProfessionnelHomologueCtrl,
									resolve : {
										professionnelHomologue : function(){ return {}},
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
									templateUrl : 'partials/professionnelHomologues/professionnelHomologuesFormulaire.html',
									controller : modalEditionProfessionnelHomologueCtrl,
									resolve : {
										professionnelHomologue : function(ProfessionnelHomologuesFactory) {
											return ProfessionnelHomologuesFactory.detail.getData({id : professionnelHomologueId}).$promise;
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
										title : function() {return "Suppression professionnel homologué"},
										message : function() {return "Etes-vous sur de vouloir supprimer ce professionnel homologué ?"},
										ok : function () { return function(item) {ProfessionnelHomologuesFactory.delete.doAction({id : item.id});}}
									}
								});
						modalDelete.result.then(function(selectedItem) {
							ProfessionnelHomologuesFactory.refreshData($scope);
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};
					
					ProfessionnelHomologuesFactory.refreshData($scope);
				});

var modalEditionProfessionnelHomologueCtrl = function($scope, $modalInstance,
		ProfessionnelHomologuesFactory, professionnelHomologue, ok) {
	$scope.professionnelHomologue = professionnelHomologue;
	$scope.ok =function(item){
		ok(item).$promise.then(
			function(response) {
				$modalInstance.close(item);
			}, 
			function(reason) {
				alert('Failed: ' + reason);
			});
	};
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};

var modalConfirmationDeleteProfessionnelHomologueCtrl = function($scope, $modalInstance, 
		ProfessionnelHomologuesFactory, id, title, message, ok) {
	$scope.id = id;
	$scope.title = title;
	$scope.message = message;
	$scope.ok =function(item){
		ok(item).$promise.then(
			function(response) {
				$modalInstance.close(item);
			}, 
			function(reason) {
				alert('Failed: ' + reason);
			});
	};
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};
