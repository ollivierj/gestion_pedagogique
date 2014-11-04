'use strict';

services.factory('StagiaireFactory', function ($resource, $rootScope) {
	
	var pagingOptions = {
			pageSizes : [5,10,15,20,25,30],
			pageSize : 10,
			currentPage : 1
	};
	
	var sortOptions = {
			fields: ["nom"],
			directions: ["ASC"]
	};
	
	var filterOptions = {
			filterText: "",
			useExternalFilter: false
	};
	
	var stagiaire = {};
	
	var readonly=true;
	
	var detail = $resource('/ng_gst_pdg/web/stagiaires/detail/:id', {}, {
		getData : { method: 'GET', params: {id: '@id'}, isArray: false }
	});
	
	var page = $resource('/ng_gst_pdg/web/stagiairesPromotions/page', {}, {
		getData : { method: 'POST'}
	});
	
	//Enregsitrement du stagiaire sélectionné
	var keepStagiaire = function (stagiaireSelected)  {
		stagiaire = stagiaireSelected;
	};
	
	var canEdit = ($rootScope.utilisateurConnecte.profil.droits[0]=='STG_E');
	var canView = ($rootScope.utilisateurConnecte.profil.droits[0]=='STG_L'||canEdit);
	
	//Retour de la factory avec ses variables
	return {
		canEdit : canEdit,
		canView : canView,
		pagingOptions	: pagingOptions,
		sortOptions 	: sortOptions,
		filterOptions	: filterOptions,
		stagiaire		: stagiaire,
		readonly		: readonly,
		keepStagiaire 	: keepStagiaire,
		page 			: page,
		detail			: detail,
		refreshData : function($scope) {
			page.getData(
				{
				pagingOptions : $scope.pagingOptions,
				filterOptions : $scope.filterOptions,
				sortOptions : 	$scope.sortOptions
				}
			).$promise.then(function(response) {
				$scope.stagiaires=response.data;
				$scope.totalServerItems=response.data.totalServerItems;
	         });			
		},
		dataAutocomplete : $resource('/ng_gst_pdg/web/stagiaires/autocomplete/:search', {}, {
			getData : { method: 'GET', params: {search: '@search'}, isArray: true }
		}),
		stagiaireOrPromotionAutocomplete : $resource('/ng_gst_pdg/web/stagiaires/stagiaireOrPromotionAutocomplete/:search', {}, {
			getData : { method: 'GET', params: {search: '@search'}, isArray: true }
		}),
		stagiaireAutocomplete : $resource('/ng_gst_pdg/web/stagiaires/stagiaireAutocomplete/:search', {}, {
			getData : { method: 'GET', params: {search: '@search'}, isArray: true }
		}),
		stagiaireOrPromotion : $resource('/ng_gst_pdg/web/stagiairesPromotions/stagiaires/:type/:id', {}, {
			getData : { method: 'GET', params: {type: '@type', id : '@id'}, isArray: true }
		})
	};
	
});

services.factory('stagiaireData', function($resource) {
	var stagiaireFactory = {};

	var stagiaireSelected;

	stagiaireFactory.set = function(stagiaire) {
		stagiaireSelected = stagiaire;		
	};

	stagiaireFactory.get = function() {
		return stagiaireSelected; 
	};

	stagiaireFactory.getAll = function() {
		return $resource('json/stagiairesLoad.json', {}, {
	 		query: { method: 'GET', isArray: true}
		 });
	};

	return stagiaireFactory;
});