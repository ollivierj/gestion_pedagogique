'use strict';

services.factory('ProfilsFactory', function ($resource) {
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
        fields: ["code"],
        directions: ["DESC"]
	};
	
	var page = $resource('/ng_gst_pdg/web/profils/page', {}, {
		getData : { method: 'POST'}
	});
	
	return {
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
					$scope.profils=response.data;
					$scope.totalServerItems=response.data.totalServerItems;
		         });			
			},
		page : page,
		titlemap : $resource('/ng_gst_pdg/web/profils/titlemap', {}, {
			getData : { method: 'GET', isArray: false}
		}),
		jsonschema :  $resource('/ng_gst_pdg/web/profils/jsonschema', {}, {
			getData : { method: 'GET'}
		}),
		detail :  $resource('/ng_gst_pdg/web/profils/detail/:id', {}, {
			getData : { method: 'GET', params: {id: '@id'}, isArray: false }
		}),
		create :  $resource('/ng_gst_pdg/web/profils/creation', {}, {
			doAction : { method: 'POST'}
		}),
		modify :  $resource('/ng_gst_pdg/web/profils/modification', {}, {
			doAction : { method: 'PUT'}
		}),
		delete :  $resource('/ng_gst_pdg/web/profils/suppression/:id', {}, {
			doAction : { method: 'DELETE', params: {id: '@id'} }
		})
	}
});





