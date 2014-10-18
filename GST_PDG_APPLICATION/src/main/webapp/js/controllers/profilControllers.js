﻿'use strict';
controllers.
	controller(
		'profilsCtrl', 
		function($scope, $modal, $log, $timeout, toaster, ProfilsFactory) {
			$scope.pagingOptions = ProfilsFactory.pagingOptions;		
			$scope.sortOptions = ProfilsFactory.sortOptions;		
			$scope.filterOptions = ProfilsFactory.filterOptions;
			$scope.title = "Profils";
			$scope.gridOptions = {
				data : 'profils',
				multiSelect : false,
				columnDefs : [
						{
							field : 'code',
							displayName : 'code'
						},{
							field : 'libelle',
							displayName : 'Libelle'
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
			
			$scope.droit = function(droit) {
				console.log(droit);
			};

			$scope.editRow = function(profil) {
				$scope.editerProfil(profil.id);
			};

			$scope.viewRow = function(profil) {
				$scope.visualiserProfil(profil.id);
			};
			
			$scope.removeRow = function(profil) {
				$scope.supprimerProfil(profil.id);
			};
			
			$scope.afficherFenetreEdition = function(){
				$scope.ajouterProfil();
			}
			
			$scope.ajouterProfil = function(
					profilId) {
				var modalAdd = $modal
						.open({
							templateUrl : 'partials/profil/profil.html',
							controller : modalProfilCtrl,
							resolve : {
								title : function() {return "Ajout d'un profil";},
								profil : function(){ return {}},
								readonly : function() {return false;},
								affprofilList : function() {return false;},
								schema : function(ProfilsFactory) {
									return ProfilsFactory.jsonschema.getData().$promise;
								},
								profils :  function(ProfilsFactory){
									return ProfilsFactory.titlemap.getData().$promise;
								},
								okTitle : function() {return "Enregistrer";},
								ok : function() { return function(item){ return ProfilsFactory.create.doAction(
										function(success) {
								    		toaster.pop('success', null, "Profil enregistré");
										},
										function(error) {
											toaster.pop('error', null, error.message);
										}
								);}}
							}
						});

				modalAdd.result.then(function(selectedItem) {
					ProfilsFactory.refreshData($scope);
				}, function() {
					$log.info('Modal dismissed at: ' + new Date());
				});
			};

			$scope.visualiserProfil = function(
					profilId) {
				var modalEdit = $modal
						.open({
							templateUrl : 'partials/profil/profil.html',
							controller : modalProfilCtrl,
							resolve : {
								title : function() {return "Visualisation d'un profil";},
								readonly : function() {return true;},
								affprofilList : function() {return false;},
								profil : function(ProfilsFactory) {
									return ProfilsFactory.detail.getData({id : profilId}).$promise;
								},
								profils : function(ProfilsFactory){
									return ProfilsFactory.titlemap.getData().$promise;
								},
								schema : function(ProfilsFactory) {
									return ProfilsFactory.jsonschema.getData().$promise;
								},
								okTitle : function() {return "Fermer";},
								ok : function() { return function(item){ return item;}}
							}
						});

				modalEdit.result.then(function(selectedItem) {
					ProfilsFactory.refreshData($scope);
				}, function() {
					$log.info('Modal dismissed at: ' + new Date());
				});
			};
			
			$scope.editerProfil = function(
					profilId) {
				var modalEdit = $modal
						.open({
							templateUrl : 'partials/profil/profil.html',
							controller : modalProfilCtrl,
							resolve : {
								title : function() {return "Edition d'un profil";},
								readonly : function() {return false;},
								affprofilList : function() {return true;},
								profil : function(ProfilsFactory) {
									return ProfilsFactory.detail.getData({id : profilId}).$promise;
								},
								profils : function(ProfilsFactory){
									return ProfilsFactory.titlemap.getData().$promise;
								},
								schema : function(ProfilsFactory) {
									return ProfilsFactory.jsonschema.getData().$promise;
								},
								okTitle : function() {return "Enregistrer";},
								ok : function() { return function(item){ return ProfilsFactory.modify.doAction(
										item,
										function(success) {
								    		toaster.pop('success', null, "Profil enregistré");
										},
										function(error) {
											toaster.pop('error', null, error.message);
										}		
								);}}
							}
						});

				modalEdit.result.then(function(selectedItem) {
					ProfilsFactory.refreshData($scope);
				}, function() {
					$log.info('Modal dismissed at: ' + new Date());
				});
			};
			
			$scope.supprimerProfil = function(
					profilId) {
				var modalDelete = $modal
						.open({
							templateUrl : 'partials/templates/dialog.html',
							controller : modalConfirmationDeleteProfilCtrl,
							resolve : {
								id : function() {return profilId},
								title : function() {return "Suppression profil";},
								message : function() {return "Etes-vous sur de vouloir supprimer ce profil ?";},
								ok : function () { return function(id) {return ProfilsFactory.delete.doAction(
										{id : id},
										function(success) {
								    		toaster.pop('warning', null, "Profil supprimé");
										},
										function(error) {
											toaster.pop('error', null, error.message);
										}
								);};}
							}
						});
				modalDelete.result.then(function(selectedItem) {
					ProfilsFactory.refreshData($scope);
				}, function() {
					$log.info('Modal dismissed at: ' + new Date());
				});
			};
			
			$scope.$watch('pagingOptions', function (newVal, oldVal) {
		        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
		        	ProfilsFactory.refreshData($scope);
		        }
		    }, true);

			$scope.$watch('filterOptions', function (newVal, oldVal) {
		        if (newVal !== oldVal) {
		        	if ($scope.timer) {
	                    $timeout.cancel($scope.timer);
	                }
		        	$scope.timer = $timeout(function () {
		        		ProfilsFactory.refreshData($scope);
	                }, 500);
		        }
		    }, true);

		    $scope.$watch('sortOptions', function (newVal, oldVal) {
		        if (newVal !== oldVal) {
		        	ProfilsFactory.refreshData($scope);
		        }
		    }, true);
			
		    ProfilsFactory.refreshData($scope);
		});

var modalProfilCtrl = function($scope, $modalInstance,
		ProfilsFactory, onlyStringsFilter, onlyNumbersFilter, title, readonly, affprofilList, profil, profils, schema, ok, okTitle) {
		$scope.title = title;
		$scope.data = profil;
		$scope.data.readonly = readonly;
		$scope.affprofilList=affprofilList;
		$scope.profilsTitleMap = profils;
		$scope.profilsEnum = onlyNumbersFilter(Object.keys($scope.profilsTitleMap)),
		$scope.okTitle = okTitle;
		$scope.ok = ok;
		$scope.schema = schema;
		if (null==$scope.data.droits){
			$scope.data.droits=
				[
				'STG_A',
				'EVAL_A',
				'SES_VAL_A',
				'RES_SALLE_A',
				'ABS_A',
				'ECH_A',
				'AVIS_A',
				'UTIL_A',
				'PRF_A',
				'SUJ_EVAL_A',
				'PRF_HMG_A',
				'TR_PRF_A',
				];
		}
		$scope.form = 
			[
			    
         	{
         		key : "code",
         		disabled : $scope.data.readonly
         	},
         	{
         		key : "libelle",
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
		$scope.decorator = 'bootstrap-decorator';
		$scope.submit =function(){
			 $scope.$broadcast('schemaFormValidate');
				if ($scope.form.profil.$valid) {
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
		};

		var modalConfirmationDeleteProfilCtrl = function($scope, $modalInstance, 
		ProfilsFactory, id, title, message, ok) {
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