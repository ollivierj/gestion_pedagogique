'use strict';

services.factory('wrapMenu', function($http) {

	return {
		getData: function() {

			return $http.get('json/titleApp.json').then(function (result) {
	            return result.data;
	        });
		}
	};

});