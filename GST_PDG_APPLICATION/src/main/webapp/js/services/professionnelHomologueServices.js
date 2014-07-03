'use strict';

services.factory('ProfessionnelHomologuesFactory', function ($resource) {
	var data = {
			professionnelHomologues : null, 
			pagingOptions : {
				pageSizes : [ 5, 10, 15, 25 ],
				pageSize : 10,
				page : 1,
				totalItems : 0,
				sortColumnBy : "PRF_HMG_ID",
				sortDirectionBy : "DESC"
			}
	};
	var page = $resource('/ng_gst_pdg/web/professionnelHomologues/page/:page/:pageSize/:totalItems/:sortColumnBy/:sortDirectionBy', {}, {
		getData : { method: 'GET', params: {page: data.pagingOptions.page, pageSize: data.pagingOptions.pageSize, totalItems : data.pagingOptions.totalItems, sortColumnBy: data.pagingOptions.sortColumnBy, sortDirectionBy : data.pagingOptions.sortDirectionBy} }
	});
	return {
		data : data,
		initializeData : function(){
			if (null==professionnelHomologues){
				refreshData();
			}
		},
		refreshData : function() {
				page.getData(
					{
					page : data.pagingOptions.page,
					pageSize : data.pagingOptions.pageSize,
					totalItems : data.pagingOptions.totalItems,
					sortColumnBy : data.pagingOptions.sortColumnBy,
					sortDirectionBy : data.pagingOptions.sortDirectionBy
					}
				).$promise.then(function(response) {
					data.professionnelHomologues=response.data;
					data.pagingOptions=response.pager;
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





