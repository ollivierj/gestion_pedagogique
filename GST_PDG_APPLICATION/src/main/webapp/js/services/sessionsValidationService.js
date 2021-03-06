'use strict';

services.factory('SessionValidationsFactory', function ($resource, $rootScope) {
	var	pagingOptions = {
		pageSizes : [ 5, 10, 15, 25 ],
		pageSize : 10,
		currentPage : 1
	};
	
	var filterOptions = {
        filterText: "",
        useExternalFilter: true
	};
	
	var sortOptions = {
        fields: ["dateDebut"],
        directions: ["DESC"]
	};

	
	var page = $resource('/ng_gst_pdg/web/sessionValidations/page', {}, {
		getData : { method: 'POST'}
	});
	var canEdit = false;
	var canView = false;
	if ($rootScope.utilisateurConnecte){
	 canEdit = ($rootScope.utilisateurConnecte.profil.droits[2]=='SES_VAL_E');
	 canView = ($rootScope.utilisateurConnecte.profil.droits[2]=='SES_VAL_L'||canEdit);
	}
	return {
		canEdit : canEdit,
		canView : canView,
		pagingOptions : pagingOptions,
		filterOptions : filterOptions,
		sortOptions : sortOptions,
		refreshData : function($scope) {
				page.getData(
					{
					pagingOptions : $scope.pagingOptions,
					filterOptions : $scope.filterOptions,
					sortOptions : 	$scope.sortOptions
					}
				).$promise.then(function(response) {
					$scope.sessionValidations=response.data;
					$scope.totalServerItems=response.totalServerItems;
		         });			
			},
		page : page,
		titlemap : $resource('/ng_gst_pdg/web/sessionValidations/titlemap', {}, {
			getData : { method: 'GET', isArray: false}
		}),
		jsonschema :  $resource('/ng_gst_pdg/web/sessionValidations/jsonschema', {}, {
			getData : { method: 'GET'}
		}),
		detail :  $resource('/ng_gst_pdg/web/sessionValidations/detail/:id', {}, {
			getData : { method: 'GET', params: {id: '@id'}, isArray: false }
		}),
		create :  $resource('/ng_gst_pdg/web/sessionValidations/creation', {}, {
			doAction : { method: 'POST'}
		}),
		modify :  $resource('/ng_gst_pdg/web/sessionValidations/modification', {}, {
			doAction : { method: 'PUT'}
		}),
		delete :  $resource('/ng_gst_pdg/web/sessionValidations/suppression/:id', {}, {
			doAction : { method: 'DELETE', params: {id: '@id'} }
		}),
		instance :  $resource('/ng_gst_pdg/web/sessionValidations/instance/:id', {}, {
			getData : { method: 'GET', params: {id: '@id'}, isArray: false },
			saveData : { method : 'POST' }
		}),
		instanceRefs : $resource('/ng_gst_pdg/web/instanceSessionValidation', {}, {
			getAll : { method : 'POST', isArray: true }
		})
	}
});





