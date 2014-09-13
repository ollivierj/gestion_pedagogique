'use strict';

services.factory('SEchangeFactory', function ($resource) {
		
	var stagiaire = {};
		
	var pagingOptions = {
			pageSizes : [5,10,15,20,25,30],
			pageSize : 10,
			currentPage : 1
	};
	
	var sortOptions = {
			fields: ["date"],
			directions: ["ASC"]
	};
	
	var filterOptions = {
			filterText: "",
			useExternalFilter: false
	};
	
	var createEchange = $resource('/ng_gst_pdg/web/stagiaires/echanges/creation', {}, {
		create : {method:'POST'}
	})
	
	var getEchanges = $resource('/ng_gst_pdg/web/stagiaires/echanges', {}, {
		load : {method:'POST'}
	});
	
	var schema = $resource('/ng_gst_pdg/web/stagiaires/echanges/jsonschema', {}, {
		get : {method:'GET'}
	});
	
	//Enregsitrement du stagiaire sélectionné
	var keepStagiaire = function (stagiaireSelected)  {
		stagiaire = stagiaireSelected;
	};
	
	//Retour de la factory avec ses variables
	return {
		pagingOptions	: pagingOptions,
		sortOptions 	: sortOptions,
		filterOptions	: filterOptions,
		stagiaire		: stagiaire,
		keepStagiaire : keepStagiaire,
		createEchange : createEchange,
		getEchanges : getEchanges,
		schema : schema
	};
	
});
