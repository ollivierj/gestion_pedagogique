'use strict';

services.factory('EvaluationsFactory', function ($resource, $rootScope) {
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

	
	var page = $resource('/ng_gst_pdg/web/evaluations/page', {}, {
		getData : { method: 'POST'}
	});
	var canEdit = false;
	var canView = false;
	if ($rootScope.utilisateurConnecte){
	 canEdit = ($rootScope.utilisateurConnecte.profil.droits[1]=='EVAL_E');
	 canView = ($rootScope.utilisateurConnecte.profil.droits[1]=='EVAL_L'||canEdit);
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
					$scope.evaluations=response.data;
					$scope.totalServerItems=response.totalServerItems;
		         });			
			},
		page : page,
		titlemap : $resource('/ng_gst_pdg/web/evaluations/titlemap', {}, {
			getData : { method: 'GET', isArray: false}
		}),
		jsonschema :  $resource('/ng_gst_pdg/web/evaluations/jsonschema', {}, {
			getData : { method: 'GET'}
		}),
		detail :  $resource('/ng_gst_pdg/web/evaluations/detail/:id', {}, {
			getData : { method: 'GET', params: {id: '@id'}, isArray: false }
		}),
		create :  $resource('/ng_gst_pdg/web/evaluations/creation', {}, {
			doAction : { method: 'POST'}
		}),
		modify :  $resource('/ng_gst_pdg/web/evaluations/modification', {}, {
			doAction : { method: 'PUT'}
		}),
		delete :  $resource('/ng_gst_pdg/web/evaluations/suppression/:id', {}, {
			doAction : { method: 'DELETE', params: {id: '@id'} }
		}),
		instance :  $resource('/ng_gst_pdg/web/evaluations/instance', {}, {
			saveData : { method : 'POST' }
		}),
		instanceRefs : $resource('/ng_gst_pdg/web/instanceEvaluation', {}, {
			getAll : { method : 'POST', isArray: true }
		})
	}
});





