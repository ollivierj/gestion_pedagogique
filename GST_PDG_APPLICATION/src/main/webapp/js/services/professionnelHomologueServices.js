'use strict';

services.factory('ProfessionnelHomologuesFactory', function ($resource) {
	var	pagingOptions = {
		pageSizes : [ 5, 10, 15, 25 ],
		pageSize : 10,
		currentPage : 1,
		totalItems : 0,
		sortColumnBy : "PRF_HMG_ID",
		sortDirectionBy : "DESC"
	};
	
	var page = $resource('/ng_gst_pdg/web/professionnelHomologues/page/:page/:pageSize/:totalItems/:sortColumnBy/:sortDirectionBy', {}, {
		getData : { method: 'GET', params: {page: '@page', pageSize: '@pageSize', sortColumnBy: '@sortColumnBy', sortDirectionBy : '@sortDirectionBy'} }
	});
	
	return {
		pagingOptions : pagingOptions,
		refreshData : function($scope) {
				page.getData(
					{
					page : $scope.pagingOptions.currentPage,
					pageSize : $scope.pagingOptions.pageSize,
					sortColumnBy : $scope.pagingOptions.sortColumnBy,
					sortDirectionBy : $scope.pagingOptions.sortDirectionBy
					}
				).$promise.then(function(response) {
					$scope.professionnelHomologues=response.data;
					$scope.totalItems=response.pager.totalItems;
		         });			
			},
		page : page,
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





