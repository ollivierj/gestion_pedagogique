'use strict';

services.factory('SalleFactory', function($resource, $filter, $rootScope) {

	var salle = {};
	
	salle.referenceSalle = $resource('/ng_gst_pdg/web/reservationSalles/salles', {}, {});
	
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

		
		var page = $resource('/ng_gst_pdg/web/salles/page', {}, {
			getData : { method: 'POST'}
		});
		
		var canEdit = ($rootScope.utilisateurConnecte.profil.droits[1]=='EVAL_E');
		var canView = ($rootScope.utilisateurConnecte.profil.droits[1]=='EVAL_L'||canEdit);
		
		return {
			canEdit : canEdit,
			canView : canView,
			pagingOptions : pagingOptions,
			filterOptions : filterOptions,
			sortOptions : sortOptions,
			salle : salle,
			refreshData : function($scope) {
					page.getData(
						{
						pagingOptions : $scope.pagingOptions,
						filterOptions : $scope.filterOptions,
						sortOptions : 	$scope.sortOptions
						}
					).$promise.then(function(response) {
						$scope.salles=response.data;
						$scope.totalServerItems=response.data.totalServerItems;
			         });			
				},
			page : page,
			titlemap : $resource('/ng_gst_pdg/web/salles/titlemap', {}, {
				getData : { method: 'GET', isArray: false}
			}),
			jsonschema :  $resource('/ng_gst_pdg/web/salles/jsonschema', {}, {
				getData : { method: 'GET'}
			}),
			detail :  $resource('/ng_gst_pdg/web/salles/detail/:id', {}, {
				getData : { method: 'GET', params: {id: '@id'}, isArray: false }
			}),
			create :  $resource('/ng_gst_pdg/web/salles/creation', {}, {
				doAction : { method: 'POST'}
			}),
			modify :  $resource('/ng_gst_pdg/web/salles/modification', {}, {
				doAction : { method: 'PUT'}
			}),
			delete :  $resource('/ng_gst_pdg/web/salles/suppression/:id', {}, {
				doAction : { method: 'DELETE', params: {id: '@id'} }
			})
		}
	
});
