'use strict';

services.factory('FichiersFactory', function ($resource) {
	/*var	pagingOptions = {
		pageSizes : [ 5, 10, 15, 25 ],
		pageSize : 10,
		currentPage : 1
	};*/
	
	var filterOptions = {
        filterText: "",
        useExternalFilter: true
	};
	/*
	var sortOptions = {
        fields: ["dateHeureDebutPassage"],
        directions: ["DESC"]
	};

	
	var page = $resource('/ng_gst_pdg/web/evaluations/page', {}, {
		getData : { method: 'POST'}
	});*/
	
	return {
		/*pagingOptions : pagingOptions,
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
					$scope.evaluations=response.data;
					$scope.totalServerItems=response.data.totalServerItems;
		         });			
			},
		page : page,*/
		filterOptions : filterOptions,
		fichiers : $resource('/ng_gst_pdg/web/fichiers/charger', {}, {
			getData : { method: 'GET', isArray: true}
		})
	}
});





