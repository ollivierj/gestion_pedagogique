'use strict';

services.factory('lECF', function($http) {

	return {
		getData: function() {

			return $http.get('json/listeECFLoad.json').then(function (result) {
	            return result.data;
	        });
		}
	};

});