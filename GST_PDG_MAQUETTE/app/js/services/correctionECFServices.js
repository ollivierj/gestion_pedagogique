'use strict';

services.factory('cECF', function($http) {

	return {
		getData: function() {

			return $http.get('json/correctionECFLoad.json').then(function (result) {
	            return result.data;
	        });
		}
	};

});