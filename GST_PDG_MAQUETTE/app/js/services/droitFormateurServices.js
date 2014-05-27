'use strict';

services.factory('droitFormateur', function($http) {

	return {
		getData: function() {

			return $http.get('json/droitFormateurLoad.json').then(function (result) {
	            return result.data;
	        });
		}
	};

});