'use strict';

services.factory('StagiairesFactory', function ($resource) {
    return $resource('/ng_gst_pdg/web/stagiaires/:page/:pageSize/:orderBy', {}, {
        query: { method: 'GET', isArray: true, params: {id: '@id', page: '@page', pageSize: '@pageSize', orderBy: '@orderBy'} },
        create: { method: 'POST' }
    });
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