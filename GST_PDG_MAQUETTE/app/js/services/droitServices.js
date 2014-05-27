'use strict';

services.factory('droit', function($http) {

	return {
		getData: function() {

			return $http.get('json/droitLoad.json').then(function (result) {
	            return result.data;
	        });
		}
	};

});