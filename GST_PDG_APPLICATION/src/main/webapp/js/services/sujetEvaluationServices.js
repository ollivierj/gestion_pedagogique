'use strict';

services.factory('SujetEvaluationsFactory', function ($resource, $rootScope) {
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
        fields: ["lienSujet"],
        directions: ["DESC"]
	};

	
	var page = $resource('/ng_gst_pdg/web/sujetEvaluations/page', {}, {
		getData : { method: 'POST'}
	});
	var canEdit = false;
	var canView = false;
	if ($rootScope.utilisateurConnecte){
	 canEdit = ($rootScope.utilisateurConnecte.profil.droits[9]=='SUJ_EVAL_E');
	 canView = ($rootScope.utilisateurConnecte.profil.droits[9]=='SUJ_EVAL_L'||canEdit);
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
					$scope.sujetEvaluations=response.data;
					$scope.totalServerItems=response.totalServerItems;
		         });			
			},
		page : page,
		titlemap : $resource('/ng_gst_pdg/web/sujetEvaluations/titlemap', {}, {
			getData : { method: 'GET', isArray: false}
		}),
		jsonschema :  $resource('/ng_gst_pdg/web/sujetEvaluations/jsonschema', {}, {
			getData : { method: 'GET'}
		}),
		detail :  $resource('/ng_gst_pdg/web/sujetEvaluations/detail/:id', {}, {
			getData : { method: 'GET', params: {id: '@id'}, isArray: false }
		}),
		create :  $resource('/ng_gst_pdg/web/sujetEvaluations/creation', {}, {
			doAction : { method: 'POST'}
		}),
		modify :  $resource('/ng_gst_pdg/web/sujetEvaluations/modification', {}, {
			doAction : { method: 'PUT'}
		}),
		delete :  $resource('/ng_gst_pdg/web/sujetEvaluations/suppression/:id', {}, {
			doAction : { method: 'DELETE', params: {id: '@id'} }
		})
	}
});





