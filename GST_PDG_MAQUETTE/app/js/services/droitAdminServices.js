'use strict';

services.factory('droitAdmin', function($http) {

	return {
		getData: function() {

			return $http.get('json/droitAdminLoad.json').then(function (result) {
	            return result.data;
	        });
		}
	};

});