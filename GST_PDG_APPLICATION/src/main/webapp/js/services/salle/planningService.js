'use strict';

services.factory('PlanningFactory', function($resource) {

	var planning = {};
	
	planning.elements = $resource('/ng_gst_pdg/web/planning', {}, {});
	
	return planning;
	
});
