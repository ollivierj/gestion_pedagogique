'use strict';

services.factory('SalleFactory', function($resource, $filter) {

	var salle = {};
	
	salle.referenceSalle = $resource('/ng_gst_pdg/web/reservationSalles/salles', {}, {});
	
	return salle;
	
});
