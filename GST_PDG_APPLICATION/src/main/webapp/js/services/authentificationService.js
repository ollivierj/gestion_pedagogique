'use strict';

services.factory('AuthentificationFactory', function($http, $cookieStore,
		$rootScope, $filter) {
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
			email : data.email,
			fonction : (data.fonction)?(data.fonction.libelle)?data.fonction.libelle:"":"",
			dateExpiration : data.dateExpiration,
			profil : {
				libelle : (data.profil)?(data.profil.libelle)?data.profil.libelle:"":"",
				droits : data.profil.droits
			},
		};
		$cookieStore.put("token",data.token);
		$rootScope.authtoken = data.token;
		$rootScope.utilisateurConnecte = utilisateurConnecteData;
	};

	service.clearCredentials = function() {
		$rootScope.titleSelected="";
		$cookieStore.remove("token");
		delete $rootScope.authtoken;
		delete $rootScope.utilisateurConnecte;
	};
	return service;
}

);