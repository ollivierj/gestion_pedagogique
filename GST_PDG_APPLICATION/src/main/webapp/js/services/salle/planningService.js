'use strict';

services.factory('PlanningFactory', function($resource, $filter) {

	var planning = {};
	
	planning.elements = $resource('/ng_gst_pdg/web/planning', {}, {});
	
	planning.initElements = function (date) {
		//Transformation de la date courante en date de début et de fin
		
		//Initialisation du jour du mois à 1
		date.setDate(1);
    	var debut = $filter('date')(date, 'yyyy-dd-MM');
    	//On rajoute 1 mois à la date pour couvrir le mois entier
    	date.setMonth(date.getMonth() + 1);
    	var fin = $filter('date')(date, 'yyyy-dd-MM');
    	
		return planning.elements.query({debut: debut, fin: fin}).$promise;
		
	};
	
	return planning;
	
});
