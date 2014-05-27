'use strict';

services.factory('profil', function($http) {

	return {
		getData: function() {

			return $http.get('json/profilLoad.json').then(function (result) {
	            return result.data;
	        });
		}
	};

});