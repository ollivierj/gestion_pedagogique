'use strict';
controllers
	.controller(
		'gestionUtilisateursCtrl',
		function($scope, $modal, $log, $timeout, UtilisateurFactory) {
			$scope.pagingOptions = UtilisateurFactory.pagingOptions;		
			$scope.sortOptions = UtilisateurFactory.sortOptions;		
			$scope.filterOptions = UtilisateurFactory.filterOptions;
			$scope.title = "Utilisateur";
			$scope.gridOptions = {
				data : 'utilisateur',
				multiSelect : false,
				columnDefs : [
						{
							field : 'civilite',
							displayName : 'Civilité'
						},
						{
							field : 'nom',
							displayName : 'Nom'
						},
						{
							field : 'prenom',
							displayName : 'Prénom'
						},
						{
							field : 'fonction',
							displayName : 'Fonction'
						},
						
						{
							field : 'telephoneFixe',
							displayName : 'Téléphone fixe'
						},
						{
							field : 'telephonePortable',
							displayName : 'Téléphone portable'
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

			$scope.editRow = function(utilisateur) {
				$scope.editerUtilisateur(utilisateur.id);
			};

			$scope.viewRow = function(utilisateur) {
				$scope.visualiserUtilisateur(utilisateur.id);
			};
			
			$scope.removeRow = function(utilisateur) {
				$scope.supprimerUtilisateur(utilisateur.id);
			};
			
			$scope.afficherFenetreEdition = function(){
				$scope.ajouterUtilisateur();
			}
			
			$scope.ajouterUtilisateur = function(
					utilisateurId) {
				var modalAdd = $modal
						.open({
							templateUrl : 'partials/templates/form.html',
							controller : modalUtilisateurCtrl,
							resolve : {
								title : function() {return "Ajout d'un utilisateur";},
								utilisateur : function(){ return {}},
								readonly : function() {return false;},
								schema : function(UtilisateurFactory) {
									return UtilisateurFactory.jsonschema.getData().$promise;
								},
								okTitle : function() {return "Enregistrer";},
								ok : function() { return function(item){ return UtilisateurFactory.create.doAction(item);}}
							}
						});

				modalAdd.result.then(function(selectedItem) {
					UtilisateurFactory.refreshData($scope);
				}, function() {
					$log.info('Modal dismissed at: ' + new Date());
				});
			};

			$scope.visualiserUtilisateur = function(
					utilisateurId) {
				var modalEdit = $modal
						.open({
							templateUrl : 'partials/templates/form.html',
							controller : modalUtilisateurCtrl,
							resolve : {
								title : function() {return "Visualisation d'un utilisateur";},
								readonly : function() {return true;},
								utilisateur : function(UtilisateurFactory) {
									return UtilisateurFactory.detail.getData({id : utilisateurId}).$promise;
								},
								schema : function(UtilisateurFactory) {
									return UtilisateurFactory.jsonschema.getData().$promise;
								},
								okTitle : function() {return "Fermer";},
								ok : function() { return function(item){ return item;}}
							}
						});

				modalEdit.result.then(function(selectedItem) {
					UtilisateurFactory.refreshData($scope);
				}, function() {
					$log.info('Modal dismissed at: ' + new Date());
				});
			};
			
			$scope.editerUtilisateur = function(
					utilisateurId) {
				var modalEdit = $modal
						.open({
							templateUrl : 'partials/templates/form.html',
							controller : modalUtilisateurCtrl,
							resolve : {
								title : function() {return "Edition d'un utilisateur";},
								readonly : function() {return false;},
								utilisateur : function(UtilisateurFactory) {
									return UtilisateurFactory.detail.getData({id : utilisateurId}).$promise;
								},
								schema : function(UtilisateurFactory) {
									return UtilisateurFactory.jsonschema.getData().$promise;
								},
								okTitle : function() {return "Enregistrer";},
								ok : function() { return function(item){ return UtilisateurFactory.modify.doAction(item);}}
							}
						});

				modalEdit.result.then(function(selectedItem) {
					UtilisateurFactory.refreshData($scope);
				}, function() {
					$log.info('Modal dismissed at: ' + new Date());
				});
			};
			
			$scope.supprimerUtilisateur = function(
					utilisateurId) {
				var modalDelete = $modal
						.open({
							templateUrl : 'partials/templates/dialog.html',
							controller : modalConfirmationDeleteUtilisateurCtrl,
							resolve : {
								id : function() {return utilisateurId},
								title : function() {return "Suppression utilisateur";},
								message : function() {return "Etes-vous sur de vouloir supprimer cet utilisateur ?";},
								ok : function () { return function(id) {return UtilisateurFactory.delete.doAction({id : id});};}
							}
						});
				modalDelete.result.then(function(selectedItem) {
					UtilisateurFactory.refreshData($scope);
				}, function() {
					$log.info('Modal dismissed at: ' + new Date());
				});
			};
			
			$scope.$watch('pagingOptions', function (newVal, oldVal) {
		        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
		        	UtilisateurFactory.refreshData($scope);
		        }
		    }, true);

			$scope.$watch('filterOptions', function (newVal, oldVal) {
		        if (newVal !== oldVal) {
		        	if ($scope.timer) {
	                    $timeout.cancel($scope.timer);
	                }
		        	$scope.timer = $timeout(function () {
		        		UtilisateurFactory.refreshData($scope);
	                }, 500);
		        }
		    }, true);

		    $scope.$watch('sortOptions', function (newVal, oldVal) {
		        if (newVal !== oldVal) {
		        	UtilisateurFactory.refreshData($scope);
		        }
		    }, true);
			
		    UtilisateurFactory.refreshData($scope);
		});

var modalUtilisateurCtrl = function($scope, $modalInstance,
UtilisateurFactory, title, readonly, utilisateur, schema, ok, okTitle) {
$scope.title = title;
$scope.data = utilisateur;
$scope.data.readonly = readonly;
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
	             		key : "fonction",
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
	        title: "Autre",
	        items: 	[
					{
						key: "motpasse",
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

var modalConfirmationDeleteUtilisateurCtrl = function($scope, $modalInstance, 
UtilisateurFactory, id, title, message, ok) {
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