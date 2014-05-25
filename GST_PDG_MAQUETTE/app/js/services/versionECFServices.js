'use strict';

services.factory('vECF', function($http) {

	return {
		getData: function() {

			return $http.get('json/versionECFLoad.json').then(function (result) {
	            return result.data;
	        });
		}
	};

});