'use strict';
angular.module('ng_gst_pdg', ['ngRoute','ngSanitize', 'ngGrid', 'ngAnimate','angularFileUpload', 
                              'schemaForm','ui.bootstrap','ui.tree','ng_gst_pdg.filters','ng_gst_pdg.controllers',
                              'ng_gst_pdg.services', 'ng_gst_pdg.directives', 'mgcrea.ngStrap.timepicker',
                              'mgcrea.ngStrap.datepicker'])
.config(['$routeProvider','$datepickerProvider', '$timepickerProvider', function($routeProvider, $datepickerProvider, $timepickerProvider) {
	    angular.extend($datepickerProvider.defaults, {
	        dateType: "string",
	        modelDateFormat: "yyyy-MM-ddTHH:mm:ss",
	    });
	    angular.extend($timepickerProvider.defaults, {
	        timeType: "string",
	        modelTimeFormat: "yyyy-MM-ddTHH:mm:ss",
	    });
	$routeProvider.
		when('/accueil', {
			templateUrl: 'partials/accueil.html',
		}).
		when('/stagiaires', {
			templateUrl: 'partials/stagiaire/stagiaire.html',
			controller: 'stagiaireCtrl'
		}).	
		when('/detailStagiaire', {
			templateUrl: 'partials/stagiaire/detailStagiaire.html',
			controller: 'detailStagiaireCtrl',
			resolve: {
				detail: function (StagiaireFactory) {
					return StagiaireFactory.getDetail();
				},
				absences: function(SAbsenceFactory) {
					return SAbsenceFactory.getAbsencesInit();
				}
			}
		}).
		when('/salles', {
			templateUrl: 'partials/salle/planningReservationSalle.html',
			controller: 'planningReservationSalleCtrl'
		}).
		when('/salle/editer/:id', {
			templateUrl: 'partials/salle/formulaireReservationSalle.html',
			controller: 'formulaireReservationSalleCtrl'
		}).
		when('/professionnelHomologues', {
			templateUrl: 'partials/templates/list.html',
			controller: 'professionelHomologuesCtrl'
		}).
		when('/titreProfessionnels', {
			templateUrl: 'partials/templates/list.html',
			controller: 'titreProfessionnelsCtrl'
		}).
		when('/sujetsEvaluations', {
			templateUrl: 'partials/templates/list.html',
			controller: 'sujetEvaluationsCtrl'
		}).
		when('/evaluations', {
			templateUrl: 'partials/templates/list.html',
			controller: 'evaluationsCtrl'
		}).
		when('/sessionValidations', {
			templateUrl: 'partials/templates/list.html',
			controller: 'sessionValidationsCtrl'
		}).
		when('/utilisateurs', {
			templateUrl: 'partials/templates/list.html',
			controller: 'utilisateursCtrl'
		}).
		when('/fichiers', {
			templateUrl: 'partials/fichiers.html',
			controller: 'fichiersCtrl'
		}).
		when('/profils', {
			templateUrl: 'partials/droit/gestionDroit.html',
			controller: 'gestionDroitCtrl'
		}).
		otherwise({
			redirectTo: '/accueil'
		});
}]);

var filters = angular.module('ng_gst_pdg.filters', []);
var services = angular.module('ng_gst_pdg.services', ['ngResource']);
var controllers = angular.module('ng_gst_pdg.controllers', []);
var directives = angular.module('ng_gst_pdg.directives', ['ui.calendar']);