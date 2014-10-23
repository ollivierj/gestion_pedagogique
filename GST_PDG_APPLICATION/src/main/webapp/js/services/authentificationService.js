'use strict';

services.factory('AuthentificationFactory', function($resource) {

	var authentification = {};
	authentification.user = null;
	
	authentification.connect = $resource('/ng_gst_pdg/web/utilisateurs/login', {}, {
		doAction : {method:'POST'}
	});

	authentification.isAuthenticated = function(){
		return authentification.user != null;
	};

	return authentification;
	
});