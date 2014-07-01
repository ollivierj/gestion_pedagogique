'use strict';

services.factory('StagiairesFactory', function ($resource) {
	return $resource('/ng_gst_pdg/web/stagiaires/page/:page/:pageSize/:sortColumnBy/:sortDirectionBy', {}, {
        query: { method: 'GET', params: {page: '@page', pageSize: '@pageSize', sortColumnBy: '@sortColumnBy', sortDirectionBy : '@sortDirectionBy'} },
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