'use strict';

services.factory('pHomologuee', function($http) {

	return {
		getData: function() {

			return $http.get('json/personnesHomologueesLoad.json').then(function (result) {
	            return result.data;
	        });
		}
	};

});