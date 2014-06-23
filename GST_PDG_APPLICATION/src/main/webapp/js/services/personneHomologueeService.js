'use strict';

services.factory('pHomologuee', function($resource) {

	return $resource('json/personnesHomologueesLoad.json', {}, {
		query: {method: 'GET', isArray: true}
	});

});