'use strict';

services.factory('AvisFactory', function ($resource, $rootScope) {
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
        fields: ["dateHeureDebutPassage"],
        directions: ["DESC"]
	};
	
	var instanceCours = {};
	
	var readonly=true;
	
	var page = $resource('/ng_gst_pdg/web/avis/page', {}, {
		getData : { method: 'POST'}
	});
	var canEdit = false;
	var canView = false;
	if ($rootScope.utilisateurConnecte){
	 canEdit = ($rootScope.utilisateurConnecte.profil.droits[6]=='AVIS_E');
	 canView = ($rootScope.utilisateurConnecte.profil.droits[6]=='AVIS_L'||canEdit);
	}
	return {
		canEdit : canEdit,
		canView : canView,
		pagingOptions : pagingOptions,
		filterOptions : filterOptions,
		sortOptions : sortOptions,
		connectedUser : $rootScope.utilisateurConnecte,
		instanceCours: instanceCours,
		readonly		: readonly,
		refreshData : function($scope) {
				page.getData(
					{
					pagingOptions : $scope.pagingOptions,
					filterOptions : $scope.filterOptions,
					sortOptions : 	$scope.sortOptions,
					connectedUser : $rootScope.utilisateurConnecte,
					id : instanceCours.id
					}
				).$promise.then(function(response) {
					$scope.avis=response.data;
					$scope.totalServerItems=response.totalServerItems;
		         });			
			},
		page : page,
		titlemap : $resource('/ng_gst_pdg/web/avis/titlemap', {}, {
			getData : { method: 'GET', isArray: false}
		}),
		jsonschema :  $resource('/ng_gst_pdg/web/avis/jsonschema', {}, {
			getData : { method: 'GET'}
		}),
		detail :  $resource('/ng_gst_pdg/web/avis/detail/:id', {}, {
			getData : { method: 'GET', params: {id: '@id'}, isArray: false }
		}),
		create :  $resource('/ng_gst_pdg/web/avis/creation', {}, {
			doAction : { method: 'POST'}
		}),
		modify :  $resource('/ng_gst_pdg/web/avis/modification', {}, {
			doAction : { method: 'PUT'}
		}),
		delete :  $resource('/ng_gst_pdg/web/avis/suppression/:id', {}, {
			doAction : { method: 'DELETE', params: {id: '@id'} }
		})
	}
});





