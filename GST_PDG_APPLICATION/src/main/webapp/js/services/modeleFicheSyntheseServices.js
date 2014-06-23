'use strict';

services.factory('ficheSynth', function($http) {

	return {
		getData: function() {

			return $http.get('json/modeleFicheSyntheseLoad.json').then(function (result) {
	            return result.data;
	        });
		}
	};

});