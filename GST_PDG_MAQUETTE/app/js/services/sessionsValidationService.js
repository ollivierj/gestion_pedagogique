'use strict';

services.factory('sessionsValidationData', function($resource) {
	return $resource('json/sessionsLoad.json', {}, {
 		query: { method: 'GET', isArray: true}
	 });
});