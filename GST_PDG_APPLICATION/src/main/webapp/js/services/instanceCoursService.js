'use strict';

services.factory('InstanceCoursFactory', function ($resource, $rootScope) {
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

	
	var page = $resource('/ng_gst_pdg/web/instanceCours/page', {}, {
		getData : { method: 'POST'}
	});
	
	var canEdit = ($rootScope.utilisateurConnecte.profil.droits[1]=='AVIS_E');
	var canView = ($rootScope.utilisateurConnecte.profil.droits[1]=='AVIS_L'||canEdit);
	
	return {
		canEdit : canEdit,
		canView : canView,
		pagingOptions : pagingOptions,
		filterOptions : filterOptions,
		sortOptions : sortOptions,
		connectedUser : $rootScope.utilisateurConnecte,
		refreshData : function($scope) {
				page.getData(
					{
					pagingOptions : $scope.pagingOptions,
					filterOptions : $scope.filterOptions,
					sortOptions : 	$scope.sortOptions,
					connectedUser : $rootScope.utilisateurConnecte
					}
				).$promise.then(function(response) {
					$scope.instanceCours=response.data;
					$scope.totalServerItems=response.data.totalServerItems;
		         });			
			},
		page : page,
		titlemap : $resource('/ng_gst_pdg/web/instanceCours/titlemap', {}, {
			getData : { method: 'GET', isArray: false}
		}),
		jsonschema :  $resource('/ng_gst_pdg/web/instanceCours/jsonschema', {}, {
			getData : { method: 'GET'}
		}),
		detail :  $resource('/ng_gst_pdg/web/instanceCours/detail/:id', {}, {
			getData : { method: 'GET', params: {id: '@id'}, isArray: false }
		}),
		create :  $resource('/ng_gst_pdg/web/instanceCours/creation', {}, {
			doAction : { method: 'POST'}
		}),
		modify :  $resource('/ng_gst_pdg/web/instanceCours/modification', {}, {
			doAction : { method: 'PUT'}
		}),
		delete :  $resource('/ng_gst_pdg/web/instanceCours/suppression/:id', {}, {
			doAction : { method: 'DELETE', params: {id: '@id'} }
		})
	}
});





