'use strict';

services.factory('StagiaireFactory', function ($resource) {
	
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
	
	//Création de la variable ressource qui appel le service REST
	var data = $resource('/ng_gst_pdg/web/stagiairesPromotions/page', {}, {
		load : {method:'POST'}
	});

	var detail = $resource('/ng_gst_pdg/web/stagiaires/detail/:id');
	
	//Création de la méthode de récupération des données avec pagination
	var getData = function (pagingOptionsIn, sortOptionsIn, filterOptionsIn) {
		//Si aucune donnée du scope n'a été modifiée, onconserve les données initiales du service
		if (pagingOptionsIn != null && sortOptionsIn != null && filterOptionsIn != null) {
			//Enregistrement des données
			pagingOptions = pagingOptionsIn;
			sortOptions = sortOptionsIn;
			filterOptions = filterOptionsIn;
		}
		
		//Création d'une promise qui sera utilisée dans le controller
		//Utilisation de la variable qui contient l'appel au service
		var promise = data.load (
				{
					pagingOptions 	: pagingOptions, 
					sortOptions 	: sortOptions, 
					filterOptions 	: filterOptions
				}
			).$promise.then (
				//Retour de la méthode dans un cas success
				function (success) {
					return success;
				},
				//Retour de la methode dans un cas error
				function (error) {
					return error;
				}
			);
		
		//On retourne la promise
		return promise;
	};
	
	//Méthode de récupération des données d'un stagiaire
	var getDetail = function () {
		
		var promise = detail.get({id : this.stagiaire.id},
				
				//Retour de la méthode success
				function(success) {
					return success;
				},
				//Retour de la méthode error
				function(error) {
					return error;
				}
			);
		
		return promise;
	};
	
	//Enregsitrement du stagiaire sélectionné
	var keepStagiaire = function (stagiaireSelected)  {
		stagiaire = stagiaireSelected;
	};
	
	//Retour de la factory avec ses variables
	return {
		getData 		: getData,
		getDetail		: getDetail,
		pagingOptions	: pagingOptions,
		sortOptions 	: sortOptions,
		filterOptions	: filterOptions,
		stagiaire		: stagiaire,
		keepStagiaire : keepStagiaire,
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