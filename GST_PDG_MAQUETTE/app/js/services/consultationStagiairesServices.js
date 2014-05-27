'use strict';

services.factory('stagiaireData', function() {
	var stagiaireServ = {};

	var stagiaireSelected;

	stagiaireServ.set = function(stagiaire) {
		stagiaireSelected = stagiaire;		
	};

	stagiaireServ.get = function() {
		return stagiaireSelected; 
	};

	return stagiaireServ;
});