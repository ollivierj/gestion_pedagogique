'use strict';

services.factory('ProfessionnelHomologuesFactory', function ($resource, $rootScope) {
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

	var page = $resource('/ng_gst_pdg/web/professionnelHomologues/page', {}, {
		getData : { method: 'POST'}
	});
	
	var canEdit = ($rootScope.utilisateurConnecte.profil.droits[10]=='PRF_HMG_E');
	var canView = ($rootScope.utilisateurConnecte.profil.droits[10]=='PRF_HMG_L'||canEdit);
	
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
					$scope.professionnelHomologues=response.data;
					$scope.totalServerItems=response.data.totalServerItems;
		         });			
			},
		page : page,
		jsonschema :  $resource('/ng_gst_pdg/web/professionnelHomologues/jsonschema', {}, {
			getData : { method: 'GET'}
		}),
		detail :  $resource('/ng_gst_pdg/web/professionnelHomologues/detail/:id', {}, {
			getData : { method: 'GET', params: {id: '@id'}, isArray: false }
		}),
		create :  $resource('/ng_gst_pdg/web/professionnelHomologues/creation', {}, {
			doAction : { method: 'POST'}
		}),
		modify :  $resource('/ng_gst_pdg/web/professionnelHomologues/modification', {}, {
			doAction : { method: 'PUT'}
		}),
		delete :  $resource('/ng_gst_pdg/web/professionnelHomologues/suppression/:id', {}, {
			doAction : { method: 'DELETE', params: {id: '@id'} }
		})
	}
});





