'use strict';

services.factory('stagiaireSelectedData', function() {
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