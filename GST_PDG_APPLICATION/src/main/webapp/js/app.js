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
//			controller: 'consultationStagiairesCtrl'
		}).
		when('/stagiaire', {
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
		when('/gestionFichesSynthese', {
			templateUrl: 'partials/gestionFichesSynthese/gestionFichesSynthese.html',
			controller: 'gestionFichesSyntheseCtrl'
		}).
		when('/editCorrection', {
			templateUrl: 'partials/gestionEvaluations/editCorrection.html',
			controller: 'editCorrectionCtrl'
		}).
		when('/editVersion', {
			templateUrl: 'partials/gestionEvaluations/editVersion.html',
			controller: 'editVersionCtrl'
		}).
		when('/salle', {
			templateUrl: 'partials/salle/planningReservationSalle.html',
			controller: 'planningReservationSalleCtrl'
		}).
		when('/salle/editer/:id', {
			templateUrl: 'partials/salle/formulaireReservationSalle.html',
			controller: 'formulaireReservationSalleCtrl'
		}).
		when('/gestionSessionsValidation', {
			templateUrl: 'partials/gestionSessionsValidation/gestionSessionsValidation.html',
			controller: 'gestionSessionsValidationCtrl',
			resolve : {
				initializeData : function(professionnelHomologuesFactory){
					professionnelHomologuesFactory.initializeData();
				}
			}
		}).
		when('/professionnelHomologues', {
			templateUrl: 'partials/templates/list.html',
			controller: 'professionelHomologuesCtrl'
		}).
		when('/titreProfessionnels', {
			templateUrl: 'partials/templates/list.html',
			controller: 'titreProfessionnelsCtrl'
		}).
		when('/sujetsEvaluation', {
			templateUrl: 'partials/templates/list.html',
			controller: 'sujetEvaluationsCtrl'
		}).
		when('/evaluation', {
			templateUrl: 'partials/templates/list.html',
			controller: 'evaluationsCtrl'
		}).
		when('/fichiers', {
			templateUrl: 'partials/fichiers.html',
			controller: 'fichiersCtrl'
		}).
		when('/gestionProfils', {
			templateUrl: 'partials/droit/gestionDroit.html',
			controller: 'gestionDroitCtrl'
		}).
		when('/gestionUtilisateurs', {
			templateUrl: 'partials/templates/list.html',
			controller: 'gestionUtilisateursCtrl'
		}).
		otherwise({
			redirectTo: '/accueil'
		});
}]);

var filters = angular.module('ng_gst_pdg.filters', []);
var services = angular.module('ng_gst_pdg.services', ['ngResource']);
var controllers = angular.module('ng_gst_pdg.controllers', []);
var directives = angular.module('ng_gst_pdg.directives', ['ui.calendar']);