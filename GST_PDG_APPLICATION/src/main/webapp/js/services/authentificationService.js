'use strict';

services.factory('AuthentificationFactory', function($resource) {

	var authentification = {};
	
	authentification.user = {};
	
	authentification.connect = $resource('/ng_gst_pdg/web/utilisateurs/login', {}, {
		doAction : {method:'POST'}
	});

	return authentification;
	
});