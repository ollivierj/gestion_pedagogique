'use strict';

services.factory('jurysSallesData', function($resource) {
	return $resource('json/jurysSallesLoad.json', {}, {
 		query: { method: 'GET', isArray: true}
	 });
});