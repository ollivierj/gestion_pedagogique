'use strict';

services.factory('SEchangeFactory', function ($resource, StagiaireFactory) {
		
	var factory = {};
	
	factory.stagiaire = {};
		
	factory.pager = {
			pagingOptions : {
					pageSizes : [5,10,15,20,25,30],
					pageSize : 10,
					currentPage : 1
			},
			sortOptions : {
					fields: ["date"],
					directions: ["ASC"]
			},
			filterOptions : {
					filterText: "",
					useExternalFilter: false
			},
			id : StagiaireFactory.stagiaire.id
	}; 
	
	factory.createLine = $resource('/ng_gst_pdg/web/echanges/addOrUpdate', {}, {
		create : {method:'POST'}
	});
	
	factory.getEchanges = $resource('/ng_gst_pdg/web/echanges/page', {}, {
		load : {method:'POST'}
	});
	
	factory.deleteLine = $resource('/ng_gst_pdg/web/echanges/suppression/:id', {}, {
		delete : {method:'DELETE', params : {id:'@id'}}
	});
	
	
	factory.getEchangesInit = function() {
		return factory.getEchanges.load (
				factory.pager,
				//Retour de la méthode dans un cas success
				function (success) {
					return success;
				},
				//Retour de la methode dans un cas error
				function (error) {
					return error;
				}
			).$promise;
	}
	
	//Retour de la factory avec ses variables
	return factory;
});
