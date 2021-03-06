'use strict';

services.factory('UtilisateursFactory', function ($resource, $rootScope) {
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
        fields: ["nom"],
        directions: ["DESC"]
	};

	var page = $resource('/ng_gst_pdg/web/utilisateurs/page', {}, {
		getData : { method: 'POST'}
	});
	var canEdit = false;
	var canView = false;
	if ($rootScope.utilisateurConnecte){
	 canEdit = ($rootScope.utilisateurConnecte.profil.droits[7]=='UTIL_E');
	 canView = ($rootScope.utilisateurConnecte.profil.droits[7]=='UTIL_L'||canEdit);
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
					$scope.utilisateur=response.data;
					$scope.totalServerItems=response.totalServerItems;
		         });			
			},
		page : page,
		titlemap : $resource('/ng_gst_pdg/web/utilisateurs/titlemap', {}, {
			getData : { method: 'GET', isArray: false}
		}),
		jsonschema :  $resource('/ng_gst_pdg/web/utilisateurs/jsonschema', {}, {
			getData : { method: 'GET'}
		}),
		detail :  $resource('/ng_gst_pdg/web/utilisateurs/detail/:id', {}, {
			getData : { method: 'GET', params: {id: '@id'}, isArray: false }
		}),
		formateurs :  $resource('/ng_gst_pdg/web/utilisateurs/formateurs/:search', {}, {
			getData : { method: 'GET', params: {search: '@search'}, isArray: true }
		}),
		create :  $resource('/ng_gst_pdg/web/utilisateurs/creation', {}, {
			doAction : { method: 'POST'}
		}),
		modify :  $resource('/ng_gst_pdg/web/utilisateurs/modification', {}, {
			doAction : { method: 'PUT'}
		}),
		delete :  $resource('/ng_gst_pdg/web/utilisateurs/suppression/:id', {}, {
			doAction : { method: 'DELETE', params: {id: '@id'} }
		})
	}
});





