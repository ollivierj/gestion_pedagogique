'use strict';

services.factory('StagiairesFactory', function ($resource) {
	
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
	
	//Création de la variable ressource qui appel le service REST
	var data = $resource('/ng_gst_pdg/web/stagiaires/page', {}, {
		load : {method:'POST'}
	});
	
	//Création de la méthode de récupération des données
	var getData = function (pagingOptionsIn, sortOptionsIn, filterOptionsIn) {
		console.log(pagingOptionsIn);
		if (pagingOptionsIn != null && sortOptionsIn != null && filterOptionsIn != null) {
			//Enregistrement des données
			pagingOptions = pagingOptionsIn;
			sortOptions = sortOptionsIn;
			filterOptions = filterOptionsIn;
		}
		
		//Création d'une promise qui sera utilisée dans le controller
		//Utilisation de la variable qui contient l'appel au service
		var promise = data.load(
				{
					pagingOptions : pagingOptions, 
					sortOptions : sortOptions, 
					filterOptions : filterOptions
				}
			).$promise.then (
					//Retour de la méthode dans un cas success
					function (success) {
						return success;
					},
					//Retour de la methode dans un cas error
					function (error) {
						return error;
			});
		
		//On retourne la promise
		return promise;
	};
	
	
	return {
		getData : getData,
		pagingOptions: pagingOptions,
		sortOptions : sortOptions,
		filterOptions: filterOptions
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
	}
	


	return stagiaireFactory;
});