'use strict';

services.factory('AbsencesFactory', function ($resource, $rootScope) {
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

	var page = $resource('/ng_gst_pdg/web/absences/page', {}, {
		getData : { method: 'POST'}
	});
	var canEdit = false;
	var canView = false;
	if ($rootScope.utilisateurConnecte){
		canEdit = ($rootScope.utilisateurConnecte.profil.droits[4]=='ABS_E');
		canView = ($rootScope.utilisateurConnecte.profil.droits[4]=='ABS_L'||canEdit);
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
					$scope.absences=response.data;
					$scope.totalServerItems=response.totalServerItems;
		         });			
			},
		page : page,
		jsonschema :  $resource('/ng_gst_pdg/web/absences/jsonschema', {}, {
			getData : { method: 'GET'}
		}),
		detail :  $resource('/ng_gst_pdg/web/absences/detail/:id', {}, {
			getData : { method: 'GET', params: {id: '@id'}, isArray: false }
		}),
		create :  $resource('/ng_gst_pdg/web/absences/creation', {}, {
			doAction : { method: 'POST'}
		}),
		modify :  $resource('/ng_gst_pdg/web/absences/modification', {}, {
			doAction : { method: 'PUT'}
		}),
		test :  $resource('/ng_gst_pdg/web/stagiaires/autocomplete/:search', {}, {
			getData : { method: 'GET', params: {search: '@search'}, isArray:true}
		}),
		delete :  $resource('/ng_gst_pdg/web/absences/suppression/:id', {}, {
			doAction : { method: 'DELETE', params: {id: '@id'} }
		}),
		jour :  $resource('/ng_gst_pdg/web/absences/jour/:year/:month/:day', {}, {
			getData : { method: 'GET', params: {year : '@year', month : '@month', day : '@day'}, isArray: true }
		})
	}
});





