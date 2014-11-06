'use strict';

services.factory('AuthentificationFactory', function($http, $cookieStore,
		$rootScope) {
	var service = {};
	service.Login = function(username, password) {
		return $http.post('/ng_gst_pdg/web/utilisateurs/login', {
			login : username,
			motPasse : password
		});
	};
	
	service.getUtilisateurFromToken = function() {
		return $http.post('/ng_gst_pdg/web/utilisateurs/loginwithtoken',
				{ token : $rootScope.authtoken});
	};

	service.setCredentials = function(data) {
		var utilisateurConnecteData = {
			id : data.id,
			prenom : data.prenom,
			nom : data.nom,
			profil : {
				droits : data.profil.droits
			},
		};
		$cookieStore.put("token",data.token);
		$rootScope.authtoken = data.token;
		$rootScope.utilisateurConnecte = utilisateurConnecteData;
	};

	service.clearCredentials = function() {
		$cookieStore.remove("token");
		delete $rootScope.authtoken;
		delete $rootScope.utilisateurConnecte;
	};
	return service;
}

);