'use strict';

services.factory('homologationData', function($resource) {
	var homologation = {};

	homologation.getAll = function() {
		return $resource('json/homologationsLoad.json', {}, {
	 		query: { method: 'GET', isArray: true}
		 });
	}

	return homologation;
});